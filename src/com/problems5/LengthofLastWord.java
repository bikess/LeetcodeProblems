package com.problems5;
/**
 *题意：返回某个字符串中，最后一个单词的长度
 * @author bike
 *
 */
public class LengthofLastWord {

	/**
	 * @param args
	 */
	public int lengthOfLastWord(String s) {
		
		int len = s.length();
		int i = len -1;
		while(i>=0){
			if(s.charAt(i)!=' '){
				break;
			}
			i--;
		}
		int lastword = 0;
		if(i==-1){
			return 0;
		}
		else{
			while(i>=0){
				if(s.charAt(i)!=' '){
					lastword++;
				}else{
					break;
				}
				i--;
			}
		}
		return lastword;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LengthofLastWord l = new LengthofLastWord();
		System.out.println(l.lengthOfLastWord("i am a  dog  "));
	}

}
