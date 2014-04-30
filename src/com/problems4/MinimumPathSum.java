package com.problems4;

public class MinimumPathSum {

	/**
	 *  其实就是从左上方移动到右下方路路径总数的变形题(每次移动只能向下或者向右)
	 *  在此题中每一个方格中都有一个非负数，
	 *  问从左上方当右下方哪条路径里的数字之和最小
	 * @param args
	 */
	/***
	 * 思路1： 动态规划的方法,到达sum[i][j]中存储的是到这个点的小路径的和
	 * 而这个子问题可以转换成 从grid[i-1][j]到这个位置与从grid[i][j-1]到这个位置的两个值中的最小值
	 * 所以有如下的递推公式
	 * sum[0][j]=grid[0][j]+...
	 * sum[i][0]=grid[i][0]+...
	 * sum[i][j]= min{sum[i-1][j],sum[i][j-1]}+grid[i][j]
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		int m= grid.length;
		int n = grid[0].length;
		int sum[][] = new int[m][n];
		sum[0][0] = grid[0][0]; 
		for(int i=1;i<m;i++){
			sum[i][0] = sum[i-1][0]+grid[i][0];
		}
		for(int i=1;i<n;i++){
			sum[0][i] = sum[0][i-1]+grid[0][i];
		}
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				sum[i][j] = Math.min(sum[i-1][j], sum[i][j-1])+grid[i][j];
			}
		}
		return sum[m-1][n-1];
	        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
