package com.problems5;

/***
 * 题意：判断一个整型数是否为回文数，要求不能利用额外的存储空间
 * 此题思路有很多，但是要求必须不能利用额外的存储空间
 * （1）若转换成字符串，然后判断逆序前后的字符串是否相同来判断是否为回文。此方法显然在转换成字符串后利用了额外的存储空间
 * （2）方法2 把此整型数 利用取除取余法逆序，判断逆序后的两个数是否相同来判断是否为回文串，但是此方法要判断逆序后的数是否溢出，若溢出则无法判断
 * @author bike
 *
 */
public class PalindromeNumber {

	/***
	 * 方法1 ：思路对数字对10取余，得到个位数，然后对该数的10^n-1(n为x的位数）取除方法得到最高位数,依次判断最高位数与最低位数是否相等
	 * 注意别忘了负数，负数一定不是回文数
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		if(x<0)
			return false;
		int n = 1;
		int d = x;
		while(d/10!=0){
			n=n*10;
			d= d/10;
		}
		int m = 10;
		while(n>=m){
			int high = x/n;
			int low = x%m;
			if(low!=high){
				return false;
			}
			x = x%n/m;
			n = n/100;
		}
		return true;
	        
	    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
