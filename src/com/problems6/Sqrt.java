package com.problems6;
/***
 * 求某个数的平方根
 * @author bike
 *
 */
public class Sqrt {

	/*
	 * 思路：牛顿迭代法：
	 * 迭代公式：x(i+1) = (x(i)+n/(xi))/2
	 */
    public int sqrt(int x) {
    	if(x==0)
    		return 0;
    	double pre;
    	double cur = 1;
    	do{
    		pre = cur;
    		cur = x/(2*pre)+pre/2.0;
    	}while(Math.abs(cur-pre)>0.00001);
		return (int)cur;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
