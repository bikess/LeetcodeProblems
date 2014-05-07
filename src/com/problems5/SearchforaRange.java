package com.problems5;
import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;

/***
 * 题目的意思：在一个有序的数组中，找到给定目标数的起始和终止位置
 * 例如[5, 7, 7, 8, 8, 10] 目标数为 8,
	返回 [3, 4].
	
	题目要求：时间复杂度必须为o(lgn)的时间
 * @author bike
 *
 */
public class SearchforaRange {

	/**
	 * @param args
	 */
	/***
	 * 思路1：采取二分查找的方法，当目标值等于中间的数字时，循环向左遍历找到左边界，循环向右遍历找到右边界
	 * @param A
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] A, int target) {
		int len = A.length;
		int result [] = {-1,-1};
		if(len<=0){
			return result;
		}
		searchTarget(A,target,result,0,len-1);
		
		return result;
        
    }
	private void searchTarget(int[] a, int target, int[] result, int start, int end) {
		// TODO Auto-generated method stub
		if(start>end){
			return ;
		}
		int mid = (start+end)/2;
		if(a[mid]==target){
			int left,right;
			for( left = mid-1;left>=start;left--){
				if(a[left]!=target){
					break;
				}
			}
			result[0] = left+1;
			for( right = mid+1;right<=end;right++){
				if(a[right]!=target){
					break;
				}
			}
			result[1] = right-1;
		}else
		if(a[mid]>target){
			searchTarget(a, target, result, start, mid-1);
		}else{
			searchTarget(a, target, result, mid+1, end);
		}
		
	}
	
	/***
	 * 
	 * 思路2：注意递归返回的值，把递归返回的值设为int[]，就很容易，直接采取二分法的递归解法了
	 * @param args
	 */
	public int[] searchRange2(int[] A, int target) {
		int len = A.length;
		int result [] = {-1,-1};
		if(len<=0){
			return result;
		}
		result = searchTarget2(A,target,0,len-1);
		
		return result;
        
    }
//	这里由于在寻找左边界，寻找右边界时出现浪费的遍历.可以设置几个boolean值,来让 当递归向左找左边界的时候不要找右边界,递归向右找右边界不要找左边界
	private int[] searchTarget2(int[] a, int target, int start, int end) {
		// TODO Auto-generated method stub
		int[] r = new int[2];
		if(start>end){
			r[0]=r[1] = -1;
			return r;
		}
		if(start==end&&a[start]==target){
			r[0]=r[1] = start;
			return r;
		}
		int mid = (start+end)/2;
		if(a[mid]==target){
			int []left = searchTarget2(a, target,  start, mid-1);
			int []right = searchTarget2(a, target, mid+1, end);
			r[0] = left[0]==-1? mid:left[0];
			r[1] = right[1]==-1? mid:right[1];
			return r;
		}else if(a[mid]>target){
			return searchTarget2(a, target, start, mid-1);
		}else{
			return searchTarget2(a, target,  mid+1, end);
		}
	}
	
	/***
	 * 方法3 ,分别进行递归找左边界,递归找右边界
	 * @param args
	 */
	public int[] searchRange3(int[] A, int target) {
		int len = A.length;
		int result [] = {-1,-1};
		if(len<=0){
			return result;
		}
//		当设置false 表示返回的是递归二分寻找左边界，设置true，表示返回的是递归二分寻找右边界
		int left = lsearch(A,0,len-1,target);
		int right = rsearch(A,0,len-1,target);
		result[0] = left;
		result[1] = right;
		return result;
	}
	private int rsearch(int[] A, int i, int j, int target) {
		// TODO Auto-generated method stub
		int start = i,end = j;
		while(start<=end){
			int mid = (start+end)/2;
			if(A[mid]>target){
				end = mid-1;
			}else{
				start = mid+1;
			}
		}
//		最终end的值肯定是从数组的右边接近target的第一个相等的值
		if(end<i)
		    return -1;
		if(A[end]!=target){
			return -1;
		}
		return end;
	}
	private int lsearch(int[] A, int i, int j, int target) {
		// TODO Auto-generated method stub
		int start = i,end = j;
		while(start<=end){
			int mid= (start+end)/2;
			if(A[mid]<target){
				start = mid+1;
			}else{
				end = mid-1;
			}
		}
//		最终start的值肯定是从数组的左边接近target的第一个相等的值
		if(start>j)
		    return -1;
		if(A[start]!=target)
			return -1;
//		最终start的值就是左边界的索引值
		return start;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
