package com.problems4;

import com.problems4.BalancedBinaryTree.TreeNode;

public class ConstructTreePreorderInorder {

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	/***
	 * 由前序遍历和中序遍历，构造二叉树，该题目也可以转换成由前序遍历序列和中序遍历序列，得到
	 * 后序遍历，方法就是先构造出这个二叉树，然后采取后序遍历得到后序序列就可以
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/***
	 * 
	 * 正确方法：思路，就是根据前序与中序，首先有前序一次得到的肯定是根节点，由这个根节点在中序遍历中的位置就可以得到左右子树的划分，然后递归构造
	 */
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		if(preorder.length==0){
			return null;
		}
		return build(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
		
	}
	private TreeNode build(int[] preorder, int[] inorder, int preL, int pleH, int InoL,
			int InoH) {
		// TODO Auto-generated method stub
		TreeNode root;
//		递归终止条件，当数组为空的时候，此节点置为null
		if(preL>pleH||InoL>InoH){
			root =null;
		}
		else{
			int i=0;
//			preorder数组的此时范围内的第一个节点，就是根节点，构造此树的根节点
			root = new TreeNode(preorder[preL]);
//			在中序遍历中找到此根节点，并返回在中序遍历中根节点的位置
			for(i=InoL;i<=InoH;i++){
				if(preorder[preL]==inorder[i]){
					break;
				}
			}
//			递归构造左子树与右子树，注意两个数组目前的范围划分！！！
//			i-InoL为此根节点的左子树的节点个数，位置为preL+(i-InoL)，对应的此左子树在中序遍历的位置为，从InoL到i-1
			root.left = build(preorder, inorder, preL+1, preL+(i-InoL), InoL, i-1);
//			前序遍历中右子树的范围为：preL+(i-InoL)+1到pleH，而对应的中序遍历的位置为：i+1到InoH
			root.right = build(preorder, inorder, preL+(i-InoL)+1, pleH, i+1, InoH);
		}
		return root;
	}
	
	
	
	/***
	 * 
	 * 
	 * Construct Binary Tree from Inorder and Postorder Traversal
	 * 下面是已知某个树的中序与后序遍历，求返回这个树，或者返回这个树的前序序列，方法同上
	 * 
	 * 注意这里的原理是后序遍历的最后一个节点一定是树的根节点，然后在中序遍历中找到这个节点，就可以区分开左右子树，然后在左右子树中递归求解
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length==0)
			return null;
		return build2(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
        
    }
	private TreeNode build2(int[] inorder, int[] postorder, int inoLow, int inoHigh,
			int posLow, int posHigh) {
		// TODO Auto-generated method stub
		if(inoHigh<inoLow||posHigh<posLow){
			return null;
		}else{
//			构造树的根节点
			TreeNode root = new TreeNode(postorder[posHigh]);
//			在中序遍历中寻找根节点
			int i =0;
			for(i =inoHigh;i>=inoLow;i--){
				if(inorder[i]==postorder[posHigh]){
					break;
				}
			}
//			递归构造右子树，注意各个点的范围划分
			root.right = build2(inorder, postorder, i+1, inoHigh, posHigh-(inoHigh-i), posHigh-1);
//			递归构造左子树，注意各个点的范围划分
			root.left = build2(inorder, postorder, inoLow, i-1, posLow, posHigh-(inoHigh-i)-1);
			return root;
		}
		
	}
}

















