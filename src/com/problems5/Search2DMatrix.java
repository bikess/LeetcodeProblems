package com.problems5;

public class Search2DMatrix {

	/**
	 * @param args
	 */
	
	/***
	 * 此题的思路，时间复杂度最低的求解思路是：
	 * 因为二维数组中所有的每一行是排序的，最右边的数是最大的
	 * 二维数组中每一列也是排序的，最下面的数也是最大的，而且每一行的第一个数
	 * 比前一行的最后一个数还大
	 * 
	 * 这里的解题思路实际上是 排除法，通过排除掉肯定不存在的元素
	 * 咱们从左下角的元素来考虑，
	 * 若目标元素比其大，则目标元素只可能在此行的往后的元素中
	 * 若目标元素比其小，则递归判断前面的矩阵
	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
    	for(int i = m-1;i>=0;i--){
    		if(target==matrix[i][0]){
    			return true;
    		}
    		if(target>matrix[i][0]){
//    			for(int j=1;j<n;j++){
////    				这里可以采取折半查找, 折半查找时间复杂度log(n)
//    				if(target==matrix[i][j]){
//    					return true;
//    				}
//    			}
    			boolean flag = zheBanFind(matrix,target,i,1,n-1);
    			if(flag==true)
    				return true;
    		}
    	}
    	return false;
	        
	}
	private boolean zheBanFind(int[][] matrix, int target, int i, int start, int end) {
		// TODO Auto-generated method stub
		if(start>end){
			return false;
		}
		int mid = (start+end)/2;
		if(target == matrix[i][mid]){
			return true;
		}else if(target<matrix[i][mid]){
			return zheBanFind(matrix, target, i, start, mid-1);
		}else{
			return zheBanFind(matrix, target, i, mid+1, end);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
