package com.problems7;
/***
 * 题目的意思：
 * 给三个字符串，判断第三个字符串是不是，第一个与第二个字符串中的字符交错形成的字符串
 * For example,
	Given:
	s1 = "aabcc",
	s2 = "dbbca",
	When s3 = "aadbbcbcac", return true.
	When s3 = "aadbbbaccc", return false.
 * @author bike
 *
 */
public class InterleavingString {
	/***
	 * 
	 * 思路1：采取动态规划的方法
	 * 
	 * 设置动态数组f[i][j],表示从A中取前i个字符，B中取前j个字符，是否和字符串C中的前i+j个字符交错匹配。
	 * 则状态转移方程为：f[i-1][j]&&A[i]==C[i+j-1] 表示的是A的前i-1个字符与B中的前j个字符与C中的前i+j-1个字符匹配，而且
	 * A的第i个字符也和C的第i+j个字符匹配
	 * f[i][j]= f[i-1][j]&&A[i]==C[i+j-1]||f[i][j-1]&&B[j]==C[i+j-1]
	 * 
	 * 一定思考动态规划的算法的类似思路
	 * （1）一般是二维动态规划（需要一个二维的动态数组），然后要将求解的问题转换成用数组每个值来表示
	 * （2）先得到起始的二维数组值，若dp[0][0]
	 * (3)找到递推公式也就是状态转移方程 dp[i][j]可能与dp[i][j-1]、dp[i-1][j],dp[i-1][j-1]等等的关系式，而且可能不止和这些递推有关系，可能还有
	 * 条件判断和转换关系
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave1(String s1, String s2, String s3) {
		int len1 = s1.length();
		int len2 = s2.length();
		if(len1+len2!=s3.length()){
			return false;
		}
		boolean dp[][] = new boolean[len1+1][len2+1];
		dp[0][0] = true;
		for(int i=1;i<=len1;i++){
			if(dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1))
				dp[i][0] = true;
		}
		for(int j=1;j<=len2;j++){
			if(dp[0][j-1]&&s2.charAt(j-1)==s3.charAt(j+0-1))
				dp[0][j] = true;
		}
		for(int i=1;i<=len1;i++)
			for(int j=1;j<=len2;j++){
				if(dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)||dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1))
					dp[i][j] = true;
			}
		return dp[len1][len2];
		
	}
	/***
	 * 思路2，也是采取的动态规划方法，只不过更加的简便
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
    public boolean isInterleave2(String s1, String s2, String s3) {
    	 int n1 = s1.length();  
         int n2 = s2.length();  
         if(n1+n2 != s3.length()) return false;  
         boolean f[][] = new boolean[n1+1][n2+1]; 
         f[0][0] = true;  
         for(int i = 0; i <= n1; ++i)  
         {  
             for(int j = 0; j <= n2; ++j)  
             {  
                 int len = i+j;  
                 if(i >= 1 && s1.charAt(i-1) == s3.charAt(len-1)) f[i][j] = f[i][j] || f[i-1][j];  
                 if(j >= 1 && s2.charAt(j-1) == s3.charAt(len-1)) f[i][j] = f[i][j] || f[i][j-1];  
             }  
         }  
         return f[n1][n2];  
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
