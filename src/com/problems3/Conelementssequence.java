package com.problems3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Conelementssequence {

	/***
	 * 题目描述，给定一个没有排序的数组，返回该数组中最长的连续序列的长度
	 * 例如：200 4 100 6 1 2 3
	 * 最长连续序列为 1 2 3 4 。长度为4
	 * 要求时间复杂度为0(n)
	 */
	/**
	 * @param args
	 */
	/**
	 * 解题思路，把所有的数组映射到hash表中
	 * @param num 
	 * @param args
	 * @return 
	 */
	public static int longestConsecutive(int[] num) {
		
		if(num.length==0)
			return 0;
		int len = num.length;
		Set<Integer> data = new HashSet<Integer>();
//		首先把数组中的所有元素映射到Hashset中去
		for(int i=0;i<len;i++)
			data.add(num[i]);
		int max = 0;
		for(int i =0;i<len;i++){
			int sum =1;
//			判断Set中是否存在某个元素
			if(data.contains(num[i])){
				data.remove(num[i]);
				int left = num[i]-1;
				while(!data.isEmpty()&&data.contains(left)){
					data.remove(left);
					left--;
					sum++;
				}
				int right = num[i]+1;
				while(!data.isEmpty()&&data.contains(right)){
					data.remove(right);
					right++;
					sum++;
				}
			}
			if(max<sum)
				max = sum;
		}	
		return max; 
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[]={100,200,8,4,1,2,3};
		int max = longestConsecutive(num);
		System.out.println(max);
		
	}

}
