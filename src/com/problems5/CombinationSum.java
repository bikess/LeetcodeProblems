package com.problems5;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/***
 * 
 * 题目的大体意思是：
 * 给一个数组和一个目标值，从数组中找出一个数字组合，其和为目标值，找出所有的这样的组合。数组中的每一个数字的使用次数都是不限的
 * 要求:
 * 1. 所有的数组中的数字均为正数
 * 2. 得到的每一个组合中，所有的数字按照非降序排列
 * 3. 结果中不能存在重复的数字组合
 * @author bike
 *
 */
public class CombinationSum {

	
	/***
	 * ！！！！对于java当我们利用对象或者集合或者数组进行传递！！！当我们想传递值时，由于所有的集合与对象默认传递的均是引用，此时我们应该传递
	 * 该集合或者对象的克隆值，而不是引用，例如ArrayList<Integer> list,当我们想传递list的值，而不是list的引用应该利用(ArrayList<Integer>)list.clone()！！！！！！！！！
	 * 思路1： 采取深度优先遍历的思路，时间复杂度0（n！）空间复杂度o（n）.
	 * 具体就是若当前需要求解组合的目标值==0，加入当前的暂存中间结果。复杂对当前的目标值，执行深度优先遍历，
	 * @param candidates
	 * @param target
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//		暂存中间结果
		ArrayList<Integer> intermediate = new ArrayList<>();
		if(candidates.length==0){
			return result;
		}
		Arrays.sort(candidates);
//    	深度遍历寻找结果
//		！！！！！这里一定要注意了，我们这里intermediate想要传递的是值而不是引用！！！！
		dfs2(candidates,0,0,target,intermediate,result);
//		dfs(candidates,target,0,intermediate,result);
    	return result;
        
    }
	private void dfs(int[] candidates, int target, int start,
			ArrayList<Integer> intermediate,
			ArrayList<ArrayList<Integer>> result) {
		// TODO Auto-generated method stub
		if(target==0){//表示找到一个合法的解
			result.add((ArrayList<Integer>) intermediate.clone());
			return ;
		}
		for(int i=start;i<candidates.length;i++){
//			因为数组已经安照从小到大进行排序了，因此一旦找到一个数比当前的目表值还大，则直接返回
			if(target<candidates[i])
				return;
			intermediate.add(candidates[i]);//进行dfs的扩展
			dfs(candidates, target-candidates[i], i, intermediate, result);
//			撤销上次的加入
			intermediate.remove(intermediate.size()-1);
		}
	}
	/**
	 * 方法2 ，思路也是DFS，但是对DFS的很多处理进行了优化,而且深度遍历的角度发生了变化
	 * @param args
	 */
//	三个int型的参数分别表示某个遍历的组合的目前和nowsum，当前在数组中开始索引的位置start，要寻找的目标值target
	private void dfs2(int[] candidates, int nowSum, int start,int target,
			ArrayList<Integer> intermediate,
			ArrayList<ArrayList<Integer>> result) {
		// TODO Auto-generated method stub
			if(nowSum==target){
				result.add((ArrayList<Integer>) intermediate.clone());
				return;
			}
			if(nowSum>target||start>=candidates.length){
				return;
			}
//			看目前需要求解的剩余的值 是当前的索引位置数的倍数,需要循环的次数
			int num = (target-nowSum)/candidates[start];
			int cur =0;
			for(int i = 0;i<=num;i++){
//				此循环分别进行的是，加当前index的数0个，1个，2个，。。。。num个分别进行深度遍历求解
//				一定要注意了！！！！！！！这里intermediate我们不是想传递引用，只是想传递值，直接传递克隆值
				dfs2(candidates, nowSum+cur, start+1, target, (ArrayList<Integer>)intermediate.clone(), result);
				intermediate.add(candidates[start]);
				cur = cur+candidates[start];
			}
		
	}
	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int candidates [] = {1};
		System.out.println(cs.combinationSum(candidates, 1).toString());
	}

}
