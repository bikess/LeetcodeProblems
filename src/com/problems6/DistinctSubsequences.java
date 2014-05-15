package com.problems6;
/***
 * 
 * 题目意思：
 * 只可以用删除字符的方法从第一个字符串变换为第二个字符串，问有多少中变换方法
 * 
 * @author bike
 *
 */
public class DistinctSubsequences {

	/**
	 * @param args
	 */
	/***
	 * 
	 * 思路1 ：利用动态规划的思路求解
	 * （1）定义dp[i][j]保存字符串1变换到字符串2的变换方法
	 * （2）若S[i]==T[j] 则dp[i][j] = dp[i-1][j-1]+dp[i-1][j]
	 * 意思是：若当前S[i]==T[j]，那么当前这个字母即可以保留也可以抛弃
	 *所以变换方法等于保留这个字母的变换方法加上不用这个字母的变换方法
	 *（3）如果S[i]!=T[i]，那么dp[i][j] = dp[i-1][j]，
	 *意思是如果当前字符不等，那么就只能抛弃当前这个字符。
	 * @param args
	 */
    public int numDistinct(String S, String T) {
    	int lens = S.length();
    	int lent = T.length();
    	if(lens==0||lent==0){
    		return 0;
    	}
    	if(lens<lent){
    		return 0;
    	}
    	int dp[][] = new int[lens+1][lent+1];
    	dp[0][0] = 1;
//    	任意一个字符串变成一个空字符串都只有一种变换方法
    	for(int i=0;i<lens;i++){
    		dp[i][0] = 1;
    	}
//    	递推公式
    	for(int i=1;i<=lens;i++){
    		for(int j=1;j<=lent;j++){
    			 //如果S和T的当前字符相等，那么有两种选法；否则S的当前字符不能要
    			dp[i][j] = dp[i-1][j];
    			if(S.charAt(i-1)==T.charAt(j-1)){
    				dp[i][j] = dp[i][j]+dp[i-1][j-1];
    			}
    		}
    	}
		return dp[lens][lent];
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
