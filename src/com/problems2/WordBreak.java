package com.problems2;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/***
 * 问题描述
 * 
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
		return wortBreakHelper(s,dict,store); 
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
	private boolean wortBreakHelper(String s, Set<String> dict, Set<String> store) {
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
					flag = wortBreakHelper(last_str, dict, store);
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

}
