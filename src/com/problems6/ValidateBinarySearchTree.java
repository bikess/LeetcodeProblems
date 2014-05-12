package com.problems6;

import java.util.ArrayList;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

题目的意思是：
给一个二叉树，决定这个二叉树是否是一个严格的二叉搜索树
一个严格的二次搜索树的定义为：
（1）所有左子树的节点的值都小于根节点
（2）所有右子树的节点的值都大于根节点
（3）所有的左右子树也是严格的二叉搜索树
 * @author bike
 *
 */
public class ValidateBinarySearchTree {

	/**
	 * @param args
	 */
	class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
		  
	}


/**
 * 方法二：递归判断，递归是传入两个参数，一个是左界，一个是右界，节点的值必须在这两个界的中间。同时更新左子树的界限与右子树的界限！！！！
 * 此方法推荐，这个方法实际上就是一步一步的保证了每一个节点的左右界，保证严格的是二叉搜索树
 * @param root
 * @return
 */
	public boolean isValidBST2(TreeNode root) {
		
//		传入一个左界，与一个有界，也就是root节点的值必须在两个值之间
		return checkValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
		
	}
	private boolean checkValidBST(TreeNode root, int minValue, int maxValue) {
	// TODO Auto-generated method stub
		if(root == null){
			return true;
		}
		if(minValue<root.val&&root.val<maxValue){
			return checkValidBST(root.left, minValue, root.val)&&checkValidBST(root.right, root.val, maxValue);
		}
		return false;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
/***
 * 
 * 方法3 ，把树按照中序进行遍历，得到遍历的结果，然后看结果是否为升序
 */
	public boolean isValidBST3(TreeNode root) {
		if(root==null){
			return true;
		}
		ArrayList<Integer> list = new ArrayList<>();
//		中序遍历得到遍历的结果
		midOrder(root,list);
		for(int i =1;i<list.size();i++){
			if(i>0){
				if(list.get(i-1)>=list.get(i)){
					return false;
				}
			}
		}
		return true;
	}
	

private void midOrder(TreeNode root, ArrayList<Integer> list) {
	// TODO Auto-generated method stub
	if(root == null){
		return;
	}
	midOrder(root.left, list);
	list.add(root.val);
	midOrder(root.right, list);
}
/***
 * 
 * 方法4 思路同方法3，但是出入的参数，只有一个左边界
 * @param root
 * @param list
 */
class Min{
	int minValue;
	public Min(int x){
		this.minValue = x;
	}
}
public boolean isValidBST4(TreeNode root) {
	Min minValue = new Min(Integer.MIN_VALUE);
	return check(root,minValue);
}
private boolean check(TreeNode root, Min mi){
	// TODO Auto-generated method stub
	if(root == null){
		return true;
	}
//	左子树的左边界是其最小值
	boolean flag = check(root.left, mi);
//	但是左子树的右边界必须是其根节点
	if(mi.minValue>=root.val||!flag){
		return false;
	}
	mi.minValue= root.val;
//	右子树的左边界必须是其根节点
	return check(root.right, mi);
}
/***
 * 思路1（）这是错误的答案：后序遍历二叉树，看二叉树根节点值是否大于左子树，小于右子树的值
 * 
 * 此思路从下往上判断出现了错误！！当保证子树是严格的二叉搜索树的时候，例如右子树的左子树也必须比根节点的值大，这一点没有保证！！！！！
 * @param root
 * @return
 */

public boolean isValidBST(TreeNode root) {
	
	if(root==null){
		return true;
	}
	boolean flag = false;
	boolean left = isValidBST(root.left);
	boolean right = isValidBST(root.right);
	if(left&&right)
	{
		flag = true;
		if(root.left!=null){
			if(root.val>root.left.val){
				flag = true;
			}else{
				return false;
			}
		}
		if(root.right!=null){
			if(root.val<root.right.val){
				flag = true;
			}else{
				return false;
			}
		}
	}else{
		return false;
	}
	return flag;        
	    
}
}
