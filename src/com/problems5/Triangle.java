package com.problems5;

import java.util.ArrayList;
/***
 * 杨慧三角的问题，要求从上往下求解路径上的最小值，要求下面的在行的坐标不小于前一行中的坐标
 * 
 * 动态规划的方法
 * dp[0] = a[0][0]
 * dp[i] = min{dp[i-1],dp[i]}+当前元素值
 * @author bike
 *
 */
public class Triangle {

	/**
	 * @param args
	 */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    	int n = triangle.size();
    	int dp[] = new int[triangle.get(n-1).size()];
    	dp[0] = triangle.get(0).get(0);
    	for(int i=1;i<n;i++){
    		for(int j=triangle.get(i).size()-1;j>=0;j--){
    			if(j==0)
    				dp[j] = dp[j]+triangle.get(i).get(j);
    			else if(j==triangle.get(i).size()-1){
    				dp[j] = dp[j-1] + triangle.get(i).get(j);
    			}else{
    				dp[j] = Math.min(dp[j-1], dp[j])+triangle.get(i).get(j);
    			}
    		}
    	}
    	int result = Integer.MAX_VALUE;
    	for(int i= 0;i<dp.length;i++){
    		result = Math.min(result, dp[i]);
    	}
		return result;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
