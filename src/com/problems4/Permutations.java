package com.problems4;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * 求解全排列
 * 此题目中不存在重复的元素
 * @author bike
 *
 */
public class Permutations {

	/***
	 * 
	 * @param num
	 * @return
	 */
	/***
	 * 思路1：固定第一位，然后算后面的全排列。然后再将第一位固定为其他的值。
	 * @param num
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
		int len = num.length;
		if(len<=0){
			return null;
		}
		ArrayList<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<>();
//		subPermute(num,0,list,Result);
		subPermute2(num,0,Result);
    	return Result;
        
    }
	private void subPermute(int[] num,int index, ArrayList<Integer> list,
			ArrayList<ArrayList<Integer>> result) {
		// TODO Auto-generated method stub
		int size = num.length;
		if(size==index){ 
//			这里一定要注意，决不能直接加入list,因为加入list是加入指向对象的引用，以后对list指向的对象所做的所有的修改都要反映到result！！
//			以后一定要注意当向某个集合中加入一个对象的时候，一定弄清楚加入的是对象的引用，还是加入的仅仅是对象的内容，若加入的是内容要利用clone（）
//			result.add(list);
			ArrayList<Integer> temp = (ArrayList<Integer>) list.clone();
			result.add(temp);
			return ;
		}
		for(int i=index;i<size;i++){
			/**
			 * 交换index位置元素与i位置上的元素
			 */
			int temp = num[index];
			num[index] = num[i];
			num[i] = temp;
//			将此时index位置元素压入
			list.add(num[index]);
			subPermute(num, index+1, list, result);
			list.remove(list.size()-1);
			temp = num[index];
			num[index] = num[i];
			num[i] = temp;
		}
	}
	/***
	 * 此方法不传入list参数！！，不易出错
	 * @param num
	 * @param index
	 * @param result
	 */
	private void subPermute2(int[] num,int index,
			ArrayList<ArrayList<Integer>> result) {
		// TODO Auto-generated method stub
		int size = num.length;
		if(size==index){ 
//			把当前的num数组加入到result中去，因为已经是需要的排列了！！
			ArrayList<Integer> temp = new ArrayList<>(); 
			for(int j=0;j<size;j++){
				temp.add(num[j]);
			}
			result.add(temp);
//			System.out.println(result.toString());
			return ;
		}
		for(int i=index;i<size;i++){
			/**
			 * 交换index位置元素与i位置上的元素
			 */
			int temp = num[index];
			num[index] = num[i];
			num[i] = temp;
//			将此时index位置元素压入
			subPermute2(num, index+1, result);
			temp = num[index];
			num[index] = num[i];
			num[i] = temp;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Permutations m = new Permutations();
		int[] num = {1,2,3};
		ArrayList<ArrayList<Integer>> list = m.permute(num);
		System.out.println(list.toString());
	}
	/***
	 * 
	 * 扩展题目，若输入的数组中存在重复的元素，要求输出所有的全排列，而且不能有重复
	 */
	/***
	 * 思路1 ：此思路还是用重复的
	 * @param num
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//    	int len = num.length;
    	if(num.length<=0){
    		return result;
    	}
    	Arrays.sort(num);
    	ArrayList<Integer> list = new ArrayList<>();
//    	getPermute(result,list,0,num);
		return result;
        
    }
//	private void getPermute(ArrayList<ArrayList<Integer>> result,
//			ArrayList<Integer> list, int index, int[] num) {
//		// TODO Auto-generated method stub
//		if(index == num.length){
//			result.add((ArrayList<Integer>)list.clone());
//			return ;
//		}
//		for(int i = index;i<num.length;i++){
//			
//			if(i==index||num[i]!=num[index])
//			{
//				int temp  = num[index];
//				num[i] = num[index];
//				num[index] = temp;
//				list.add(num[index]);
//				getPermute(result, list, index+1, num);
//				temp = num[index];
//				num[index] = num[i];
//				num[i] = temp;
//				list.remove(list.size()-1);
//			}
//			
//		}
//	}
	/***！！！！！！！！！！！ 推荐用这个方法！！！！！！！！！！！！！！！！！！！！
	 *  思路2 ：此方法无论数组中是否含有重复的元素，都是可用的，而且不用对数组元素进行频繁的交换，只是额外使用一个boolean数组表示前一个元素是否适用过！
	 * （1） 首先对数组进行排序，让重复的元素相邻在一起
	 * （2） 开辟一个boolean 数组表示当前的元素是否被使用过，
	 *  方法当当前元素与i-1的元素相等的时候，若i-1个元素已经被使用过了，就跳出当前的递归
	 */
    public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//    	int len = num.length;
    	if(num.length<=0){
    		return result;
    	}
    	Arrays.sort(num);
    	ArrayList<Integer> list = new ArrayList<>();
//    	定义一个数组用来标示当前元素是否被用过了
    	boolean used[] = new boolean[num.length];
    	getPermute2(result,list,used,num);
		return result;
        
    }
	private void getPermute2(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, boolean[] used, int[] num) {
		// TODO Auto-generated method stub
		if(list.size()==num.length){
			result.add(new ArrayList<>(list));
			return;
		}
		for(int i = 0;i<num.length;i++){
//			判断当前的元素与前一个元素是否相等且前一个元素是否已经使用过了
			if(i>0&&used[i-1]&&num[i]==num[i-1])
				continue;
			if(!used[i]){
				used[i] = true;
				list.add(num[i]);
				getPermute2(result, list, used, num);
				list.remove(list.size()-1);
				used[i] = false;
			}
		}
	}
	
}
