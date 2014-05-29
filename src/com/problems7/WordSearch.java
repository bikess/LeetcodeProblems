package com.problems7;

import java.util.HashMap;

/***
 * 题意很简单，给你一个二维字母的数组，可以上下左右走，查找是否某个单词是否存在。同一位置的字母不可以被使用多次。
 * 注意题意是，看一个单词是否在这个二维数组中，某个单词中的某个字符在二维数组的A[i][j]位置上，则看单词的下一个字符是否在的时候，该单词的位置必须是 A[i][j+1]
 * 或者A[i][j-1]或者A[i-1][j]或者A[i+1][j]
 * 也就是下一个字符必须在上一个数组中匹配字符的上下左右四个位置中的一个
 * For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 * @author bike
 *
 */
public class WordSearch {

	/***
	 * 思路1 递归回溯的方法
	 * 类似于走迷宫的方法，
	 * 用一个辅助数组记录走过的位置
	 * @param board
	 * @param word
	 * @return
	 */
	
	public boolean exist1(char[][] board, String word) {
		if(word.length()==0){
			return false;
		}
		int m = board.length;
		int n =board[0].length;
//		设置辅助数组，记录当前数组的位置是否被访问过了
		boolean visited[][] = new boolean[m][n];
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(word.charAt(0)==board[i][j]){
					visited[i][j]=true;
//					递归判断下一个字符是否匹配
					if(word.length()==1||search_DFS(board,word.substring(1),i,j,visited)){
						return true;
					}
					visited[i][j]=false;
				}
			}
		}
		return false;
		
	}
	
	
	private boolean search_DFS(char[][] board, String word, int i, int j,
			boolean[][] visited) {
		// TODO Auto-generated method stub
		
		if(word.length()==0){
			return true;
		}
//		判断i，j位置的字符的四个方向是否存在与word下一个字符相等的字符， 这里仍然是递归回溯的方法,注意判断i+1，i-1,j+1,j-1的值不要越界
		if(i<board.length-1){
			if(visited[i+1][j]==false&&word.charAt(0)==board[i+1][j]){
				visited[i+1][j]=true;
				if(search_DFS(board, word.substring(1), i+1, j, visited))
					return true;
				visited[i+1][j]=false;
			}
		}
		if(i>0){
			if(visited[i-1][j]==false&&word.charAt(0)==board[i-1][j]){
				visited[i-1][j]=true;
				if(search_DFS(board, word.substring(1), i-1, j, visited))
					return true;
				visited[i-1][j]=false;
			}
		}
		if(j<board[0].length-1){
			if(visited[i][j+1]==false&&word.charAt(0)==board[i][j+1]){
				visited[i][j+1]=true;
				if(search_DFS(board, word.substring(1), i, j+1, visited))
					return true;
				visited[i][j+1]=false;
			}
		}
		if(j>0){
			if(visited[i][j-1]==false&&word.charAt(0)==board[i][j-1]){
				visited[i][j-1]=true;
				if(search_DFS(board, word.substring(1), i, j-1, visited))
					return true;
				visited[i][j-1]=false;
			}
		}
		return false;
	}
	/***
	 * 前面的递归代码重复冗余太多，为了简化代码，递归匹配的这个方法我们可以下面的方法写
	 * @param board
	 * @param word
	 * @param i
	 * @param j
	 * @param visited
	 * @return
	 */
	private boolean search_DFS2(char[][] board, String word, int i, int j,
			boolean[][] visited) {
		if(word.length()==0){
			return true;
		}
//		定义四个方法的数组，依次判断这四个方法，每个方向的判断也是递归回溯的方法
		int direction[][]={{-1,0},{1,0},{0,-1},{0,1}};
		for(int k = 0;k<4;k++){
			int plaX = i+direction[k][0];
			int plaY = j+direction[k][1];
			if(plaX>=0&&plaX<board.length&&plaY>=0&&plaY<board[0].length&&visited[plaX][plaY]==false){
				if(word.charAt(0)==board[plaX][plaY]){
					visited[plaX][plaY]=true;
					if(search_DFS2(board, word.substring(1), plaX, plaY, visited))
						return true;
					visited[plaX][plaY]=false;
				}
			}
		}
		return false;
		
	}

	/***
	 * 思路X：统计board中所有字符的个数和种类，然后用word中的每一个字符去看是否在bord中存在这个字符
	 *   这个思路是错误的因为，你没有了完全理解题意
	 * @param args
	 */
    public boolean existx(char[][] board, String word) {
    	int m = board.length;
    	int n = board[0].length;
    	HashMap<Character, Integer> map = new HashMap<>();
    	for(int i=0;i<m;i++){
    		for(int j=0;j<n;j++){
    			if(map.isEmpty()||!map.containsKey(board[i][j])){
    				map.put(board[i][j], 1);
    			}else{
    				map.put(board[i][j], map.get(board[i][j])+1);
    			}
    		}
    	}
    	for(int i=0;i<word.length();i++){
    		if(!map.containsKey(word.charAt(i))){
    			return false;
    		}else{
    			if(map.get(word.charAt(i))>=1){
    				map.put(word.charAt(i),map.get(word.charAt(i))-1);
    			}else{
    				return false;
    			}
    		}
    	}
		return true;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
