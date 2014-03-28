package com.problems2;
/**
 * 
 *  问题描述:给你一些孩子的rating，这些孩子站成一条直线，相邻的孩子的rating高的一定要比
 *  低rating的孩子的candy多，所有孩子至少发一个candy
 * @author bike
 *	 解题思路
 *	从左到右遍历一遍，所有比前面邻居的rating高的孩子 candy+1，然后再从右向左遍历一遍，所有比前面邻居的rating高的孩子的candy+1；
 *	注意从右向左遍历的时候，对于rating高的孩子，先判断此时candy数是否比i+1的孩子的candy多，只有在<=的情况下才用i+1的candy个数+1
 */
public class Candy {

	/**
	 * @param args
	 */
    public int candy(int[] ratings) {
    	int result = 0;
    	int len = ratings.length;
    	if(len<1) return 0;
    	result =1;
    	int cap[] = new int[len];
    	cap[0] = 1;
		int tmp =1;
    	for(int i=0;i<len-1;i++){
    		if(ratings[i+1]>ratings[i])
    		{	
    			tmp++;
    		}else{
    			tmp = 1;
    		}
    		cap[i+1] = tmp;
			result = result+tmp;
    	}
    	for(int i=len-1;i>0;i--){
    		if(ratings[i-1]>ratings[i]){
    			if(cap[i-1]>cap[i])
    				continue;
    			else{
    				int old = cap [i-1];
    				cap[i-1]=cap[i]+1;
    				result = result + cap[i-1]-old;
    			}
    		}
    	}
		return result;    
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
