package com.problems7;
/**
 * 译码：
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 * @author bike
 *
 */
public class DecodeWays {

	public int count = 0;
	/***
	 * 思路1 采取递归的方法
	 * @param s
	 * @return
	 */
    public int numDecodings(String s) {
		int len = s.length();
		if(len<=0){
			return 0;
		}
    	getDecodingNum(s,0);
    	return count;
    }
	private void getDecodingNum(String s, int start) {
		// TODO Auto-generated method stub
		if(start==s.length()){
			count++;
			return;
		}
		if(start<s.length()-1){
			int num = (s.charAt(start)-'0')*10+s.charAt(start+1)-'0';
			if(num>=10&&num<=26){
				getDecodingNum(s, start+2);
			}
		}
		if(s.charAt(start)>='1'&&s.charAt(start)<='9'){
			getDecodingNum(s, start+1);
		}
	}
	/***
	 * 思路2 采取动态规划的方法。
	 * 递推公式：
	 * 设第i个元素加入以前n-1个元素的：总共有 n(i-1)种编码方式，
	 * 当加入第i个元素后，存在的编码方式，
	 * （1）若此元素与前一个元素可以构成两位数的编码方式，则n(i)=n(i-1)+n(i-2),但是这里要注意排除特例：
	 * 	当构成的可以编码的两位数是10或者20的时候，由于此时第i个元素与第i-1个元素不能拆开，此时n[i]=n[i-2]
	 * 	(2) 若此元素与前一个元素不可以构成两位数的编码方式，则n（i）=n(i-1)
	 * @param args
	 */
    public int numDecodings2(String s) {
		int len = s.length();
		if(len<=0){
			return 0;
		}
//		注意此时第一个数为0，则无法编码
    	if(s.charAt(0)=='0')
    		return 0;
    	int dp[] = new int[len+1];
    	dp[0]=1;
    	dp[1]=1;
    	for(int i = 1;i<len;i++){
    		int num = (s.charAt(i-1)-'0')*10+s.charAt(i)-'0';
//    		当两个数的大小处于10到26之间
    		if(num>=10&&num<=26){
//    			当等于10或者20时，dp[i+1] = dp[i-1]
    			if(s.charAt(i)=='0'){
    				dp[i+1] = dp[i-1];
    			}else
    				dp[i+1] = dp[i]+dp[i-1];
    		}else{
//    			这是不能构成两位数，但是可以构成单位数编码
    			if(s.charAt(i)>='1'&&s.charAt(i)<='9'){
    				dp[i+1] = dp[i];
    			}else{
    				return 0;
    			}
    		}
    	}
    	return dp[len];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecodeWays d = new DecodeWays();
		System.out.println(d.numDecodings("110"));
		System.out.println(d.numDecodings2("110"));
	}

}
