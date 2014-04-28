package com.problems4;

public class BalancedBinaryTree {

	/**
	 * @param args
	 */
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	/***
	 * 方法1 采取的是计算左右子树的深度看当前根节点是否构成平衡二叉树，然后递归判断左右子树
	 * @param root
	 * @return
	 */
	 public boolean isBalanced(TreeNode root) {
		if(root==null)
			return true;
//		递归判断左子树与右子树的高度
		int leftHeight = getHeight(root.left);
		int rightheight = getHeight(root.right);
//		这里一定注意递归的顺序，先判断当前的节点为根节点是否为平衡二叉树，再递归判断该节点的
//		左孩子与右孩子是否为平衡二叉树。不能先递归判断左右子树是否为平衡二叉树，这样存在大量的重复计算，会超时
		if(leftHeight-rightheight<-1||leftHeight-rightheight>1){
			return false;
		}
		return isBalanced(root.left)&&isBalanced(root.right);
	        
	 }
	private int getHeight(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null){
			return 0;
		}
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		int height = left>right? left+1:right+1;
		return height;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/***
	 * 方法2 ：采取后序遍历的方式先判断左右子树是否为平衡二叉树，然后递归判断根树，这是需要保存左右子树的深度并返回
	 */
	class Value{
		boolean isBalanced;
		int depth;
		public Value(boolean is, int depth){
			this.isBalanced = is;
			this.depth = depth;
		}
	}
	public boolean isBalanced2(TreeNode root){
		if(root == null){
			return true;
		}
		return checkBalanced(root).isBalanced;
	}
	public Value checkBalanced(TreeNode root){
		if(root ==null){
			return new Value(true, 0);
		}
		Integer s =5;

//		递归判断左右子树是否为平衡二叉树，然后返回左右子树的深度
		Value left = checkBalanced(root.left);
		Value right = checkBalanced(root.right);
		if(left.isBalanced&&right.isBalanced&&Math.abs(left.depth-right.depth)<=1){
			return new Value(true, left.depth>right.depth? left.depth+1:right.depth+1);
		}
		return new Value(false,left.depth>right.depth? left.depth:right.depth);
	}
	/***
	 * 
	 * 方法3 采取后序遍历，而且传递一个参数值(由于整个参数记录深度，且一直保持，建立一个对象)记录当前子树的深度
	 */
	class Depth{
		int depth;
		public Depth(int depth){
			this.depth = depth;
		}
	}
	public boolean isBalanced3(TreeNode root){
		if(root ==null){
			return true;
		}
		Depth depth = new Depth(0);
		return checkIsBalanced(root , depth);
	}
	private boolean checkIsBalanced(TreeNode root, Depth depth) {
		// TODO Auto-generated method stub
		if(root == null){
			depth.depth = 0;
			return true;
		}
		Depth left = new Depth(0);
		Depth right = new Depth(0);
		
		if(checkIsBalanced(root.left,left)&&checkIsBalanced(root.right,right)){
			if(Math.abs(left.depth-right.depth)>1){
				return false;
			}
		}else{
			return false;
		}
		depth.depth = left.depth>right.depth? left.depth+1:right.depth+1;
		return true;
	}
}
