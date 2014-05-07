package com.problems5;
/***
 * 
 * 题目的意思：
 * 写一个函数找出一组字符串的最长的公共前缀
 * @author bike
 *
 */
public class LongestCommonPrefix {

	/**8
	 * 方法1 采取宽带优先遍历，依次用第一个字符串的每一个元素去判断其他字符串
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		String result = "";
		int len = strs.length;
		if(len==0){
			return result;
		}
		for(int i =0;i<strs[0].length();i++){
			char c = strs[0].charAt(i);
			boolean flag = true;
			for(int j=1;j<len;j++){
				if(i==strs[j].length()){
					flag = false;
					break;
				}else{
					if(strs[j].charAt(i)!=c){
						flag = false;
						break;
					}
				}
			}
			if(flag==true){
				result = result+c;
			}else{
				return result;
			}
		}
		return result;
        
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
