package com.problems7;

import java.util.Stack;

/**
 * 题目意思：给一些非负数(注意考虑0的情况)，代表了矩形的高度，所有的矩形的宽带都是1，
 * 问这些非负数组成的矩形按照数组的输入排在一起，所组合形成的矩形的最大值为多少
 * @author bike
 *
 */
public class LargestRectangleinHistogram {
	/***
	 * 思路1 ：0（n^2）的时间复杂度
	 * 两层循环，开始从i位置开始，一次计算i位置往后，任何一个位置到i位置的矩形的面积，算出这些矩形面积中最大的即为所求，需要有一个变量记录当i位置的矩形的
	 * 矩形高度最小的那一个
	 * 但是这种方法，在求解大的集合的时候超时了
	 *
	 * @param height
	 * @return
	 */
    public int largestRectangleArea(int[] height) {
    	int len = height.length;
    	if(len<=0){
    		return 0;
    	}
    	int maxResult = 0;
    	boolean isSame = true;
    	for(int i=0;i<len;i++){
    		int minHeight = height[i];
    		for(int j=i;j<len;j++){
    			if(height[j]!=height[i]){
    				isSame = false;
    			}
    			if(height[j]<minHeight){
    				minHeight = height[j];
    			}
    			if(minHeight*(j-i+1)>maxResult)
    			{
    				maxResult = minHeight*(j-i+1);
    			}
    		}
    		if(isSame==true){
    			break;
    		}
    	}
		return maxResult;

    }
    /***
     * 采取辅助栈的方法，用辅助栈来存储,辅助栈是好东西！！有时候要存储一些用到的东西要用栈
     * @param args
     */
    class node{
    	int index;
    	int height;
    	public node(int index,int height){
    		this.index = index;
    		this.height = height;
    	}
    }
    public int largestRectangleArea2(int[] height) {
		
    	Stack<node> s = new Stack<>();
    	int result = 0;
    	for(int i=0;i<height.length;i++){
    		if(s.isEmpty()){
    			s.add(new node(i, height[i]));
    		}
    		else{
    			while(!s.isEmpty()){
    				node temp = s.peek();
//    				若当前的节点高度，比栈顶的节点的高度大，则入栈
    				if(temp.height<=height[i]){
    					s.add(new node(i, height[i]));
    					break;
    				}else{
//    					否则栈顶的元素出栈，此时有比之前的元素的高度小
    					s.pop();
//    					下一个栈顶存储的就是比刚弹出的元素的高度小的，则位于刚刚弹出的栈的temp元素与当前栈顶元素s.peek这两个元素之间的那些高度都是比
//    					弹出的栈顶元素的height高度大的
    					int leftindex = s.isEmpty()? 0:s.peek().index+1;
    					result = Math.max(result, (i-temp.index+temp.index-leftindex)*temp.height);
    					
    				}
    			}
    			if(s.isEmpty()){
    				s.add(new node(i, height[i]));
    			}
    		}
    	}
    	int index = height.length;
    	while(!s.isEmpty()){
    		node temp = s.peek();
    		s.pop();
    		int leftindex = s.isEmpty()? 0:s.peek().index+1;
    		result = Math.max(result, (index-temp.index+temp.index-leftindex)*temp.height);
    	}
    	return result;
    	
    }
/***
 * 方法3 仍然是辅助栈的使用，其思路与方法2相同
 * @param height
 * @return
 */
    public int largestRectangleArea3(int[] height) {
    	if(height==null||height.length==0)
    		return 0;
    	int result = 0;
    	Stack<node> s = new Stack<>();
    	for(int i=0;i<height.length;i++){
//    		当栈空，或者当前高度比栈顶的元素的高度高，则入栈
    		if(s.isEmpty()||s.peek().height<=height[i]){
    			s.add(new node(i, height[i]));
//    			当当前栈顶的元素的高度低，则出栈，计算从当前栈顶元素的index一致到当前i位置的面积
    		}else if(s.peek().height>height[i]){
    			int leftindex = 0;
    			while(!s.isEmpty()&&s.peek().height>height[i]){
    				node temp = s.pop();
    				leftindex = temp.index;
    				int tempArea = temp.height*(i-leftindex);
    				if(result<tempArea){
    					result = tempArea;
    				}
    			}
//    			 这里一定要注意加入的新元素，是加入的leftindex，而高度是height[i];这里leftindex表明i之前的所有高度中最后一个比自己值大的index
    			s.add(new node(leftindex, height[i]));
    		}
    		
    	}
    	while(!s.isEmpty()){
    		node temp = s.pop();
    		int leftindex = temp.index;
    		int tempArea = temp.height*(height.length-leftindex);
    		if(tempArea>result)
    			result = tempArea;
    	}
		return result;
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
