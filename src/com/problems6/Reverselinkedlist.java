package com.problems6;
/***
 * 
 * 翻转单链表，就是将单链表翻转过来。
 * @author bike
 *
 */
public class Reverselinkedlist {
	class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
     val = x;
     next = null;
     }
}

	/**
	 * @param args
	 */
	/**
	 * 
	 * 一种思路若不是原地翻转的话，可以建立一个新的链表，直接形成新的逆转后的链表
	 * 
	 * 思路2：原地翻转，就是从第1个节点开始，每次将节点的next移动到当前的head节点（head节点可以临时建立）的前面，
	 * 此思路的关键点！！！！就是设置一个辅助的头节点永远指向当前链表的头节点!!!!!!!!!
	 * 例如原来的链为：head-》1-》2-》3-》4
	 * 一次翻转后为：head-》2-》1-》3-》4
	 * 二次翻转后：head->3->2->1-》4
	 * 三此翻转后：head-》4-》3-》2-》1
	 * 返回head。next
	 * @param args
	 */
	public ListNode reverseBetween(ListNode head) {
		
		if(head==null){
			return null;
		}
		ListNode MaxHead = new ListNode(0);
		MaxHead.next = head;
		ListNode cur = head;
		while(cur.next!=null){
			ListNode temp = cur.next;
			cur.next = cur.next.next;
			temp.next = MaxHead.next;
			MaxHead.next = temp;
		}
		cur.next = null;
		return MaxHead.next;
		        
		    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reverselinkedlist r = new Reverselinkedlist();
		ListNode h = r.new ListNode(1);
		ListNode h1 = r.new ListNode(2);
		ListNode h2 = r.new ListNode(3);
		h.next =h1;
		h1.next = h2;
		h2.next = null;
		ListNode m = h;
		while(m!=null){
			System.out.println(m.val);
			m = m.next;
		}
		ListNode head = r.reverseBetween(h);
	
		while(head!=null){
			System.out.println(head.val);
			head = head.next;
		}
	}

	
	/***
	 * 扩展题目，现在不是要求翻转整个的链表了，而是要求翻转第m个节点到第个节点.
	 * 前提条件，m，n满足
	 * 1 ≤ m ≤ n ≤ length of list.
	 * 思路与前面的基本一致，就是
	 */
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head==null){
			return null;
		}
		if(head.next==null){
			return head;
		}
    	int curnum =1;
    	ListNode before = new ListNode(0);
//    	先找到第m个节点的前驱节点
    	ListNode cur = head;
//    	其实的时候before指向头节点
    	before.next = head;
    	while(curnum!=m){
    		before = cur;
    		cur = cur.next;
    		curnum++;
    	}
//    	before节点指向了第m个节点的前驱节点,而cur当前指向的的正是第m个节点
    	while(curnum!=n){
    		ListNode temp = cur.next;
    		cur.next = cur.next.next;
    		temp.next = before.next;
    		before.next = temp;
    		curnum++;
    	}
    	if(m!=1)
    		return head;
    	else
    		return before.next;
        
    }
}
