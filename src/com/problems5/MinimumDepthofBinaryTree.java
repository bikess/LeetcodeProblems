package com.problems5;
/***
 * 题目意思：
 * 寻找一个二叉树的最小深度，最小的深度是到达最近的叶子节点的节点的数目
 * @author bike
 *
 */
public class MinimumDepthofBinaryTree {

	class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }


     public int minDepth(TreeNode root) {
    	 if(root==null)
    		 return 0;
    	 if(root.left==null&&root.right==null)
    		 return 1;
    	 int mindepth;
    	 if(root.left!=null&&root.right==null)
    	 {
    		 mindepth = 1+minDepth(root.left);
    	 }else if(root.right!=null&&root.left==null){
    		 mindepth = 1+minDepth(root.right);
    	 }else{
    		 mindepth = 1+Math.min(minDepth(root.left), minDepth(root.right));
    	 } 
    	 return mindepth;
		        
		    
     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
