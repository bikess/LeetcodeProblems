package com.problems2;

import java.util.HashMap;
import java.util.Map;

/***
 * 题目描述，就是深拷贝一个链表，而链表中，每一个节点除了有一个next指针外，还有一个随机的指向链表中其他任何一个节点的指针random
 * ，深拷贝的定义就是拷贝一个新的对象，两个对象是各自独立的个体
 * @author bike
 *
 */

class RandomListNode {
     int label;
     RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
}

public class CopyList {

	/**
	 * @param args
	 */
	/***
	 * 思路一：先拷贝链表next指针，然后在构造random指针
	 * 此时采用万能的HashMap，；利用Map保存每一个原始节点与新的复制节点的映射关系
	 * 然后利用这个映射关系，再次循环的时候构造每一个节点的random指针
	 * 由于需要一个Map来保存映射关系，因此空间复杂度为0（n）
	 * 由于是深拷贝链表，所以新的链表每一个节点都有指向新的复制节点的next指针与random指针
	 * @param head
	 * @return
	 */
    public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null)
    		return head;
        Map<RandomListNode,RandomListNode> map = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        
        map.put(head, newHead);
        RandomListNode p1 = head;
        RandomListNode p2 = newHead;
        p1 = p1.next;
//      利用hashmap 存储原来链表节点对应的拷贝的新的链表的节点，此处遍历只是复制next指针
        while(p1!=null){
        	RandomListNode tmp = new RandomListNode(p1.label);
        	p2.next = tmp;
        	map.put(p1, tmp);
        	p1 = p1.next;
        	p2 = p2.next;
        }
        p1 = head;
        p2 = newHead;
        while(p1 !=null){
        	RandomListNode random = p1.random;
        	if(random!=null)
        		p2.random = map.get(random);
        	p1 = p1.next;
        	p2 = p2.next;
        }
        return newHead;
    }
    
    /***
     * 思路2 ：不保存原节点与新节点的映射关系
     * 第一步：构造链，使原链的next指向复制的新节点，然后一个隔一个，也就是原链中任何两个节点A与B之间都插入的一个新的复制的节点A'，节点链依次变为
     * A A’B B'C C'
     * 这样做的核心目的就是这样以后可以很容易的取得新链的某个节点的random指针：new1-random = old1-random.next,本题的难点就是如何能够获取构造的新链的
     * 某个节点的random的地址
     * 
     * 第二步：构建新链中每一个新节点的random指针：
     * new1-random = old1-random.next
     * 
     * 第三步：恢复原始的链表以及构建新的链表
     * @param args
     * Accepted
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
		if(head ==null){
			return head;
		}
//		像这样的拷贝就是浅拷贝，也就是你对cur的操作，相应的head也会发生
		RandomListNode cur = head;
//		构造新的节点，把每一个新节点插入到其复制的节点的后面，这样利用 原节点的random的next指针就获得了复制节点的random指针位置
		while(cur!=null){
			RandomListNode newNode = new RandomListNode(cur.label);
			RandomListNode tmp = cur.next;
			cur.next = newNode;
			newNode.next = tmp;
			cur = tmp;
		}
//		把构造的新链拆成 原来的链表已经新的链表
		cur = head;

		
//		这里恢复一定注意一定要先设置random，因为一旦链结构破坏，则random就出现错误的
		while(cur!=null){
			if(cur.random!=null)
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}
//		保存新链的表头位置,接下来才是恢复2个链
		cur = head;
		RandomListNode newhead = null;
		newhead = cur.next;
		cur.next = newhead.next;
		cur = cur.next;
		RandomListNode newcur = newhead;
		while(cur!=null){
			newcur.next = cur.next;
			cur.next = cur.next.next;
			cur = cur.next;
			newcur = newcur.next;
		}
		return newhead;
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
