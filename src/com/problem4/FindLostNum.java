package com.problem4;
/***
 * 给定一个无序的整数数组，怎么找到第一个大于0，并且不在此数组的整数。
 * 比如[1,2,0] 返回 3, [3,4,-1,1] 返回 2。最好能O(1)空间和O(n)时间。
 * @author bike
 *
 */

/**
 * 思路：0(n)的时间复杂度，0（1）的空间复杂度
 * @author bike
 *
 */
public class FindLostNum {

	/**
	 * @param args
	 */
	public int findLost(int []A){
		int len = A.length;
		
		for(int i=0;i<len;i++){
//			经过这一层的循环，在A[i]位置上的数字要么是一个<0或者>len的数
//			要么经过多次的交换A[i]位置正好放置的是i+1的值，
//			A[i]!=A[A[i]-1]防止出现A[i]与A[A[i]-1]的值相同而陷入死循环
			while(A[i]>0&&A[i]<=len&&A[i]!=i+1&&A[i]!=A[A[i]-1])
			{
				int temp = A[i];
				A[i] = A[A[i]-1];
				A[temp-1] = temp;
			}
		}
//		现在i的位置应该放着i+1的数字，第一个不是的i值就是确实的那个i+1的数
		for(int i=0;i<len;i++){
			if(A[i]!=i+1)
				return i+1;
		}
		return len;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindLostNum m = new FindLostNum();
		int data[] = {1,4,5,7,9};
		System.out.println(m.findLost(data));
	}

}
