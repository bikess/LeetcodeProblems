package com.problems2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/***
 * 问题描述
 * 给一个字符串，和一个字典，看这个字符串是否能够分解为有字典中的一个或多个单词组成的
 * @author bike
 *
 */
public class WordBreak {

	/**
	 * @param args

	 */
/**
 * 解题思路，采用动态规划DP的方式，当遍历的时候存储后
 * @param s
 * @param dict
 * @return
 */
	/***
	 * substring(参数)是java中截取字符串的一个方法
	有两种传参方式
	一种是public String substring(int beginIndex)
	返回一个新的字符串，它是此字符串的一个子字符串。该子字符串从指定索引处的字符开始，直到此字符串末尾。
	另一种是public String substring(int beginIndex, int endIndex)
	返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的 beginIndex 处开始，直到索引 endIndex - 1 处的字符。
	因此，该子字符串的长度为 endIndex-beginIndex。
	 * @param s
	 * @param dict
	 * @return
	 */
    public boolean wordBreak(String s, Set<String> dict) {
    	if(s.length()<1)
    		return true;
    	int len = s.length();
//    	store 中暂存未被在字典中匹配的字符串
    	Set<String> store = new HashSet<>();
		return wordBreakHelper(s,dict,store); 
    }
    public boolean wordBreak2(String s, Set<String> dict) {
    	int n  = s.length();
    	Vector<Boolean> dp = new Vector<>(n+1);
    	dp.set(0, true);
    	for(int i =1;i<=n;i++){
    		if(dp.get(i-1)==true){
    			int idx = i-1;
    			for(int j = idx;j<n;j++){
    				String cur = s.substring(idx,j-idx+1);
    				if(dict.contains(cur)){
    					dp.set(j+1, true);
    				}
    			}
    		}
    	}
    	return dp.get(n);
    }
	private boolean wordBreakHelper(String s, Set<String> dict, Set<String> store) {
		if(s.length()<1) return true;
		boolean flag = false;
		for(int i =1;i<=s.length();i++){
//			截取0到i的字符串
			String str = s.substring(0, i);
//			判断在字典中是否存在这个字符串，若存在，则继续验证后面的字符串是否在字典中
			if(dict.contains(str)){
				String last_str  = s.substring(i);
//				判断后面的后面字符串是否已经暂存
				if(store.contains(last_str))
					continue;
				else{
					flag = wordBreakHelper(last_str, dict, store);
					if(flag==true)
						return true;
					else{
						store.add(last_str);
					}
				}
			}
		}
	return false;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/***
	 * 题目扩展：不但要判断字符串能否分解为字典中的词，还要把所有可能的分解情况输出
	 * For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
	 */
	/**
	 * 思路1：采取上面的思路1的方法，
	 * @param s
	 * @param dict
	 * @return
	 */
    public List<String> wordBreakII(String s, Set<String> dict) {
    	List<String> result = new ArrayList<String>();
		if(dict.size()==0||s.length()<1)
			return result;
		Set<String> store = new HashSet<String>();
    	wordGetBreak(result,store,"",s,dict);
    	return result;
        
    }
	private boolean wordGetBreak(List<String> result, Set<String> store,
			 String inter,String s, Set<String> dict) {
		// TODO Auto-generated method stub
		if(s.length()==0){
			String temp = inter;
			temp = temp.substring(0, temp.length()-1);
			result.add(temp);
			return true;
		}
		boolean flag = false;
		for(int i=1;i<=s.length();i++){
			String str;
			if(i==s.length())
				str = s.substring(0);
			else
				str = s.substring(0, i);
			if(dict.contains(str)){
				String temp = inter + str+" ";
				String leaveStr = s.substring(i);
				if(!store.isEmpty()&&store.contains(leaveStr)){
					continue;
				}else
				{
					if(wordGetBreak(result,store,temp,leaveStr,dict)==true)
					{
						flag = true;
					}else{
						if(!leaveStr.equals(""))
							store.add(leaveStr);
					}
				}
			}else{
				if(!str.equals(""))
					store.add(str);
				continue;
			}
		}
		if(flag==false)
			return false;
		else
			return true;
	}
	
	
	/***
	 * 方法2，利用动态规划的方法求解
	 * 
	 * 设置动态规划数组dp[i][j]保存从i到j的字符串是否存在与字典中
	 */
	 public List<String> wordBreakII2(String s, Set<String> dict) {
	    List<String> result = new ArrayList<String>();
		if(dict.size()==0||s.length()<1)
			return result;
		int len = s.length();
//		dp[i][j]表示从第i个字符（包括第i个）到第j个字符（包括第j个）的字符串是否存在与字典中！！
		boolean dp[][] = new boolean[len][len];
		for(int i=0;i<len;i++){
			for(int j=i;j<len;j++){
//				若从i到j的字符串在字典中，则该数组元素置为true；
				String str;
				if(j+1==len)
					str = s.substring(i);
				else
					str = s.substring(i,j+1);
				if(dict.contains(str)){
					dp[i][j] = true;
				}
			}
		}
//		输出所有的组合
		getCombination(result,"",dp,len-1,s);
		return result;
		 
	 }
//	  一定注意这种利用DFS（深度优先遍历的方法）得当结果的方式
	private void getCombination(List<String> result,String inter, boolean[][] dp, int end,
			String s) {
		if(end==-1){
			String temp = inter.substring(0,inter.length()-1);
			result.add(temp);
			return;
		}
		else{
			for(int i=0;i<=end;i++){
				if(dp[i][end]==true){
					String str;
					if(end+1==s.length())
						str = s.substring(i);
					else
						str = s.substring(i,end+1);
					String temp =str+" " + inter;
					getCombination(result, temp, dp, i-1, s);
				}
			}
		}
	}
}
