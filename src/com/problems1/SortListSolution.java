package com.problems1;
/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 在固定的空间复杂度的情况下要求对一个链表在0（n*lgn)时间复杂度内进行排序
 * 思路：利用归并排序对链表进行排序，而不是采用快速排序
 */
class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) {
	         val = x;
	         next = null;
	      }
	 }

public class SortListSolution {

	public static ListNode sortList(ListNode head) {
//		若链表长度为0或者为1则直接返回
		if(head==null||head.next==null){
			return head;
		}
//		取得链表的头与尾
		ListNode pre = null;
//		这是游标，用于遍历链表取得尾部元素
		ListNode cur = head;
		while(cur!=null){
//			暂存游标
			pre = cur;
			cur = cur.next;
		}
//		对链表进行归并排序
        return GuiBingSort(head,pre);
    }
//	这里采取的是2路归并递归排序
	private static ListNode GuiBingSort(ListNode head, ListNode tail) {
		// TODO Auto-generated method stub
//		要取得对整个链表2路归并排序的分割点，递归的进行归并排序
//		这是递归归并排序的结束条件，当head为空或者head=null的时候结果返回
		if(head==null||head==tail){
			head.next = null;
			return head;
		}
//		否则的话将链表分割为2部分分别进行归并
		ListNode fast = head;
		ListNode slow = head;
		ListNode mid = null;
//		经过下面的循环，由于fast比slow的速度快一倍，因此当fast遍历完整个链表，
//		则mid中存储的正好是链表的中间位置的左边元素,slow存储的是链表中间位置的右边元素
		while(fast!=tail.next&&fast!=tail){
			fast= fast.next.next;
			mid = slow;
			slow = slow.next;
		}
//		递归进行归并平排序
		ListNode l_head = GuiBingSort(head, mid);
		ListNode r_head = GuiBingSort(slow, tail);
//		然后将2个链进行归并
		ListNode merged_head = mergeList(l_head,r_head);
		return merged_head;
	}
//	此方法将2个递归归并排序的链表进行合并
	private static ListNode mergeList(ListNode l_head, ListNode r_head) {
		// TODO Auto-generated method stub
		ListNode head = null;
		ListNode tail = null;
		while(l_head!=null&&r_head!=null){
//			若左链的元素比右链元素小,此时将左链的元素合并进去
			if(l_head.val<=r_head.val){
//				若这时第一次比较
				if(head==null){
					head = l_head;
					tail = l_head;
				}else{
					tail.next = l_head;
					tail = tail.next;
				}
				l_head = l_head.next;
			}else{
				if(head==null){
					head = r_head;
					tail = r_head;
				}else{
					tail.next = r_head;
					tail = tail.next;
				}
				r_head = r_head.next;
			}
		}
//		下面处理当有一个链为空的情况
		if(l_head!=null){
			tail.next = l_head;
		}else if(r_head!=null){
			tail.next = r_head;
		}
		return head;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(2);
		ListNode tail = head;
		ListNode one = new ListNode(1);
		tail.next = one;
		tail = one;
		ListNode two = new ListNode(3);
		tail.next = two;
		tail = two;
		ListNode result = sortList(head);
		while(result!=null){
			System.out.println(result.val);
			result = result.next;
		}
	}

}
