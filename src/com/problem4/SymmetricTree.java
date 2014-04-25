package com.problem4;

public class SymmetricTree {

	public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
	 }
	/**
	 * @param args
	 */
	 public boolean isSymmetric(TreeNode root) {
		 
		 if(root==null)
			 return true;
		 
		 return isLeftRight(root.left,root.right);
	        
	 }
	private boolean isLeftRight(TreeNode left, TreeNode right) {
		// TODO Auto-generated method stub
		boolean isEqual = false;
		if(left==null&&right==null){
			return true;
		}else if(left!=null&&right!=null&&left.val == right.val){
			isEqual = true;
		}else{
			return false;
		}
		boolean isLeft = isLeftRight(left.left, right.right);
		boolean isRight = isLeftRight(left.right, right.left);
		if(isEqual&&isLeft&&isRight){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
