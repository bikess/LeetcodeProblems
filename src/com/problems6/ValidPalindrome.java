package com.problems6;
/***
 * 题意：
 * 给出一个字符串，检查它是不是一个回文。判断过程中只考虑字母数字的字符并且忽略大小写。
	例如：
	“A man, a plan, a canal: Panama”是一个回文
	“race a car”不是一个回文
	注意：
	在这里我们将空字符串定义为回文
 * @author bike
 *
 */
public class ValidPalindrome {
	/***
	 * 思路1，采取夹逼的方法，设置一个指针指向字符串的头部，设置一个指针指向字符串的尾部
	 * ，然后判断两个字符是否相等。一直判断到两个指针重合，一旦存在不相等，则结束判断
	 * @param s
	 * @return
	 */
    public boolean isPalindrome(String s) {
		int len = s.length();
		if(len<=1){
			return true;
		}
//		设置首尾两个指针
		int start = 0;
		int end = len-1;
		while(start<end){
			char c = s.charAt(start);
			while(!((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z'))&&start<end){
				start++;
				c = s.charAt(start);
			}
			c = s.charAt(end);
			while(!((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z'))&&start<end){
				end--;
				c = s.charAt(end);
			}
			char left = s.charAt(start);
			char right = s.charAt(end);
			if(s.charAt(start)>='A'&&s.charAt(start)<='Z'){
				left = (char)(s.charAt(start)+32);
			}
			if(s.charAt(end)>='A'&&s.charAt(end)<='Z'){
				right = (char)(s.charAt(end)+32);
			}
			if(left!=right){
				return false;
			}
			start++;
			end--;
		}
    	return true;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
