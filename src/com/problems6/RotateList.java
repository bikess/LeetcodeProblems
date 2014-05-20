package com.problems6;
/***
 * 题目意思：就是循环移动链表k个元素，若是链尾元素循环移动到链头
 * @author bike
 *
 */
public class RotateList {

	class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
	}
/***
 * 思路:先计算出链表中的节点总数，然后根据节点总数判断循环移动的实际移动次数。
 * 由这个实际移动的次数对链表进行循环移动
 * @param head
 * @param n
 * @return
 */
	public ListNode rotateRight(ListNode head, int n) {
		if(head==null){
			return head;
		}
		ListNode cur = head;
		ListNode pre = head;
		int num = 0;
		while(cur!=null){
			num++;
			cur = cur.next;
		}
		int rotate = 0;
		if(n<num){
			rotate = n;
		}
		else{
			rotate = n%num;
		}
		cur = head;
		while(rotate!=0){
			cur = cur.next;
			rotate--;
		}
//		此时pre指向的位置是第1个元素的前驱节点
		if(cur==head){
			return head;
		}else{
			while(cur.next!=null){
				cur = cur.next;
				pre = pre.next;
			}
			cur.next = head;
			head = pre.next;
			pre.next = null;
			return head;
		}
		    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
