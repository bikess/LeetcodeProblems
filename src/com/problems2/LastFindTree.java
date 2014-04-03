package com.problems2;

import java.util.ArrayList;
import java.util.Stack;
/***
 * 
 * 本文是后序遍历二叉树的算法，
 * 对于后序遍历二叉树，遍历节点的顺序为：左孩子-右孩子-自己，那么一个节点需要两种情况下才可以输出
 * 一种是：它已经是叶子节点，第二是他的所有左右孩子已经输出过了。
 * @author bike
 *
 */
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }


public class LastFindTree {

//	前序遍历的方式
	public ArrayList<Integer> FrontpostorderTraversal(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		if(root == null)return list;
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			list.add(cur.val);
			if(cur.right!=null)
				stack.push(cur.right);
			if(cur.left!=null)
				stack.push(cur.left);
		}
		return list;
		
	}
//	中序遍历，中序遍历，先遍历左孩子，后遍历自己，再遍历后孩子
	public ArrayList<Integer> MidpostorderTraversal(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root == null) return list;
		TreeNode p = root;
		while(p!=null||!stack.isEmpty()){
			if(p!=null){
				stack.push(p);
				p= p .left;
			}else{
				p = stack.pop();
				list.add(p.val);
				p = p.right;
			}
		}
		return list;
		
	}
//	后序遍历的方式
    public ArrayList<Integer> LastpostorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<>();
    	
    	if(root == null) return list;
    	stack.push(root);
    	TreeNode t = root;
//    	判断栈中是否为空
    	while(!stack.isEmpty()){
//    		读取栈中最上面的一个节点，但是不出栈，cur中存储的是目前处于栈顶的节点
    		TreeNode cur = stack.peek();
//    		判断这个节点是否为叶子节点或者其左右孩子全部已经输出了
//    		判断目前处于栈顶的节点的左右孩子是否已经全部输出了，或者此节点是否为叶子节点
    		if(cur.right == t || cur.left == t || (cur.right==null&&cur.left==null)){
    			TreeNode node = stack.pop();
    			list.add(node.val);
//    			t中存储的是刚才弹出的节点
    			t = cur;
    		}else{
//    			顺序一定是先压入右孩子，再压入左孩子
    			if(cur.right !=null){
    				stack.push(cur.right);
    			}
    			if(cur.left !=null){
    				stack.push(cur.left);
    			}
    		}
    	}
    	return list;
        
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
