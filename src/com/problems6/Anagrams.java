package com.problems6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 题意 ：回文构词法,给一系列的字符串，返回所有的满足回文构词的字符串。
 * 回文字符串是指颠倒字母顺序组成的单词，也就是两个字符串构词的所有的字符
 * 是相同的，只是字符的排列顺序不同，此时两个字符串就是回文串
 * 例如：
 * Input:　　["tea","and","ate","eat","den"]
   Output:   ["tea","ate","eat"]
   再比如“dormitory”颠倒字母顺序可以变成“dirty room”，“tea”可以变成“eat”。
 * @author bike
 *
 */
public class Anagrams {
/***
 * 
 * 思路1：由于回文构词的两个字符串中，字符的种类和数目没有改变
 * 只是改变了字符的排列顺序。
 * 所以可以把字符串中所有字符按照顺序进行排序，然后就可以判断他们是否为回文串了
 * 
 * @param strs
 * @return
 */
	public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> result = new ArrayList<>();
		if(strs.length<=1)
			return result;
//		构造hashmap注意hashmap《key，value》的值，其中key的值是按照排序后的字符串作为键值，而value的值是此字符串在原来输入的字符数组中的位置
//		这种hashMap，key保存修改后的值作为key，而原来数组中的位置作为value的思路，学习要！！！！！！！！！！！！！！
		Map<String,Integer> anagram = new HashMap<>();
		for(int i=0;i<strs.length;i++){
//			得到一个字符串，将字符串的字符按照顺序进行排序
			char m[] = strs[i].toCharArray();
			Arrays.sort(m);
//	得到排序以后的字符串
			String tem = String.valueOf(m);
//			判断此字符串是否在HashMap中，没有则插入这个字符串
			if(anagram.isEmpty()||!anagram.containsKey(tem)){
				anagram.put(tem, i);
			}else{
//				若存在这个字符串的回文串，并且此回文串没有输出过
				if(anagram.get(tem)>=0){
					result.add(strs[anagram.get(tem)]);
					anagram.put(tem, -1);
				}
//				输出当前的回文串
				result.add(strs[i]);
			}
		}
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
