package com.partition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 此类包含了所有leetcode上关于链表的所有问题的解法，以后不但leetcode上的关于链表的问题，
 * 其他任何的关于链表的问题都可以写在这个包中
 * @author bike
 *
 */
class ListNode{
	int val;
	ListNode next;
	public ListNode(int x) {
		this.val = x;
		this.next = null;
	}
}
public class List {

	/***！！！！！问题1--------------------归并排序链表---------------------！！！！！！！！！！
	 * 
	 * 关键学习点： 双指针求解中间节点，归并排序的算法思想：分治思想，先分界后合并！！！
	 * 问题1：链表排序（归并排序）
	 * 将一个链表在o（nlgn）的时间内，利用固定的存储空间将一个链表中的所有数进行排序
	 * 思路就是归并排序
	 * 问题描述：
	 * 输入：链表head，输出：（返回）排序后的链表 head
	 */
	public ListNode SortList(ListNode head){
		if(head==null||head.next==null)
			return head;
		ListNode end = head;
//		遍历链表得到链表的尾节点
		while(end.next!=null){
			end = end.next;
		}
//		归并递归的两个参数分别是头节点与尾节点
//		System.out.println(end.val);
		ListNode newHead = GuiBingSortList(head,end);
		return newHead;
		
	}

	private ListNode GuiBingSortList(ListNode start, ListNode end) {
		// TODO Auto-generated method stub
//		只有一个节点了，直接返回，归并结束
		if(start==end){
			start.next=null;
			return start;
		}
//		递归归并的关键点，是找到中间节点，然后递归归并！！！
//		寻找中间节点的方法就是采取双指针遍历的方法
		ListNode pre = start;
		ListNode after = start;
		while(after!=end&&after.next!=end){
			pre = pre.next;
			after = after.next.next;
		}
//		一定要注意这里必须先保存一下pre.next节点，否则在执行了 lhead = GuiBingSortList(start, pre)后pre.next就变为空了
//		或者将	ListNode lhead = GuiBingSortList(start, pre);与ListNode rhead = GuiBingSortList(lstart, end);交换位置，先执行右边数组的归并
		
		ListNode lstart = pre.next;
//		此时pre指向的是左边数组的右边界，pre.next指向右边数组的左边界，递归归并这两个边界
		ListNode lhead = GuiBingSortList(start, pre);
		ListNode rhead = GuiBingSortList(lstart, end);
//		把两个数组进行合并
		ListNode mergeHead = MergeTwoLists(lhead,rhead);
		return mergeHead;
	}

	private ListNode MergeTwoLists(ListNode lhead, ListNode rhead) {
		// TODO Auto-generated method stub
		ListNode pre1 = lhead;
		ListNode pre2 = rhead;
		ListNode head = null;
		if(lhead.val<rhead.val){
			head = lhead;
			pre1 = pre1.next;
		}else{
			head = rhead;
			pre2 = pre2.next;
		}
		ListNode pre = head;
		while(pre1!=null&&pre2!=null){
			if(pre1.val<pre2.val){
				pre.next = pre1;
				pre1 = pre1.next;
			}else{
				pre.next = pre2;
				pre2 = pre2.next;
			}
			pre = pre.next;
		}
		pre.next = pre1==null? pre2:pre1;
		return head;
	}
	/***
	 * 
	 * （以后在写归并算法的时候还是尽量利用上面的方法，以最后一个节点作为end进行归并，这样比较不容易出错！！！）
	 * 归并排序的另一种写法,之所以是另一种写法是因为，归并排序的末尾值是null,而不是利用指针遍历，找到的链表的真正的尾部节点，而是尾部节点的下一个节点，null
	 * @param args
	 */
	public ListNode SortList2(ListNode head){
		if(head==null&&head.next==null)
			return head;
//			 否则进行递归归并排序，递归的第一个节点与最后一个节点下一个节点，因此归并链表的时候不包括end节点
		return guibingSort(head,null);
	}
	private ListNode guibingSort(ListNode start, ListNode end) {
		// TODO Auto-generated method stub
//		这句判断，可有可无
//		if(start==null)
//			return null;
//		这里start.next==end，保证此时该归并的链表只有一个节点，则直接返回！！，该语句已经包括了start为null的情况了
		if(start.next==end)
//			这里一定要注意，将start.next 置为null否则会出现某个指针next不为null
		{   start.next=null;
			return start;
		}
		//设置快慢指针确定链表的中间节点
		ListNode slow = start;
		ListNode fast = start;
//		这里一定注意判断条件
		while(fast!=end&&fast.next!=end){
			slow = slow.next;
			fast = fast.next.next;
		}
//		此时slow节点要么在正中的节点，要么在中间偏右的节点
//		左链表归并我们不不含slow节点，右链表归并包含slow节点
		ListNode l1 = guibingSort(start, slow);
		ListNode l2 = guibingSort(slow, end);
		return merge(l1,l2);
	}
//	这种递归的方式,合并两个链表固然简单,但是容易出现栈溢出,我们最好还是不用递归
	private ListNode merge(ListNode l1, ListNode l2) {
		// TODO Auto-generated method stub
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;
		ListNode result = null;
		if(l1.val<=l2.val){
			result = l1;
			result.next = merge(l1.next, l2);
		}else{
			result = l2;
			result.next = merge(l1, l2.next);
		}
		return result;
	}
	private ListNode merge2(ListNode l1,ListNode l2){
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;
		ListNode cur1 = l1,cur2 = l2;
//		下面对两个链表进行归并
//		设置合并链表的表头
		ListNode head = null;
		if(cur1.val<=cur2.val)
		{
			head = cur1;
			cur1 = cur1.next;
		}else{
			head = cur2;
			cur2 = cur2.next;
		}
		ListNode pre = head;
		while(cur1!=null&&cur2!=null){
			if(cur1.val<=cur2.val){
				pre.next = cur1;
				cur1= cur1.next;
			}else{
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1==null? cur2:cur1;
		return head;
	}
	public static void main(String [] args){
		List l = new List();
		ListNode head = new ListNode(6);
		ListNode data1 = new ListNode(5);
		head.next = data1;
		ListNode data2 =new ListNode(4);
		data1.next = data2;
		data2.next = new ListNode(3);
//		l.SortList(head);
		ListNode newHead = l.SortList2(head);
		ListNode pre = newHead;
		while(pre!=null){	
			System.out.print(pre.val+"\t");
			pre = pre.next;
		}
	}
	
	/***！！！！！问题2--------------------插入排序链表---------------------！！！！！！！！！！
	 * 
	 * 问题2：将一个链表进行插入排序
	 * 关键思想：（1）设置一个永远指向头节点的引用，也就是头指针，永远指向头节点的指针可以避免很多麻烦（2）链表指针的移动
	 * 问题描述：
	 * 输入：链表head，输出：（返回）插入排序后的链表head
	 */
	/***
	 * 思路1，这是从将后面未排序的元素往前面排好序的链表插入
	 * @param head
	 * @return
	 */
	public ListNode insertSortList(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
		ListNode newHead = new ListNode(0);
//		这个节点永远指向头节点
		newHead.next = head;
//		定义当前比较的节点,pre暂存已经排好序的链表的最后一个节点，也就是pre的前驱节点
		ListNode pre = head;
		ListNode cur = head.next;
		while(cur!=null){
//			开始寻找插入的位置,循环跳出的时候insert.next的位置就是插入的最终位置
			ListNode insert = newHead;
			while(insert.next!=cur&&insert.next.val<cur.val){
				insert = insert.next;
			}
			if(insert.next==cur){
//				这表明是在pre的后面插入
				pre = cur;
				cur = cur.next;
				continue;
			}
			pre.next = cur.next;
			cur.next = insert.next;
			insert.next = cur;
//			在pre的前面插入
			cur = pre.next;
		}
		return newHead.next;
	}
	/***
	 * 思路2，这是从前面进行插入
	 * 基本思路
	 * @param head
	 * @return
	 */
	public ListNode insertSortList2(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
//		设置两个指针，分别指向已经排序的链表的尾部节点与需要进行插入排序的当前节点
//		设置一个节点永远指向头节点
		ListNode newHead = new ListNode(0);
		newHead.next  = head;
//		已经排好序的链表的尾部节点
		ListNode pre = head;
//		需要进行插入排序的当前节点
		ListNode cur = head.next;
		while(cur!=null){
//			判断当前节点插入的位置是在pre节点之前还是之后
//			若插入位置是pre节点之后
			if(cur.val>=pre.val){
				pre = cur;
				cur = cur.next;
			}
//			若插入的位置是在pre节点之前
			else{
				ListNode insertplace = newHead;
				while(insertplace.next!=pre&&cur.val>=insertplace.next.val){
					insertplace = insertplace.next;
				}
//				找到了插入位置，就是insertplace的next的位置,则进行节点指针的修改
				pre.next = cur.next;
				cur.next = insertplace.next;
				insertplace.next = cur;
//				排序完毕，指向下一个要排序的位置
				cur = pre.next;
			}
		}
		return newHead.next;	
	}
	
	/***！！！！！问题3--------------------重新排序链表（链表节点位置重新分布）---------------------！！！！！！！！！！
	 * 关键的学习点:
	 * (1)注意将链表逆转或者链表中某一部分进行逆转这是基本的链表算法，利用逆转后的链表进行下一步的求解非常常用 （2）注意操作是获得某个节点的前驱节点操作的遍历
	 * 用于node.next进行操作比用 当前node节点操作方便的多！！！
	 * 
	 * 问题描述：L1->L2->l3->L4......->Ln-1->Ln 重新分布后变为 L1->Ln->L2->Ln-1......，要求原地进行变换，不能改变节点的值
	 * 
	 * 输入：链表head, 输出链表head
	 * 
	 * 思路：（当然你可以把所有的节点存在一个队列中，然后重新构造链表，但是这样不知道符不符合题目的要求，应该是不可以的）
	 * （1）将链表从中间一分为2，分为2个链表
	 * （2）将后面的链表进行逆转
	 * （3）把两个链表合并成题目要求的链表
	 *****/
	public ListNode ReorderList(ListNode head){
		
		if(head==null||head.next==null){
			return head;
		}
//		首先利用双指针的方法，取得链表的中间节点
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next!=null&&fast.next.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
//		此时slow节点指向了中间的节点，下面把slow节点后面的所有节点逆转（逆序）
		ListNode pre  = slow.next;
		ListNode cur = pre.next;
		while(cur!=null){
			pre.next = cur.next;
			cur.next = slow.next;
			slow.next = cur;
			cur = pre.next;
		}
//		逆转后半部分的链表完成，下面合并两个链表
//		分别设置一个前半部分链表的指针与后半部分链表的指针
		ListNode leftPro = head;
		ListNode rightPro = slow.next;
		while(leftPro!=slow){
			ListNode temp = rightPro.next;
			rightPro.next = leftPro.next;
			leftPro.next = rightPro;
			leftPro = rightPro.next;
			rightPro = temp;
			slow.next = rightPro;
		}
		return head;
		
	}
	
	
	/***！！！！！问题4--------------------确定一个链表中是否存在环（要求不用额外的空间）---------------------！！！！！！！！！！
	 * 关键点：设置双指针，当指针相遇的时候表明存在环！！
	 * 
	 * 输入：链表 head，输出：链表中是否存在环 boolean
	 * 思路：采取双指针的思路，一个指针每次移动1步，一个指针移动2步，若两个指针相遇在存在环，否则不存在环
	 */
	public boolean hasCycle(ListNode head){
		if(head==null||head.next==null)
			return false;
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next!=null&&fast.next.next!=null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow==fast){
				return true;
			}
		}
		return false;
		
	}
	/***！！！！！问题5--------------------确定一个链表中是否存在环，若存在返回这个环，不存在则返回null（要求不用额外的空间）---------------------！！！！！！！！！！
	 * 关键思路：双指针二次相遇的位置
	 * 
	 * 输入：链表head，输出：链表中存在的环 head
	 * 思路：也是采取双指针的思路
	 * （1）设置快慢指针，但快指针与满指针相遇的时候
	 * （2）快指针回答头节点重新扫描(但是此时快指针是一步一步的移动)，慢指针继续从当前位置扫描
	 * （3）当快慢指针再一次相遇的位置，就是环开始的位置，就是环的头节点
	 * 
	 */
	public ListNode getCycle(ListNode head){
		if(head==null||head.next==null){
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next!=null&&fast.next.next!=null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow==fast){
				fast = head;
				while(slow!=fast){
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}
		return  null;
	}
	/***！！！！！问题6--------------------对一个每个节点拥有随机指针的链表进行一个深复制---------------------！！
	 * 关键思路：（1）在旧的链表每个节点后面深复制一个新的节点，可以容易得到旧链表某个节点在对应的新链表中的位置
	 * 
	 * 输入： 一个链表头 head  输出： 深复制链表的头head
	 * 思路：
	 * （1）在原来的链表中每一个节点后面复制一个新的节点，也就是构成 1->new1->2->new2....
	 * （2）这样在求new1的random节点的时候，就是1.random.next,就是新的节点的random节点
	 */
	 class RandomListNode {
		      int label;
		      RandomListNode next, random;
		      RandomListNode(int x) { this.label = x; }
	 }
	public RandomListNode copyRandomList(RandomListNode head){
		if(head==null){
			return null;
		}
//		在每一个旧的链表的next节点上深复制一个新的节点！！！！
		RandomListNode cur = head;
		while(cur!=null){
			RandomListNode node = new RandomListNode(cur.label);
			node.next = cur.next;
			cur.next = node;
			cur = node.next;
		}
//		新的节点深拷贝完成，但是需要构造出新的链表
		cur = head;
//		一定要注意首先构造random节点，不能边构造random，边构造next，因为有可能random节点是指向的当前位置的前一个
		while(cur!=null){
			if(cur.random!=null){
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		cur = head;
//		下面是构造新链表的next指针，并且恢复原来的链表
		boolean flag = false;
		RandomListNode newHead = null;
		while(cur!=null){
//			保存新链表的头节点
			if(flag==false){
				flag=true;
				newHead = cur.next;
			}
//			然后构造cur.next节点的next指针
//			先保存下一步要遍历的指针
//			恢复原来链表的指向
			RandomListNode temp = cur.next.next;
			if(temp!=null)
				cur.next.next = temp.next;
			else
				cur.next.next = null;
			cur.next = temp;
			cur = temp;
		}
		return newHead;
	}
	
	
	/***！！！！！问题7--------------------将一个tree的所有节点原地转换成一个链表---------------------！！
	 * 关键点：（1）树的求解利用递归是非常牛的算法，直接递归就可以了。（2）栈在树中也是非常重要的应用数据结构（3）注意设置返回值！！！
	 * （4）某个递归函数的参数设置也是很有学问的，设置正确能够更加好的求解问题
	 * 
	 * 输入 ： tree 输出：链表
	 * 问题描述：
	 * 每一个树的根节点的右指针指向其左节点，然后其左节点的右指针再指向其右节点
	 */
	class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
    }
	public void TreeToList(TreeNode root){
		if(root==null||(root.left==null&&root.right==null))
				return ;
		getList(root);
//		也可以多传进去一个变量pre，pre表示当前链表的最后一个节点
		TreeNode pre=null;
		getList2(root,pre);
	}

	private TreeNode getList2(TreeNode root,TreeNode pre) {
		// TODO Auto-generated method stub
		if(root==null){
			return pre;
		}
		if(pre!=null){
			pre.right = root;
		}
		pre = root;
		TreeNode right = root.right;
		pre = getList2(root.left,pre);
		root.left=null;
		pre = getList2(right, pre);
		return pre;
	}

	private TreeNode getList(TreeNode root) {
		// TODO Auto-generated method stub
		if(root.left==null&&root.right==null){
			return root;
		}
//		暂存树的右子树
		TreeNode right = root.right;
		if(root.left!=null){
//			构造当前root的右子树，递归求解左子树，返回左子树的构成的链表的最后一个节点
			root.right = root.left;
			TreeNode tail = getList(root.left);
//			此时若右子树也不为空，则左子树最后一个节点的left指向右子树
			root.left = null;
			if(right!=null)
			{
				tail.right = right;
//				递归求解右子树
				return getList(tail.right);
			}else{
				return tail;
			}
		}
//		当左子树为空，则必定是右子树不为空
		return getList(right);
		
	}
	
	
	
	/***！！！！！问题8--------------------将一个有序的链表，转换成一个平衡的二叉搜索树---------------------！！
	 * 关键点：（1）还是双指针求链表的中间节点法    （2） 注意递归的退出条件   （3）注意递归函数的参数设置，参数设置的好坏，直接确定了问题能不能很容易求解
	 * 
	 * 输入： 有效链表 head  输出：平衡二叉搜索树的根节点root
	 * 思路：
	 * （1）每次用双指针的方法找到当前的中间节点，作为根节点
	 */
	public TreeNode SortListToBST(ListNode head){
		if(head==null)
			return null;
//		设置递归调用的函数的参数，分别是链表的开始节点，链表的结束节点的下一个节点（不包括）
		return getTree(head,null);
		
	}

	private TreeNode getTree(ListNode start, ListNode end) {
		// TODO Auto-generated method stub
//		注意递归退出的条件，因为我们设计的算法是end是链表最后一个节点next节点，因此退出条件就是start.next==end；
		if(start==null){
			return null;
		}
		if(start.next==end){
			TreeNode node = new TreeNode(start.val);
			return node;
		}
//		否则的话寻找中间节点
		ListNode slow = start;
		ListNode fast = start;
		while(fast.next!=end&&fast.next.next!=end){
			slow = slow.next;
			fast = fast.next.next;
		}
//		此时slow节点指向的是中间的节点,也就是作为树根的节点，同时作为下一次递归的左子树的右边界
		TreeNode root = new TreeNode(slow.val);
//		若当前slow节点不是最左的节点，则递归形成左子树
		if(slow!=start){
			root.left = getTree(start, slow);
		}
//		递归形成右子树
		root.right = getTree(slow.next, end);
		return root;
	}
	
	/***！！！！！问题9--------------------链表逆转的变形题，就是将两边从m位置到n位置的节点逆转(1<=m<=n<=length)---------------------！！
	 * 
	 * 输入 ：链表head  输出：链表head
	 * 
	 */
	public  ListNode reverseList(ListNode head,int m,int n){
		if(head==null||m==n){
			return head;
		}
//		设置一个节点永远指向头节点
		ListNode newhead = new ListNode(0);
		newhead.next = head;
		int num = 1;
//		设置扫描指针，扫描指针永远指向的是第m个指针的前驱指针
		ListNode cur = newhead;
		while(num<m){
			cur = cur.next;
			num++;
		}
//		下面开始逆转指针
//		设置每一个要逆转的节点的前驱节点，要逆转的节点是pre.next,而逆转节点需要加在cur的后面
		ListNode pre = cur.next;
		num++;
		while(num<=n){
			ListNode temp = pre.next;
			pre.next = pre.next.next;
			temp.next = cur.next;
			cur.next = temp;
			num++;
		}
		return newhead.next;
	}
	
	/***！！！！！问题10--------------------Partition List 给定一个单项链表与一个值x，划分这个链表使得所有比x小的node都在所有比x大的node的前面。同时注意保持节点原有的相对位置关系---------------------！！
	 * 关键思路：（1）设置双指针，注意保存指针的前驱节点的重要性  （2）设置一个永远指向头部的节点newHead，并且遍历的时候利用 cur = newHead，
	 * 并利用cur.next进行链表的遍历扫描，与指针的指向调整，可以很好的减少头指针的考虑，最后返回newHead.next来返回新的链表
	 * 
	 * 输入：链表head和一个值x，输出链表head
	 * 思路：维护两个指针，一个指针是指向在x元素的节点前面的比x元素小的链表的末尾
	 * 一个指针 指向当前的指针进行判断
	 */
	public ListNode partitionList(ListNode head,int x){
		if(head==null){
			return null;
		}
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		设置指向比x元素小的指针的尾部的元素,注意这里tail指向最后一个比x小的元素
		ListNode tail = newHead;
//		保存其前驱指针
		ListNode pre = newHead;
//		设置扫描指针
		ListNode cur = head;
		while(cur!=null){
			if(cur.val>=x){
				pre = cur;
				cur = cur.next;
			}else{
//				注意若pre等于cur表明此前所有的元素一直是小于x的，此时将指针依次向后移动就可以了
				if(pre==tail){
					tail = cur;
					pre = cur;
					cur = cur.next;
					continue;
				}
				pre.next = cur.next;
				cur.next = tail.next;
				tail.next = cur;
				tail = cur;
				cur = pre.next;
			}
		}
		
		return newHead.next;
		
	}
	
	/***！！！！！问题10--------------------去除有序链表中的数字重复的节点，要求每一个重复的数字保留1个节点---------------------！！
	 * 
	 * 输入 :链表 head  输出：去除重复后的节点的链表head
	 */
	
	public ListNode deleteDuplicates(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
//		设置指针cur,用cur.next进行循环
		ListNode cur = head;
		while(cur!=null&&cur.next!=null){
//			若当前两个节点值重复，删除节点，cur指向不变
			if(cur.val==cur.next.val){
				cur.next = cur.next.next;
//				复杂cur指向下一个节点
			}else{
				cur = cur.next;
			}
		}
		return head;
		
	}
	
	/***！！！！！问题11--------------------去除有序链表中的数字重复的节点，要求只要是重复节点全部删除，不保留任何一个---------------------！！
	 * 
	 * 
	 * 输入：链表head  输出：去除所有重复后的节点的链表head
	 */
	public ListNode deleteDuplicates2(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
//		由于可能会删除头节点，因此设置一个指针永远指向头节点
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		设置扫描指针，不过这里是cur从newhead开始，
		ListNode cur = newHead;
//		设置int变量，保存目前比较的值
		int data = Integer.MAX_VALUE;
//		pre指针保存上一次已经完全确定没有重复的节点的尾部
		ListNode pre = newHead;
		while(cur.next!=null){
//			若当前data值与cur.next的值不相等
			if(cur.next.val!=data){
				data = cur.next.val;
				pre = cur;
				cur = cur.next;
//				若相等则是删除当前cur与cur.next两个节点，同时cur指向pre；
			}else{
				pre.next = cur.next.next;
				cur = pre;
			}
		}
		return newHead.next;
	}
	/***！！！！！问题12--------------------合并两个有序的链表，合并后的链表也是有序的---------------------！！
	 * 
	 * 
	 * 实际上，本题就是归并排序的简化版本，对两个链表进行归并
	 *输入：两个有序的链表list1与list2，输出 ：两个链表合并后返回的链表head
	 *思路:利用递归的方法就行归并，由于两个链表已经有效了
	 */
	public ListNode mergeTwoLists(ListNode list1,ListNode list2){
		if(list1==null){
			return list2;
		}
		if(list2==null){
			return list1;
		}
		ListNode result =null;
		if(list1.val<=list2.val){
			result = list1;
			result.next = mergeTwoLists(list1.next, list2);
		}else{
			result = list2;
			result.next = mergeTwoLists(list1, list2.next);
			
		}
		return result;
	}
	
	/***！！！！！问题13--------------------将链表的奇数位置的节点按照顺序移动到链表的左边，将链表偶数位置的节点按照顺序移动到链表的右边---------------------！！
	 * 例如 1->2->3->4->5->6
	 * 变换后为：1-3->5->2->4->6
	 * 
	 * 关键思路：双指针的思路方法
	 * 输入：链表head， 输出：转换后的链表head
	 * 
	 */
	public ListNode changeToSuchList(ListNode head){
		if(head==null||head.next==null||head.next.next==null)
			return head;
//		设置两个指针，其中一个指针指向奇数位置节点的最后一个节点，一个指针指向下一个要调整位置的奇数节点的前驱节点
		ListNode tail = head;
		ListNode pre = head.next;
		while(pre.next!=null){
			ListNode temp = pre.next;
			pre.next = pre.next.next;
			temp.next = tail.next;
			tail.next = temp;
			tail = tail.next;
			if(pre.next==null){
				break;
			}
			pre = pre.next;
		}
		return head;
		
	}
	/***！！！！！问题14---------------------------------------将链表逆转----------------------------------------------!!!!!
	 * 
	 * 
	 * 输入：链表 head，输出链表 head
	 */
	public ListNode roatate(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
//		设置新节点永远指向链表的头部
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		设置指针进行节点的遍历
		ListNode cur = head;
		while(cur.next!=null){
			ListNode temp = cur.next;
			cur.next = cur.next.next;
			temp.next = newHead.next;
			newHead.next = temp;
		}
		return newHead.next;
	}
	/***！！！！！问题14---------------------------------------将链表的部分进行旋转转，----------------------------------------------!!!!!
	*Given 1->2->3->4->5->NULL and k = 2,
	return 4->5->1->2->3->NULL.
	*输入；链表head，整型数k，要求把链表尾部的k个节点移动到链表的头部 输出：旋转后的链表 head
	*/
	public ListNode rotate2(ListNode head,int k)
	{
		if(head==null){
			return head;
		}
//		先看一下链表中节点的个数
		ListNode cur = head;
		int num = 0;
		while(cur!=null){
			num++;
			cur = cur.next;
		}
//		得到实际链表要逆转的节点个数
		int n = k%num;
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		遍历一遍链表，得到最后一个非null节点，以及要逆转的第一个节点的前驱节点
		ListNode pre = head;
		ListNode fast = head;
		while(n>0){
			fast =fast.next;
			n--;
		}
		while(fast.next!=null){
			fast= fast.next;
			pre= pre.next;
		}
//		逆转链表
		fast.next = newHead.next;
		newHead.next = pre.next;
		pre.next = null;
		return newHead.next;
	}

	
	/***！！！！！问题15对k个有序的链表进行归并成一个链表---------------------------------------将链表的部分进行旋转转，----------------------------------------------!!!!!
	 * 关键思路：两两归并，归并排序是链表最好的排序算法，一定要掌握！！！
	 * 
	 * 思路1：采取两两归并的思路
	 * 思路2：建立堆，维护一个小根堆，每次从堆中取出堆顶的元素！！！！利用堆求解
	 * 输入：k个链表，输出，合并后的链表
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists){
		int size = lists.size();
		if(size==0){
			return null;
		}
//		递归进行归并
		return guiBingLists(lists,0,size-1);
		
	}

	private ListNode guiBingLists(ArrayList<ListNode> lists, int start, int end) {
		// TODO Auto-generated method stub
		if(start<end){
			int mid = start+(end-start)/2;
			ListNode l1 = guiBingLists(lists, start, mid);
			ListNode l2 = guiBingLists(lists, mid+1, end);
			return merge2(l1, l2);
		}
		return lists.get(start);
	}
	
	
	public ListNode mergeKLists2(ArrayList<ListNode> lists){
		int size = lists.size();
		if(size==0){
			return null;
		}
//		构造head节点永远指向合并后链表的头节点
		ListNode head = new ListNode(0);
//		建立一个这些链表的小根堆，在java中优先队列就是由堆来底层实现的
		PriorityQueue<ListNode> q = new PriorityQueue<>(size, new Comparator<ListNode>(){

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.val-o2.val;
			}
			
		});
//		将所有的链表加入
		for(int i=0;i<size;i++){
			if(lists.get(i)!=null)
				q.add(lists.get(i));
		}
//		然后开始进行构造合并后的链表，每次只需取出堆顶的元素，然后在读取取出节点的下一个节点，然后维护堆继续为一个小根堆，直到堆中没有元素为止
		ListNode cur = head;
		while(!q.isEmpty()){
			ListNode t = q.poll();
			if(t.next!=null)
				q.add(t.next);
			cur.next = t;
			cur = cur.next;
		}
		return head.next;
	}
	
	
	/***！！！！！问题16 删除链表中的从链表尾部开始的第k个节点----------------------------------------------!!!!!
	 * 
	 * 
	 * 思路：双指针的方法，使两个指针保持k个节点的差距
	 * 输入：链表head以及int型数字k，输出：删除节点后的链表head
	 */
	
	public ListNode deleteKNode(ListNode head,int k){
		
		if(head==null||k==0){
			return head;
		}
//		设置节点，永远指向链表的头节点
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		设置双指针，两个指针相差k个节点,要删除的节点是slow.next的节点
		ListNode slow = newHead;
		ListNode fast = head;
		int i =1;
		while(i<k){
			fast = fast.next;
			i++;
		}
//		此时slow与fast相差k个节点，而且要删除的节点是slow.next节点
		while(fast.next!=null){
			slow = slow.next;
			fast = fast.next;
		}
//		删除第k个节点，从后面向前的
		slow.next=slow.next.next;
		return newHead.next;
		
	}
	
	/***！！！！！问题17:交换任意两个相邻节点的位置----------------------------------------------!!!!!
	 * 例如：Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * 输入：链表  输出：链表
	 * 
	 */
	
	public ListNode swapTwoNodes(ListNode head){
		if(head==null||head.next==null){
			return head;
		}
//		设置一个节点永远指向头节点
		ListNode newHead = new ListNode(0);
		newHead.next = head;
//		设置指针，指向需要变换位置的两个节点的前驱节点
		ListNode cur = newHead;
		while(cur.next!=null&&cur.next.next!=null){
//			进行两个节点的交换
			ListNode temp = cur.next;
			cur.next = cur.next.next;
			temp.next = cur.next.next;
			cur.next.next = temp;
			cur = temp;
		}
		return newHead.next;
		
	}
}
