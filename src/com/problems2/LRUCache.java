package com.problems2;

import java.util.HashMap;
import java.util.List;


/***
 * 
 * @author bike
 *	
 */
class CacheNode{
	int key;
	int value;
	CacheNode pre,next;
	public CacheNode(int k,int v) {
		this.key = k;
		this.value = v;
		this.pre = null;
		this.next = null;
	}
}
public class LRUCache {
	int capacity;
//	这里的双向链采用的是有专门的链头与链尾的双向链
	CacheNode head;
	CacheNode tail;
	int nodenum;
//	注意hashmap中key存储的是目前在cache中的key，通过key可一直直接找到key对应的CacheNode节点，也就是链表中这个key对应的CacheNode的地址，可以快速的
//	把目前Key对应的CacheNode移动到链表的头部。HashMap的作用还有就是可以迅速判断cache中是否存在这个key。
	HashMap<Integer, CacheNode> cacheMap;
	public LRUCache(int capacity){
		if(capacity <1 )return;
		this.capacity  = capacity;
		cacheMap = new HashMap<>();
		head = new CacheNode(0, 0);
		tail = new CacheNode(0, 0);
		head.next = tail;
		tail.pre = head;
		nodenum = 0;
	}
	int get(int key){
		if(cacheMap.containsKey(key)){
//			包含这个key，将这个key移动到双向链表的头部
			CacheNode cur = cacheMap.get(key);
//			这里是把cur移动后链给接住
			cur.pre.next = cur.next;
			cur.next.pre = cur.pre;
			
//			把cur移动到链头
			cur.next = head.next;
			cur.pre = head;
			head.next.pre = cur;
			head.next = cur;
			
			return cur.value;
		}else{
			return -1;
		}
	}
	void set(int key,int value){
		if(capacity<1) return;
//		首先在HashMap中看是否已经存在这个key
		if(cacheMap.containsKey(key)){
//			若已经存在，看是否在链表的头部，否则不他弄到链表的头部
			CacheNode node = cacheMap.get(key);
			node.pre.next = node.next;
			node.next.pre = node.pre;
				
//			更新value的值
			node.value = value;
//			移动到链头
			node.next = head.next;
			node.pre = head;
			head.next.pre = node;
			head.next = node;
		}else{
//			若cache中不包含这个key，那么就把key插入map
			CacheNode node  = new CacheNode(key, value);
			if(nodenum<capacity){
//				没有达到cache的容量直接把key插入map,把CacheNode插入表头
				nodenum++;	

			}else{
//				若已经到达cache的容量，则把链表头元素对应的key从map中移除，同时把该cachenode从链表中移除
				CacheNode dnode = tail.pre;
				tail.pre = dnode.pre;
				dnode.pre.next = tail;
				cacheMap.remove(dnode.key);
			}
//			插入；链头
			cacheMap.put(key,node);
			node.next = head.next;
			node.pre = head;
			head.next.pre =node;
			head.next = node;
		}
	}
}
