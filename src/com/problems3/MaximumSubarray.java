package com.problems3;

public class MaximumSubarray {

	/***
	 * 思路1：分而治之，利用分治法求解，因为最大的连续子数组要
	 * 首先先去中点，以中点分界，则最大连续子数组可能有三种情况
	 * （1）一种此连续子数组包括这个中点，则分别以这个中点计算前后两部分的
	 * 最大子数组的和
	 * （2）在此中点的前半部分，递归求解前半部分的数组
	 * （3）在此中点的后半部分，递归求解后半部分的数组
	 */
	/**
	 * @param args
	 */
	public  int maxSubArray(int[] A){
		if(A.length<=0){
			return 0;
		}
		return partionMaxsubArray(A,0,A.length-1);
	}
	private  int partionMaxsubArray(int[] A, int left, int right) {
		if(left>=right){
			return A[left];
		}
		int mid = (left+right)/2;
//		求解以跨mid这个点的最大子数组的值
		int maxCross = maxSubArray( A,left,mid,right);
		int maxLeft = partionMaxsubArray(A, left, mid-1);
		int maxright = partionMaxsubArray(A, mid+1, right);
		int max;
		if(maxCross>maxLeft){
			max = maxCross;
		}else{
			max = maxLeft;
		}
		if(max<maxright){
			max = maxright;
		}
		return max;
	}
	private int maxSubArray(int[] A, int left, int mid, int right) {
		// TODO Auto-generated method stub
		int Max = A[mid];
		int temp = A[mid];
		for(int i=mid+1;i<=right;i++){
			temp = temp + A[i];
			if(temp>Max){
				Max = temp;
			}
		}
		System.out.println(Max);
		temp = Max;
		for(int i=mid-1;i>=left;i--){
			System.out.println(temp);
			temp = temp + A[i];
			if(temp>Max){
				Max = temp;
				System.out.println(Max);
			}
		}
		System.out.println(Max);
		return Max;
	}
	
	
	
	/**
	 * 
	 * 思路2 采取动态规划的方法
	 * 
	 * 思路：
	 * （1）设置两个变量，一个变量记录当前连续子数组达到的最大值，
	 * 一个变量记录以当前的元素为最后一个元素，所能达到的最大连续子串的值
	 * （2）其原理就是一个动态规划方法
	 * max_subarray(A[n]) = max(Max_subarray(A[n-1],A[n],max_subarray(A[n-1)+A[n])
	 * (3)也就是已知一个数组A[n]及其最大子数组max_subarray(A[n])，
	 * 我们添加一个元素A[n+1]进去。则有几种可能的情况：
	 * 一种情况：添加元素之前，当A【n】元素的连续子数组的和<0,此时可以判断n+1之前的所有元素一定不在连续子数组中（若最终的最大值不小于0），此时将最大连续子数组第一个元素置为A【n+1】
	 * 一种情况：若当n+1之前的和不小于0，则继续与A【n+1】求和，然后与最大值比较
	 * @param args
	 */
	public  int maxSubArray2(int[] A){
		int len = A.length;
		if(len<=0)
			return 0;
//		记录当前的连续子数组的最大值
		int max = A[0];
//		记录以当前的i为止的连续子数组的最大值
		int untilMax = A[0];
		for(int i=1;i<len;i++){
//			若当前到i之前的最大连续子数组为负数，则重置连续子数组开始元素为当前元素
			if(untilMax<0){
				untilMax = A[i];
			}else{
				untilMax = untilMax + A[i];
			}
//			判断当前的最大值与到当前元素的最大值
			if(max<untilMax){
				max = untilMax;
			}
		}
		return max;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[]={-1,0,-2};
		MaximumSubarray m = new MaximumSubarray();
//		m.maxSubArray(data);
		System.out.println(m.maxSubArray(data));
		
	}

}
