package com.problems5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
   /**
    * 二叉树的层次遍历
    * @param root
    * @return
    */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(root==null){
			return result;
		}
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	int curlevel = 1;
    	int nextlevel = 0 ;
    	q.add(root);
    	ArrayList<Integer> list = new ArrayList<>();
    	while(!q.isEmpty()){
    		TreeNode temp = q.remove();
    		list.add(temp.val);
    		curlevel--;
    		if(temp.left!=null){
    			q.add(temp.left);
    			nextlevel++;
    		}
    		if(temp.right!=null){
    			q.add(temp.right);
    			nextlevel++;
    		}
    		if(curlevel==0){
    			curlevel = nextlevel;
    			nextlevel = 0;
    			result.add(list);
    			list = new ArrayList<>();
    		}
    	}
    	return result;
		        
		    
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
