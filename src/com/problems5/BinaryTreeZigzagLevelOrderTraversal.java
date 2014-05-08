package com.problems5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.xml.internal.ws.api.pipe.NextAction;

/***
 * 
 * 方法1 采取的是先按照层次遍历得到结果，然后把得到的结果偶数个结果进行逆转，得到最终的结果
 * @author bike
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {

	/**
	 * @param args
	 */
	class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		if(root==null){
			return ret;
		}
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	ArrayList<Integer> list = new ArrayList<>();
    	int curlevelnum = 1;
    	int nexlevelnum = 0;
    	//利用队列得到结果
    	while(!q.isEmpty()){
    		TreeNode t = q.poll();
    		curlevelnum--;
    		if(t.left!=null){
    			q.offer(t.left);
    			nexlevelnum++;
    		}
    		if(t.right!=null){
    			q.offer(t.right);
    			nexlevelnum++;
    		}
    		if(curlevelnum==0){
    			curlevelnum = nexlevelnum;
    			nexlevelnum = 0;
    			list.add(t.val);
    			ret.add((ArrayList<Integer>) list.clone());
    			list = new ArrayList<>();
    		}else{
    			list.add(t.val);
    		}
    	}
//    	对得到的结果，相应的子结果进行逆转
    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    	for(int i=0;i<=ret.size()-1;i++){
    		result.add(ret.get(i));
    		i++;
    		if(i<=ret.size()-1){
    			ArrayList<Integer> temp = ret.get(i);
    			ArrayList<Integer> reverse = new ArrayList<>();
    			for(int j=temp.size()-1;j>=0;j--){
    				reverse.add(temp.get(j));
    			}
    			result.add(reverse);
    		}
    	}
    	return result;
    }
    
    /***
     * 
     * 方法2： 在进行层次遍历的时候，直接对偶数层次的的结果进行逆转！！！
     * @param args
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		if(root==null){
			return ret;
		}
		Queue<TreeNode> curlevel = new LinkedList<>();
		curlevel.add(root);
//		设置一个变量，记录当前遍历的树的层次
		int level = 1;
		while(!curlevel.isEmpty()){
			Queue<TreeNode> nextlevel = new LinkedList<>();
			ArrayList<Integer> list = new ArrayList<>();
			while(!curlevel.isEmpty()){
				TreeNode t = curlevel.poll();
				if(t.left!=null){
					nextlevel.offer(t.left);
				}
				if(t.right!=null){
					nextlevel.offer(t.right);
				}
				list.add(t.val);
			}
			if(level%2==0){
				Collections.reverse(list);
			}
			level++;
			curlevel = nextlevel;
			ret.add(list);		
		}
		return ret;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
