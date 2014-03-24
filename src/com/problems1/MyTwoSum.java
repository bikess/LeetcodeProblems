package com.problems1;

import java.util.HashMap;  
import java.util.Hashtable;
public class MyTwoSum{
public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {2,7,6,11, 15};
		int target = 8;
		int ret[]= twoSum(a,target);
		System.out.println("index1="+ret[0]+", index2="+ret[1]);
	}
public static int[] twoSum(int numbers[], int target){
//		建立一个hash表，键key存储数组值，键value存储的是数组索引
		int len = numbers.length;
		assert(len>=2);
		HashMap<Integer,Integer> indexA = new HashMap<Integer,Integer>();
//		定义一个存储所得目标元素的两个元素位置
		int[] ret = new int[2];
//		数组边向map中放数，边判断和
		for(int i=0;i<len;i++){
			if(indexA.containsKey(target-numbers[i])){
				ret[1]=i+1;
				ret[0]=indexA.get(target-numbers[i]);
				break;
			}else{
				indexA.put(numbers[i], i+1);
			}
		}
		return ret;
	}
}
