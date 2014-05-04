package com.problems5;
/***
 * 此题目的的意思是变成任意的二叉树,此时思路与二叉树是完全二叉树的思路是相同的，区别就是，在任意的二叉树中，某个节点的右子树为空，此时左子树指向下一个节点的
 * 第一个不为空的节点。
 * @author bike
 *
 */
public class PopulatingNextRightPointersEachNode {

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}


	 public void connect(TreeLinkNode root) {
		 if(root == null)
			 return;
		 TreeLinkNode cur = root.next;
		 TreeLinkNode p = null;
		 while(cur!=null){
			 if(cur.left!=null){
				 p = cur.left;
				 break;
			 }
			if(cur.right!=null){
				p = cur.right;
				break;
			}
			cur = cur.next;
		 }
//		 这里要进行子树的判断
		 if(root.left!=null&&root.right!=null){
			 root.right.next = p;
			 root.left.next = root.right;
		 }else if(root.left!=null&&root.right==null){
			 root.left.next = p;
		 }else if(root.right!=null&&root.left==null){
			 root.right.next = p;
		 }
		 connect(root.right);
		 connect(root.left);   
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
