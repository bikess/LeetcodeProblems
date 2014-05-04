package com.problems5;

import java.util.ArrayList;

public class PathSumTotal {

	/***
	 * Given a binary tree and a sum, 
	 * determine if the tree has a root-to-leaf(从根节点到叶子节点的路径） path such that adding up all the values along the path equals the given sum.
	 * 
	 * @author bike
	 *
	 */
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

 /**
  * 方法1 ：理解错了题目的意思，题目的意思是必须从根节点都叶子节点，而不包括中间的路径
  * @param root
  * @param sum
  * @return
  */
 class Sum{
	 int sum;
	 public Sum(){
		 this.sum = 0;
	 }
	 public void addElement(int num){
		 this.sum = this.sum+num;
	 }
	 public void removeElement(int num){
		 this.sum = this.sum-num;
	 }
 
 }
	 public boolean hasPathSum(TreeNode root, int sum) {
		 if(root==null){
			 return false;
		 }
		 Sum result = new Sum();
		 return isHasPathSum(root,sum,result);
		 
	 }

	private boolean isHasPathSum(TreeNode root, int sum, Sum result) {
		// TODO Auto-generated method stub
		if(root==null){
			return false;
		}
		result.addElement(root.val);
		int nowResult = result.sum;
		if(nowResult==sum){
			return true;
		}else{
			return isHasPathSum(root.left, sum, result)||isHasPathSum(root.right, sum, result);
		}
	}

	/**
	 * @param args
	 */
	
	/***
	 * 方法2 ：方法1稍微改一下，可以变成从根节点到叶子节点的路径求解
	 * @param args
	 */
	 public boolean hasPathSum2(TreeNode root, int sum) {
		 if(root==null){
			 return false;
		 }
		 Sum result = new Sum();
		 return isHasPathSum2(root,sum,result);
		 
	 }

	private boolean isHasPathSum2(TreeNode root, int sum, Sum result) {
		// TODO Auto-generated method stub
		if(root == null){
			return false;
		}
		result.addElement(root.val);
		if(root.left==null&&root.right==null){
			if(result.sum==sum){
				return true;
			}else{
				return false;
			}
			
		}else{
			boolean leftflag = false, rightflag = false;
			if(root.left!=null){
				leftflag = isHasPathSum2(root.left, sum, result);
				result.removeElement(root.left.val);
			}if(root.right!=null){
				rightflag = isHasPathSum2(root.right, sum, result);
				result.removeElement(root.right.val);
			}
			return leftflag||rightflag;
		}
		
	}
	
	
	/***
	 * 
	 * 方法3 ：不用利用类也可以的啊,思路是深度优先遍历的方法DFS
	 * @param args
	 */
	 public boolean hasPathSum3(TreeNode root, int sum) {
		 if(root==null){
			 return false;
		 }
		 int result = 0;
		 return isHasPathSum(root,sum,result);
		 
	 }
	private boolean isHasPathSum(TreeNode root, int sum, int result) {
		// TODO Auto-generated method stub
//		这个用来返回当某个节点的左或右节点为空，肯定返回false
		if(root==null){
			return false;
		}
		if(root.left==null&&root.right==null){
			if(result+root.val==sum){
				return true;
			}else{
				return false;
			}
		}
		return isHasPathSum(root.left, sum, result+root.val)||isHasPathSum(root.right, sum, result+root.val);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/****
	 * 题目扩展：要求不是返回是否存在一条从根节点到叶子节点的和为sum的路径，而是要求返回满足这样要求的所有的路径
	 * 
	 *求解的思路同上面，但是返回的值有变化
	 */
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(root == null)
			return result;
		ArrayList<Integer> list = new ArrayList<>();
		boolean flag = getPathsum(result,list,root,sum);
		return result;

        
    }
/***
 * 这是带返回值来确定是否向list中添加了元素
 * @param result
 * @param list
 * @param root
 * @param sum
 * @return
 */
	private boolean getPathsum(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, TreeNode root, int sum) {
		// TODO Auto-generated method stub
		if(root==null){
			return false;
		}
		if(root.left==null&&root.right==null){
			if(sum - root.val==0){
				list.add(root.val);
				result.add((ArrayList<Integer>) list.clone());
				return true;
			}else {
				return false;
			}
		}else{
				list.add(root.val);
				boolean isadd = getPathsum(result, list, root.left, sum-root.val);
				if(isadd==true){
					list.remove(list.size()-1);
				}
				isadd = getPathsum(result, list, root.right, sum-root.val);
				if(isadd == true){
					list.remove(list.size()-1);
				}
				return true;	
		}
	
	}
	/***
	 * 不用返回值也可以，关键就是，每次递归回来后，一定要移除队列尾部的元素就可以了，java的集合都是指向对象的引用，没一个传递都是引用传递
	 */
	private void getPathsum2(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, TreeNode root, int sum){
		if(root.left==null&&root.right==null){
		    list.add(root.val);
			if(sum - root.val==0){
				result.add((ArrayList<Integer>) list.clone());
			}
		}else{
		         list.add(root.val);
		        if(root.left!=null)
			    {
			        
			        getPathsum(result, list, root.left, sum-root.val);
			        list.remove(list.size()-1);
			    }
				
				
				if(root.right!=null)
				{
				    getPathsum(result, list, root.right, sum-root.val);
				    list.remove(list.size()-1);
				}
		
				
		}
	}
}






















