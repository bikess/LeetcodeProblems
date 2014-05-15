package com.problems6;
/**
 * 
 * 题意：给一个组合的排列，要求原地返回该组合的下一个排列
 * 例如：
 *  1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
 * @author bike
 *
 */
public class NextPermutation {

	/**
	 * @param args
	 * 
	 * 思路：数组从后向前遍历，比较前一个数是否比后一个数大，当找到第一个前一个数比后一个数小的时候停止。
	 * （2）然后交换当前的小的数与此数后面的所有数中比该数大的数中最小的数。
	 * （3）交换后需要将该数后面的按从小到大排列（也就是交换后，把该数后面的数组进行逆序排列就可以了）
	 */
    public void nextPermutation(int[] num) {
        int len = num.length;
        if(len<=1){
        	return;
        }
        int pla = len-2;
        while(pla>=0&&num[pla]>=num[pla+1]){
        	pla--;
        }
//        若没有找到比后面数小的值，说明此时数组已经是组合的最后一个排列，完全逆转整个数组
        if(pla<0){
        	reverse(num,0,len-1);
        }
        else{
//          否则，此时则找到了前面比后面数小的第一个数，此数的位置为pla
//        	下一步是寻找此数后面比此数大的所有数中最小的一个数
        	int next = pla+2;
        	while(next<len&&num[next]>num[pla]){
        		next++;
        	}
        	next--;
//        	此时找到了这个数
//        	交换两个数的位置
        	num[pla] = num[pla]^num[next];
        	num[next] = num[pla]^num[next];
        	num[pla] = num[pla]^num[next];
//        	把pla后面的所有数逆转
        	 reverse(num, pla+1, len-1);
        }
    }
	private void reverse(int[] num, int start, int end) {
		// TODO Auto-generated method stub

		while(start<end){
//			利用异或的运算，交换两个数的值！！
			num[start] = num[start]^num[end];
			num[end] = num[start]^num[end];
			num[start] = num[start]^num[end];
			start++;
			end--;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
