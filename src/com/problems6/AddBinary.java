package com.problems6;

public class AddBinary {

	/***
	 * 
	 * 注意 加法是从最低位开始运算，而字符串是从高位开始存储的
	 * @param a
	 * @param b
	 * @return
	 */
    public String addBinary(String a, String b) {
    	String result = "";
    	int len1 = a.length();
    	int len2 = b.length();
//    	forward表示后一位向当前位的进位
    	int forward = 0;
    	int i = len1-1,j = len2-1;
    	while(i>=0&&j>=0){
    		int cur = forward + a.charAt(i)-'0'+b.charAt(j)-'0';
    		if(cur>=2){
    			result = String.valueOf(cur-2)+result;
    			forward = 1;
    		}else{
    			result = String.valueOf(cur)+result;
    			forward = 0;
    		}
    		i--;j--;
    	}
	    if(i>=0){
	    	for(int k = i;k>=0;k--){
	    		int cur = forward+a.charAt(k)-'0';
	    		if(cur>=2){
	    			result = String.valueOf(cur-2)+result;
	    			forward = 1;
	    		}else{
	    			result = String.valueOf(cur)+result;
	    			forward = 0;
	    		}
	    	}
	    }
	    if(j>=0){
	    	for(int k = j;k>=0;k--){
	    		int cur = forward+b.charAt(k)-'0';
	    		if(cur>=2){
	    			result = String.valueOf(cur-2)+result;
	    			forward = 1;
	    		}else{
	    			result = String.valueOf(cur)+result;
	    			forward = 0;
	    		}
	    	}
	    }
	    if(forward!=0){
	    	result = String.valueOf(forward)+result;
	    }
	    return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
