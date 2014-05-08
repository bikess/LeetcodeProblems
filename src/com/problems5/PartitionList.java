package com.problems5;
/***
 * 
 * 题目大意：给一个链表和链表中某个值，要求把链表中所有小于该目标值的节点移动到链表的前面。
 * 
 * @author bike
 *
 */
public class PartitionList {

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
 	 * 思路1：设置两个指针，前一个指针指向目前调整后的链表的链尾，另一个指针遍历链表
 	 * @param head
 	 * @param x
 	 * @return
 	 */
	public ListNode partition(ListNode head, int x) {
		if(head==null){
			return null;
		}
		ListNode adjustTail = null;
		ListNode cur = head;
		if(head.val<x){
			adjustTail = head;
		}
//		利用cur.next进行循环，避免存储前驱节点
		while(cur!=null&&cur.next!=null){
			if(cur.next.val<x){
//				此时表示还没有发现比x小的节点,新发现的节点作为表头
				if(adjustTail==null){
					ListNode temp = cur.next;
					cur.next = cur.next.next;
					temp.next = head;
					head = temp;
					adjustTail = temp;
				}else{
					ListNode temp = cur.next;
					cur.next = cur.next.next;
					temp.next = adjustTail.next;
					adjustTail.next = temp;
					adjustTail = adjustTail.next;
				}
				
//				这里非常容易出错的是，什么时候cur向后滑动！！！，当
//				（1）当前cur的元素值比x大，而cur.next的值比x小，则cur不滑动！！！
//				（2）当cur.nxet的值也比x大，则cur一定滑动
//				（3）当前cur的元素值比x小，而cur.next的值也比x小，则cur要滑动！！！不滑动此时就一直循环判断了！！！
				if(cur.val<x){
					cur = cur.next;
				}
			}
			else{
				cur = cur.next;
			}
		}
		return head;
	        

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
