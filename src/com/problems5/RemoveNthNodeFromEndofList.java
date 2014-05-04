package com.problems5;

public class RemoveNthNodeFromEndofList {
/***
 * 题目大意：在一个链表中，从链表删除第n个数（从链表末尾数第n个数），并返回链表的头部
 * @author bike
 *
 */
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
        next = null;
     }
 }

/***
 * 方法1 先遍历一遍链表得到链表中的元素个数，然后第二遍遍历的时候删除相应的元素
 * @param head
 * @param n
 * @return
 */
   public ListNode removeNthFromEnd(ListNode head, int n) {
	   
	   int len = 0;
	   ListNode p = head;
	   while(p!=null){
		   len++;
		   p = p.next;
	   }
	   if(len<n){
		   return head;
	   }
//	        若删除的是头元素
	   if(n==len){
		   head = head.next;
		   return head;
	   }
	   p = head;
	   int pla = len-n;
	   while(p!=null&&pla>1){
		   p = p.next;
		   pla--;
	   }
	   p.next = p.next.next;
	   return head;
		        
   }
   /***
    * 方法2 ： 若只要求循环一次就能得到结果请问如何求得结果！！！！！！
    * 思路：设置2个指针，进行遍历！！！其中第一个指针首先遍历，当第一个指针遍历了n步的时候，第二个指针开始进行遍历，当第一个指针遍历的指针尾部的时候，第二个指针的位置
    * 便是要删除的节点的位置
    */
   public ListNode removeNthFromEnd2(ListNode head, int n) {
	   ListNode front = head;
	   while(front!=null&&n>1){
		   front = front.next;
		   n--;
	   }
	   if(front==null){
		   return head;
	   }
//	      这里看是否删除的节点是头节点
	   if(front.next==null){
		   head = head.next;
		   return head;
	   }
//	   after 指向的位置是要删除的节点的前驱节点
	   ListNode after = head;
	   front = front.next;
	   while(front.next!=null){
		   front = front.next;
		   after = after.next;
	   }
	   after.next = after.next.next;
	   return head;
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
