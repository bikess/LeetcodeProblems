package com.problems4;

import java.util.ArrayList;

public class GrayCode {

	/**
	 * @param args
	 */
	/***
	 * 思路1： 采取深度优先遍历的方式
	 *   二进制码->格雷码（编码）：从最右边一位起，依次将每一位与左边一位异或（XOR），作为对应格雷码该位的值，最左边一位不变（相当于左边是0）；
     *   格雷码->二进制码（解码）：从左边第二位起，将每位与左边一位解码后的值异或，作为该位解码后的值（最左边一位依然不变）
	 * @param n
	 * @return
	 */
//	 这是二进制码转为格雷码
    public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> list =new ArrayList<>();
		int size = 1 << n;
		for(int i =0;i<size;i++){
			list.add((i>>1)^i);
		}
    	return list;
        
    }
//    格雷码转为二进制码
//    public ArrayList<Integer> grayCodeTo(int n)
//    {
//    	ArrayList<Integer> list =new ArrayList<>();
//		int size = 1 << n;
//		
//    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
