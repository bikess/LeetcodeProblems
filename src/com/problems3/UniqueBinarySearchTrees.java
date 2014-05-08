package com.problems3;

import java.util.ArrayList;

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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; left = null; right = null; }
}

	/****
	 * 
	 * 题目扩展 Unique Binary Search Trees 2.要求不是计算给予一个值，返回这个值可以构造出的二叉树的所有形态
	 * 思路：DPS,深度优先，依次以每一个数作为根节点，将左右两部分分开，分别递归左右两部分.然后把左右区间得到的结果依次作为该根节点的左右子树
	 */
	public ArrayList<TreeNode> generateTrees(int n) {
		
		return getTrees(1,n);
	        
	   
}
	private ArrayList<TreeNode> getTrees(int start,
			int end) {
		// TODO Auto-generated method stub
		ArrayList<TreeNode> result = new ArrayList<>();
		if(start>end){
			result.add(null);
			return result;
		}
//		循环遍历，依次将各个节点作为根节点
		for(int i= start;i<=end;i++){
//			根节点将左右子树分开，依次递归求解左子树的所有形态，右子树的所有形态
			ArrayList<TreeNode> left = getTrees(start, i-1);
			ArrayList<TreeNode> right = getTrees(i+1, end);
//			得到左右子树的所有形态后，便是组合问题了，每次取两个不同的左右子树作为根节点的左右孩子
			for(int j=0;j<left.size();j++){
				for(int k=0;k<right.size();k++){
					TreeNode root = new TreeNode(i);
					root.left = left.get(j);
					root.right = right.get(k);
//					加入根节点
					result.add(root);
				}
			}
		}
		return result;
	}
}
