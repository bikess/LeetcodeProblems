package com.problems4;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UniquePaths {

	/**
	 * @param args
	 */
	/**
	 * 方法1，采取递归的方式进行运算，此方法明显出现超时
	 * @param m
	 * @param n
	 * @return
	 */
	  public int uniquePaths(int m, int n) {
			int result = 0;
			if(m==1&&n==1){
				return 0;
			}
	        if(m>1&&n==1){
				return 1;
			}
			if(m==1&&n>1){
				return 1;
			}
			else{
				result = result+uniquePaths(m-1,n);
				result = result+uniquePaths(m,n-1);
			}
			return result;
	    }
	  
	  /***
	   * 方法2，设置一个数组来存储重复计算的部分，采取动态规划(DP)的方式进行运算
	   * @param args
	   */
	  public int uniquePaths2(int m, int n) {
		  int path[][] = new int[m+1][n+1];
		  for(int i = 1;i<=n;i++){
			  path[m][i] = 1;
		  }
		  for(int i=0;i<=m;i++){
			  path[i][n] = 1;
		  }
		  for(int i=m-1;i>=1;i--){
			  for(int j=n-1;j>=1;j--){
				  path[i][j] = path[i][j+1]+path[i+1][j];
			  }
		  }
		  return path[1][1];
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UniquePaths u = new UniquePaths();
		System.out.println(u.uniquePaths(10, 20));
	}

	
	/***
	 * 题目扩展
	 * 
	 * 当矩阵中有些位置存在障碍物，障碍物的位置无法通过，问存在多少的路径
	 */
	
	/***
	 * 方法1 ，仍然是利用动态规划的方法，只不过判断一下当前方格是否有障碍物
	 * http://www.cnblogs.com/remlostime/archive/2012/11/15/2772282.html
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int path[][] = new int[m][n];
//		此方法采取的是从【0,0】位置开始的
//		先看起始位置，起始位置有障碍物则到此点的障碍物为0
		path[0][0] = obstacleGrid[0][0]==1? 0:1;
		for(int i =1;i<m;i++){
//			这里一定注意path[i][0] = ? path[i-1][0]
			path[i][0] = obstacleGrid[i][0]==1? 0:path[i-1][0];
		}
		for(int i=1;i<n;i++){
			path[0][i] = obstacleGrid[0][i]==1? 0:path[0][i-1];
		}
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				path[i][j] = obstacleGrid[i][j]==1? 0:path[i-1][j]+path[i][j-1];
			}
		}
		return path[m-1][n-1];
        
    }
	/***
	 * 方法同上，不过数组存储从后往前
	 */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
       	int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int path[][] = new int[m][n];
		path[m-1][n-1] = obstacleGrid[m-1][n-1]==1? 0:1;
		for(int i =m-2;i>=0;i--){
			path[i][n-1] = obstacleGrid[i][n-1]==1? 0:path[i+1][n-1];
		}
		for(int i =m-2;i>=0;i--){
			path[m-1][i] = obstacleGrid[m-1][i]==1? 0:path[m-1][i+1];
		}
		for(int i=m-2;i>=0;i--){
			for(int j=n-2;j>=0;j--){
				path[i][j] = obstacleGrid[i][j]==1? 0:path[i+1][j]+path[i][j+1];
			}
		}
		return path[0][0];
    }
    
    /***
     * 
     * 方法2 利用广度优先遍历递归求解，这种方法虽然超时但是也要会写
     */
    
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
		
     	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	if(m==0||n==0){
    		return 0;
    	}
    	int result = 0;
    	result = DFSPathsWithObstacles(obstacleGrid,m-1,n-1);
    	return result;
    	
    }

	private int DFSPathsWithObstacles(int[][] obstacleGrid, int m, int n) {
		if(m==0&&n==0){
			return obstacleGrid[0][0] == 1? 0:1;
		}
		if(m==0&&n>0){
			return obstacleGrid[m][n]==1? 0:DFSPathsWithObstacles(obstacleGrid, m, n-1);
		}
		if(n==0&&m>0){
			return obstacleGrid[m][n]==1? 0:DFSPathsWithObstacles(obstacleGrid, m-1, n);
		}
		return obstacleGrid[m][n]==1? 0:DFSPathsWithObstacles(obstacleGrid, m-1, n)+DFSPathsWithObstacles(obstacleGrid, m, n-1);		
		
	}
}
