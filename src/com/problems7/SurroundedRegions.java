package com.problems7;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 题目的意思：把一个字符矩阵中，被x包围的所有的o替换成x
 * @author bike
 *
 */
public class SurroundedRegions {

	/****
	 * 深度优先遍历 DFS，但是这种深度遍历，出现递归深度太深了，虽然结果思路正确，但是无法AC！！
	 * 先从矩阵的边缘（外围）开始，若边缘某个位置的值为‘0’，则该值的上下左右的所有邻居若为‘0’，则肯定无法替换成‘x’了
	 * 对这样的位置进行标记‘N’，最后所有为’N‘标记的位置无法被替换，肯定是‘0’，而所有仍然为‘0’标记的位置则被‘x’所替换
	 * 
	 * @param board
	 */
    public void solve(char[][] board) {
        if(board==null||board.length==0)
        	return;
        int m = board.length;
        int n = board[0].length;
        
//      DFS对矩阵的第一行与最后一行进行递归标记
        for(int i=0;i<n;i++){
        	DFS_Mark(board,0,i);
        	DFS_Mark(board, m-1, i);
        }
//      DFS对矩阵的第一列与最后一列进行递归标记
        for(int i=1;i<m;i++){
        	DFS_Mark(board, i, 0);
        	DFS_Mark(board, i, n-1);
        }
        
//      矩阵递归标记完成后，对矩阵中根据标记进行相应的值替换
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(board[i][j]=='D'){
        			board[i][j]='O';
        		}else{
        			if(board[i][j]=='O'){
        				board[i][j]='X';
        			}
        		}
        	}
        }
    }
	private void DFS_Mark(char[][] board, int l, int r) {
		// TODO Auto-generated method stub
//		注意判断条件是不等于O终止，不能以==‘X'作为终止，当矩阵中只包含O的时候，就无法终止递归，陷入无限递归导致栈溢出
		if(l<0||l>=board.length||r<0||r>=board[0].length||board[l][r]!='O')
			return;
//		对边缘是O的元素进行替换标记N
		board[l][r]='N';
		DFS_Mark(board, l-1, r);
		DFS_Mark(board, l+1, r);
		DFS_Mark(board, l, r-1);
		DFS_Mark(board, l, r+1);
		
	}
	/***
	 * 思路2 BFS（宽带优先遍历搜索），采取上面的DFS明显在大结合的时候会超时，
	 * 思路：
	 * （1）从每一个边缘的是’0‘的元素开始，一层一层的扩展搜索遍历，把每一层的肯定为’0‘的元素压入一个辅助队列，然后再从这一层的元素开始扩展
	 * （2）由于采取了宽带优先遍历，因此不会出现递归层次过多，栈溢出的情况
	 * 
	 * @param args
	 */
	class pair{
		int l;
		int r;
		public pair(int l, int r) {
			this.l = l;
			this.r= r;
		}
	}
	public void solve2(char[][] board) {
		 if(board==null||board.length==0)
			 return ;
		 int m = board.length;
		 int n = board[0].length;
		 
//		从第一行和最后一行的元素开始扩展
		 for(int i=0;i<n;i++){
			 if(board[0][i]=='O'){
				 BFS_Mark(board,0,i);
			 }
			 if(m-1>0)
				 if(board[m-1][i]=='O')
				 {
					 BFS_Mark(board, m-1, i);
				 }
		 }
//		 从第一列和最后一列的元素开始进行扩展
		 for(int i=0;i<m;i++){
			 if(board[i][0]=='O'){
				 BFS_Mark(board,i,0);
			 }
			 if(n-1>0)
				 if(board[i][n-1]=='O')
				 {
					 BFS_Mark(board,i,n-1);
				 }
		 }
//		 根据最终标志的字符进行最终的修改
		 for(int i=0;i<m;i++)
			 for(int j=0;j<n;j++)
			 {
				 if(board[i][j]=='O'){
					 board[i][j]='X';
				 }else{
					 if(board[i][j]=='N'){
						 board[i][j]='O';
					 }
				 }
			 }
	}
	private void BFS_Mark(char[][] board, int l, int r) {
		// TODO Auto-generated method stub
		int m = board.length;
		int n = board[0].length;
//		对当前的坐标元素的值进行标记
		board[l][r] = 'N';
		Queue<pair> q = new LinkedList<>();
//		对于队列中如何存放这个坐标，1种方法就是用类定义这个坐标，还可以存储的是 s = l*10+r,然后解析的时候 l = s/10, r= s%10;
		q.offer(new pair(l,r));
		while(!q.isEmpty()){
			pair p = q.poll();
			int x = p.l;
			int y = p.r;
//			下面是针对此位置的元素向下一层进行扩展
			int direction[][] = {{-1,0},{1,0},{0,-1},{0,1}};
			for(int k=0;k<4;k++){
				int xx = x+direction[k][0];
				int yy = y+direction[k][1];
				if(xx>=0&&xx<m&&yy>=0&&yy<n&&board[xx][yy]=='O'){
					board[xx][yy] = 'N';
					q.offer(new pair(xx,yy));
				}
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
