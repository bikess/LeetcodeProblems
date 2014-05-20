package com.problems6;
/**
 * 题意：找出一个未排序的数组中，第一个丢失的正整数
 * 要求算法时间复杂度为0（n），而且固定的空间复杂度
 * @author bike
 *
 */
public class FirstMisssingPositive {

	/**
	 * 思路遍历为排序的数组，若数组的值等于下标的值，则遍历下一个值
	 * 若数组的值不等于数组的值，则交换该数组值位置上的数与此位置的数
	 * 
	 * 然后o（n）遍历，寻找第一个索引位置与数不对应的数
	 * @param A
	 * @return
	 */
	public int firstMissingPositive(int[] A) {
		int len = A.length;
		if(len<=0){
			return 1;
		}
		int i = 0;
		while(i<len){
			if(A[i]==i+1){
				i++;
				continue;	
			}else{
//				A[A[i]]!=A[i]是为了防止出现重复判断
//				当出现这种情况的时候，i的值保持不变继续判断，直到不满足条件位置
				if(A[i]>0&&A[i]<=len&&A[A[i]-1]!=A[i]){
					int temp = A[i];
					A[i] = A[A[i]-1];
					A[temp-1] = temp;
					continue;
				}else{
					i++;
					continue;
				}
			}
		}
		for(int j=0;j<len;j++){
			if(A[j]!=j+1){
				return j+1;
			}
		}
		return len+1;
	        
	    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
