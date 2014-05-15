package com.problems4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 此题是merge two sorted lists 的扩展题，题目要求将k个有序链表合并成一个有序链表，而且要求分析复杂度
 * @author bike
 *
 */
public class MergekSortedLists {

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
     val = x;
     next = null;
     }
 }
	/***
	 * 
	 * 思路1 ：分治法就是归并排序（时间复杂度（0（nlgn））。
	 * 思路：先分成2个子任务，然后递归求解子任务，最后回溯回来.
	 * 先把k个list分成2半，然后继续划分，直到剩下两个list就合并
	 * 合并时用到mergeTwo Sorted Lists
	 * 算法的时间复杂度。假设总共有k个list，每个list的最大长度是n，那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)。
	 * 根据主定理，可以算出算法的总复杂度是O(nklogk)，空间复杂度的话是递归栈的大小O(logk)
	 * @param args
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if(lists==null||lists.size()<=0){
			return null;
		}
		return GuiBing(lists,0,lists.size()-1);
    
	}
//	一定学习这种递归归并的判断条件思路
	private ListNode GuiBing(ArrayList<ListNode> lists, int start, int end) {
//		小于的时候如何处理
		if(start<end){
			int mid = (start+end)/2;
			return mergeTwoLists(GuiBing(lists, start, mid),GuiBing(lists, mid+1, end));
		}
//		其他情况如何处理
		return lists.get(start);
	}
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null)
	        return l2;          
	    if(l2 == null)
		    return l1;
		ListNode newHead = null;
		ListNode cur = null;
		if(l1.val<=l2.val){
				newHead = l1;
				cur = newHead;
				l1 = l1.next;
				cur.next = null;
		}else{
				newHead = l2;
				cur = newHead;
				l2 = l2.next;
				cur.next = null;
		}
		while(l1!=null&&l2!=null){
				if(l1.val<=l2.val){
					cur.next = l1;
					cur = l1;
					l1 = l1.next;
					cur.next = null;
				}
				else{
					cur.next = l2;
					cur = l2;
					l2 = l2.next;
					cur.next = null;
					}
	
		}
		if(l1!=null){
			cur.next = l1;
		}
		if(l2!=null){
			cur.next = l2;
		}
		return newHead;
	
	}
	/***
	 * 思路2：利用堆的数据结构，此方法一定要记住，尤其是利用priorityQueue构造堆的思路一定要学习！！！！
	 * 维护一个大小为k的堆，每次去堆顶的最小元素放到结构中，然后读取
	 * 该元素的下一个元素放入堆中，重新维护好堆。
	 * 时间复杂度分析，总共有n*k个元素,总共要向堆中插入n*K次，每次维护堆的性质需要lgk的时间因此时间复杂度为0(nklogk)
	 * 空间复杂度即为堆的深度logk。
	 * @param args
	 */
	/**
	 * 在java中我们无须自己建立堆，java集合priorityQueue就是优先队列，底层实现就是用堆来实现
	 */
	public ListNode mergeKLists2(ArrayList<ListNode> lists) {
		if(lists==null||lists.size()<=0){
			return null;
		}
//		注意priorityQueue的用法,此处相当于建立个一个根据节点元素值的小根堆,堆顶元素是元素值最小的节点
//		学习比较器Comparator的使用
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.val-o2.val;
			}
		}
		);
//		向初始化构造堆，当此节点不为空加入堆中
		for(int i=0;i<lists.size();i++){
			if(lists.get(i)!=null)
				heap.add(lists.get(i));
		}
		ListNode head  = null;
		ListNode pre = head;
//		看堆中是否存在元素
//		while(!heap.isEmpty()){
		while(heap.size()>0){
			ListNode node = heap.poll();
			if(head==null){
				head = node;
				pre = head;
			}else{
				pre.next = node;
			}
			pre = node;
//			弹出的元素，是否有下一个元素，则加入
			if(node.next!=null){
				heap.add(node.next);
			}
		}
		return head;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
