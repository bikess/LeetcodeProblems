package com.problems6;

import java.util.ArrayList;

/***
 * 题目意思是：两个非负数反向存储在两个链表链表中，然后返回求得的这两个数的和的链表
 * 例如 两个数,342 465  和为807
 * 在链表中为：(2 -> 4 -> 3) + (5 -> 6 -> 4),返回的和的链表为 7 -> 0 -> 8，
 * @author bike
 *
 */
public class AddTwoNum {

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
}
/**
 * 思路1：不用新建一个链表，直接在两个链表的基础上修改
 * @param l1
 * @param l2
 * @return
 */

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		ListNode cur1 = l1,cur2 = l2;
//		标示低位到高位的进位
		int p = 0;
		ListNode pre = null;
		while(cur1!=null&&cur2!=null){
			int num =p+cur1.val+cur2.val;
			if(num>9){
				cur1.val = num-10;
				p = 1;
			}else{
				cur1.val = num;
				p=0;
			}
			pre = cur1;
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		if(cur1==null){
			pre.next = cur2;
		}
		while(pre.next!=null&&p!=0){
			int num = p + pre.next.val;
			if(num>9){
				pre.next.val = num - 10;
				p=1;
			}else{
				pre.next.val = num;
				p=0;
			}
			pre = pre.next;
		}
		if(p!=0){
			ListNode node = new ListNode(p);
			node.next = null;
			pre.next = node;
		}
		return l1;
		        
		    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddTwoNum a = new AddTwoNum();
		ListNode l1 = a.new ListNode(7);
		ListNode l2 = a.new ListNode(9);
		l2.next = a.new ListNode(9);
		ListNode head = a.addTwoNumbers2(l1, l2);
		while(head!=null){
			System.out.println(head.val);
			head = head.next;
		}
	}
/***
 * 题目扩展：上面的题目链表的低位数存储在链表的前面，而高位存储在后面，
 * 若反过来，低位数存储在链表的后面（链表头是高位），而高位数存储在链表的前面（链尾是低位）；
 * 此时的解题思路
 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode cur1 = l1,cur2 = l2;
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		while(cur1!=null||cur2!=null){
			if(cur1!=null)
			{	list1.add(cur1.val);
				cur1 = cur1.next;
			}
			if(cur2!=null){
				list2.add(cur2.val);
				cur2 = cur2.next;
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		int n1 = list1.size()-1,n2 = list2.size()-1;
		int p =0;
		while(n1>=0&&n2>=0){
			int r = list1.get(n1)+list2.get(n2)+p;
			ret.add(r%10);
			p = r/10;
			n1--;
			n2--;
		}
		while(n1>=0){
			int r = list1.get(n1)+p;
			ret.add(r%10);
			p = r/10;
			n1--;
		}
		while(n2>=0){
			int r = list2.get(n2)+p;
			ret.add(r%10);
			p = r/10;
			n2--;
		}
		if(p!=0)
		{
			ret.add(p);
		}
		if(ret.size()<1){
			return null;
		}
		ListNode head = new ListNode(ret.get(ret.size()-1));
		ListNode pre = head;
		for(int i=ret.size()-2;i>=0;i--){
			ListNode tem = new ListNode(ret.get(i));
			pre.next = tem;
			pre = pre.next;
		}
		return head;
		
	}
}
