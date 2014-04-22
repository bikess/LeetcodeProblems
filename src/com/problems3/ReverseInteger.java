package com.problems3;

public class ReverseInteger {

	/**
	 * @param args
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

}
