package com.problems5;

/***
 * 此题就是模拟类题目，没有算法
 * 题目的意思：题意是n=1时输出字符串1；n=2时，数上次字符串中的数值个数，因为上次字符串有1个1，所以输出11；
 * n=3时，由于上次字符是11，有2个1，所以输出21；n=4时，由于上次字符串是21，有1个2和1个1，所以输出1211
 * 。依次类推，写个countAndSay(n)函数返回字符串
 * @author bike
 *
 */
public class CountandSay {

	/**
	 * @param args
	 */
	/***
	 * 
	 * 思路1，采取动态规划的方法，记录前一个数的count and say
	 * @param n
	 * @return
	 */
    public String countAndSay(int n) {
    	String result = "";
		if(n == 1)
			return result+1;
//		开始的时候暂存n==1的值
    	String temp = ""+1;
    	for(int i=2;i<=n;i++){
    		result = "";
    		char data = 0;
    		int num = 0;
    		for(int j=0;j<temp.length();j++){
    			if(j==0){
    				data = temp.charAt(0);
    				num = 1;
    			}else{
    				if(temp.charAt(j)==data){
    					num=num+1;
    				}else{
    					result = result+String.valueOf(num)+String.valueOf(data);
    					data = temp.charAt(j);
    					num=1;
    				}
    			}
    		}
    		result = result+String.valueOf(num)+String.valueOf(data);
    		temp = result;
    	}
    	return result;
        
    }
    
    /***
     * 
     * 方法2 
     * @param args
     */
    public String countAndSay2(int n) {
    	String result = "";
    	if(n==0)
    		return result;
    	for(int i=0;i<n;i++){
    		result = getNext(result);
    	}
    	return result;
    }
	private String getNext(String result) {
		// TODO Auto-generated method stub
		if(result.equals(""))
			return "1";
		String temp="";
		for(int i=0;i<result.length();i++){
			int cnt = 1;
			char data = result.charAt(i);
			while(i+1<result.length()&&result.charAt(i)==result.charAt(i+1)){
				i++;
				cnt++;
			}
			temp = temp+String.valueOf(cnt)+String.valueOf(data);
		}
		return temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountandSay cs = new CountandSay();
		System.out.println(cs.countAndSay2(3));
	}

}
