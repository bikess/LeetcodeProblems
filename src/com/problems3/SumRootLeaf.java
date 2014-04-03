package com.problems3;
 
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
public class SumRootLeaf{
	public int sum_nodeLeaf(TreeNode node,int sum){
		int x = sum*10+ node.val;
		int l=0,r=0;
		if(node.left!=null){
			l = sum_nodeLeaf(node.left,x);
		}
		
		if(node.right!=null){
			r = sum_nodeLeaf(node.right,x);
		}
		if(l==0&&r==0)
			return x;
		else
			return l+r;
	}
    public int sumNumbers(TreeNode root) {
        if(root == null)
			return 0;
		return sum_nodeLeaf(root,0);
    }
}