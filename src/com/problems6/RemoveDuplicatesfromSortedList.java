package com.problems6;
/***
 * 
 * 题目意思，删除有效链表中所有重复节点
 * @author bike
 *
 */
public class RemoveDuplicatesfromSortedList {

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
 * 方法1 自己的思路 
 * （1）设置一个flag标志，目前有没有设置头节点
 * （2）设置myhead指针，永远指向当前链表的头节点
 * （3）before指针，永远指向上一个链表中可以存在的节点
 * 
 * @param head
 * @return
 */

	public ListNode deleteDuplicates(ListNode head) {
		
		if(head==null||head.next==null){
			return head;
		}
		ListNode myHead = new ListNode(0);
		ListNode before  = new ListNode(0);
		boolean flag = false;
		before.next = head;
		myHead.next = null;
		ListNode cur = head;
		ListNode chong = head;
		while(cur.next!=null){
			if(cur.next.val!=cur.val){
				if(flag==false){
					myHead.next = cur;
					flag=true;
				}
				before = cur;
				cur = cur.next;
			}else{
				chong = cur;
				while(chong.next!=null&&chong.val==chong.next.val){
					chong = chong.next;
				}
				before.next = chong.next;
				if(chong.next==null){
					return myHead.next;
				}else{
					cur = chong.next;
				}
			}
			
		}
		if(cur!=null){
			if(flag==false){
				myHead.next = cur;
			}else{
				before.next = cur;
			}
		}
		return myHead.next;
		        
		    
	}
	/**
	 * 
	 * 方法2 ，采取递归删除的方法
	 * @param args
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode cur = head;
		while(cur.next!=null&&cur.val==cur.next.val){
			cur = cur.next;
		}
//		经过此循环，cur.next要么指向了null。要么指向了下一个节点
//		若当前的cur不等于头节点了，说明此时需要改变头节点
		if(cur!=head){
			while(head!=cur.next){
				ListNode temp = head;
				head = head.next;
			}
//			循环完毕后，head要么指向null，要么指向了下一个节点
			return deleteDuplicates2(cur.next);
		}
//		若此是循环cur仍然指向头节点，此时需要判断下一个
		head.next = deleteDuplicates2(head.next);
		return head;
		
	}
	/***
	 * 
	 * 方法3 参考别人的思路
	 * @param args
	 */
	public ListNode deleteDuplicates3(ListNode head) {
		if(head==null){
			return head;
		}
//		myhead永远指向当前要返回的链表的头节点
		ListNode myhead = new ListNode(0);
		myhead.next = head;
		ListNode cur = head.next;
//		preCur 指向cur的前驱节点
		ListNode preCur = head;
		ListNode Last = myhead;
//		保证当前节点不为null
		while(cur!=null){
//			用于标示当前与前驱值是否相等
			boolean flag = false;
//			判断前驱值是否等于后面的值
			if(cur!= null&&cur.val==preCur.val){
				flag = true;
			}
			while(flag==true&&cur!=null&&cur.val==preCur.val){
				cur = cur.next;
			}
//			若flag为true，表明至少有2个节点重复，此时cur要么指向null，要么指向的是第一个与前驱值不同的节点，last.next 指向了cur
			if(flag==true){
				Last.next = cur;
				preCur  = cur;
//			falg 为false,找到了第一个不是重复元素的节点，此时cur要么为null
			}else{
				Last = preCur;
				preCur = cur;
			}
            if(cur != null)
                cur = cur.next;
		}
		return myhead.next;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
