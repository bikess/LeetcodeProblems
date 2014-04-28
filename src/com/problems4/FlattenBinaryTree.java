package com.problems4;

import java.util.Stack;

public class FlattenBinaryTree {

 class TreeNode {
	int val;
    TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
	/**
	 * @param args
	 */
   public void flatten(TreeNode root) {
     TreeNode cur = null;
     if(root==null){
    	 return ;
     }
     Stack<TreeNode> stack = new Stack<>();
     stack.add(root);
     while(!stack.isEmpty()){
    	 TreeNode tem = stack.pop();
    	 if(cur==null)
    	 {
    		 cur = tem;
    	 }
    	 else
    	 {
    		 cur.right = tem;
    		 cur = cur.right;
    	 }
    	 if(tem.right!=null){
    		 stack.add(tem.right);
    	 }if(tem.left!=null){
    		 stack.add(tem.left);
    	 }
    	 cur.left=null;
    	 cur.right=null;
     }
     
 }
   /***
    * 利用 递归的方式改变
    * @param root
    */
   public void flatten2(TreeNode root){
	  if(root==null){
	    	 return ;
	  }
	  TreeNode pre = null;
	  flattenutil(root,pre);
   }
	private TreeNode flattenutil(TreeNode root, TreeNode pre) {
	// TODO Auto-generated method stub
	if(root==null)
		return pre;
	if(pre!=null)
		pre.right = root;
	pre = root;
	TreeNode right = root.right;
	pre = flattenutil(root.left, pre);
	root.left = null;
	pre = flattenutil(right, pre);
	return pre;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
