package com.problems6;
/**
 * 题目意思：
 * 给两个单词word1和word2，看最少经过多少步可以由单词word1变为word2
 * 可以进行三种操作
 * （1）插入一个字符
 * （2）删除一个字符
 * （3）改变一个字符
 * @author bike
 *
 */
public class EditDistance {

	/**
	 * 
	 * 思路：动态规划的方法
	 * dp[i][j]表示word1的前i个字母与与word2的前j个字母编辑距离
	 * （1）若word[i+1]==word[j+1],dp[i+1][j+1] = dp[i][j];否则：
	 * 利用替换原则dp[i+1][j+1]=dp[i][j]+1;
	 * (2)dp[i+1][j+1]还可以取dp[i][j+1]与dp[i+1][j]中的较小值（利用删除
	 * 添加原则）
	 * （3）最后可以得到dp[i+1][j+1]= min{dp[i][j+1]+1(删除原则),dp[i+1][j]+1（添加原则）,dp[i][j]+1（替换原则）};
	 * @param args
	 */
	public int minDistance(String word1, String word2) {
		
		int len1 = word1.length();
		int len2 = word2.length();
//		排除了两个字符串其中一个为空字符串的情况
		if(len1==0)
			return len2;
		if(len2==0)
			return len1;
//		dp[i][j]表示单词1第i与单词2第j个字母的编辑距离
		int dp[][] = new int[len1+1][len2+1];
//		设置word1到第0个字符与word2到第i个字符的差距
		for(int i=0;i<=len2;i++){
			dp[0][i] = i;
		}
//		设置word2到第0个字符与word1到第j个字符的差距
		for(int j=0;j<=len1;j++){
			dp[j][0] = j;
		}
		for(int i=1;i<=len1;i++){
			for(int j=1;j<=len2;j++){
				if(word1.charAt(i-1)==word2.charAt(j-1)){
					dp[i][j]= dp[i-1][j-1];
				}else{
//					添加与替换之间的最小值
					dp[i][j] = Math.min(dp[i-1][j-1]+1, dp[i-1][j]+1);
//					添加、替换、与删除之间的最小值
					dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
				}
			}
		}

		
		return dp[len1][len2];
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
