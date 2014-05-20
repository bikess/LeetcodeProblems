package com.problems6;

/***
 * 题目的意思：一个二叉搜索树的两个元素的位置被错误的交换，不改变树的结构而恢复为二叉搜索树
 * 题目要求：
 * 有空间复杂度0（n）的解法，有没有固定空间复杂度的解法？
 * Two elements of a binary search tree (BST) are swapped by mistake.Recover the tree without changing its structure.
 * @author bike
 *
 */
public class RecoverBinarySearchTree {
	
	class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	}

	/***
	 * 思路1：0（n）的复杂度，可以利用中序遍历得到遍历结果，然后将遍历结果中不满足排序的两个元素交换后，给节点重新赋值
	 * 需要0（n）的空间复杂度
	 * @param root
	 */
	/**
	 * 思路2：也是采取中序遍历，记录两个破坏中序遍历性质：每一个元素不小于前一个遍历的值，找到这两个位置然后交换
	 * @param root
	 */
//	s1，s2分别存储需要交换的两个节点
	TreeNode s1=null,s2=null;
//	pre节点总是存储的是上一个输出的节点
	TreeNode pre=null;
	public void recoverTree(TreeNode root) {  
	    if(root==null){
	    	return;
	    }
	    preOreder(root);
	    s1.val = s1.val^s2.val;
	    s2.val = s1.val^s2.val;
	    s1.val = s1.val^s2.val;
	}
	private void preOreder(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		preOreder(root.left);
//		判断之前输出的值是否比当前根节点的值大，这说明之前输出的节点破坏了二叉搜索树的性质
		if(pre!=null&&pre.val>root.val){
//			这是找到的第一个破坏二叉树性质的节点，并把root先复制给s2.
			if(s1==null){
				s1= pre;
				s2 = root;
//				这表明找到的第二个，则此时root是破坏性质的节点而不是pre。
			}else{
				s2 = root;
			}
		}
//		pre的第一个输出值一定是最左子树的根节点
		pre = root;
		preOreder(root.right);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
