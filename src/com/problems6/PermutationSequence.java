package com.problems6;

import java.util.ArrayList;

import com.problems4.Pow;

/***
 * 题目意思:给n个数的全排列，从小到大排列，输出第k个排列，n的范围是1-9
 * @author bike
 *
 */
public class PermutationSequence {

	/***
	 * 
	 * 方法1，与求n个数的全排列采取相同的方式，采取深度遍历的方式，得到其第k个排列然后输出，此方法超时
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	int TheKnum = 0;
    public String getPermutation(int n, int k) {
 
		if(n==0){
			return null; 
		}
		int num[] = new int[n];
		for(int i=0;i<n;i++){
			num[i] = i+1;
		}
		ArrayList<Integer> list = new ArrayList<>();
		boolean used [] = new boolean[n];
		boolean flag = getKPermunation(num,used,k,list);
		if(flag==true)
			return list.toString();
		else{
			return null;
		}
        
    }
	private boolean getKPermunation(int[] num, boolean[] used, int k, ArrayList<Integer> list) {
		// TODO Auto-generated method stub
		if(list.size()==num.length){
//			System.out.println(TheKnum+"\t"+list.toString());
			TheKnum++;
			if(TheKnum==k){
				return true;
			}
			return false;
		}
		for(int i = 0;i<num.length;i++){
			if(used[i]==false)
			{
				used[i] = true;
				list.add(num[i]);
				boolean flag = getKPermunation(num,  used, k, list);
				if(flag==true){
					return true;
				}
				list.remove(list.size()-1);
				used[i] = false;
			}
			
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationSequence p = new PermutationSequence();
		String s = p.getPermutation(3, 6);
		System.out.println(s);
	}
/***
 * 
 * 方法2 ：注意到有如下的规律，n的全排列即n!存在n！种全排列
 * 而以每个数字开头的个数都是（n-1）！，这样n个数字正好为n!
 * 同理以第二位每一个数字开头都有（n-2）！种.
 * 例如n=6，k=400
 * 思路：
 * （1）当第1位为6的时候，最少已经存在排列5！*5+1=601个
 *依次类推当第1为为5的时候，最少已经存在排列5！*4+1=481个
 *所以可以推测第1位以4开头，此时已经具有的排列数为5！*3+1 = 361
 *（2）下面开始推测第二位的数字，第二位推测的方式是：
 * 以46开头的数字至少存在了：4！*4+1=97个，361+97>400则继续看
 * 以45开头
 * 以44开头
 * 最后得到以42开头
 * （3）依次类推，确定第三位的数字也是这样求解
 */
	public String getPermutation2(int n, int k) {
//		标示当前第i个值是否已经在结果排列中使用了，默认false标示没有使用
		boolean used[] = new boolean[n+1];
//		num数组存储的是，i！的个数。num[n]=n!;
		int num[] = new int[n+1];
		num[1] = 1;
		for(int i=2;i<=n;i++){
			num[i] = num[i-1]*i;
		}
		String result = "";
//		第一重循环确定第i个位置的数字，从n开始循环
		for(int i=n;i>=1;i--){
//			第二重循环，确定i位置上的数字是多少，从n依次判断
			for(int j=n;j>=1;j--){
//				看当前位置的数字是否还可以用
				if(used[j]==false){
					int count =0;
					for(int q =1;q<=n;q++){
//						统计目前未被使用的数字，而且当前数组要比j小
						if(used[q]==false&&q<j){
							count++;
						}
					}
//					若以j作为i为的数字，小于当前的k
					if(num[i-1]*count<k){
						k = k- num[i-1]*count;
						result = result+String.valueOf(j);
//						确定的这一位数字置为已经使用
						used[j]=true;
						break;
					}
				}
			}
		}
		return result;
	}
}
