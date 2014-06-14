package com.partition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import sun.security.util.Length;

public class Treeleetcode {

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val){
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}
	/**
	 * @param args
	 */
	/********************************问题1：求解二叉树中最大路径的和，（路径的开始节点与路径的结束节点可以是路径中的任何一个节点）*************************************！！
	 * 
	 * 
	 * (1)求解最大路径的和，以根节点开始肯定：最大路径的和要么是左子树中路径最大值+根节点，要么是右子树中路径的最大值+根节点，要么是只取根节点
	 * 要么是左子树到右子树
	 * (2)递归求解子树的时候，求解的是以当前子树的根节点为根的最大路径的和，但是返回的时候只是返回左子树与右子树中的最大值
	 * 输入：二叉树的根节点root， 输出最大路径的和
	 */
	int maxNum = Integer.MIN_VALUE;
	public int MaxPathSum(TreeNode root){
		if(root==null){
			return 0;
		}
		getMaxPathSum(root);
		return maxNum;
	}
	public int getMaxPathSum(TreeNode root){
		if(root==null){
			return 0;
		}
//		记录当前根节点的元素的值
		int rootnum = root.val;
//		保存 当前子树的最大路径的和
		int curMaxNum = rootnum;
//		递归求解左子树
		int leftsum = getMaxPathSum(root.left);
//		递归求解右子树
		int rightsum = getMaxPathSum(root.right);
//		求出此子树的最大路径的和
		if(leftsum>0){
			curMaxNum = curMaxNum + leftsum;
		}
		if(rightsum>0){
			curMaxNum = curMaxNum+ rightsum;
		}
//		看当前子树中的最大路径有没有比目前的最大路径和大
		if(curMaxNum>maxNum){
			maxNum = curMaxNum;
		}
//		注意返回的值是能够构成路径的最大值
		return Math.max(rootnum, Math.max(rootnum+leftsum, rootnum+rightsum));
	}
	
	/*******************************问题2：一个二叉搜索树中，2个元素被错误的交换了，要求不改变树的结构而恢复树的原来的形状**************************************！！
	 * 关键点：二叉搜索树中的中序遍历的顺序就是按递增排序的！！！
	 * 
	 * 思路：（1）由中序遍历得到元素的排序（2）记录破坏递增平排序的节点的位置
	 * 输入：二叉树 ；无需输出
	 */
	TreeNode first = null;
	TreeNode second = null;
	TreeNode pre = null;
	public void recoverBST1(TreeNode root){
		if(root==null)
			return;
		findTwoNodes(root);
//		交换两个找到的位置的节点
		first.val = first.val^second.val;
		second.val = first.val^second.val;
		first.val = first.val^second.val;
	}
	
//	实际上也是中序遍历二叉树的过程
	private void findTwoNodes(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
//		中序遍历左子树
		findTwoNodes(root.left);
//		判断当前节点的值与其前驱节点值是否递增的顺序排列的
		if(pre!=null&&pre.val>root.val){
//			先假设需要交换的两个节点就是pre与root两个位置的节点
			if(first==null){
				first = pre;
				second = root;
//				当再找到破坏递增的排序的位置的时候，直接标记当前root为第二个需要调整的位置
			}else{
				second = root;
				return;
			}
		}
//		记录前驱节点
		pre = root;
		findTwoNodes(root.right);	
	}
	
	// 思路2：这个思路不是根据破坏中序序列递增排序的思路求解，而是直接从破坏二叉搜索树结构的思路进行求解。
	   public void recoverBST2(TreeNode root) {
			if(root==null)
				return;

			isBST(root,root.left,Integer.MIN_VALUE,root.val);
			isBST(root,root.right,root.val, Integer.MAX_VALUE);
//			交换两个位置
			first.val = first.val^second.val;
			second.val = first.val^second.val;
			first.val = first.val^second.val;
		}
//		参数分别表示的内容是，根节点，当前节点，当前节点应该满足的最小值与最大值
		private void isBST(TreeNode root, TreeNode cur,int min, int max) {
			// TODO Auto-generated method stub
			if(cur==null)
				return;
			int num = cur.val;
			if(num>=min&&num<=max){
				isBST(cur,cur.left, min, num);
				isBST(cur, cur.right, num, max);
			}else{
				if(first==null){
					first = root;
					second = cur;
					isBST(cur,cur.left, min, num);
					isBST(cur, cur.right, num, max);
				}else{
					second = cur;
					return;
				}
			}

		}
		
		/*******************************问题3：  判断一个二叉树是否是一个合法的二叉搜索树***************************
		 * 关键点：二叉搜索树的性质，每一个节点若为左子树必须小于根节点，大于其递归的右根节点（是有范围的）；若为右子树必须大于其根节点，小于其递归的左根节点
		 * 
		 * 输入：树的根节点，输出：是否为二叉搜索树
		 * 思路：
		 */
		
	public boolean isBSTTree(TreeNode root){
		if(root==null)
			return true;
//		进行递归判断左右子树是否为二叉搜索树，
//		两个int型参数分别为，当前的节点的值的范围，（最小值，最大值）
		return isBSTTree2(root.left,Integer.MIN_VALUE,root.val)&&isBSTTree2(root.right, root.val, Integer.MAX_VALUE);
		
	}
		private boolean isBSTTree2(TreeNode root, int min, int max) {
			// TODO Auto-generated method stub
			if(root==null)
				return true;
			if(root.val>min&&root.val<max){
				return isBSTTree2(root.left, min, root.val)&&isBSTTree2(root.right, root.val, max);
			}else{
				return false;
			}
		}
		
		/*******************************问题4：有一个二叉树的前序序列与中序序列，构造二叉树*************************
		 * 关键点：（1）递归函数中，返回值的设置以及参数的设置非常的关键！！！！
		 * 
		 * 思路：由前序序列第一个节点为根节点，然后再由中序序列就可以划分出左子树与右子树了
		 * 
		 * 输入：二叉树的前序序列与中序序列         输出：二叉树
		 */
		public TreeNode buildTree(int[] preorder, int[] inorder){
			if(preorder.length==0){
				return null;
			}
//			递归构造左右子树
			return preorderInorder(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
			
		}
//		四个int型的参数，分别是当前需要构造的树的所有节点，在前序序列中的位置与在中序序列中的位置
		private TreeNode preorderInorder(int[] preorder, int[] inorder, int pstart,
				int pend, int istart, int iend) {
			// TODO Auto-generated method stub
			if(pstart>pend){
				return null;
			}
//			当前pstart的位置必然是该子树的根节点，下面重点是划分出左右子树
//			关键是在中序序列中找到这个跟节点
			int i = istart;
			while(preorder[pstart]!=inorder[i]){
				i++;
			}
//			此时i的位置指向了根节点的位置
//			先构造该根节点
			TreeNode root = new TreeNode(preorder[pstart]);
//			则该根节点的左子树的所有节点在中序序列中的位置为：istart-i-1;前序序列中的位置为：pstart+1-pstart+i-istart
			root.left = preorderInorder(preorder, inorder, pstart+1,pstart+i-istart,istart, i-1);
			root.right = preorderInorder(preorder, inorder, pstart+i-istart+1, pend, i+1, iend);
			return root;
		}
		
		/*******************************问题5：由一个二叉树的后序序列与中序序列来构造二叉树*************************
		 * 
		 * 思路：由后序序列最后一个元素肯定是根节点，再根据这个根节点与中序序列，就可以区分出其左右子树了
		 * 输入：一个中序序列与一个后序序列，输出：构造的二叉树
		 */
		public TreeNode buildTree2(int inorder[],int postorder[]){
			if(inorder.length==0){
				return null;
			}
			return inorderPostOrder(inorder,postorder,0,inorder.length-1,0,postorder.length-1);	
		}
		private TreeNode inorderPostOrder(int[] inorder, int[] postorder,
				int istart, int iend, int pstart, int pend) {
			// TODO Auto-generated method stub
			if(istart>iend){
				return null;
			}
//			当前的根节点是
			TreeNode root = new TreeNode(postorder[pend]);
//			根据这个根节点在中序序列中划分出左右子树
			int pla = istart;
			while(inorder[pla]!=postorder[pend]){
				pla++;
			}
//			此时pla指向了在中序序列中根节点的位置
			root.left = inorderPostOrder(inorder, postorder, istart, pla-1, pstart, pend-(iend-pla)-1);
			root.right = inorderPostOrder(inorder, postorder, pla+1, iend, pend-(iend-pla), pend-1);
			return root;
		}
		
		/*********************************问题6：求二叉树的层次遍历的转换序列**********************！！
		 * 
		 * 关键思路：（1）设置两个计数变量，分别记录当前层级的节点数量与下一个层级的节点数量，这样可以标示某一层的节点是否已经完全遍历了
		 * （2）设置一个变量，来标示当前是树的哪一层，从而可以确定当前层的节点序列是否需要逆序
		 * 例如：
		 *   3
   		    / \
  		   9  20
    	  / \
   		15   7
		 * 
		 * 最后得到的结果为：
		 * [
  			[3],
  			[20,9],
  			[15,7]
			]
		 *思路：
		 ***
		 * 输入：二叉树， 输出：二叉树的层次遍历的转换序列
		 */
		public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root){
			ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
				if(root==null){
					return ret;
				}
				Queue<TreeNode> q = new LinkedList<>();
				q.add(root);
				int curlevel =1;
				int curnum = 1;
				int nextnum =0;
				while(!q.isEmpty()){
					ArrayList<Integer> list = new ArrayList<>();
					while(curnum>0){
						TreeNode temp = q.poll();
						list.add(temp.val);
						if(temp.left!=null){
							q.add(temp.left);
							nextnum++;
						}
						if(temp.right!=null){
							q.add(temp.right);
							nextnum++;
						}
						curnum--;
					}
					curnum = nextnum;
					nextnum=0;
					if(!list.isEmpty()&&curlevel%2==0){
						ArrayList<Integer> tem = new ArrayList<>();
						for(int i=list.size()-1;i>=0;i--){
							tem.add(list.get(i));
						}
						list = new ArrayList<>(tem);
					}
//					向结果集中加入当前层级得到的结果序列
					ret.add(list);
//					当前树的层级增1
					curlevel++;
				}
				return ret;
			
		}
		
		/*********************************问题7：独一无二的二叉搜索树1： 就是给一个二叉树节点的数量n，要求返回含有n个节点的二叉树形态的数量**********************！！
		 * 关键的思想：（1）二叉树的左右节点又是二叉树，而左右子树的二叉树节点的数量又是递归求解
		 * 输入：二叉树的节点数n,返回二叉树的的形态的数量
		 * 
		 * 思路：动态规划的思路
		 * 
		 */
		public int uniqueBST1(int n){
			if(n==0||n==1)
				return 1;
			int unique[] = new int[n+1];
			unique[0] = 1;
			unique[1] = 1;
			unique[2] = 2;
//			第一重循环分别求解节点数为i时二叉搜索树的形态数量
			for(int i=3;i<=n;i++){
//				针对i个节点时，左右子树分别取j与i-j-1个时候的形态数量
				for(int j=0;j<i;j++){
					unique[i] = unique[i]+unique[j]*unique[i-j-1];
				}
			}
			return unique[n];
		}
		
		/*********************************问题8：独一无二的二叉搜索树1： 就是给一个二叉树节点的数量n，要求返回二叉树的所有的形态**********************！！
		 * 
		 * 
		 * 输入：节点数n  输出：所有形态二叉树的根节点的集合
		 */
		public ArrayList<TreeNode> uniqueBST2(int n){

			return  getTrees(1,n);
		}
//		设置两个int参数，分别表示节点开始的数，与节点结束的数
		private ArrayList<TreeNode> getTrees(int start,int end) {
			// TODO Auto-generated method stub
			ArrayList<TreeNode> result = new ArrayList<>();
			if(start>end){
//				若start>end，返回空值
				result.add(null);
				return result;
			}
//			循环分别将i作为二叉树的根节点。
			for(int i = start;i<=end;i++){
//				递归构造start到i-1形成的所有可能的左子树的节点序列
				ArrayList<TreeNode> left = getTrees(start,i-1);
//				递归构造i+1到end的形成的所有可能的右子树的节点序列
				ArrayList<TreeNode> right = getTrees(i+1, end);
				
//				组合任意两个左右子树，构成当前的一种形态的二叉树
				for(int j=0;j<left.size();j++){
					for(int k=0;k<right.size();k++){
						TreeNode root = new TreeNode(i);
						root.left = left.get(j);
						root.right = right.get(k);
						result.add(root);
					}
				}
			}
			return result;
		}
		/*********************************问题：9 求解树的从根节点到树的叶节点的最小深度************************************
		 * 
		 * 
		 * 输入：树的根节点root  输出：最小的深度
		 */
		public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        if(root.left!=null&&root.right==null){
            return 1+minDepth(root.left);
        }
        if(root.left==null&&root.right!=null){
            return 1+minDepth(root.right);
        }
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }
}































