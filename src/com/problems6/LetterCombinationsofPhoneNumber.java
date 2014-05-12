package com.problems6;
/***
 * 
 * 题目的意思是：针对手机键盘的数字与字母表，输入一串数字，返回数字所代表的所有的字母组合
 * 例如：
 * Input:Digit string "23"
   Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
import java.util.ArrayList;
import java.util.HashMap;

public class LetterCombinationsofPhoneNumber {

	
	/**
	 * 
	 * 思路采取深度遍历的方式
	 * @param digits
	 * @return
	 */
	public HashMap<Integer, ArrayList<Character>> dict = new HashMap<>();
    public ArrayList<String> letterCombinations(String digits) {
    	createDict();
    	ArrayList<String> result = new ArrayList<>();
    	if(digits.length()<=0){
    	    result.add("");
    		return result;
    	}
    	String intermediate = "";
    	dfs(result,0,intermediate,digits);
    	return result;
        
    }
	private void dfs(ArrayList<String> result, int start, String intermediate,String digits) {
		// TODO Auto-generated method stub
		if(start==digits.length()){
			result.add(intermediate);
			return;
		}
		int c = digits.charAt(start)-'0';
		
		for(int i =0;i<dict.get(c).size();i++){
		    String temp = intermediate;
			temp = temp+dict.get(c).get(i);
			dfs(result, start+1, temp, digits);
		}
	}
	private void createDict() {
		// TODO Auto-generated method stub
		
		ArrayList<Character> list = new ArrayList<>();
		list.add('a');
		list.add('b');
		list.add('c');
		dict.put(2, (ArrayList<Character>) list.clone());
		list.clear();
		list.add('d');
		list.add('e');
		list.add('f');
		dict.put(3, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('g');
		list.add('h');
		list.add('i');
		dict.put(4, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('j');
		list.add('k');
		list.add('l');
		dict.put(5, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('m');
		list.add('n');
		list.add('o');
		dict.put(6, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('p');
		list.add('q');
		list.add('r');
		list.add('s');
		dict.put(7, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('t');
		list.add('u');
		list.add('v');
		dict.put(8, (ArrayList<Character>)list.clone());
		list.clear();
		list.add('w');
		list.add('x');
		list.add('y');
		list.add('z');
		dict.put(9, (ArrayList<Character>)list.clone());
		list.clear();
	}
	
	
	/***
	 * 思路同上只不过不需要这样创建字母表
	 * @param args
	 */
	public static  final String [] strD = {"","","abc","def","ghi","jkl","mno","qprs","tuv","wxyz"};
	public ArrayList<String> letterCombinations2(String digits) {
		String intermediate = "";
		ArrayList<String> result = new ArrayList<>();
		
		dfs2(result,intermediate,0,digits);
		return result;
		
	}
	private void dfs2(ArrayList<String> result, String intermediate, int index,
			String digits) {
		if(index==digits.length()){
			result.add(intermediate);
			return ;
		}
		int num = digits.charAt(index)-'0';
		for(int i=0;i<strD[num].length();i++){
			String now = intermediate+strD[num].charAt(i);
			dfs2(result, now, index+1, digits);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LetterCombinationsofPhoneNumber l = new LetterCombinationsofPhoneNumber();
		l.letterCombinations("23");
	}

}
