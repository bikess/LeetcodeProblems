package com.problems3;
/***
 * 问题描述：Given a sorted array and a target value, return the index 
 * if the target is found. If not, return the index where it would be if it were inserted in order.
 * @author bike
 *
 */
public class SearchInsertPosition {

	/***
	 * 思路1：时间复杂度0（n），遍历一遍数组，记录比次数小的所有数的个数
	 */
    public int searchInsert(int[] A, int target) {
        int len = A.length;
        if(len<=0){
        	return 0;
        }
        boolean ishave = false;
        int pla = 0;
        for(int i=0;i<len;i++){
        	if(target>A[i]){
        		pla++;
        	}
        	if(target==A[i]){
        		ishave = true;
        		pla++;
        	}
        }
        if(ishave==true)
        	return pla-1;
        else
        	return pla;
    }
    /***
     * 思路2：采取二分查找,因为数组已经有序了，所以可以用二分查找
     * 
     */
    public int searchInsert2(int[] A, int target) {
    	if(A.length<=0)
    		return 0;
    	int start = 0;
    	int end = A.length-1;
    	int mid =0;
    	while(start<=end){
    		mid = (start+end)/2;
    		if(target == A[mid]){
    			return mid;
    		}else if(target<A[mid]){
    			end = mid -1;
    		}else
    			start = mid+1;
    	}
    	return start;
    }
    
//    采取二分查找
    public int searchInsert3(int[] A, int target) {
    	if(A.length<=0)
    		return 0;
    	return partitionSearch(A,0,A.length-1,target);
    }
	private int partitionSearch(int[] A, int left, int right , int target) {
		// TODO Auto-generated method stub
		if(left>right)
			return left;
		int mid = (left+right)/2;
		if(A[mid]==target){
			return mid;
		}
		else if(A[mid]<target){
			return partitionSearch(A, left, mid-1, target);
		}else{
			return partitionSearch(A, mid+1, right, target);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
