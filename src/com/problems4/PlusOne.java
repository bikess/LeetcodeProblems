package com.problems4;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * 
 * Plus One
 * 题意：Given a number represented as an array of digits, plus one to the number.
 * 给你一个用数组表示的数，求加一之后的结果，结果还是用数组表示。
 * @author bike
 *
 */
public class PlusOne {

	/**
	 * @param args
	 */
	/***
	 * 方法1，就是从右向左依次对数组元素+1，若不为10则停止，若为10
	 * 则该元素置0，继续向左遍历
	 * 当遍历到最后仍然全为9，则重新初始化数组，且第一个元素置为0
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int len = digits.length;
		boolean flag = false;
		for(int i=len-1;i>=0;i--){
			if(digits[i]+1<=9){
				digits[i] = digits[i]+1;
				flag =true;
				break;
			}
			if(digits[i]+1==10){
				digits[i] = 0;
			}
		}
//		 各位结果全为9；
		if(flag==false){
			digits = new int[len+1];
			digits[0]=1;
		}
		return digits;
        
    }
	
	/****
	 * 方法2 
	 * @param args
	 */
	 public int[] plusOne2(int[] digits){
		 add(digits,1);
		 return digits;
	 }
//	 此方法就是对数组表示的数进行加value运算
	private void add(int[] digits, int value) {
		// TODO Auto-generated method stub
//		c 表示来自第一位的进位
		int c = value;
		for(int i = digits.length-1;i>=0;i--){
			digits[i]= digits[i]+c;
			c = digits[i]/10;
			digits[i] = digits[i]%10;
			if(c==0)
				return;
		}
//		还有进位
		if(c>0){
//			这里如何是digits数组能动态增长，而且同时又不会改变digits引用指向的对象是个难题，这里不好弄
//			下面这种方法实现了数组的动态扩容，但是引用指向的对象确也改变了
			digits = Arrays.copyOf(digits, digits.length+1);
			for(int i=digits.length-1;i>=1;i--){
				digits[i] = digits[i-1];
			}
			digits[0] = 1;
		}
//		for(int i=0;i<digits.length;i++){
//			System.out.print(digits[i]+" ");
//		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlusOne p = new PlusOne();
		int digits[] = {9};
		int[] m = p.plusOne2(digits);
		for(int i=0;i<m.length;i++){
			System.out.print(m[i]+" ");
		}
	}

}
