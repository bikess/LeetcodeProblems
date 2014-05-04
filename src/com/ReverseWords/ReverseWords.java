package com.ReverseWords;

public class ReverseWords {

	/**
	 * @param s 
	 * @param args
	 * @return 
	 */
//	时间复杂度应该是0（n）
	public static String reverseWords(String s){
		String result = "";
		int len = s.length();
		int i=0;
		int j=len-1;
//		这样从前面找到了第一个不是空格的位置 i
		while(i<len&&s.charAt(i)==' '){
			i++;
		}
//		这样从后面找到了第一个不是空格的位置j
		while(j>=0&&s.charAt(j)==' '){
			j--;
		}
		int k;
		while(i<=j){
//			这里把不是不含空格的一个单词存储起来
//			String substring(begin,end);包含头，不包含尾
			k=i;
			while(i<=j&&s.charAt(i)!=' ')
				i++;
//			这里是保证一个单词时后面没有空格跟着
			if(result.length()==0)
			{
				result = s.substring(k,i);
			}
			else
				result = s.substring(k,i)+" "+result;
//		这里是把句子中间多余的空格屏蔽掉
			while(i<=j&&s.charAt(i)==' ')
				i++;
		}
		if(result.isEmpty()){
			s="";
			return s;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " aaf    bafa   ";
		System.out.println(reverseWords(s));
	}

}
