package com.problems7;
/***
 * 题意分析：http://blog.csdn.net/zhouworld16/article/details/14121477
 * @author bike
 *
 */
public class ZigZagConversion {

	/**
	 * @param args
	 */
    public String convert(String s, int nRows) {
    	if(nRows <= 1||s.length()==0){
    		return s;
    	}
    	String result = "";
    	int len = s.length();
    	for(int i=0;i<len&&i<nRows;i++){
    		int index = i;
    		result = result+s.charAt(index);
    		for(int k =1;index<len;k++){
//    			第一行或最后一行
    			if(i==0||i==nRows-1){
    				index = index + 2*nRows-2;
    			}
//    			中间的行需要判断奇偶行,
    			else{
    				if(k%2==1){
    					index = index+ 2*(nRows-1-i);
    				}else{
    					index = index+2*i;
    				}
    			}
//    			判断index的合法性
    			if(index<len){
    				result = result+s.charAt(index);
    			}
    		}
    	}
		return result;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
