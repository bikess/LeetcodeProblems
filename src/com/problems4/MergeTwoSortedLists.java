package com.problems4;


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
      }
 }
      
public class MergeTwoSortedLists {

	/**
	 * @param args
	 */
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
	/**
	 * 方法2 递归求解
	 * @param args
	 */
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		ListNode result = null;
		if(l1.val<l2.val){
			result = l1;
			result.next = mergeTwoLists2(l1.next, l2);
		}
		else{
			result = l2;
			result.next = mergeTwoLists2(l1, l2.next);
		}
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
