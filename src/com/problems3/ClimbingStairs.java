package com.problems3;

public class ClimbingStairs {

	public int climbStairs(int n) {
		if(n<=2)
			return n;
		int step[] = new int[n+1];
		step[1]=1;
		step[2]=2;
		for(int i=3;i<=n;i++){
			step[i]= step[i-1]+step[i-2];
		}
		return step[n];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
