package com.problems2;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

	You must do this in-place without altering the nodes' values.

	For example,
	Given {1,2,3,4}, reorder it to {1,4,2,3}
 * @author bike
 *
 */

class ListRNode{
     int val;
     ListRNode next;
     ListRNode(int x) {
        val = x;
        next = null;
     }
 }
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RecorderList {

	public void reorderList(ListRNode head) {    
	    List<ListRNode> list = new ArrayList<>();
	    ListRNode current = head;
//	    把所有的节点压入栈后，在按照需要的结果重新组织链
	    while(current!=null){
	    	list.add(current);
	    	current = current.next;
	    }
	    
	    int len = list.size();
	    for(int i =0;i<len/2;i++){
	    	int r = len-1-i;
	    	if(i<r){
	    		ListRNode tmp = list.get(i).next;
	    		list.get(i).next = list.get(r);
	    		list.get(r).next = tmp;
	    	}
	    }
	    if(len>0){
	    	list.get(len/2).next = null;
	    }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
