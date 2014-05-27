package com.problems6;
/***
 * 题目意思：
 * 求两个数的除法，不能用乘法运算、除法运算、与取余运算
 * @author bike
 *
 */
public class DivideTwoIntegers {
	/***
	 * 方法1，用被除数一直减除数，直到结果为0，则作除数的次数就是结果，但这种方法时间复杂度太大
	 * 方法2 思路：
	 * 由于不能用乘除和取余运算，因此只能用加减运算与位运算
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    public int divide(int dividend, int divisor) {
//    	除数为0，直接返回最大值
		if(divisor==0)
			return Integer.MAX_VALUE;
		int res = 0;
//		若被除数等于最小值,则若直接转换为正数，将会超过正数的表示范围，因此我们把dividend加上除数divisor的绝对值，这样dividend就不会再溢出了！！！
    	if(dividend==Integer.MIN_VALUE){
    		res = 1;
    		dividend = dividend + Math.abs(divisor);
    	}
//    	若除数等于最小值，直接返回结果，因为除数等于最小值，要么被除数等于最小值，返回1；要么其他情况下，均是返回0；
    	if(divisor==Integer.MIN_VALUE)
    		return res;
//    	判断两个数是否同号，同号的正，异号得负，两个数字异或后的值向左无符号移动31为，可以判断出最高位即符号为是正数还是负数。
    	boolean isNeg = ((dividend^divisor)>>>31==1)? true:false;
    	dividend = Math.abs(dividend);
    	divisor = Math.abs(divisor);
    	int digit = 0;
		// 被除数右移1位，除数左移1位
    	while(divisor<=(dividend>>1)){
    		divisor = divisor<<1;
    		digit++;
    	}
    	while(digit>=0){
    		if(dividend>=divisor){
    			dividend = dividend-divisor;
//    			这里一定注意(1<<digit)需要加括号，优先级的问题
    			res = res +(1<<digit);
    		}
    		divisor  = divisor>>1;
    		digit--;
    	}
    	return isNeg? -res:res;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
