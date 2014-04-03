package com.problems2;

import java.util.ArrayList;
import java.util.Stack;
class Tree2Node {
     int val;
     Tree2Node left;
     Tree2Node right;
     Tree2Node(int x) { val = x; }

 }


public class BinaryTreeInorder {

	/**
	 * @param args
	 */
	
	/**
	 * 中序遍历树
	 * @param root
	 * @return
	 */

//	方法   中序遍历，中序遍历，先遍历左孩子，后遍历自己，再遍历后孩子
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
