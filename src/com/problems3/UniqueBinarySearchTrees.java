package com.problems3;

public class UniqueBinarySearchTrees {

	/**
	 * @param args
	 */
	/***
	 * 采取动态规划的方法，记录当节点数为n的时候，分别有多少中形态
	 * 当n大于3的时候，根节点有n种选择，确定根节点后，分别计算左右子树的可能情况
	 * ，然后相乘就是当前根节点下所有的变形的种类，之后再求和即可！！
	 * @param args
	 */
	public int numTrees(int n){
		if (n == 1)
		     return 1;
        if (n == 2)
	         return 2;
		int[] record = new int[n+1];
		record[0] = 1;
		record[1] = 1;
		record[2] = 2;
		for(int i=3;i<=n;i++){
			int tmp =0;
			for(int k=0;k<i;k++){
				tmp = tmp+record[k]*record[i-k-1];
			}
			record[i] = tmp;
		}
		return record[n];
				
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
