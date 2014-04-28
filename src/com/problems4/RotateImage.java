package com.problems4;

public class RotateImage {

	/**
	 * 矩阵旋转90度，要求原地反转
	 * @param matrix
	 * @author bike
	 * </ul>http://oj.leetcode.com/problems/rotate-image/
	 */
	/***
	 *  方法1 ，采取非原地反转,空间复杂度o（N*N）
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		int r = matrix.length;
		int l = matrix[0].length;
	    int result[][] = new int[r][l];  
	    for(int i=0;i<r;i++){
	    	for(int j=0;j<l;j++){
	    		result[i][j] = matrix[r-1-j][i];
	    	}
	    }
		for(int i=0;i<r;i++){
			for(int j=0;j<l;j++){
				matrix[i][j] = result[i][j];	
			}
		}
	}
	/**
	 * 方法2 ，旋转正方形矩形。右旋90度，O(1)的空间复杂度。
	 *方法思路，将[i][j] [j][n-i-1] [n-j-1][i] [n-i-1][n-j-1]
	 *四个位置的元素循环置换一遍就可以了
	 */
	public void rotate2(int[][] matrix) {
		int temp;
		int n = matrix.length;
		for(int i =0;i<n/2;i++){
			for(int j=i;j<n-i-1;j++){
				temp = matrix[j][n-i-1];
				matrix[j][n-i-1] = matrix[i][j];
				matrix[i][j] = matrix[n-j-1][i];
				matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
				matrix[n-i-1][n-j-1] = temp;
			}
		}
	}
	
	/***
	 * 方法3：
	 * 题目http://oj.leetcode.com/problems/rotate-image/
	 * 解决http://www.2cto.com/kf/201401/274473.html
	 * 首先沿着副对角线翻转一次，然后沿着水平中线翻转一次
	 * 1,2 -》   4,2 -》 3 1
	 * 3,4     3,1    4 2 
	 */
	public void rotate3(int[][] matrix) {
//		首先沿着副对角线翻转一次
		int n = matrix.length;
		int temp ;
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i;j++){
				temp = matrix[i][j];
//				在一层循环内，等式前面行不变，等式后面列不变
				matrix[i][j] = matrix[n-1-j][n-1-i];
				matrix[n-1-j][n-1-i] = temp;
			}
		}
		
//		在沿着中心线上下翻转一次
		for(int i=0;i<n/2;i++){
			for(int j=0;j<n;j++){
				temp = matrix[i][j];
				matrix[i][j] = matrix[n-1-i][j];
				matrix[n-1-i][j] = temp;
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RotateImage m = new RotateImage();
		int matrix[][]={
	    {1,2,3}
		,{4,5,6}
		,{7,8,9}
		};
		m.rotate(matrix);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}

}
