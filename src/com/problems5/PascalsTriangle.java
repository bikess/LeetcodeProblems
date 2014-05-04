package com.problems5;
import java.util.ArrayList;
public class PascalsTriangle {

	/**
	 * @param args
	 */
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(numRows<=0)
			return result;
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		result.add(list);
		for(int i=1;i<numRows;i++){
			list = new ArrayList<>();
			for(int j=0;j<=i;j++){
				if(j==0||j==i)
					list.add(1);
				else
				{
					int num = result.get(i-1).get(j-1)+result.get(i-1).get(j);
					list.add(num);
				}
				result.add(list);
			}
		}
			
    	return result;
	        
	    
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * 扩展题:
	 * Given an index k, return the kth row of the Pascal's triangle.

		For example, given k = 3,
		Return [1,3,3,1].
	 */
	/***
	 * 方法1 此种方法利用了多个数组进行存储，空间复杂度为0（k^2)
	 * @param rowIndex
	 * @return
	 */
	public ArrayList<Integer> getRow(int rowIndex) {
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		if(rowIndex==0){
			return list;
		}
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=1;i<=rowIndex;i++){
			result = new ArrayList<>();
			for(int j=0;j<=i;j++){
				if(j==0||j==i)
					result.add(1);
				else{
					int num = list.get(j-1)+list.get(j);
					result.add(num);
				}
			}
			list = new ArrayList<>();
			list = (ArrayList<Integer>) result.clone();
		}
		return result;
	        
	}
	/***
	 * 方法2 ：要得到一个空间复杂度为0(k)的方法，也就是设置一个数组，开始是数组记录矩阵的一行，当计算矩阵的下
	 * 一行的时候，从后向前进行替换数组中的值，就可以更新为新的一行的值
	 */
	public ArrayList<Integer> getRow2(int rowIndex) {
        ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		if(rowIndex==0){
			return list;
		}
		for(int i=1;i<=rowIndex;i++){
			for(int j=i;j>=0;j--){
				if(j==i)
					list.add(1);
				else if(j==0){
				    continue;
				}
				else{
					int num = list.get(j)+list.get(j-1);
					list.set(j, num);
				}
			}
		}
		return list;
		
	}
}
