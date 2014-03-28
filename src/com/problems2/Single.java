package com.problems2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/***
 * Given an array of integers, every element appears twice except for one. 
 * Find that single one.
 * 
 * @author bike
 *解题思路 ：所有的元素异或，最后异或的值就是那个落单的元素，因为相同元素异或自身为0；
 *元素异或上0为其自身
 */
public class Single {

	/**
	 * @param args
	 */
	/***
	 * 题目变形1，有2个元素只出现一次，而其他元素出现两次，解题思路，是
	 * 先所有元素异或，得到的值是2个落单元素的异或，因此此值位为1的元素，这两个元素必然
	 * 一个元素在此为为0，一个元素在此为为1，这样把所有的元素按这个为0，为1分为了2组，
	 * 每一组必定各包含一个落单元素，然后异或求解即可。
	 * @param A
	 * @return
	 */
	public int singleNumber1(int[] strB) {
		int r = 0;
		for(int i=0;i<strB.length;i++){
			r = r^strB[i];
		}
//		判断r哪个位置的位为1
		int p = 0;
		if((r&1)==1){
			p=0;
		}
		if(((r>>1)&1)==1){
			p++;
		}
//		以p位置为1这个条件对所有数字划分分别异或
		int a=0,b=0;
		for(int i=0;i<strB.length;i++){
			if(((strB[i]>>p)&1)==0){
				a = a^strB[i];
			}
			else{
				b = b^strB[i];
			}
		}
		return r;
    }
	/***
	 * 题目变形2：Given an array of integers, every element 
	 * appears three times except for one. Find that single one.
	 * 一组数列，所有元素都出现3次，除了一个以外。
	 * 题目的终极变形是：一个数组中有一个元素只出现1次，其他所有元素都出现k次，求这个只出现1次的元素
	 * 思路1，利用HashMap，然后边框一遍
	 * 
	 * 思路 2：当k为偶数时候，则解题思路就是异或，异或后的结果便是那个出现一次的数
	 * 当k为奇数的时候，解题思路是将数组中每一个元素的每一位相加对k取余数，得到的结果即为
	 * 出现一次的元素，时间复杂度为0（n*len），空间复杂度为0（1）
	 * 但是也没有必要 开个int bit【32】数组来去计算，
	 * 改为用模拟二进制加法
	 * 
	 * @param A
	 * @return
	 */
	/***
	 * 学习：Map的遍历
	 * 当使用HashMap的时候，以HashpMap<String,String> map = new HashMap<>();为例
	 * 1. 只遍历key，使用keyset（）性能最优
	 * for(String key: map.keySet()){
	 * 		value = map.get(key);
	 * }
	 * 2. 只遍历value，使用values 性能最佳
	 * for(String value : map.values(){
	 * }
	 * 3. 遍历key与value，使用entry性能最佳
	 * for(Entry<String,String> entry : map.entrySet()){
	 * 		key = entry.getKey();
	 * 		value = entry.getValue();
	 * }
	 * @param A
	 * @return
	 */
	public int singleNumberK(int[] A){
		int one  = 0,two =0,three =0;
		for(int i=0;i<A.length;i++){
			three  = two & A[i];//已经出现两次并且再次出现
			two = two | one & A[i];////曾经出现两次的或者曾经出现一次但是再次出现的
			
			one = one | A[i]; //出现一次的  
              
	        two = two & ~three; //当某一位出现三次后，我们就从出现两次中消除该位  
	        one = one & ~three; //当某一位出现三次后，我们就从出现一次中消除该位
		}
		return one;
	
	}
	public int singleNumber2(int[] A){
		Map<Integer,Integer> v = new HashMap<>();
		for(int i=0;i<A.length;i++){
			if(v.containsKey(A[i])){
				v.put(A[i], v.get(A[i])+1);
			}else{
				v.put(A[i], 1);
			}
		}
//		for(Entry<Integer,Integer> entry : v.entrySet()){
//			if()
//		}
		for(Integer key : v.keySet()){
			if(v.get(key)==1)
				return key;
		}
		return 0;
	}
	
	/***
	 * 
	 * @param A
	 * @return
	 */
	public int singleNumber(int[] A) {
		int r = 0;;
		for(int i=0;i<A.length;i++){
			r = r^A[i];
		}
		return r;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
