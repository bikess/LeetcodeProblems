package com.problems6;

import java.util.Stack;

/***
 * 题目意思： 求最长有效匹配括号子串的长度
 * 给一个括号序列，求解满足匹配括号的子串的最大长度
 * @author bike
 *
 */
public class LongestValidParentheses {

	/***
	 * 方法1自己的思路，计算结果不正确，需要进一步修改
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		int len = s.length();
		if(len<=1){
			return 0;
		}
		int maxLength = 0;
		int sub = 0;
		Stack<Character> stack = new Stack<Character>();
//		curlength 记录当前第一个
		int curlength = 0;
		for(int i =0;i<len;i++){
			char c = s.charAt(i);
//			若当前括号为左括号直接入栈
			if(c=='('){	
				stack.add(c);
			}
//			若当前括号为右括号，则
			if(c==')'){
				if(!stack.isEmpty()){
					stack.pop();
					curlength = curlength+2;
				}
//				这时候说明已经一次将栈清空了
				if(stack.isEmpty()){
					sub = sub+curlength;
					maxLength = Math.max(maxLength, sub);
					curlength=0;
//					栈没有被清空
				}else{
					if(i+1<len){
						if(s.charAt(i+1)==')'){
//							继续判断下一个字符	
//							若下一个字符不再是)
						}else{
							sub = curlength;
							maxLength = Math.max(maxLength, curlength);
							curlength = 0;
						}
					}else{
						maxLength = Math.max(maxLength, curlength);
					}
				}
			}
		}
		return maxLength;
	        
	    
	}
	/***
	 * 
	 * 思路2 ：设置matched数组记录每一位是否匹配成功，设置一个辅助数组，记录所有匹配的括号对应的位置为1；
	 * 记录完成后，连续的1的个数的最大值即为最长的匹配长度
	 * @param args
	 */
	public int longestValidParentheses2(String s) {
		int len = s.length();
//		设置一个辅助数组，用于记录所有能够匹配的括号对应的位置
		int []matched = new int[len];
		for(int i=0;i<len;i++){
//			当前的为右半括号，则在字符串中寻找与此括号匹配的括号，方法就是寻找前面的遇到的第一个match【i】不是1的位置，而且值为左半括号
			if(s.charAt(i)==')'){
				int j = i-1;
				while(j>=0&&!(matched[j]==0&&s.charAt(j)=='(')){
					j--;
				}
				if(j>=0){
					matched[i]=matched[j]=1;
				}
			}
		}
//		最后寻找连续1的最大个数就可以
		int maxlength=0;
		int tem = 0;
		for(int i=0;i<matched.length;i++){
			if(matched[i]==1){
				tem++;
			}else{
				maxlength = Math.max(maxlength, tem);
				tem=0;
			}
		}
		maxlength = Math.max(maxlength, tem);
		return maxlength;
	}
	/***
	 * 
	 * 方法3 ，利用栈，是对自己的方法的修改，但是是别人的思路
	 * @param args
	 */
	public int longestValidParentheses3(String s){
//		栈中保存的上一次左半括号的i的位置
		Stack<Integer> stack = new Stack<>();
//		此变量记录，连续匹配子串开始的地方
		int lastleft = 0;
//		变量记录连续匹配子串的最大值
		int longest = 0;
		for(int i=0;i<s.length();i++){
//			若是左半括号，则直接将当前的位置入栈
			if(s.charAt(i)=='('){
				stack.add(i);
//				是右括号
			}else{
//				栈中存在左括号，则出栈首先
				if(!stack.isEmpty()){
					stack.pop();
//					出来一个左括号后，则当前栈不为空，则返回当前最大子串长度，与目前栈两个位置的差
					if(!stack.isEmpty()){
						longest = Math.max(longest,i-stack.peek());
//					出来一个左括号后，则当前栈为空，则返回当前最大子串长度，与上一次连续子串开始位置与当前位置的差	
					}else{
						longest = Math.max(longest, i-lastleft+1);
					}
				}else{
					lastleft = i+1;
				}
			}
		}
		return longest;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestValidParentheses l = new LongestValidParentheses();
		System.out.println(l.longestValidParentheses3("(()(((()()"));
	}

}
