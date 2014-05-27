package com.problems6;

import java.util.ArrayList;

/***
 * 给两个字符串（非负数），求解两个字符串的乘积。用字符串返回两个字符串的乘积
 * @author bike
 *
 */
public class MultiplyStrings {
	/***
	 * 方法1 模拟手乘法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		ArrayList<Integer> list = new ArrayList<>();
		int len1 = num1.length();
		int len2 = num2.length();
		return num2;
		
	}
	/***
	 * 方法2
	 * @param args
	 */
	public String multiply2(String num1, String num2) {
//		注意这个是去除，当一个字符串为”0“的时候，是输出”0“，要是这里不排除这种情况，下面会输出”0000“这种情况；
//		字符串比较一定要用equals（）！！！！！！！！
		if(num1.equals("0")||num2.equals("0"))
			return "0";
		int len1 = num1.length();
		int len2 = num2.length();
		int intnum1[] = new int[len1];
		int intnum2[] = new int[len2];
		int result[] = new int[len1+len2];
//		这是把所有的字符串1 转换成数组
		for(int i=0;i<len1;i++){
			intnum1[i] = num1.charAt(i)-'0';
		}
//		把字符串2 转换成数组
		for(int i=0;i<len2;i++){
			intnum2[i] = num2.charAt(i)-'0';
		}
//		字符串i位置数与字符串j位置的数相乘的结果=结果的第i+j+1的位置的结果，并且结果是累加的！！！！！
//		!!!!!这个一点非常重要，result[i+j+1] = result[i+j+1]+intnum1[i]*intnum2[j]
		for(int i=0;i<len1;i++){
			for(int j=0;j<len2;j++){
				result[i+j+1] = result[i+j+1]+intnum1[i]*intnum2[j];
			}
		}
//		表示字符串结果
		String ret  = "";
//		设置变量表示低位向高位的进位
		int k = 0;
		for(int i = len1+len2-1;i>=1;i--){
			int tem  = result[i]+k;
			k = tem/10;
			ret = String.valueOf(tem%10)+ret;
		}
		if(k!=0)
			ret = String.valueOf(k)+ret;
		return ret;
		
	}
	public static void main(String[] args) {

	}

}
