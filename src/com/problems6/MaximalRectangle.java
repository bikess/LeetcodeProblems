package com.problems6;

import java.util.Stack;

/***
 * 在一个0,1矩阵中，找到多有1围成的矩形的面积中最大的一个，并返回这个面积
 * @author bike
 *
 */
public class MaximalRectangle {

	/***
	 * 
	 * 思路1 ：用一个数组f[i][j]来存储记录i行j列为结尾，往前连续的1的个数
	 * 然后再一个0（n^3)循环来找以（i，j）为右下角的矩形最大的1的面积
	 * @param matrix
	 * @return
	 */
    public int maximalRectangle(char[][] matrix) {
		int m = matrix.length;
		if(m<=0)
			return 0;
		int n = matrix[0].length;
		int f[][] = new int[m][n];
		for(int i=0;i<m;i++){
			f[i][0] = matrix[i][0]=='1'? 1:0;
		}
//		经过此循环可以得到以i，j为结尾，往前连续的1的个数
    	for(int i=0;i<m;i++){
    		for(int j=1;j<n;j++){
    			f[i][j] = matrix[i][j] =='1'? f[i][j-1]+1:0;
    		}
    	}
    	int result = 0;
    	for(int i=0;i<m;i++){
    		for(int j=0;j<n;j++){
    			int k = i;
    			int width = Integer.MAX_VALUE;
    			while(k>=0){
    				if(f[k][j]==0)
    					break;
//    				当前矩形的宽的最小值,宽带小了，那么深度才有可能增加
    				width = Math.min(width, f[k][j]);
    				result = Math.max(result, width*(i-k+1));
    				k--;
    			}
    		}
    	}
    	return result;
        
    }
    /***
     * 方法2 时间复杂度0（n^2),通过构造类似直方图的方法
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
		int m = matrix.length;
		if(m==0)
			return 0;
		int n = matrix.length;
		if(n==0)
			return 0;
		
		int dp[][] = new int[m][n];
		for(int i=0;i<n;i++){
			dp[0][i] = matrix[0][i]=='1'? 1:0;
		}
//		够造直方图
		for(int i=0;i<n;i++){
			for(int j=1;j<m;j++){
				dp[j][i]= matrix[j][i]=='1'? dp[j-1][i]+1:0;
			}
		}
		int result = 0;
		for(int i=0;i<m;i++){
			int tmp = largestRectangleArea(dp[i],n+1);
			if(tmp>result)
				result = tmp;
		}
    	return result;
    }
	private int largestRectangleArea(int[] height, int length) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<>();
		int i=0;
		int result = 0;
		while(i<length){
			if(stack.isEmpty()||height[stack.peek()]<=height[i]){
				stack.push(i++);
			}else{
				int tem = stack.pop();
				int area = height[tem]*(stack.isEmpty()? i:i-stack.peek()-1);
				result  = Math.max(result, area);
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
