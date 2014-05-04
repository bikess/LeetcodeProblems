package com.problems5;

import java.util.ArrayList;

public class Combinations {

	/**
	 * @param args
	 * 就是求从1到n的所有数的所有k位组合
	 * 方法1 采取深度优先遍历与宽带遍历相结合的的方式，k代表遍历的深度，n代表遍历的宽带。
	 */
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(k==0||n==0){
			return result;
		}
//		注意n的值必须大于等于k的值
		if(n<k){
			return result;
		}
		ArrayList<Integer> list = new ArrayList<>();
//		第三个参数表示从数字begin开始遍历
		getCombinations(result,list,1,n,k);
		return result;
	        
	}
	private void getCombinations(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int begin,int n,
			int k) {
//		if(begin+k>4){
//			return;
//		}
//		递归退出的条件，同时注意加入的是list的引用对象的副本，不是加入list的引用对象
//		这里是浅复制只复制内容而不是深复制复制对象
		if(k==0){
			result.add((ArrayList<Integer>) list.clone());
			return;
		}
//		注意每次循环是，从begin开始，一直循环到数字个数能满足k为数的要求
		for(int i=begin;i<=n-k+1;i++){
			list.add(i);
			getCombinations(result,list,i+1,n,k-1);
			list.remove(list.size()-1);
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Combinations c= new Combinations();
		System.out.println(c.combine(4, 4).toString());
	}

}
