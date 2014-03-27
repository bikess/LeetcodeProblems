package com.problems2;
/***
 * 问题描述，就是判断一个链表是否有环存在，若有环返回环开始的位置，若没有环返回null。
 * 要求不能使用额外的存储空间
 * 方法：判断是否有环的方法是，一个快指针，一个慢指针，快的每次遍历2个节点，慢的每次遍历
 * 1个节点，当2个指针重合的是否表明存在环，而若指针不重合表明没有环存在
 * 关键的问题是确定环开始的位置！！！
 * 返回环开始的位置的方法是：
 * 设环的长度为l，当2个指针第一次相遇时，慢的走了m步，快的走了2*m步，则快的比慢的多走了m步，
 * 假设 慢的走的路程 s = 起始到环开始+环开始到相遇 记为：s = x + y
 * 快的走的路程 f = 起始到环的开始 + 2 * 环开始到相遇 + 相遇再到环开始：记为 f = x+2*y+z
 * 则又有 f = 2*s 可以推出来，x = z
 * 因此当相遇的时候，让一个从相遇的位置继续走，而另一个从链表开始位置走，最终相遇的位置必然是环开始的位置
 * @author bike
 *
 */

 class LinkNode {
     int val;
     LinkNode next;
     LinkNode(int x) {
         val = x;
         next = null;
     }
 }
public class LinkedListCycle {

//	ci方法只用来判断链表中是否存在环
	 public boolean detectCycle(LinkNode head) {
		LinkNode fast = head;
		LinkNode slow = head;
		if(head==null||head.next==null)
			return false;
		while(fast.next !=null){
			fast = fast.next;
			slow = slow.next;
			if(fast.next!=null){
				fast = fast.next;
				if(fast == slow){
					return true;
				}
			}else{
				return false;
			}
		}
		return false;   
}
	 
//	 此方法不但要判断链表中是否存在环，还要返回环开始的位置
	  public LinkNode detectCycleReturn(LinkNode head) {
		
		  if(head==null||head.next == null)
			  return null;
	      LinkNode fast = head;
	      LinkNode slow = head;
	      while(fast.next !=null){
	    	  fast = fast.next;
	    	  slow = slow.next;
	    	  if(fast.next!=null){
	    		  fast = fast.next;
	    		  if(fast==slow){
	    			  fast = head;
	    		      while(slow!=fast){
	    		    	  slow = slow.next;
	    		    	  fast = fast.next;
	    		      }
	    		      return slow;
	    		  }
	    	  }else{
	    		  return null;
	    	  }
	      }
	      return null;
	    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
