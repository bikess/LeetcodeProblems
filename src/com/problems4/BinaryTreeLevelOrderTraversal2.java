package com.problems4;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class BinaryTreeLevelOrderTraversal2 {




/***
 * 方法1 递归求解
 * @param root
 * @return
 */
     public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
    	 if(root == null){
    		 return null;
    	 }
    	 ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    	 ArrayList<Integer> list = new ArrayList<>();
    	 addNode(result,1,1,list,root);
    	 return result;
	        
	    }

	private void addNode(ArrayList<ArrayList<Integer>> result,int depth,int now, ArrayList<Integer> list, TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null){
			
		}
//		addNode(result,list,2,now+1,root.left);
//		addNode(result,list,root.right);
		list.add(root.val);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeLevelOrderTraversal2 m = new BinaryTreeLevelOrderTraversal2();
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);

		one.left = two;
		one.right = three;
		two.left = new TreeNode(6);
		two.right = new TreeNode(5);
		m.levelOrderBottom21(one);
		
	}
	/***
	 * 方法2：利用辅助队列、,此处利用了2个辅助队列
	 */
	
	   public ArrayList<ArrayList<Integer>> levelOrderBottom21(TreeNode root) {
		     ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		    	 if(root == null){
		    		 return result;
		    	 }
		    	 Queue<TreeNode> deque1 = new LinkedList<TreeNode>();
		    	 Queue<TreeNode> deque2 = new LinkedList<TreeNode>();
		    	 deque1.add(root);
		    	 
		    	 while(!deque1.isEmpty()){
		    		 ArrayList<Integer> list = new ArrayList<>();
		    		 while(!deque1.isEmpty()){
		    			 TreeNode temp = deque1.remove();
		    			 //System.out.println(temp.val);
		    			 list.add(temp.val);
		    			 if(temp.left!=null){
		    				 deque2.offer(temp.left);
		    			 }
		    			 if(temp.right!=null){
		    				 deque2.offer(temp.right);
		    			 }
		    		 }
		    		 result.add(list);
		    		 deque1 = deque2;
		    		 deque2 = new LinkedList<TreeNode>();
		    	 }
		    	 ArrayList<ArrayList<Integer>> r = new ArrayList<>();
		    	 for(int i= result.size()-1;i>=0;i--){
		    		 r.add(result.get(i));
		    	 }
		    	 //System.out.println(r.toString());
		    	 return r;
			        
			    }
	    /***
	     * 此处利用了一个标志位
	     * @param root
	     * @return
	     */
	   public ArrayList<ArrayList<Integer>> levelOrderBottom22(TreeNode root) {
	    	
	    	 ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	    	 if(root == null){
	    		 return result;
	    	 }
	    	 Queue<TreeNode> deque1 = new LinkedList<TreeNode>();
//	    	 这里利用另一个deque而导致超时
//	    	 Queue<TreeNode> deque2 = new LinkedList<TreeNode>();
	    	 deque1.add(root);
	    	 deque1.add(new TreeNode(Integer.MIN_VALUE));
	    	 ArrayList<Integer> list = new ArrayList<>();
	    	 while(!deque1.isEmpty()){
//	    		注意这里队列的add与push的区别，add、offer是在队列尾部添加元素，而push是在队列的头部添加元素	  
	    		TreeNode temp = deque1.remove();
//	    	     System.out.println(temp.val);
//	    		设置一个每一层的标志位！！
	    		if(temp.val!=Integer.MIN_VALUE){
	    				 list.add(temp.val);
	    		 }else{
	    			 result.add(list);
	    			 list = new ArrayList<>();
	    			 System.out.println(deque1.size());
	 	    		 if(!deque1.isEmpty())
		    		 {
	 	    			 System.out.println("nimei");
		    			 deque1.add(new TreeNode(Integer.MIN_VALUE));
		    		 }
	    			 continue ;
	    		 }
	    		if(temp.left!=null){
	    				 deque1.add(temp.left);
	    			 }
	    		if(temp.right!=null){
	    				 deque1.add(temp.right);
	    		}
	    	 }
	    	 ArrayList<ArrayList<Integer>> r = new ArrayList<>();
	    	 for(int i= result.size()-1;i>=0;i--){
	    		 r.add(result.get(i));
	    	 }
	    	 System.out.println(r.toString());
	    	 return r;   
		    }
	   /***
	    * 方法2的变形，不再利用两个队列而是利用一个队列，利用整型来判断深度,此方法接收了
	    */
	   public ArrayList<ArrayList<Integer>> levelOrderBottom3(TreeNode root) {
		   ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		   if(root == null){
	    		 return result;
	    	 }
		   Queue<TreeNode> q = new LinkedList<TreeNode>();
		   q.add(root);
//		   记录当前level和下一个level的节点的数目，这里用两个整型量来标示层数，因此不会出现超时
		   int currentlevel = 1;
		   int nextlevel =0;
		   ArrayList<Integer> list = new ArrayList<>();
		   while(!q.isEmpty()){
			   TreeNode temp = q.remove();
//			   当前深度的节点数减1
			   currentlevel--;
			   list.add(temp.val);
			   if(temp.left!=null){
				   q.add(temp.left);
				   nextlevel++;
			   }
			   if(temp.right!=null){
				   q.add(temp.right);
				   nextlevel++;
			   }
			   if(currentlevel==0){
				   result.add(list);
//				   这里一定要注意，重新初始化一下list，！！
				   list = new ArrayList<>();
				   currentlevel = nextlevel;
				   nextlevel = 0;
			   }
		   }
//		   反转链表，得到结果
		   ArrayList<ArrayList<Integer>> r = new ArrayList<>();
	       for(int i= result.size()-1;i>=0;i--){
	    		 r.add(result.get(i));
	    	 }
	       return r;
	   }
}
