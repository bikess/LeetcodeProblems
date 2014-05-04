package com.problems5;

import java.util.ArrayList;
/***
 * 给一个集合数组，返回这个集合的所有的子集
 * （1）集合中所有元素不同
 * （2）返回的结果非递减顺序
 * @author bike
 *
 */
public class Subsets {

	/**
	 * 思路：采取深度优先遍历
	 * （1）返回当前深度的解
	 * （2）当当前深度等于最大递归深度时返回
	 * 具体的说，数组的长度为递归的最大深度，从第0个元素从深度1开始递归......第n个元素从深度n开始递归，递归的深度都是达到数组的长度
	 * @param args
	 */
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		int len = S.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(len<=0){
			return result;
		}
		ArrayList<Integer> list = new ArrayList<>();

		getSubsets(result,list,S,0,0,len);
		return null;
        
    }
//	参数分别表示：递归开始的元素，当前递归的深度，递归的最大深度
	private void getSubsets(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] s,int start,int curdepth,int depth) {
		// TODO Auto-generated method stub
//		返回当前的解
		result.add((ArrayList<Integer>)list.clone());
//		当前深度等于最大递归深度返回
		if(curdepth==depth){
			return;
		}
		for(int i = start;i<s.length;i++){
			list.add(s[i]);
			getSubsets(result, list, s, i+1, curdepth+1, depth);
			list.remove(list.size()-1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subsets s = new Subsets();
		int num[] = {1,2,3};
		System.out.println(s.subsets(num).toString());
	}

}
