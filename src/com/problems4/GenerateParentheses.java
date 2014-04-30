package com.problems4;

import java.util.ArrayList;

public class GenerateParentheses {

	/***
	 * 此题思路，其实就是构造括号对的过程，就是进栈，出栈过程的变化。
	 * 
	 * 方法1：http://www.cnblogs.com/codingmylife/archive/2012/09/09/2677428.html
	 * 递归出栈与进栈
	 */
    public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<>();
		if(n<=0)
		{
			return result;
		}
//    	传递两个参数，in表示还未进栈的个数，out表示还未进栈的个数
    	return unguarded_generate(result, "", n, n);
        
    }
	private ArrayList<String> unguarded_generate(ArrayList<String> result,
			String string, int in, int out) {
		// TODO Auto-generated method stub
//		当所有元素已经进栈，而且所有元素已经进栈后出栈了，则得到一个进出栈序列
		if(in==0&&out==0){
			result.add(string);
		}
		else{
//			当还有元素没有进栈的时候，则递归调用元素进栈，未进栈的元素减1，同时输出左半括号。
			if(in!=0){
				unguarded_generate(result, string+"(", in-1, out);
			}
//			当还没有进栈元素的小于还未出栈元素时，表示需要出栈一个元素，此时out-1，同时输出右半括号
			if(in<out&&out!=0){
				unguarded_generate(result, string+")", in, out-1);
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		GenerateParentheses m = new GenerateParentheses();
		System.out.println(m.generateParenthesis(n).toString());
		System.out.println(m.generateParenthesis(n).size());
	}
	/**
	 * 
	 * 方法2 思路http://blog.csdn.net/pickless/article/details/9141935
	 */
	public ArrayList<String> generateParenthesis2(int n) {
		ArrayList<String> result = new ArrayList<>();
		
		if(n<=0){
			return result;
		}
//		这里设置两个变量，in表示进栈的个数，out表示出栈的个数
		digui_generate(n,result,"",0,0);
		return result;
	}
	private void digui_generate(int n, ArrayList<String> result,
			String string, int in, int out) {
		// TODO Auto-generated method stub
//		当进栈的个数小于需要出栈的个数时候，终止
		if(in<out){
			return ;
		}
//		当进栈个数+出栈个数等于n的2倍的时候，此时一次进出栈的过程完成了。
		if(in+out==2*n){
//			当in==out表示恰好完成这个过程
			if(in==out){
				result.add(string);
			}
			return;
		}
//		递归调用让一个左半括号进栈
		digui_generate(n, result, string+"(", in+1, out);
//		递归调用让一个右半括号出栈
		digui_generate(n, result, string+")", in, out+1);
	}
}
