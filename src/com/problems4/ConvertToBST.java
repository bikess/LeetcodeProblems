package com.problems4;
/**
 * Given an array where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * 一个递增的有效数组，转换成平衡二叉树
 * @author bike
 *
 */
/***
 * 思路1：由于数组已经排好序了，因此可以每次以中间节点作为根节点，递归进行,每次取中间节点作为当前子树的根节点，然后递归构造左子树，
 * 然后递归构造右子树！！
 * @author bike
 *
 */
public class ConvertToBST {

	public class TreeNode {
		    int val;
		    TreeNode left;
		    TreeNode right;
		    TreeNode(int x) { val = x; }
		  }
    public TreeNode sortedArrayToBST(int[] num) {
		if(num.length==0)
			return null;
		TreeNode root = null;
		root = putNumToTree(num,0,num.length-1);
		return root;        
    }
    /***
     *  这里注意java的一个易错点，java传递对象的时候是传递对象引用的副本，例如
     *  TreeNode root = new TreeNode(num[mid]);
     *  void method(root)
     *  传递的只是root指向的对象的一个引用，当在方法体内对对象的修改，就是对root指向的对象的修改，但是一旦形参root指向了另一个对象，则连接就中断了
     *  实参的root仍然指向原来的对象。
     * @param num
     * @param left
     * @param right
     * @return
     */
	private TreeNode putNumToTree(int[] num, int left, int right) {
		// TODO Auto-generated method stub
		if(left>right)
			return null;
		int mid = (left+right)/2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = putNumToTree(num, left, mid-1);
		root.right = putNumToTree( num, mid+1, right);
		return root;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
