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

}
