package com.problems7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/***
 * 题目的意思：You are given a string, S, and a list of words, L, that are all of the same length.
 *  Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and
 *   without any intervening characters.
	For example, given:
	S: "barfoothefoobarman"
	L: ["foo", "bar"]
	给一个字符串，和一个单词表，单词表中的每一个单词都是长度相同的字符串，要求在字符串中找出其实字符的索引，是的该索引以后的字符串
	能够无间隔的包含单词表中的每个单词，而且每个单词只能出现一次
 * @author bike
 *
 */
public class SubstringwithConcatenationofAllWords {

	/**
	 * @param args
	 * 思路一：hashtap+统计的思路，首先用hashtap统计单词表中的单词，以及每个单词出现的个数
	 * 然后，在某一次判断的循环中，判断这个范围的字符是否满足这些单词
	 */
    public List<Integer> findSubstring(String S, String[] L) {
    	List<Integer> result = new ArrayList<Integer>();
		if(L.length==0){
			return result;
		}
		int wordnum = L.length;
		int wordlen = L[0].length();
    	Map<String,Integer> wordcount = new HashMap<>();
//    	统计单词表中的所有单词，以及每个单词对应的个数
    	for(int i=0;i<wordnum;i++){
    		if(wordcount.isEmpty()||!wordcount.containsKey(L[i])){
    			wordcount.put(L[i], 1);
    		}else{
    			wordcount.put(L[i], wordcount.get(L[i])+1);
    		}
    	}
//    	初始化一个统计的单词的副本
    	Map<String,Integer> p = new HashMap<>(wordcount);
    	boolean issuccess = true;
    	for(int i=0;i<=S.length()-wordlen*wordnum;i++){
//    		判断从i到 i+j*wordlen-1这个字符串是否包含所有的单词
    		for(int j=1;j<=wordnum;j++){
    			String word = "";
    			if(i+j*wordlen<S.length())
    				 word = S.substring(i+(j-1)*wordlen, i+j*wordlen);
    			else
    				 word = S.substring(i+(j-1)*wordlen);
    			if(!p.isEmpty()&&p.containsKey(word)&&p.get(word)>0){
    				p.put(word, p.get(word)-1);
    			}else{
    				issuccess = false;
    				break;
    			}
    		}
    		if(issuccess==true)
    			result.add(i);
    		issuccess = true;
    		p = new HashMap<>(wordcount);
    	}
    	return result;
        
    }
    
    /***
     * 思路2 采取类似的滑动窗口的方式进行判断
     * 思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。
     * 假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。
     * 做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串。
     * 因为每次扫描的时间复杂度是O(2*n/l)(每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，总共扫描l次（i=0, ..., l-1)，
     * 所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量
     * @param args
     */
    public List<Integer> findSubstring2(String S, String[] L) {
    	List<Integer> result = new ArrayList<Integer>();
		if(L.length==0){
			return result;
		}
		int wordnum = L.length;
		int wordlen = L[0].length();
    	Map<String,Integer> wordcount = new HashMap<>();
//    	统计单词表中的所有单词，以及每个单词对应的个数
    	for(int i=0;i<wordnum;i++){
    		if(wordcount.isEmpty()||!wordcount.containsKey(L[i])){
    			wordcount.put(L[i], 1);
    		}else{
    			wordcount.put(L[i], wordcount.get(L[i])+1);
    		}
    	}
//    	第一重循环，是循环判断下标从 0,1...wordlen-1 开始的，长度为wordlen的所有单词
    	for(int i=0;i<wordlen;i++){
    		HashMap<String, Integer> curMap = new HashMap<>();
//    		记录当前得到的单词数
    		int count = 0;
//    		表示左边下标开始的位置，开始的时候，位置为i,下一个开始位置变成了i+wordlen.......
    		int left = i;
    		for(int j=i;j<=S.length()-wordlen;j=j+wordlen){
    			String str ="";
    			if(j+wordlen<S.length())
    				str = S.substring(j,j+wordlen);
    			else
    				str = S.substring(j);
//    			当该字符串存在与单词表中的时候
    			if(wordcount.containsKey(str)){
    				if(curMap.containsKey(str))
    					curMap.put(str, curMap.get(str)+1);
    				else
    					curMap.put(str, 1);
    				if(curMap.get(str)<=wordcount.get(str))
    					count++;
//    				当出现在目前窗口范围内，某单词的个数比应该具有的个数多的时候，左端的窗口需要左移
    				else{
//    					窗口左移，直到该单词的个数等于其应该具有的个数
    					while(curMap.get(str)>wordcount.get(str)){
    						String temp= "";
    						if(left+wordlen<S.length())
    							temp = S.substring(left, left+wordlen);
    						else
    							temp = S.substring(left);
    						curMap.put(temp, curMap.get(temp)-1);
    						if(curMap.get(temp)<wordcount.get(temp))
    							count--;
    						left = left + wordlen;
    					}
    				}
//    				此时判断当前统计的单词数是否等于应该具有的单词数
    				if(count==wordnum){
//    					若相等则，找到了一个index，加入结果集
    					result.add(left);
//    					这时候把窗口的左边界右移一个单词，然后继续从移动后的位置，寻找符号条件的index
    					String temp = S.substring(left, left+wordlen);
    					curMap.put(temp, curMap.get(temp)-1);
    					count--;
    					left = left+wordlen;
    				}
//    				不存在的话，直接，将left置为当前位置的下一个位置
    			}else{
    				curMap.clear();
    				count = 0;
    				left = j + wordlen;
    			}
    		}
    	}
		return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
