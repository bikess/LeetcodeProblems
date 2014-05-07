package com.problems5;

import java.util.ArrayList;
import java.util.Arrays;
/***
 * 给一个集合数组，返回这个集合的所有的子集，集合中所有元素互不相同
 * （1）集合中所有元素不同
 * （2）返回的结果非递减顺序
 * @author bike
 *
 */
public class Subsets {

	/**
	 * 思路：先进行排序，然后采取深度优先遍历
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
//		对S进行排序
		Arrays.sort(S);
		getSubsets(result,list,S,0,0,len);
		return result;
        
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
		int num[] = {4,1,0};
		System.out.println(s.subsets2(num).toString());
	}
	/***
	 * 方法2 ：也是采取递归的思路,就是数组的某个元素是放还是不放的问题
	 */
	public ArrayList<ArrayList<Integer>> subsets2(int[] S) {
		int len = S.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(len<=0){
			return result;
		}
		ArrayList<Integer> list = new ArrayList<>();
//		对S进行排序
		Arrays.sort(S);
		generate(result,list,S,0);
		return result;
        
    }
	private void generate(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] s, int len) {
		// TODO Auto-generated method stub
		if(len==s.length){
			result.add((ArrayList<Integer>) list.clone());
			return;
		}else{
//			不放
			generate(result, list, s, len+1);
			list.add(s[len]);
			generate(result, list, s, len+1);
			list.remove(list.size()-1);
		}
	}
	/***
	 * 方法3 ： 循环生成子集 ，然后找子集中依次添加新元素。
	 * 子集的元素个数可能有0，1，2...n个。所以可以循环在更小的子集中添加元素形成更大的子集。为了防止重复，可以先将输入字符串排序
	 *  http://blog.csdn.net/lanxu_yy/article/details/11885327
	 */
//	public ArrayList<ArrayList<Integer>> subsets3(int[] S) {
//		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//		if(S.length<=0){
//			return result;
//		}
//		ArrayList<Integer> list = new ArrayList<>();
//		Arrays.sort(S);
//		
//		int len =0;
//		ArrayList<ArrayList<Integer>> pre = new ArrayList<>();
//		ArrayList<ArrayList<Integer>> cur = new ArrayList<>();
//		cur.add(list);
// 		do{
//			pre = cur;
//			cur.clear();
//			for(int i=0;i<pre.size();i++){
//				result.add(pre.get(i));
//				if(pre.get(i).size()>0){
//					for(int j=0;j<S.length;j++){
//						if(S[j]<pre.get(i).get(0)){
//							ArrayList<Integer> temp  = pre.get(i);
//							temp.add(temp., element)
//						}
//					}
//				};
//			}
//			
//		}
//		return null;
//		
//	}
	
	/***
	 * 方法4：思路 (这种思路好！！！！！！！！！！！）
	 * 有n个元素的集合的真子集的个数为2^n,当增加一个元素后，元素个数为n+1，则真子集的个数为2^(n+1)，
	 * 实际上增加的2^n个真子集就是对n个元素的所有的真子集添加一个元素后得到的结果
	 */
	public ArrayList<ArrayList<Integer>> subsets4(int[] S){
		int len = S.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(len<=0){
			return result;
		}
		Arrays.sort(S);
		ArrayList<Integer> list = new ArrayList<>();
//		首先加入的是空集
		result.add(list);
		for(int i =0;i<len;i++){
//			获得目前结果集中的真子集的元素个数
			int j = result.size();
//			向目前结果集中的所有的真子集，每一个真子集添加新增加的元素后形成新的真子集，把新形成的真子集也加入进去
			while(j-->0){
				list = new ArrayList<>(result.get(j));
				list.add(S[i]);
				result.add(list);
			}
		}
		return result;
	}
	
	/***
	 * 方法5： 如果元素个数小于32个
	 * 把n个元素对应到n个比特位上，那么穷举真子集其实就是遍历0到2^n-1这2^n个数字的二进制表示，比特位为1表示该位对应的元素存在，
	 * 比特为0表示该为对应的元素不存在
	 * 
	 */
	public ArrayList<ArrayList<Integer>> subsets5(int[] S){
		int len = S.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(len<=0){
			return result;
		}
		Arrays.sort(S);
//		从0一直循环遍历到到2^n
		for(int i=0;i<Math.pow(2, len);i++){
			ArrayList<Integer> list = new ArrayList<>();
			for(int j=0;j<len;j++){
//				判断当前的该比特位是否为1
				if((i&(1<<j))>0){
					list.add(S[j]);
				}
			}
			result.add(list);
		}
		return result;
	}
	
	
	/***
	 * 
	 * 扩展题目：
	 * 给一个集合数组，返回这个集合的所有的子集，但是集合中某个元素可能存在多个
	 * （1）返回的结果不能包含重复的子集
	 * （2）返回的结果非递减顺序
	 * 例如：
	 * If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
	 */
	/***
	 * 上面的方法4的修改，就是在n个元素真子集的个数为2^n,当增加一个元素的时候，注意判断是否与前一个添加的元素重合，
	 * 不重合的话还是为结果集中每个子集添加当前元素后加入结果集。但是当是重复元素的时候，不是为结果集中的每一个子集添加当前元素
	 * ，而是为上一个重复元素新添加的子集添加当前重复元素。
	 * @param num
	 * @return
	 */
	 public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		int len = num.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(len<=0){
			return result;
		}
		Arrays.sort(num);
		ArrayList<Integer> list = new ArrayList<>();
		result.add(list);
//		记录前一个元素加入的新的子集的数量；
		int prenumAdd = 0;
		for(int i =0;i<len;i++){
			int size = result.size();
			if(i>0&&num[i-1] == num[i]){
					for(int j=size-prenumAdd;j<size;j++){
						list = new ArrayList<>(result.get(j));
						list.add(num[i]);
						result.add(list);
					}
					prenumAdd = result.size() - size;
			}else{
					prenumAdd = size;
					for(int j=0;j<size;j++){
						list = new ArrayList<>(result.get(j));
						list.add(num[i]);
						result.add(list);
					}
			}
			
		}
		return result;
		 
	 }
}
