package com.problems6;
/***
 * 
 * 题意：把链表中的节点k个一组，进行逆转。不足k个的无需逆转
 * 例如：Given this linked list: 1->2->3->4->5

	For k = 2, you should return: 2->1->4->3->5

	For k = 3, you should return: 3->2->1->4->5
 * @author bike
 *
 */
public class ReverseNodesinkGroup {

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
 * 思路1:找到每一个k组节点，把每一个k组节点逆转.
 * 关键点包括：
 * （1）注意设置一个节点保存该链表的始终的头节点
 * （2）注意每一个k组节点的逆转
 * @param head
 * @param k
 * @return
 */

	public ListNode reverseKGroup(ListNode head, int k) {
		
		if(head == null||k==1){
			return head;
		}
		ListNode  before = new ListNode(0);
		ListNode myHead = new ListNode(0);
		boolean flag = false;
		myHead.next = head;
		before.next= head;
		ListNode cur = head;
		ListNode start = head;
		ListNode end = head;
		int num =1;
		while(cur!=null){
			while(end!=null&&num<k){
				end = end.next;
				num++;
			}
//			此时end要么指向第k个节点，要么指向为空
//			若指向为空
			if(end==null)
				return myHead.next;
//			指向不为空则逆转此k个节点
			num=1;
			while(num<k){
				ListNode temp = start.next;
				start.next = start.next.next;
				temp.next = before.next;
				before.next = temp;
				num++;
			}
			if(flag==false){
				myHead.next = before.next;
				flag = true;
			}
			before = start;
			end = start.next;
			start = end;
			cur = end;
			num=1;
		}
		
			
		return head;
		        
		   
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
