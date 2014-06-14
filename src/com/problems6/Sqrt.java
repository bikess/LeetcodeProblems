package com.problems6;
/***
 * 求某个数的平方根
 * @author bike
 *
 */
public class Sqrt {

	/*
	 * 思路1：牛顿迭代法：
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
    
    
    /****
     * 采取二分法,每次取
     * @param args
     */
    public int sqrt2(int x){
    	if(x==0)
    		return 0;
    	int begin = 0;
    	int end = (x+1)/2;
    	while(begin<end){
    	 int mid = begin + (end-begin)/2;
    	 if(mid*mid==x)
    		 return mid;
    	 else if(mid*mid<x){
    		 begin = mid+1;
    	 }else{
    		 end = mid-1;
    	 }
    	}
    	if(end*end>x)
    		return end-1;
    	else{
    		return end;
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
