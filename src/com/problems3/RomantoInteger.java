package com.problems3;
/**
 * 
 * 罗马数字有如下符号：

基本字符            	I	V	X	L	C	D	M
对应阿拉伯数字	1	5	10	50	100	500	1000
计数规则：
相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
正常使用时，连续的数字重复不得超过三次
在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）

其次，罗马数字转阿拉伯数字规则（仅限于3999以内）：

从前向后遍历罗马数字，如果某个数比前一个数小，则加上该数。反之，减去前一个数的两倍然后加上该数
 * @author bike
 *
 */
public class RomantoInteger {

	public int charToInt(char c){
		int data = 0;
		switch (c) {
		case 'I':
			data =1;
			break;
		case 'V':
			data = 5;
			break;
		case 'X':
			data =10;
			break;
		case 'L':
			data = 50;
			break;
		case 'C':
			data=100;
			break;
		case 'D':
			data = 500;
			break;
		case 'M':
			data = 1000;
			break;
		}
		return data;
	}
	 public int romanToInt(String s) {
	        int total,pre,cur;
	        total = charToInt(s.charAt(0));
	        for(int i=1;i<s.length();i++){
	        	pre = charToInt(s.charAt(i-1));
	        	cur = charToInt(s.charAt(i));
	        	if(cur<=pre){
	        		total = total+cur;
	        	}
	        	else{
	        		total = total - pre*2+cur;
	        	}
	        }
	        return total;
	 }
	 
	 /***
	  * 扩展题 如何把Integer转换成罗马数字
	  */
	 
	 public String intToRoman(int num) {
	      StringBuffer result = new StringBuffer();
	      
	      int [] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	      String[] Roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	      for(int i=0;i<values.length;i++){
	    	  while(num>=values[i]){
	    		  num = num-values[i];
	    		  result.append(Roman[i]);
	    	  }
	      }
	      return result.toString();
	   }
	/**
	 * @param args
	 */
	 /***
	  * 方法2
	  * 罗马数字是由字符I,V,X,L,C,D,M等等表示的，其中
I = 1;
V = 5;
X = 10;
L = 50;
C = 100;
D = 500;
M = 1000;
接下来应该是V开始的重复，但是上面要加一个横线，表示对应数字的1000倍。
个位应该是：I,II,III,IV,V,VI,VII,VIII,IX
十位应该是：X,XX,XXX,XL,L,LX,LXX,LXXX,XC
百位应该是：C,CC,CCC,CD,D,DC,DCC,DCCC,CM
	  * @param args
	  */
	 public String intToRoman2(int num) {
		 char [] Roman={'I','V','X','L','C','D','M'};
		 StringBuffer result = new StringBuffer();
		 int scale = 1000;
		 int pla = 6;
		 while(num!=0){
			 int bit = num/scale;
			 RomanChange(result,bit,Roman,pla);
			 num = num%scale;
			 scale = scale/10;
			 pla = pla -2;
		 }
		 return result.toString();
	 }
	private void RomanChange(StringBuffer result, int bit, char[] roman,int pla) {
		// TODO Auto-generated method stub
//		当某一位为0，直接返回
		if(bit == 0)
			return;
//		若某一位小于3，则此为的罗马数字就是 bit个对应的罗马字母
		else if(bit<=3)
		{
			for(int i=0;i<bit;i++)
				result.append(roman[pla]);
//			等于4，则是
		}else if(bit ==4){
			result.append(roman[pla]);
			result.append(roman[pla+1]);
//			大于等于5且小于等于8则是
		}else if(bit<=8){
			result.append(roman[pla+1]);
			for(int i=5;i<bit;i++){
				result.append(roman[pla]);
			}
		}else if(bit ==9){
			result.append(roman[pla]);
			result.append(roman[pla+2]);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
