package com.problems5;

import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class SpiralMatrix {

	/**
	 * 螺旋遍历一个mxn的矩阵矩阵
	 * @param args
	 */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
    	 int m = matrix.length;
    	 ArrayList<Integer> result = new ArrayList<>();
    	    if(m==0){
 			return result;
 		}
		int n = matrix[0].length;
//		四个变量分别表示行起始，行末，列起始，列末
    	getOrder(result,matrix,0,0,m-1,n-1);
    	return result;
	        
	}
	private void getOrder(ArrayList<Integer> result,int[][] matrix, int rStart, int lStart, int rEnd,
			int lEnd) {
		// TODO Auto-generated method stub
		if(rStart>rEnd||lStart>lEnd){
			return ;
		}
		else{
			for(int i = lStart;i<=lEnd;i++){
				result.add(matrix[rStart][i]);
			}
			for(int i = rStart+1;i<=rEnd;i++){
				result.add(matrix[i][lEnd]);
			}
			for(int i = lEnd-1;i>=lStart;i--){
				if(rEnd == rStart)
					break;
				result.add(matrix[rEnd][i]);
			}
			for(int i = rEnd-1;i>rStart;i--){
				if(lEnd==lStart)
					break;
				result.add(matrix[i][lStart]);
			}
			getOrder(result, matrix, rStart+1, lStart+1, rEnd-1, lEnd-1);
		}
	}
	/***
	 * 方法2 ：不用递归也可以，就是循环减1判断也可以的
	 * @param args
	 */
	public ArrayList<Integer> spiralOrder2(int[][] matrix) {
		int m = matrix.length;
    	ArrayList<Integer> result = new ArrayList<>();
    	if(m==0){
 			return result;
 		}
		int n = matrix[0].length;
		int rs = 0,ls = 0,re = m-1,le = n-1;
		while(rs<=re&&ls<=le){
			for(int i = ls;i<=le;i++){
				result.add(matrix[rs][i]);
			}
			for(int i = rs+1;i<=re;i++){
				result.add(matrix[i][le]);
			}
			for(int i= le-1;i>=ls;i--){
				if(re==rs)
					break;
				result.add(matrix[re][i]);
			}
			for(int i=re-1;i>=rs-1;i--){
				if(le==ls)
					break;
				result.add(matrix[i][ls]);
			}
			rs++;re--;
			ls++;le--;
		}
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SpiralMatrix s = new SpiralMatrix();
//		int matrix [] [] = {
//				{1,2,3},
//				{1,2,3},
//				{1,2,3}
//		};
//		System.out.println(s.spiralOrder(matrix).toString());
		SpiralMatrix m = new SpiralMatrix();
		int n = 5;
		int result[][] = m.generateMatrix(n);
		for(int i =0;i<n;i++)
		{	for(int j=0;j<n;j++)
				System.out.print(result[i][j]+"\t");
			System.out.println();
		}
		
	}
	/***
	 * 题目扩展:Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
	 * 
	 * 方法1 采取的是宽带优先遍历
	 */
	public int[][] generateMatrix(int n) {
		int result [][] = new int[n][n];
		if(n==0){
			return result;
		}
		int e = 0,s = n-1;
		int num = 1;
		while(e<=s){
			for(int i = e;i<=s;i++){
				result[e][i] = num++;
			}
			if(e==s)
				break;
			for(int i = e+1;i<=s;i++){
				result[i][s] = num++;
			}
			for(int i = s-1;i>=e;i--){
				result[s][i] = num++;
			}
			for(int i = s-1;i>=e+1;i--){
				result[i][e] = num++;
			}
			e++;s--;
		}
		return result;
        
    }
}
