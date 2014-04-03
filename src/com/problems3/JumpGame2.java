package com.problems3;

public class JumpGame2 {

	/**
	 * @param args
	 */
	/***
	 * 题目描述：Given an array of non-negative integers, 
	 * you are initially positioned at the first index of the array.
	 * Each element in the array represents your maximum jump 
	 * length at that position.Your goal is to reach the last index 
	 * in the minimum number of jumps.For example:
	 * Given array A = [2,3,1,1,4]
	 * The minimum number of jumps to reach the last index is 2. 
	 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
	 * 就是求从位置0跳到最后一个位置的最小的跳数，每一个位置的跳的最大位数是
	 * 该位置的数值。
	 * @param A
	 * @return
	 */
	/***
	 * 思路1：采用动态规划的方法，建立一个辅助数组，数组记录，从该位置保存跳到末尾所需要的
	 * 最小的跳数
	 * @param A
	 * @return
	 */
//	但是此方法超时了!!!因为每一个位置的数都要从1一直判断到这个数，若数很大，判断起来必然超时
	public static int jump(int[] A) {
		int len = A.length;
		if(len<=1)
			return 0;
//		辅助数组，例如 minJump[len-1]表示从该位置跳到末尾所需要的最小跳数
		int [] minJump = new int[len];
		minJump[len-1] = 0;
		for(int i = len-2;i>=0;i--){
			minJump[i] = Integer.MAX_VALUE;
			int pos = 0;
			for(int j=A[i];j>=1;j--){
				if(i+j<len-1)
					pos = i+j;
				if(i+j>=len-1)
				{	
					pos = len-1;
					minJump[i]=Math.min(minJump[i],minJump[pos]+1);
					break;
				}
				minJump[i] = Math.min(minJump[i],minJump[pos]+1);
			}
		}
		return minJump[0];
	        
	}
	/***
	 * 思路2：采用递归的方法求解
	 * 题目的意思是跳到最后一个位置需要多少跳，其实就是把跳达想象成连接关系，就是利用宽带优先
	 * 遍历求解最短距离的问题
	 * @param args
	 */
	public static int jump2(int []A){
		int len = A.length;
		assert(len>=0);
		if(len<=1)
			return 0;
		int pre = 0,farest = A[0];
		int curJump = 1;
//		宽度优先遍历的思想
		while(pre<farest){
			if(farest>=len-1)
				return curJump;
			int t = farest;
			while(pre<=t){
				farest = Math.max(farest, A[pre]+pre);
				pre++;
			}
			--pre;
			curJump++;
		}
		return -1;
	}
	/***
	 * 思路3：采取优化的动态规划的方法
	 * @param args
	 */
	public static int jump3(int []A){
		int len = A.length;
//		数组存储的是到达当前i位置最小的跳数
		int step[] = new int[len];
		int lasti = 0,maxreach = A[0],reachindex = 0;
		for(int i=1;i<len;i++){
//			判断上一个i位置所能到达的最远的位置是否大于当前i的位置
//			若大于当前i的位置，则数组step[i]中保存到该位置的最小的跳数
			if(lasti+A[lasti]>=i){
				step[i] = step[lasti]+1;
			}
//			若不能到达当前i的位置
			else{
				step[i]= step[reachindex]+1;
				lasti = reachindex;
			}
//			若当前最远到达的位置不如目前i位置所能到达的位置
			if(i+A[i]>maxreach){
				maxreach = i+A[i];
				reachindex = i;
			}
		}
		return step[len-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2,3,1,1,4,3,1};
		int result =jump(A);
		System.out.println(result);
	}
	/***
	 * Given an array of non-negative integers, 
	 * you are initially positioned at the first index of the array.
	 * Each element in the array represents your maximum jump length at 
	 * that position.Determine if you are able to reach the last index.
	 * For example:A = [2,3,1,1,4], return true.
	 * A = [3,2,1,0,4], return false.
	 */
	/**
	 * 思路，利用贪心策略，每一步保证最大的移动步数，局部最优也是全局最优
	 * 也就是每一步在跳的时候，保证跳的位置最大
	 */
	boolean canJump(int A[]){
		if (A.length ==0){
			return false;
		}
		int step = A[0];
		for(int i=1;i<A.length;i++){
			if(step>0){
				step--;
				step = Math.max(step, A[i]);
			}
			else{
				return false;
			}
		}
		return true;
	}
//	贪心策略，每次选择目前所能到达的最大的位置作为当前选择
	boolean canJump2(int A[]){
		int maxi;
//		第0个位置元素所能到达最大的位置
		maxi = A[0];
		for(int i=1;i<A.length-1;i++){
//			若所能到达的最大位置比当前元素位置小，返回false
			if(maxi<i)
				return false;
//			i位置肯定保定可以到达，在i位置所能到达的位置与当前所能到达的最大位置比较
			if(i+A[i]>maxi){
				maxi = i +A[i];
			}
		}
		if(maxi>=A.length-1)
			return true;
		else
			return false;
	}
}
