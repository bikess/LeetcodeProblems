package com.problems5;

public class SetMatrixZeroes {

	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 * 给一个mxn的矩阵，若某个元素为0，则把它的整行和整列置为0，原地完成这个任务
	 */
	/**
	 * @param args
	 */
	/***
	 * 方法1:此种思路是错误的
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        setZero(0,0,m-1,n-1,matrix);
	}
	private void setZero(int rs, int ls, int r, int l, int[][] matrix) {
		// TODO Auto-generated method stub
		if(rs>r||ls>l){
			return;
		}
		for(int i=rs;i<=r;i++){
			for(int j=ls;j<=l;j++){
				if(matrix[i][j]==0){
//					将此元素所在的行与列全部置为0
					for(int m = ls;m<=l;m++)
						matrix[i][m]=0;
					for(int m = rs;m<=r;m++)
						matrix[m][j] = 0;
//					递归对此元素的左下方矩阵，以及此元素的右下方矩阵进行操作
					setZero(i+1, ls, r, j-1, matrix);
					setZero(i+1, j+1, r, l, matrix);
				}
			}
		}
	}
	/***
	 * 方法2 每一次将每一个元素置为0，采取存储空间的方法,空间复杂度0（mXn）
	 * @param args
	 */
	public void setZeroes2(int[][] matrix) {
		int m = matrix.length;
        int n = matrix[0].length;
        int temp [][]= new int[m][n];
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		temp[i][j]=matrix[i][j];
        	}
        }
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(temp[i][j]==0){
        			for(int k = 0;k<n;k++)
						matrix[i][k] = 0;
					for(int k = 0;k<m;k++)
						matrix[k][j] = 0;
        		}
        	}
        }
	}
	
	/***
	 * 方法3，设置两个向量，行向量中每个值记录当前行中是否有0，列向量中每个值记录当前列中是否有0
	 * 空间复杂度为0（m+n)
	 * @param args
	 */
	public void setZeroes3(int[][] matrix) {
		int m = matrix.length;
        int n = matrix[0].length;
        boolean zeroR[]=new boolean[m];
        boolean zeroL[]=new boolean[n];
//      先遍历一遍数组，记录当前行或者列中是否存在0
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(matrix[i][j]==0){
        			zeroR[i]=true;
        			zeroL[j]=true;
        		}
        	}
        }
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(zeroR[i]||zeroL[j]){
        			matrix[i][j]=0;
        		}
        	}
        }
	}
	
	/***
	 * 方法3，常量的空间复杂度
	 
	 思路：使用第一行第一列作为"额外空间", 有一个"重叠"的思想. 
	 （1）首先先遍历一遍第一行与第一列，用两个变量记录第一行与第一列是否存在为0的元素
	 （2）然后遍历整个数组，若数组中某个元素a[i][j]==0;则把a[i][0]与a[0][j]置为0
	 （3）然后根据第一列中每个元素是否为0，若为0把所在的行所有元素置为0；根据第一行中每个元素是否为0，若为0把所在的列所有元素置为0
	 （4）最后根据两个变量的记录，把第一行与第一列进行相应的处理
	 * @param args
	 */
	public void setZeroes4(int[][] matrix) {
		int row0 = 1,column0 = 1;
		int m = matrix.length;
		int n = matrix[0].length;
		
		//此循环在第一列中寻找是否存在0的数
		for(int i=0;i<n;i++){
			if(matrix[0][i]==0){
				row0 = 0;
				break;
			}
		}
		//此循环在第一行中寻找是否存在0的数
		for(int j=0;j<m;j++){
			if(matrix[j][0]==0){
				column0 = 0;
				break;
			}
		}
		//此循环遍历一遍数组，将为0的数所在行所在列的，第一个数置为0；
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				if(matrix[i][j]==0){
					matrix[i][0]=0;
					matrix[0][j]=0;
				}
			}
		}
		//根据目前第一行的置0情况，对相应的列进行处理
		for(int i=1;i<n;i++){
			if(matrix[0][i]==0){
				for(int j=1;j<m;j++){
					matrix[j][i]=0;
				}
			}
		}
		//根据目前第一列的置0情况，对相应的行进行处理
		for(int i=1;i<m;i++){
			if(matrix[i][0]==0){
				for(int j=1;j<n;j++){
					matrix[i][j]=0;
				}
			}
		}
		//根据row0的值对第一列进行置0处理
		if(row0==0){
			for(int i=0;i<n;i++){
				matrix[0][i]=0;
			}
		}
		//根据culumn0的值对第行列进行置0处理
		if(column0==0){
			for(int i=0;i<m;i++){
				matrix[i][0]=0;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
