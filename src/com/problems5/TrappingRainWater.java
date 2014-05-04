package com.problems5;
/***
 * 题意：给以一个数组序列，求解这个数组序列所能盛放的最大的水量
 * @author bike
 *
 */
public class TrappingRainWater {

	/***
	 * 思路1：此种思路在很多题中应该会用到，就是分别求解左右元素中的最值！！
	 * （1）得到每个元素的左元素的最大值，每个元素的右元素的最大值，
	 * （2）取每个元素的左右元素的最大值中的较小的一个减去该元素的值作为该元素所能盛放的水量
	 * （3）收集每个点的水量即为该数组序列的所能盛放的最大的水量
	 * @param A
	 * @return
	 */
	public int trap(int[] A) {
		int len = A.length;
		if(len<=1){
			return 0;
		}
		int []left = new int[len];
		int []right = new int[len];
//		先从左向右，计算每个元素的的左边元素的最大值，也就是索引<=index的最大值
		left[0] = A[0];
		int max = A[0];
		for(int i = 1;i<len;i++){
			if(A[i]<max){
				left[i] = max;
			}else{
				left[i] = A[i];
				max = A[i];
			}
		}
//		从右向左，计算每个元素的右边元素的最大值，也就是索引>=index 的最大值
		max = A[len-1];
		right[len-1] = A[len-1];
		for(int i = len-2;i>=0;i--){
			if(A[i]<max){
				right[i] = max;
			}else{
				right[i] = A[i];
				max = A[i];
			}
		}
//		下面计算总的存储容量
		int result = 0;
		for(int i=0;i<len;i++){
//			没一个点的存储容量，等于左右元素的最小值减去该点的元素值，而且必须大于0，该点的容量
//			才有效
			int volumn = Math.min(left[i], right[i])-A[i];
			if(volumn>0){
				result = result + volumn;
			}
		}
		
		return result;
	        
	    
	}
	/***
	 * 方法2 ：这种方法http://blog.csdn.net/doc_sgl/article/details/12307171
	 * 此种方法虽然对于大的集合内存耗费太大，但是却是一种非常好的思考方法
	 * 就是根据数组中的值，构造一个最大值*数组个数的矩阵，矩阵中根据每个元素的值，进行置true操作
	 */
	public int trap2(int[] A) {
		int len = A.length;
		if(len<=1){
			return 0;
		}
		int max = A[0];
		for(int i =0;i<len;i++){
			if(A[i]>max){
				max = A[i];
			}
		}
//		构造一个boolean矩阵
		boolean [][] isnum = new boolean[max][len];
		for(int i=0;i<len;i++){
			for(int j=1;j<=A[i];j++){
				isnum[max-j][i] = true;;
			}
		}
		
//		扫描矩阵，任意两个为true的元素之间的数量为能盛放的水量
		int result = 0;
		int left = -1;
		for(int i=0;i<max;i++){
			left = -1;
			for(int j=0;j<len;j++){
				if(isnum[i][j]==true){
					if(left==0){
						left = j;
					}else{
						result = result +j-left-1;
						left = j;
					}
				}
			}
		}
		return result;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
