package com.problems6;

import java.util.HashMap;
import java.util.Map;
/***
*
*题意：求解字符串中的最长无重复子串的长度。
*例如abcd 最长无重复子串为4
*/
public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * 方法1，利用hashmap来存储构成当前子串的字符，已经字符对象的数组中的坐标
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		int len = s.length();
		if(len<=1){
			return len;
		}
		Map<Character,Integer> map = new HashMap<>();
		int maxLength = 0;
		int curLength = 0;
		int start = 0;
		for(int i=0;i<len;i++){
//			若map为空，或者map不存在当前字符 或者 存在当前字符但是目前map中记录的当前字符在字符串中的坐标比当前子串的其实位置start小，则
			if(map.isEmpty()||!map.containsKey(s.charAt(i))||map.get(s.charAt(i))<start){
				map.put(s.charAt(i), i);
				curLength++;
				if(curLength>maxLength){
					maxLength = curLength;
				}
//				发现不满足上面的条件时，更新start的位置，并更新目前子串的长度值
			}else{
				start = map.get(s.charAt(i))+1;
				curLength = i-start+1;
				map.put(s.charAt(i),i);
			}
		}
		return maxLength;
	        
	    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
