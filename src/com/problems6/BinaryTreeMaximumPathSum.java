package com.problems6;
/***
 * 求二叉树的最大路径和，即给一个二叉树，返回树中任意两点路径的最大值。只要两点之间具有路径联通就好了。
 * @author bike
 *
 */
public class BinaryTreeMaximumPathSum {

class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
}
/***
 * 
 * 思路：递归的方法寻找。
 */
// 定义一个全局变量，可以相当于在两个函数中使用变量，无需
// 传递函数
	int max = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if(root==null){
			return 0;
		}
		maxSum(root);
		return max;
    
	}
	private int maxSum(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null){
			return 0;
		}
//		当前value值为节点的元素值
		int value = root.val;
//		定义左右子树具有连通性的节点的最大值
		int lmax = 0;
		int rmax = 0;
		
//		左子树不为空，递归得到左子树连通的值的最大值
		if(root.left!=null){
			lmax = maxSum(root.left);
//			若当前左子树连通的最大值大于0
			if(lmax>0){
				value += lmax;
			}
		}
//		右子树不为空，递归得到右子树连通的值的最大值
		if(root.right!=null){
			rmax = maxSum(root.right);
//			有子树连通的最大值大于0，当前value加上
			if(rmax>0){
				value+= rmax;
			}
		}
//		经过上面的计算，我们最终得到了以root.val为根节点的左子树到根节点再到右子树的具有连通新的路径的最大值
		
//		更新最大值，当前max全局变量记录最大的值
		if(value>max){
			max = value;
		}
		
//		注意作为递归函数返回的值，肯定返回的是左子树到当前root的连通路径，右子树当当前root的连通路径，以及root，这三个值中的最大值
//		千万注意不是返回以当前root为根的连通节点的最大值，因为root的父节点当root的一条连通路径，不可能既到左子树又到右子树！！！！
		return Math.max(root.val, Math.max(root.val+lmax, root.val+rmax));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
