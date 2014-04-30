package com.problems4;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Pow {

	/**
	 * @param args
	 */
	/**
	 * 题意计算x的n次方，考虑复杂度和n的取值。
	   n有可能是正数或者负数，分开计算。
	 * @param x
	 * @param n
	 * @return
	 */
	/***
	 * 方法1 递归求解，时间复杂度0(n),此时出现栈溢出，错误的原因除了是未考虑：对于n取值INT_MIN时，-n并不是INT_MAX，也由于递归的次数太多造成，出现栈溢出
	 * @param x
	 * @param n
	 * @return
	 */
	 public double pow(double x, int n) {
		if(n==0){
			return 1.0;
		}
		if(n<0){
			if(n==Integer.MIN_VALUE)
				return 1.0/(pow(x, Integer.MAX_VALUE)*x);
			return 1.0/pow(x, -n);
		}
		return x*pow(x, n-1);
	        
	    }
	 /***
	  * 由于n个x相乘具有对称的特点，可以采取折半递归的方式，使其时间复杂度为0（logn）,此方法还是出现栈溢出的错误，错误的原因是为考虑：对于n取值INT_MIN时，-n并不是INT_MAX
	  * 
	  * 需要注意的几个问题：
	  * 1) x取值为0时，0的正数次幂是1，而负数次幂是没有意义的；判断x是否等于0不能直接用“==”。

		2) 对于n取值INT_MIN时，-n并不是INT_MAX，这时需要格外小心。

		3) 尽量使用移位运算来代替除法运算，加快算法执行的速度。
	  * @param args
	  */
	 public double pow2(double x, int n) {
		 if(n==0){
			 return 1.0;
		 }
		 if(n<0){
//			 非常容易出错，这里当n=Integer.MIN_VALUE,-n并不是直接取-的，例如 8位整型表示范围为：-256~255，Integer.MIN_VALUE为-256，-n是256，而Integer.MAX_VALUE是255
//			由于机器不能表示256这个数，因此会出现栈溢出错误，正确方法应该是，255*x来表示！！！	 
			 if(n==Integer.MIN_VALUE)
				 return 1.0/(pow2(x, Integer.MAX_VALUE)*x);
			 return 1.0/pow2(x, -n);
		 }
		 double half = pow2(x, n/2);
		 if(n%2==0){
			 return half*half;
		 }else{
			 return x*half*half;
		 }
		 
	 }
	 /***
	  * 方法3 通过扫描n的二进制表示形式里不同位置上的1，来计算x的幂次,
	  * 此方法利用位运算，时间复杂度只有o（（n的位数））
	  * @param x
	  * @param n
	  * @return
	  */
	 public double pow3(double x, int n) {
		 if(n==0){
			 return 1.0;
		 }
		 if(x==0&&n<0){
//			 不能出现
		 }
		 if(x!=0&&n<0){
			 if(n==Integer.MIN_VALUE){
				 return 1.0/(pow3(x,Integer.MAX_VALUE)*x);
			 }
			 return 1.0/(pow3(x, -n));
		 }
		 double ans = 1.0;
		 for(;n>0;x = x*x,n>>=1){
//			 若此位为1，ans = ans *当前的x的值
			 if((n&1)>0){
				 ans = ans * x;
			 }
		 }
		 return ans;
	 }
	 /***
	  * 
	  * 
	  * 
	  * @param args
	  */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pow p = new Pow();
		System.out.println(p.pow2(2, 6));
	}

}
