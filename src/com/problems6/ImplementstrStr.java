package com.problems6;
/***
 *两个字符串，A字符串中查找B字符串第一次出现的位置，并返回
 * @author bike
 *
 */
public class ImplementstrStr {

	/***
	 *	思路1，字符串匹配的问题,利用思路比较容易想，但是时间复杂度为0（m*n）
	 *	就是原字符串i=0
	 *	要匹配的字符串j=0
	 *	开始的时候比较 s[i]==s[j]
	 *	若相等，继续比较i+1==j+1
	 *	若不相等，则另 i=i+1，然后重新进行匹配
	 * @param haystack
	 * @param needle
	 * @return
	 */
    public String strStr(String haystack, String needle) {
    	int len1 = haystack.length();
    	int len2 = needle.length();
    	if(len1<len2){
    		return null;
    	}
    	for(int i =0;i<len1-len2;i++){
    		boolean flag = true;
    		for(int j=0;j<len2;j++){
    			if(haystack.charAt(i+j)!=needle.charAt(j)){
    				flag=false;
    				break;
    			}
    			if(flag==true){
    				return haystack.substring(i);
    			}
    		}
    	}
		return null;
        
    }

    /***
     * 方法2 ：采取KMP字符串匹配的方法，时间复杂度为0（m+n）
     * @param args
     */
    
    public String strStr2(String haystack, String needle) {
    	int len1 = haystack.length();
    	int len2 = needle.length();
    	if(len1<len2){
    		return null;
    	}
    	int nextval[] = new int[len2+1];
//    	计算要匹配的字符串needle的next函数
    	get_nextval(needle,nextval);
		int i = 0;
		int j=0;
		int index = 0;
		while(i<len1&&j<len2){
			if(haystack.charAt(i)==needle.charAt(j)){
//				当某个字符匹配成功，或者j=-1的时候i++，j++
				i++;
				j++;
			}else{//当匹配失败的时候，i保持不变，也就是主串的位置不变，而匹配串右移1<=右移位数<=j-1个位置，也就是右移next[j]个位置，右移的位数由
//				匹配串t所决定，而与主串s无关。
				index = index + j-nextval[j];
				if(nextval[j]!=-1){
					j = nextval[j];
				}else{
					j=0;
					i++;
				}
			}
		}
		if(j>=len2)
			return haystack.substring(index);
		else
			return null;
  
    	
    }
	private void get_nextval(String needle, int[] nextval) {
		// TODO Auto-generated method stub
		int i = 0;
		nextval[0] = -1;
		int j = -1;
		while(i<needle.length()-1){
			if(j==-1||needle.charAt(i)==needle.charAt(j)){
				++i;
				++j;
				if(needle.charAt(i)!=needle.charAt(j)){
					nextval[i]=j;
				}else{
					nextval[i]=nextval[j];
				}
			}else{
				j = nextval[j];
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
