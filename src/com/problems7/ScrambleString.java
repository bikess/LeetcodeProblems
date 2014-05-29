package com.problems7;
/***
 * 题目意思：
 * 简单的说，就是s1和s2是scramble的话，那么必然存在一个在s1上的长度l1，将s1分成s11和s12两段，同样有s21和s22。
	那么要么s11和s21是scramble的并且s12和s22是scramble的；！！！！（存在这两种可能）
	要么s11和s22是scramble的并且s12和s21是scramble的。！！！！
 * @author bike
 *
 */
public class ScrambleString {

	/**
	 * @param args
	 */
	/***
	 * 方法1 采取递归加 减枝的方法
	 * @param args
	 */
    public boolean isScramble(String s1, String s2) {
    	int len1 = s1.length();
    	int len2 = s2.length();
    	if(len1!=len2){
    		return false;
    	}
    	if(s1.equals(s2)){
    		return true;
    	}
//    	判断两个字符串的字符是种类与个数是否相同,只要两个字符串的个数种类，个数均相同，那么总有一种装换会使两个字符串变成相同的字符串
//    	这里这种思路相当于进行了对递归进行了减枝，不用进一步的递归判断了，直接通过这个思路：
//    	只要两个字符串的个数种类，个数均相同，那么总有一种装换会使两个字符串变成相同的字符串，就可以判断
    	int A[] = new int[26];
    	for(int i=0;i<len1;i++){
    		A[s1.charAt(i)-'a']++;
    	}
    	for(int i=0;i<len2;i++){
    		A[s2.charAt(i)-'a']--;
    	}
    	for(int i=0;i<26;i++){
    		if(A[i]!=0)
    			return false;
    	}
//    	递归来判断
    	for(int i=1;i<len1;i++){
//    		判断从某个位置截断，两个字符串是否是转换字符串，这里是判断s11和s21是scramble的并且s12和s22是scramble
    		boolean result = isScramble(s1.substring(0,i), s2.substring(0,i))&&isScramble(s1.substring(i), s2.substring(i));
//    		虽然从某个位置截断，两个字符串不同，但是有可能减枝后交换了两个分支的位置（左右交换），因此要判断是否是这种交换导致的
//    		这里是判断s11和s22是scramble的，并且s12和s21是scramble的
    		result = result || isScramble(s1.substring(0, i), s2.substring(len2-i))&&isScramble(s1.substring(i), s2.substring(0, len2-i));
    		if(result==true)
    			return true;
    	}
		return false;
        
    }
    /***
     * 方法1中若不采取减枝来减少递归的方法，而是直接就是一直递归判断，就会在大集合的时候出现超时，其思路下面这种
     */
    public boolean isScramble1(String s1, String s2) {
    	int len1 = s1.length();
    	int len2 = s2.length();
    	if(len1!=len2){
    		return false;
    	}
    	if(s1.equals(s2)){
    		return true;
    	}
    	for(int i=1;i<len1;i++){
//    		从i位置截断，得到的s1的两个子串 s11与s12
    		String s11 = s1.substring(0,i);
    		String s12 = s1.substring(i);
//    		从i位置截断，得到的s2的两个字符串 s21与s22
    		String s21 = s2.substring(0,i);
    		String s22 = s2.substring(i);
//    		递归判断是否属于第一种情况：判断s11和s21是scramble的并且s12和s22是scramble
    		boolean result = isScramble1(s11, s21)&&isScramble1(s12, s22);
    		
//    		第一种情况不符合，则看第二种情况：判断s11和s22是scramble的，并且s12和s21是scramble
    		if(result==false)
    		{
    			s21 = s2.substring(0, len2-i);
    			s22 = s2.substring(len2-i);
    			result = isScramble1(s11, s22)&&isScramble1(s12, s21);
    		}
    		if(result==true)
    			return true;
    	}
    	return false;
    }
    /***
     * 方法2 ：采取三维动态规划的方法
     * 思路：
     * （1）用三维数字F[i][j][k]=true,表示S1[i....i+k-1]==S2[j....j+k-1] （注意是到k-1的位置！！！！）
     * 也就是表示，F[i][j][k]记录S1从i开始的k个字符与S2从第j个字符开始的k个字符是否为Scramble String
     * （2）递推公式推导
     * 当k=1的时候，也就是S1[i]与S2[j]是否为Scramble String
     * 当k=2的时候，F[i][j][2] = F[i][j][1]&&F[i+1][j+1][1] || F[i][j+1][1]&&F[i+1][j][1]
     * F[i][j][1]&&F[i+1][j+1][1] 表示的是 S11与s21相等 s12与s22相等，而 F[i][j+1][1]&&F[i+1][j][1]表示的是s11与s22相等且s12与s21相等
     * F[i][j][1] = S1[i]==s2[j]
     * 当k=k的时候，判断S1从i开始的k个字符与S2从第j个字符开始的k个字符是否为Scramble String,这是需要寻找符号条件的分割点
     * for(m=1;m<k;m++) （m表示的是判断从m的位置进行分割！！！）
     * F[i][j][k] = F[i][j][m]&&F[i+m][j+m][k-m] || F[i][j+k-m][m]&&F[i+m][j][k-m]
     * @param args
     */
//    下面是别人的的代码
   
     boolean isScramble2(String s1, String s2) {
    	        // Start typing your C/C++ solution below
    	        // DO NOT write int main() function
	        if (s1.length() != s2.length()) {
	            return false;
	        }
	        int length = s1.length();
//	        动态规划的数组
	        boolean f[][][]=new boolean[length][length][length+1];
//	        三重循环
//	        第一重循环是 判断的字符串的长度
//	        第二三重循环是 字符串S1 与 S2分别的起始位置
	        for (int k = 1; k <=length; k++) {
	            for (int i = 0; i <=length - k; i++) {
	                for (int j = 0; j <=length - k; j++) {
	                    if (k == 1) {
	                        f[i][j][k] = s1.charAt(i) == s2.charAt(j);
	                    }
	                    else {
	                        for (int l = 1; l < k; l++) {
	                            if ((f[i][j][l] && f[i + l][j + l][k - l]) || (f[i][j + k - l][l] && f[i + l][j][k - l])) {
	                                f[i][j][k] = true;
	                                break;
	                            }                            
	                        }
	                    }
	                }
	            }            
	        }
	                
	        return f[0][0][length];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("sasfawfe".substring(5,2));
	}

}
