package com.problems2;
/**
 * Sort a linked list using insertion sort.
 * @author bike
 * 对一个链数组进行插入排序,从小到大排序
 * 一定注意列表的操作技巧，尤其是单链，往往是采取 p.next的形式进行操作，操作的元素是p.next,但是可以很容易获得操作元素的前驱P和后继p.next.next
 * 若对p操作则无法获取前驱
 */
class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val = val;
		this.next = null;
	}
}
public class InsertSortList {

	/**
	 * @param args
	 */
	public static ListNode insertSort(ListNode head){

		if(head ==null){
			return null;
		}
//		代表被插入的节点
		ListNode inserted = head;
		while(inserted.next !=null){
			ListNode p = head;
//			若被插入的节点元素比此时的元素小则插入元素
//			这里是对当插入位置是链的头部时候进行处理
			if(inserted.next.val<=p.val){
//				下一个要排序的位置元素
				p = inserted.next.next;
				inserted.next.next = head;
				head = inserted.next;
				
//				重新定义赋值inserted.next;
				inserted.next = p;
			}else{
				while(!(inserted.next.val<=p.next.val)){
					p = p.next;
				}
				if(p!=inserted){
					ListNode tmp = inserted.next;
					inserted.next = tmp.next;
					tmp.next = p.next;
					p.next = tmp;
				}else{
					inserted = inserted.next;
				}
			}
		}
		return head;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(3);
		ListNode one = new ListNode(2);
		ListNode two = new ListNode(4);
		head.next = one;
		one.next = two;
		
		ListNode L = insertSort(head);
		while(L!=null){
			System.out.println(L.val);
			L = L.next;
		}
	}

}
