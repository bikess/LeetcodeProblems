package com.problems7;
/***
 * 将一个字符串装换成int型
 * @author bike
 *
 */
public class StringtoInteger {
	/***
	 * 注意的问题：
	 * 	1 数字开头可能有空格
		2 可能存在正负符号
		3 中间有字母处理字母之前的数字
		4 有溢出 输出最大/最小
		5 比较大小的话用 longlong 就没问题了！！！这个很关键
	 * @param str
	 * @return
	 */
    public int atoi(String str) {
		if(str==null||str.equals(""))
			return 0;
		boolean isPositive = true;
		int pla = 0;
		while(pla<str.length()&&str.charAt(pla)==' ')
			pla++;
    	if(pla<str.length()&&(str.charAt(pla)=='+'||str.charAt(pla)=='-')){
    		if(str.charAt(pla)=='-'){
    			isPositive = false;
    		}
    		pla++;
    	}
    	long ret = 0;
    	while(pla<str.length()&&str.charAt(pla)>='0'&&str.charAt(pla)<='9'){
    		ret = ret*10+(str.charAt(pla)-'0');
    		if(ret>Integer.MAX_VALUE)
    			return isPositive==true? Integer.MAX_VALUE:Integer.MIN_VALUE;
    		pla++;
    	}
    	return isPositive==true?(int)ret:(int)-ret;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
