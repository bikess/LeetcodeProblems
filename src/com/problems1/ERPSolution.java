package com.problems1;

import java.util.Stack;

public class ERPSolution {

	/**
	 * @param args
	 */
/*
 * 
 * 题目描述
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
//	逆波兰表示法的求解
	public static int evalRPN (String[] tokens){
		int result;
		Stack<Integer> digit = new Stack<>(); 
		for(int i=0;i<tokens.length;i++){
//			这里进行判断字符是否为数字，只需判断第一个就可以
			char[] num = tokens[i].toCharArray();
			if(Character.isDigit(num[0])){
//				若是数字则压入栈，由于是遵守后进先出，因此，首先栈的是第二个运算符
				digit.push(Integer.parseInt(tokens[i]));
			}
			else{
//				一定注意某个数字是负数的情况				
				if(num.length>1){
					digit.push(Integer.parseInt(tokens[i]));
				}
				else{
				int o1,o2,r = 0;
				o2 = digit.pop();
				o1 = digit.pop();
				switch (num[0]) {
				case '+':
					r = o1+o2;
					break;
				case '-':
					r = o1-o2;
					break;
				case '*':
					r = o1*o2;
					break;
				case '/':
					r = o1/o2;
					break;
				}
				digit.push(r);
				}
			}
		}
		result = digit.peek();
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tokens ={"3","-4","+"};
		int result =  evalRPN(tokens);
		System.out.println(result);
		System.out.println(Integer.parseInt("-10"));
	}

}
