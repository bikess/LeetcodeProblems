package com.problems1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


public class TwoSum {

	/*
	 * 题目描述：
	 * Given an array of integers, find two numbers such that they add up to a specific 
	 * target number.he function twoSum should return indices of the two numbers such 
	 * that they add up to the target, where index1 must be less than index2. 
	 * Please note that your returned answers (both index1 and index2) are not 
	 * zero-based.You may assume that each input would have exactly one solution.
	 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
	 */
	/**
	 * @param args
	 */
	/*
	 * 解题思路1：可以直接遍历，求任意两个元素的和，看看呢是否等于target的值，时间复杂度为
	 * 0(n*n)
	 */
	/*
	 * 解题思路2：可以先排序0（n*logn）的时间，然后再在利用依次查找是否 target-arr[i]是否在数组中
	 * 时间复杂度为0（n*logn)
	 */
	public static int[] TwoSumAlgorithm2(int a[], int target){
//		建立一个hash表，键key存储数组值，键value存储的是数组索引
		Map<Integer,Integer> indexA = new HashMap<>();
//		定义一个存储所得目标元素的两个元素位置
		int[] ret = new int[2];
//		数组边向map中放数，边判断和
		for(int i=0;i<a.length;i++){
			if(indexA.get(target-a[i])!=null){
				ret[1]=i+1;
				ret[0]=indexA.get(target-a[i]);
			}else{
				indexA.put(a[i], i+1);
			}
		}
		return ret;
	}
	//快速排序的算法
	public void quickSort(int[] a,int begin,int end){
		if(begin<end){
			int fence = partitionArray(a, begin, end);
			quickSort(a, begin, end-1);
			quickSort(a, fence+1, end);
		}
	}
//	这是快速排序进行分割的函数
	public int partitionArray(int[]a,int begin,int end){
//		从begin与end中取一个随机数作为比较的数
		int pla = (int)Math.random()*(end-begin)+begin;
		
//		交换这个数为end位置的数
		int temp1 = a[pla];
		a[pla] = a[end];
		a[end] = temp1;
		
//		开始找到分割位置
		int fence = begin-1;
		for(int i =begin;i<end;i++){
			if(a[i]<=a[end]){
				fence++;
//				交换分割位置
				int temp2  = a[fence];
				a[fence] = a[i];
				a[i] = temp2;
			}
		}
		fence++;
		int temp3 = a[fence];
 		a[fence] = a[end];
 		a[end] = temp3;
		return fence;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {2, 7, 11, 15};
		int target = 9;
		int ret[]= TwoSumAlgorithm2(a,target);
		System.out.println("index1="+ret[0]+", index2="+ret[1]);
	}
	/*
	 * 解题思路3：可以先排序0（n*logn）的时间，然后i=0，j=a.length-1,判断a[i]+a[j],若比taget小，则i++，比taeget大在j--；这些寻找target时间负责
	 * 度为0（n），最终时间复杂度为0（n*logn）
	 */
	public void TwoSumAlgorithm3(int a[], int target){
//		首先把数组进行排序，最快采用快速排序，时间复杂度为0（n*logn）
//		Arrays.sort(a);
//		quickSort(a, 0, a.length-1);
//		首先应该记录数组a中每个值的index
		Map<Integer,Integer> indexA = new HashMap<>();
		for(int i =0;i<a.length;i++){
			indexA.put(i+1, a[i]);
		}
//	现在需要对Map的value进行排序，方法一，自己偏写快速排序的方法，方法2 调用函数
		List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(indexA.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}
		});
	}

}
