package com.problems3;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) 
    {
    	val = x;
    	next = null;
    }
   
}
public class RemoveDuplicates {




    public ListNode deleteDuplicates(ListNode head) {
		if(head==null)
			return null;
    	ListNode p = head;
    	while(p!=null){
    		ListNode t = p.next;
    		if(t==null)
    			return head;
    		else if(t.val==p.val){
    			p.next = t.next;
    		}else{
    			p = p.next;
    		}
    		
    	}
    	return head;
		        
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
