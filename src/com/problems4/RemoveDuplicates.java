package com.problems4;

import java.util.Arrays;

public class RemoveDuplicates {

	/**
	 * @param args
	 */
	/***
	 * 思路：
	 * 
	 * 当前key的值与当前数组值不同的时候，新数组的当前置为key，新数长度加1，然后key更新为这个当前的这个数组值，
	 * 
	 * 该题的关键思路是，新的数组覆盖原数组的位置，一旦得到一个不重复的数字，就加入到新的数组中，加入位置就是新数组目前长度的位置
	 * 这种覆盖原数组位置的思路一定要会！！！
	 * @param A
	 * @return
	 */
	public int removeDuplicates(int[] A) {
		 int len = A.length;
		 if(len<=1)
			return len;
		 
		 int key = A[0];
		 int newLen = 0;
		 for(int i=0;i<len;i++){
			 if(key!=A[i]){
				 A[newLen++] = key;
				 key = A[i];
			 }
		 }
		 A[newLen++] = key;
//		 Arrays.sort(A);
		 int a =5;
		 String s = String.valueOf(a);
		return len;    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char a[] = {'a','b','c'};
		System.out.println(Arrays.toString(a));
		System.out.println(String.valueOf(a));
	}
	/***
	 * 
	 * 扩展题： 若每一个重复元素 最多存在2此，求此时返回的数组长度
	 * 
	 * 上面的以允许重复一次的时候，是当只要前后两此元素不同就覆盖，这里再加一次相同的时候的判断
	 */
	 	public int removeDuplicates2(int[] A) {
			int len = A.length;
			if(len<=1){
				return len;
			}
			int key = A[0];
			int newLen = 0;
			int duplicate = 0;
			for(int i=0;i<len;i++){
				if(A[i]!=key){
					A[newLen++] = key;
					key = A[i];
					duplicate = 0;
				}
//				比上面只要增加一个变量，当重复出现一次的时候更新数组
				if(A[i]==key&&duplicate==1){
					A[newLen++] = key;
				}
				duplicate++;
			}
			A[newLen++] = key;
	 		return newLen;
	        
	    }

}
