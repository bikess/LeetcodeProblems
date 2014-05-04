package com.problems5;

/**
 * 注意本题中数组原来是按升序排列的。
 * 题目的意思是：在一个已经升序排序的队列中，在某个点被旋转了,
 * 例如：(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 求目前元素在现在旋转以后的数组中的下标
 * 本题的关键是：
 * 采取二分搜索是，确定那一部分是有序的！！！！！
 * （1）当A[start]<A[mid]  则start到mid一定是升序有序的
 * （2）当A[start]>A[mid]  则start到mid一定是有序的
 * @author bike
 *
 */
public class SearchinRotatedSortedArray {

	/**
	 * @param args
	 * 
	 * 方法1 遍历一遍数组，统计比目标数大的数的数目，时间复杂度0（n)
	 */
	
	public int search(int[] A, int target) {
		int len = A.length;
		if(len<1)
			return -1;
		boolean flag = false;
		int index = 0;
		for(int i=0;i<len;i++){
			if(A[i]==target){
				flag = true;
				index = i;
			}
		}
		if(flag==false)
			return -1;
		
		return index;
	        
	}
	/***
	 * 方法2 采取二分查找时间复杂度为0（lgn）
	 * 二分查找的思路
	 * （1）A[mid]
	 * @param args
	 */
	public int search2(int[] A, int target) {
//		采取二分搜索
		int len = A.length;
		if(len<1)
			return -1;
		return BinarySearch(A,0,len-1,target);
	}
	private int BinarySearch(int[] A, int start, int end, int target) {
		// TODO Auto-generated method stub
		if(start>end){
			return -1;
		}
		int id  = -1;
//		这表明数组是升序排列，是有序的，进行二分搜索
		if(A[start]<=A[end]){
			int mid = (start+end)/2;
			if(A[mid]==target){
				id = mid;
			}
			else{
				if(A[mid]>target)
				{
					id = id==-1? BinarySearch(A, start, mid-1, target):id;
				}else
					id = id==-1? BinarySearch(A, mid+1, end, target):id;
			}
			
		}else{
			int mid = (start+end)/2;
			if(A[mid] == target){
				id = mid;
//			注意当判断出数组被旋转后，采取二分查找时，不能根据target与A【mid】值的大小判断在哪边进行二分查找，要在两边均进行二分查找
			}else{
					id = id==-1? BinarySearch(A, start, mid-1, target):id;
					id = id==-1? BinarySearch(A, mid+1, end, target):id;
			}
		}
		return id;
	}
	/***
	 * 方法3 不采取递归，直接通过值判断进行循环
	 * @param args
	 */
	public int search3(int A[], int target) {
		int len = A.length;
		if(len<1)
			return -1;
		int start = 0,end = len-1;
		while(start<=end){
			int mid = (start+end)/2;
			if(A[mid]==target){
				return mid;
			}
			if(A[start]<=A[mid]){
//				因为A[start]<A[mid],则保证start到mid之间是一定是有序的
//				这里利用看target是否在A[start]与A[mid]之间，直接进行二分查找就可以了
				if((A[start]<=target)&&target<A[mid]){
					end = mid-1;
				}else{
					start = mid+1;
				}
//			 这里再看A[start]>A[mid]的情况，此时start到mid之间不一定是有序的，但是mid到end之间一定是有序的，
//				而且一定是按照升序排列的，因为是对有效数字的旋转，因此此时要判断target是否在A【mid】与A【end】之间，从过这个来判断在哪一部分进行二分查找
			}else{
				if(A[mid]<target&&target<=A[end]){
					start = mid+1;
				}else{
					end = mid -1;
				}
			}
		}
		return -1;
		
	}
	/***
	 * 下面利用这个思路，对上面的二分递归搜索查找进行修改
	 * @param args
	 */
	private int BinarySearch2(int[] A, int start, int end, int target) {
		// TODO Auto-generated method stub
		if(start>end){
			return -1;
		}
		int id  = -1;
		int mid = (start+end)/2;
		if(A[mid]==target){
			return mid;
		}
//		start到mid之间必定升序有序
		if(A[start]<=A[mid]){
			if(A[start]<=target&&target<A[mid]){
				id = BinarySearch2(A, start, mid-1, target);
			}else{
				id = id==-1? BinarySearch2(A, mid+1, end, target):id;
			}
		}
//		mid 到end之间必定升序有序
		else{
			if(A[mid]<target&&target<=A[end]){
				id = id==-1? BinarySearch2(A, mid+1, end, target):id;
			}else{
				id = id==-1? BinarySearch2(A, start, mid-1, target):id;
			}
		}
		return id;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/***
	 * 题目扩展：上面的搜索中，数组中不会出现重复的数字，因此时间复杂度最短为0（lgn），那么若允许数组中出现重复的数字，那时间复杂度会有影响吗？
	 * 思路：
	 * 当 左 中 右三个位置的值相等的时候++left，--right
	 */
	
	  public boolean Duplicatesearch(int[] A, int target) {
		
		  int len = A.length;
		  if(len<1){
			  return false;
		  }
		  int left = 0,right = len-1;
		  while(left<=right){
			  int mid = (left+right)/2;
			  if(A[mid]==target){
				  return true;
			  }
			  if(A[left]==A[mid]&&A[mid]==A[right]){
				  ++left;
				  --right;
			  }
			  else if(A[left]<=A[mid]){
				  if(A[left]<=target&&target<A[mid]){
					  right = mid-1;
				  }else{
					  left = mid+1;
				  }
			  }
			  else{
				  if(A[right]>=target&&target>A[mid]){
					  left = mid+1;
				  }else{
					  right = mid -1;
				  }
			  }
			  
		  }
		  return false;
	        
	    }
}
