package com.problems2;

import java.util.ArrayList;

public class PalindromePartitioning {

	/**
	 * @param args
	 */
	/***
	 * 思路一： 将字符串分成任意个Part，只要保证每一个Part是
	 * Palindrome就可以
	 * @param args
	 */
	public ArrayList<ArrayList<String>> partition (String s){
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> tmp = new ArrayList<String>();
		dfs(res, tmp, s);
		return res;
		
	}
	private void dfs(ArrayList<ArrayList<String>> res, ArrayList<String> tmp,
			String s) {
		// TODO Auto-generated method stub
//		若字符串的长度为0，把目前的tmp中保存的分割的回文串，加入到回文串队列中去
		if(s.length()==0)
			res.add(tmp);
		for(int i=1;i<s.length();i++){
//		取从0到i的字符串的子串substr
			String substr = s.substring(0,i);
//			判断子串substr是否为回文串
			if(isPalindrome(substr)){
//				若此部分是回文串，把此部分加入保存某一次分割的数组tmp中
				tmp.add(substr);
//				若从0到i为回文串，则递归判断i往后的字符串是否为回文串
				dfs(res,tmp,s.substring(i));
//				把tmp清空
				tmp.remove(tmp.size()-1);
			}
		}
	}
//	判断是否为回文串的函数
//	注意如何判断什么是回文串，第i与第substr.length()-1比较，且i++，j--
	private boolean isPalindrome(String substr) {
		// TODO Auto-generated method stub
		int i =0;
		int j = substr.length()-1;
		while(i<j){
			if(substr.charAt(i++)!=substr.charAt(j--))
				return false;
		}
		return true;
	}
/***
 * 思路2：利用动态规划的方法进行求解，考虑到验证某个字符串中哪些子字符串是回文串，存在
 * 很多重复的子问题的判断，因此这是个动态规划的DP问题，可以利用动态规划的方法来求解
 * 创建一个二维数组，用户保存从某个字符开始到某个字符结束是否为回文串，例如T[1][4]表示从第1个
 * 字符到第4个字符的子字符串是否为回文串
 * @param args
 */
	public ArrayList<ArrayList<String>> partition2(String s){
		ArrayList<ArrayList<String>> res =new ArrayList<ArrayList<String>>();
		ArrayList<String> tmp = new ArrayList<String>();
		dfs2(res,tmp,s,table(s),0);
		return res;
	}
	private void dfs2(ArrayList<ArrayList<String>> res, ArrayList<String> tmp,
		String s, boolean[][] t, int pos) {
	// TODO Auto-generated method stub
		if(pos == s.length()) 
			res.add(new ArrayList<String>(tmp));
		for(int i=pos;i<s.length();i++){
			if(t[pos][i]){
				tmp.add(s.substring(pos,i+1));
				dfs2(res,tmp,s,t,i+1);
				tmp.remove(tmp.size()-1);
			}
		}
}
//	此方法就是建立辅助数组的方法，辅助数组用于构造某个子串是否为回文串
	private boolean[][] table(String s) {
	// TODO Auto-generated method stub
		boolean[][] T = new boolean[s.length()][s.length()];
		for(int i=0;i<s.length();i++){
//			数组的每一个对角线肯定是true的，因为单独的一个字符肯定是回文串
			T[i][i] = true;
		}
		for(int i=0;i<s.length();i++){
			int l = i-1;
			int r = i;
//			此处是判断若第k位置上元素等于第k+1个位置是元素，则递归判断在左边再增加一个元素
//			右边再增加一个元素，若两个元素相同，则此字符串也是回文串
			while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
				T[l][r] = true;
				l--;
				r++;
			}
			l = i -1;
			r = r+1;
//			此处是判断子某个元素的左右两边各增加一个元素，是否为回文串，而且递归判断
			while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
				T[l][r] = true;
				l--;
				r++;
			}
		}
		return T;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/***
	 * 
	 * 题目扩展：返回某个字符串的回文串的最小切割数
	 */
	public int minCut(String s){
		int len  = s.length();
//		构造回文串判断的辅助数组
		boolean[][] T = createTable(s);
//		数组cut中记录切割最小值，cut[i]表示的从i位置开始到字符串结尾的最小的切割次数
		int[] cut = new int[len+1];
//		第len个切割次数为0；
		cut[len]=0;
		for(int i=len-1;i>=0;i--){
//			cut[i] 初始化为len-i，表示为切割为一个一个的单个字符串
			cut[i] = len - i;
			for(int j=i;j<len;j++){
//				若T[i][j]是回文串，也就是从i字符串到j字符串是回文串，则从i位置到字符串结尾切割为回文串的最小次数是目前的从0到i切割，与从j+1切割加1的最小值
				if(T[i][j]){
					cut[i] = Math.min(cut[i],1+cut[j+1]);
				}
			}
		}
		return cut[0]-1;
		
	}
//	构造字符串的辅助数组，用于判断从i字符到j字符是否为回文串
	public boolean[][] createTable(String s){
		boolean[][] T = new boolean[s.length()][s.length()];
		for(int i =0;i<s.length();i++){
			T[i][i] = true;
		}
		for(int i=0;i<s.length();i++){
			int l = i-1;
			int r = i;
			while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
				T[l][r] = true;
				l--;
				r++;
			}
			l = i-1;
			r = i+1;
			while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
				T[l][r] = true;
				l--;
				r++;
			}
		}
		return T;
}
}