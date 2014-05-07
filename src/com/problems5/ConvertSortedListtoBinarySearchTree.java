package com.problems5;
/***
 * 题目意思：
 * 把一个已经按照升序排列的链表，转换成一个二叉平衡搜索树
 * 思考思路：
 * 有序数组转换平衡树我们采取的思路，是每一次去中间元素作为二叉树的根节点，这样
 * 得到的二叉树肯定是平衡的。但是这里是链表，无法随机访问链表的中间元素
 * 有一个方法肯定是这样的：
 * 将链表转换成数组，不就可以利用数组构建二叉树了，时间复杂度0（n），空间复杂度为0（n），显然这个方法空间复杂度太高，显然有更好的算法
 * @author bike
 *
 */
public class ConvertSortedListtoBinarySearchTree {

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; next = null; }
 }

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

	/***
	 * 思路1： 递归的方法，类似数组的方法，只要得到目前链表的中间节点就得到了当前的根节点,只不过寻找中间节点需要遍历当前的该段链表
	 * 设置两个指针一快一慢就可以得到中间节点
	 * @param head
	 * @return
	 */
	public TreeNode sortedListToBST(ListNode head) {
		
		if(head==null){
			return null;
		}
		return getToBST(head,null);
		        
		    
	}
	// 在区间[start, end)里递归，后面的end是包括在内的，这样可以避免要多用一个指针来记录mid前的节点
	private TreeNode getToBST(ListNode start, ListNode end) {
		// TODO Auto-generated method stub
//		注意递归结束的条件是start==end
		if(start==end){
			return null;
		}
//		 设置慢指针与快指针
		ListNode slow = start;
		ListNode fast = start;
		while(fast!=end&&fast.next!=end){
			fast = fast.next.next;
			slow = slow.next;
		}
//		最终slow指向了中间位置的节点，而fast指向了end或者end的前一个元素
		TreeNode root = new TreeNode(slow.val);
//		左区间是start到mid，不用担心slow值重复递归，因为递归结束条件是start==end
		root.left = getToBST(start, slow);
		root.right = getToBST(slow.next, end);
		return root;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		注意内部类的实例化，必须先实例化外部类
		// TODO Auto-generated method stub
		ConvertSortedListtoBinarySearchTree con = new ConvertSortedListtoBinarySearchTree();
		ListNode h = con.new ListNode(3);
		ListNode h1 = con.new ListNode(5);
		ListNode h2 = con.new ListNode(8);
		h.next = h1;
		h1.next = h2;
		con.sortedListToBST3(h);
	}
	/***
	 * 方法2 :此方法和1类似，但是不是根据开始的节点到终点节点寻找中间节点，而是开始先得到链表的长度，得到链表长度同时又开始的节点就可以得到长度/2的位置的节点
	 */
	public TreeNode sortedListToBST2(ListNode head) {
		int length = 0;
		ListNode node = head;
		while(node!=null){
			node = node.next;
			length++;
		}
		if(length==0)
		{
			return null;
		}
		return buildToBST(head,length);
	}
	private TreeNode buildToBST(ListNode head, int length) {
		// TODO Auto-generated method stub
		if(head==null||length==0){
			return null;
		}
		ListNode mid = head;
		int index = (length/2);
//		得到中间位置的节点作为根节点
		for(int i=0;i<index;i++){
			mid = mid.next;
		}
		TreeNode root = new TreeNode(mid.val);
		root.left = buildToBST(head, index);
		root.right = buildToBST(mid.next, length-index-1);
		return root;
	}
	
	/***
	 * 思路3 每次递归调用时，分成左右两部分，左边构造完时，正好指针指向mid，创建一下root，继续构造右部分子树
	 * 
	 *
	 */
	
	public TreeNode sortedListToBST3(ListNode head) {
		int length = 0;
		ListNode node = head;
		while(node!=null){
			node = node.next;
			length++;
		}
		if(length==0)
		{
			return null;
		}
		return buildToBST2(head,0,length-1);
	}
	private TreeNode buildToBST2(ListNode node, int start, int end) {
		// TODO Auto-generated method stub
//		System.out.println(node.val);
		if(start>end)
			return null;
//		得到中间节点的位置
		int mid = (start+end)/2;
//		我们可以很清晰的看到，此方法递归的思路不是右根节点递归得到左右子树
//		而是由左右子树递归得到根节点
//		可以看出从node遍历到mid-1一定是中间节点的左子树
		TreeNode left = buildToBST2(node, start, mid-1);
		
		TreeNode root = new TreeNode(node.val);
		System.out.println(root.val);
		root.left = left;
		node = node.next;
		System.out.println(node.val);
		TreeNode right = buildToBST2(node, mid+1, end);
		root.right = right;
		return root;
	}
}
