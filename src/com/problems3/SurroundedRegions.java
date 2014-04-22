package com.problems3;

public class SurroundedRegions {


	public void solve(char[][] board) {
		int n = board.length;
		if(n==0)
			return ;
		for(int i =1;i<n-1;i++){
			dfs(board,i,0);
			dfs(board,i,n-1);
		}
	}

	private void dfs(char[][] board, int i, int j) {
		// TODO Auto-generated method stub
		if(i<0||i>board.length-1||j<0||j>board.length-1)
			return;
		if(board[i][j]=='O'){
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
