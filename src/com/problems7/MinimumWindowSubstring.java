package com.problems7;

import java.util.HashMap;

/***
 * 题意：给两个字符串，在第一个字符串中找出能够包含第二个字符串的所有字符的最小的子串，要求时间复杂度0（n)
 * 	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".
 * @author bike
 *
 */
public class MinimumWindowSubstring {

	/**
	 * 思路：设置两个指针begin与end，同时设置两个hashmap，needTofind和hasfind,needTofind记录需要寻找的字符串T中的所有字符
	 * 已经字符对应的个数，hasfind则记录字符串T中目前已经找到的字符以及找到的字符对应的个数。并设置一个count变量记录当前已经找到
	 * 的T中的字符，当count=T。length的时候表明已经找到了一个符号条件的窗口
	 * end指针先遍历，当找到一个合法的windows的时候，看begin指针能否向前移动，移动的标准，就是当移动到一个在T中出现的字符时，
	 * 看该字符x，对于的hasfind（x）个数与needTofind（x）的个数，若hasfind（x）> needTofind（x）则begin向前移动;否则，就继续移动end指针.
	 * 每当找到这样一个窗口，判断此窗口是比当前获得的窗口小，是的话替换当前的窗口
	 * @param args
	 */
    public String minWindow(String S, String T) {
    	if(S==null||S.equals("")){
    		return "";
    	}
    	HashMap<Character,Integer> needTofind = new HashMap<>();
    	HashMap<Character, Integer> hasFind = new HashMap<>();
    	int winbegin = -1,winend = S.length();
    	int count = 0;
//    	这里是对需要判断的字符进行统计
    	for(int i=0;i<T.length();i++){
    		if(needTofind.isEmpty()||!needTofind.containsKey(T.charAt(i))){
    			needTofind.put( T.charAt(i), 1);
    			hasFind.put(T.charAt(i), 0);
    		}else{
    			needTofind.put(T.charAt(i), needTofind.get(T.charAt(i))+1);
    		}
    	}
    	for(int begin = 0,end =0;end<S.length();end++){
    		char c = S.charAt(end);
    		if(needTofind.containsKey(c)){
    			hasFind.put(c, hasFind.get(c)+1);
    			if(hasFind.get(c)<=needTofind.get(c)){
    				count++;
    			}
//    			这说明已经找到了满足条件的一个窗口
    			if(count==T.length()){
//    				这是对begin指针移动, 当前begin对应的字符不在needToFind中或者，需要找到字符数小于已经找到的字符数
    				while(!needTofind.containsKey(S.charAt(begin))||needTofind.get(S.charAt(begin))<hasFind.get(S.charAt(begin))){
    					if(needTofind.containsKey(S.charAt(begin))&&needTofind.get(S.charAt(begin))<hasFind.get(S.charAt(begin))){
    						hasFind.put(S.charAt(begin), hasFind.get(S.charAt(begin))-1);
    					}
    					begin++;
    				}
    				if(end-begin<winend-winbegin){
    					winbegin = begin;
    					winend = end;
    				}
    			}
    		}
    	}
//    	若获得的最终窗口的开始索引为-1，表明没有找到这样的串口，直接返回“”
    	if(winbegin == -1)
    		return "";
    	else{
    		return S.substring(winbegin, winend+1);
    	}
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinimumWindowSubstring m = new MinimumWindowSubstring();
		System.out.println(m.minWindow("abc", "a"));
	}

}
