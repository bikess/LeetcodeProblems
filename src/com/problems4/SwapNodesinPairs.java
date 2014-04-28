package com.problems4;

class LinkNode{
	int val;
	ListNode next;
	public LinkNode(int x){
		this.val = x;
		this.next = null;
	}
}
public class SwapNodesinPairs {

	 public ListNode swapPairs(ListNode head) {
		
		 if(head==null&&head.next==null)
			 return head;
		 ListNode newHead;
		 ListNode front = head;
		 ListNode after = head.next;
		 
		 newHead = after;
		 front.next = newHead.next;
		 newHead.next = front;
		 
		 front = newHead.next;
		 after = newHead.next.next;
		 while(after!=null&&after.next!=null){
			 front.next = after.next;
			 after.next = after.next.next;
			 front.next.next = after;
			 front = after;
			 after = front.next;
		 }
		 front.next = after;
		 return newHead;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
