package com.problems3;

public class ReverseInteger {

	/**
	 * @param args
	 */
	/***
	 * 方法1 转换成字符串，然后利用字符串逆序
	 * 此方法还有可以优化的地方，就是 字符串逆序的时候，可以原地逆序，
	 * 就是交换依次交换字符串中的a[0]与a[len-1]字符
	 * @param x
	 * @return
	 */
	public int reverse(int x){
		String s;
		String result = "";
		if(x>=0){
			s = Integer.toString(x);
		}else{
			s = Integer.toString(Math.abs(x));
		}
		for(int i=s.length()-1;i>=0;i--){
			result = result + s.charAt(i);
		}
		if(x>0)
			return Integer.parseInt(result);
		else{
			return -Integer.parseInt(result);
		}
		
	}
//	方法2 ：利用取储取余法
	public int reverse2(int x){
	
		int flag = x>0? 1:-1;
		
		int X = Math.abs(x);
		int  result = 0;
		while(X!=0){
			result = result*10+X%10;
			X= X/10;
		}
		return flag*result;	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
// ！！！！但是上面的方法都没有考虑逆序后是否溢出的问题！！！
//	无论是负数还是正数，当逆序后会有溢出的可能的因此要对溢出进行判断
	public int reverse(int x,boolean flag) {//flag means if the result is overflow  
	    int signal=1;  
	    if (x<0)  
	    {  
	        signal=-1;  
	    }  
	    int temp,ret=0,newv=0;  
	    while(x!=0)  
	    {  
	        temp = x%10;  
	        x = x/10;  
	        if (x!=0)  
	        {  
	            newv = newv*10 + temp;  
	        } 
//	        当在加最后一个元素的时候判断是否溢出了
	        else//last bit may overflow  
	        {  
//	        	若是负数，溢出的条件
	            if (signal<0)  
	            {  
//	            	溢出的条件就是值大于0了
	                if ( 10*newv + temp >= 0)  
	                {  
	                    flag = false;  
	                    ret = -1;  
	                    return ret;  
	                }  
	                ret = 10 * newv + temp;  
	            }  
	            else  
	            {  
//	            	若为正数，溢出的条件就是，得到的值小于0了
	                if ( 10*newv + temp <= 0)  
	                {  
	                    flag = false;  
	                    ret = -1;  
	                    return ret;  
	                }  
	                ret = 10 * newv + temp;  
	            }  
	              
	        }  
	    }  
	   return ret;
}
}