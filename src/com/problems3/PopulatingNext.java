package com.problems3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class TreeLinkNode {
	     int val;
	     TreeLinkNode left, right, next;
	     TreeLinkNode(int x) { val = x; }
	  }
public class PopulatingNext {

	/***
	 * 方法1 采取的是广度优先遍历的方法，但是使用了2个队列，其中一个队列压入，另一个队列出
	 * @param root
	 */
	  public void connect(TreeLinkNode root) {
		  	if(root==null){
		  		return;
		  	}
		  	Deque<TreeLinkNode> q1 = new ArrayDeque<>();
		  	Deque<TreeLinkNode> q2 = new ArrayDeque<TreeLinkNode>();
		  	q1.add(root);
		  	while(!q1.isEmpty()){
		  		while(!q1.isEmpty()){
		  			q2.add(q1.pop());
		  		}
		  		q1.clear();
		  		TreeLinkNode t = q2.pop();
	  			if(t.left!=null)
	  				q1.add(t.left);
	  			if(t.right!=null)
	  				q1.add(t.right);
		  		t.next = null;
		  		while(!q2.isEmpty()){
		  			TreeLinkNode out = q2.pop();
		  			if(out.left!=null)
		  				q1.add(out.left);
		  			if(out.right!=null)
		  				q1.add(out.right);
		  			out.next = null;
		  			t.next = out;
		  			t =out;
		  		}
		  	}
	  }
	/***
	 * 方法2 也是采取广度优先遍历的方法但是利用一个null作为每一层结束的标志
	 */
	  
	  public void connect2(TreeLinkNode root) {
		  if(root == null)
			  return;
		  Deque<TreeLinkNode> q = new ArrayDeque<TreeLinkNode>();
		  q.add(root);
//		   设置每一行结束的标志,不能用null作为队列中的标志位！！！
		  TreeLinkNode b = new TreeLinkNode(Integer.MIN_VALUE);
		  q.add(b);
		  while(!q.isEmpty()){
			  TreeLinkNode t = q.pop();
			  if(t==b)
			  {
			      q.add(b);
			      if(q.size()==1)
					  break;
				  continue;
			  }
			  else{
				  if(t.left!=null)
					  q.add(t.left);
				  if(t.right!=null)
					  q.add(t.right);
				  if(q.peek()!=b)
				    t.next = q.peek();
				  else
				    t.next = null;
			  }
		  }
	  }
	 /***
	  * 
	  * 方法3 利用递归的方法求解
	  */
	  
	  public void connect3(TreeLinkNode root) {
		  if(root ==null)
			  return;
		  TreeLinkNode cur = root.next;
		  TreeLinkNode p = null;
//		  这首寻找root.right节点的后继节点，为上层root节点的处于同一层的所有节点的第一个不为空的孩子节点。
		  while(cur!=null){
			  if(cur.left!=null){
				  p = cur.left;
				  break;
			  }
			  if(cur.right!=null){
				  p = cur.right;
				  break;
			  }
			  cur = cur.next;
		  }
//		  若root节点的右孩子不为空，则root的孩子节点的next指向上面求得的节点
		  if(root.right!=null){
			  root.right.next = p;
		  }
//		  若root节点的左孩子不为空，则左孩子指向其右孩子
		  if(root.left!=null){
			  root.left.next = root.right;
		  }
//		  递归左右孩子进行next指针的连接，每次构建next指针实际上是构建root节点的孩子节点的next指针！！而root节点所处的层的next指针早就已经构建完成了！！
		  connect3(root.right);
		  connect3(root.left);
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
