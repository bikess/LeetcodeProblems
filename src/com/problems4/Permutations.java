package com.problems4;

import java.util.ArrayList;

/***
 * 求解全排列
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

}
