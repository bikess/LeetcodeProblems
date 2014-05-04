package com.problems5;

import java.util.ArrayList;
import java.util.Stack;

/***
 * 题目意思：Given a string containing just the characters '(', ')', '{', '}', 
 * '[' and ']', determine if the input string is valid.
 * 给一个括号的串看是否合法
 * @author bike
 *
 */
public class ValidParentheses {

	/**
	 * @param args
	 */
	/***
	 * 方法1 考虑利用辅助栈来完成，若是左半括号则进栈，而一旦遇到右半括号，则从栈中出栈，看出栈的左括号是否匹配，不匹配，违法结束，匹配，继续进行
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		
		int len = s.length();
		if(len<=1){
			return false;
		}
		Stack<Character> stack = new Stack<>();
		char c;
		for(int i = 0;i<len;i++){
			c = s.charAt(i);
			switch(c){
				case  '(':
				stack.add(c);
				break;
				case  '[':
				stack.add(c);
				break;
				case '{':
				stack.add(c);
				break;
				case  ')':{
					if(stack.isEmpty()){
						return false;
					}else{
						if(stack.pop()!='(')
							return false;
					}
					break;
				}
				case  ']':{
					if(stack.isEmpty()){
						return false;
					}else{
						if(stack.pop()!='[')
							return false;
					}
					break;
				}
				case  '}':{
					if(stack.isEmpty()){
						return false;
					}else{
						if(stack.pop()!='{')
							return false;
					}
					break;
				}
			}
		}
//		注意当经过上面的处理，stack一定是空的，否则，就是还有未出栈的左括号，则一定为false
		if(stack.isEmpty())
			return true;  
		else
			return false;
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
