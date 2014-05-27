package com.problems6;

import java.util.ArrayList;
import java.util.List;

/***
 * Restore IP Addresses 
 * 题目的意思：
 * 给一串数字，返回这一串数字所有可能的组成的ip字符串，返回所有的可能性
 * @author bike
 *
 */
public class RestoreIPAddresses {
	/***
	 * 思路1
	 * @param s
	 * @return
	 */
	//深度优先遍历的方法
	public List<String> restoreIpAddresses(String s) {
//		先得到这个串的长度，有长度
		ArrayList<String> result = new ArrayList<>();
		String midResult="";
//		两个整型参数，第一个表示ip值四个字段目前输出几个，第二个表示开始判断是否满足ip字段的起始位置
		getDFSIPAddress(result,midResult,s,0,0);
		return result;
	       
	}
	private void getDFSIPAddress(ArrayList<String> result, String midResult,
			String s, int ip, int start) {
		// TODO Auto-generated method stub
//		ip字段个数小于4，要在中间加上.
		if(ip>0&&ip<4){
			midResult = midResult+".";
		}
//		已经是一个ip地址，而且到达字符串的末位置，则结果集加入这个中间结果（一种可能性），然后返回
		if(ip==4&&start==s.length()){
			result.add(new String(midResult));
			return;
//			若已经是ip地址，但是不是字符串的未位置，此结果不能作为一个结果，直接返回
		}else if(ip==4&&start!=s.length()){
			return;
		}
		String data = "";
//		由于每一个字段可以是1个数、2个数、3个数
		for(int i=1;i<=3&&start+i<=s.length();i++){
//			判断是否满足ip字段的要求
			data = s.substring(start, start+i);
			if(isValid(data)){
				getDFSIPAddress(result, midResult+data, s, ip+1, start+i);
			}
		}
	}
//	ip字段的合法性判断
	private boolean isValid(String data) {
		// TODO Auto-generated method stub
//		注意去除第一个数字为0，必须为0，不能有个多个0的情况
		if(data.charAt(0)=='0'&&data.length()==1){
			return true;
		}
		if(data.charAt(0)=='0'&&data.length()>1){
			return false;
		}
		int num = Integer.parseInt(data);
		if(num<=255){
			return true;
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
