package com.problems5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

/***
 * 各种sum类的题目（2sum的问题，前面已经做过了）
 * @author bike
 *
 */
public class SumProblems {

	/****
	 * 一： 3sum 的问题：Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
	 *题目的意思是
	 *给一个数组，重数组中找出三个数的和为0，（可能不止一个）；
	 *结果要求：三个数按照非递减的顺序排列，且结果中不能存在重复的三个数
	 */
	/***
	 * 思路1： 利用2sum的问题，先把数组进行排序，然后从有序数组中依次取出1个数，看数组中的其他元素是否存在两个个数的和为该数的相反数。这样就转换成了
	 * 求twosum的问题！！！。求解twoSum首先用收尾指针的方法求解，时间复杂度为：nlogn（排序）＋ｎ^2(搜索选择）=0（n^2)
	 */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		int n = num.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(n<3){
			return result;
		}
//    	数组从小到大排序
//		 注意防止结果重复，当取出第一个数，判断后面数是否两个的和为该数的相反数，判断玩后，再取第二个数，寻找另两个数的反复是该数后面的所有数，前面的数无需再判断
		Arrays.sort(num);
		for(int i = 0;i<n;i++){
//			注意这里是去除，（当重复的把同一个数作为第一个数，来寻找另外两个数）重复的三个数
			if(i!=0){
				if(num[i]==num[i-1]){
					continue;
				}
			}
			int target = -num[i];
			ArrayList<Integer> list = new ArrayList<>();
			list.add(num[i]);
			searchForTwoSum(num,target,i+1,n-1,result,num[i]);
		}
//		但是这样的结果仍然会有重复的例如：-2,-2,1,1,1,1,结果会得到-2,1,1与-2,1,1,-2,1,1,-2,1,1
    	return result;
        
    }
	private void searchForTwoSum(int[] num, int target, int i, int j,
			ArrayList<ArrayList<Integer>> result, int first) {
		// TODO Auto-generated method stub
		int start = i,end = j;
		while(start<end){
			if(num[start]+num[end]==target){
//				这里是去除，当第一个数一定，寻找另外两个数出现重复的三个数
				if(end<j){
					if(num[start]==num[start-1]&&num[end]==num[end+1]){
						start++;
						end--;
						continue;
					}
				}	
				ArrayList<Integer> list = new ArrayList<>();
				list.add(first);
				list.add(num[start]);
				list.add(num[end]);
				result.add(list);
				start++;
				end--;
			}else if(num[start]+num[end]<target){
				start++;
			}else{
				end--;
			}
		}
	}
	
	/***
	 * 
	 * 思路2 ： 利用2sum的问题，无序对数组进行排序。然后这次利用hashMap解决，边向hashmap插入边判断，当向hashMap中插入某个数x的时候，
	 * 判断该hashmap是否存在taget-x，存在则到达一个值对，不存在则插入该数到hashmap中去，但是注意去除重复的数
	 * @param args
	 */
	public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {
		int n = num.length;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if(n<3){
			return result;
		}
		Arrays.sort(num);
		HashSet<Integer> first = new HashSet<>();
		for(int i=0;i<n;i++){
//			去除第一个数相同的重复
			if(first.contains(num[i])){
				continue;
			}
			else{
				first.add(num[i]);
				HashMap<Integer,Integer> twosum = new HashMap<>();
				int target = -num[i];
				for(int j=i+1;j<n;j++){
					if(twosum.containsKey(target-num[j])){
//						去除已经添加的两个数的重复
						if(twosum.containsKey(num[j])){
							if((num[j]==target-num[j])&&twosum.get(num[j])>1){
								ArrayList<Integer> list = new ArrayList<>();
								list.add(num[i]);
								list.add(target-num[j]);
								list.add(num[j]);
								result.add(list);
								twosum.put(num[j], 2);
							}
							continue;
						}else{
							twosum.put(num[j],1);
							ArrayList<Integer> list = new ArrayList<>();
							list.add(num[i]);
							list.add(target-num[j]);
							list.add(num[j]);
							result.add(list);
						}
					}else{
						if(twosum.containsKey(num[j])){
							continue;
						}
						twosum.put(num[j],1);
					}
				}
			}
		}
    	return result;
}
	/***
	 * 
	 * 思路3：枚举前2个数，然后二分查询第三个数，时间复杂度同上面的方法0（nlgn+n^2)
	 * @param args
	 */
	
	
	/***
	 * 
	 * 第二类问题：4sum的问题，给一个数组，重数组中找出四个数的和为0（或者是目标数），（可能不止一个）；
	 * 结果要求：四个数按照非递减的顺序排列，且结果中不能存在重复的四个数
	 * @param args
	 */
	
	/***
	 * 
	 * 第一类的方法的思路，就是把四个数的转换为求三个数的，在转换为求2个数的,最后还是利用求2个数的进行求解，时间复杂度0（nlgn+n^3）
	 * 第二路的方法的思路，先枚举，两个数，然后寻找另连个数，时间复杂度同样为0（n^3),其实和思路1也差不多
	 * 还是推荐理由思路2，这种利用数组，设置首尾两个指针，根据两个指针的值和，来决定下一步移动的方向，这种思路，思路清晰，并且容易实现
	 * 而且很容易处理重复的结果！！！！！！！
	 * @param args
	 */
	
	 public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
		   	int n = num.length;
	    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	    	if(n<4){
	    		return result;
	    	}
	    	Arrays.sort(num);
	    	
	    	for(int i =0;i<n;i++){
//	    		若等于前一个值，continue，去掉第一个数的重复
	    		if(i>0&&num[i]==num[i-1])
	    			continue;
	    		for(int j=i+1;j<n;j++){
//	    			若等于前一个值，continue，去掉第二个数的重复
	    			if(j>i+1&&num[j]==num[j-1])
	    				continue;
	    			int left = j+1,right = n-1;
	    			while(left<right){
//	    				若等于前一个值，continue，去掉第三个数的重复
	    				if(left>j+1&&num[left]==num[left-1])
	    				{
	    					left++;
	    					continue;
	    				}
//	    				同等于前一个值，continue，去掉第四个数的重复
	    				if(right<n-1&&num[right]==num[right+1]){
	    					right--;
	    					continue;
	    				}
	    				int TwoSum = target-num[i]-num[j];
//	    				前后连个指针的和等于Twosum，指针均移动
	    				if(TwoSum==num[left]+num[right]){
	    					ArrayList<Integer> list = new ArrayList<>();
	    					list.add(num[i]);
	    					list.add(num[j]);
	    					list.add(num[left]);
	    					list.add(num[right]);
	    					result.add(list);
	    					left++;
	    					right--;
//	    					前后两个指针的和小于Twosum，左指针右移
	    				}else if(TwoSum>num[left]+num[right]){
	    					left++;
//	    					前后两个指针的和大于Twosum，右指针左移
	    				}else{
	    					right--;
	    				}
	    			}
	    		}
	    	}
	    	return result;
	        
	 }
	/***
	 * 这里我们着重来介绍第三类思路的求解，时间复杂度0（n^2)
	 * 此方法的思路是
	 * 首先建立一个hashmap。这个hashmap的key 是任意两个数的值做为key，而value则是所有以这个值为和的两个数组成的链表
	 * 然后以这个hashmap中的任意两个数的和组成的数组，就变成了TwoSum的问题了
	 * @param args
	 */
	/***
	 * 此方法重复问题的解决很难办！！，目前没想到好的解决重复的办法，因此还无法实现
	 * @param num
	 * @param target
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> fourSum3(int[] num, int target) {
    	int n = num.length;
    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    	if(n<4){
    		return result;
    	}
    	Arrays.sort(num);
    	HashMap<Integer, HashMap<Integer, Integer>> twoSum = new HashMap<>();
//    	建立这个hashmap
    	for(int i=0;i<n;i++){
    		for(int j=i+1;j<n;j++){
    			int sum = num[i]+num[j];
    			if(twoSum==null||!twoSum.containsKey(sum)){
    				HashMap<Integer, Integer> p = new HashMap<>();
    				p.put(num[i], 1);
    				twoSum.put(sum, p);
    			}else{
    				HashMap<Integer, Integer> temp = twoSum.get(sum);
    				if(temp.containsKey(num[i])){
    					int value = temp.get(num[i]);
    					temp.put(num[i], value+1);
    				}else{
    					temp.put(num[i], 1);
    				}
    			}
    		}
    	}
//    	对这个hashmap 执行求解twosum
    	HashSet<Integer> mum = new HashSet<>();
    	for(Map.Entry<Integer, HashMap<Integer,Integer>> entry : twoSum.entrySet()){
    		int second = target-entry.getKey();
    		if(mum.contains(second)){
    			for(Map.Entry<Integer, Integer> entry1: entry.getValue().entrySet()){
    				for(Map.Entry<Integer, Integer> entry2 : twoSum.get(second).entrySet()){
    					ArrayList<Integer> list = new ArrayList<>();
    					list.add(entry1.getKey());
    					list.add(entry.getKey()-entry1.getKey());
    					list.add(entry2.getKey());
    					list.add(second-entry2.getKey());
    					Collections.sort(list);
    					result.add(list);
    				}
    			}
    		}else{
    			if(second==entry.getKey()){
    				for(Map.Entry<Integer, Integer> entry1: entry.getValue().entrySet()){
    					for(Map.Entry<Integer, Integer> entry2 : entry.getValue().entrySet())
    					{
    						if(entry1.getKey()==entry2.getKey())
    						{if(entry1.getValue()>=2){
    							ArrayList<Integer> list = new ArrayList<>();
    							list.add(entry1.getKey());
    							list.add(entry.getKey()-entry1.getKey());
    							list.add(entry1.getKey());
    							list.add(entry.getKey()-entry1.getKey());
    							result.add(list);
    						}}else{
    	    					ArrayList<Integer> list = new ArrayList<>();
    	    					list.add(entry1.getKey());
    	    					list.add(entry.getKey()-entry1.getKey());
    	    					list.add(entry2.getKey());
    	    					list.add(entry.getKey()-entry2.getKey());
    	    					Collections.sort(list);
    	    					result.add(list);
    						}
    					}
    				}
    			}
    			mum.add(entry.getKey());
    		}
    	}
//    	把rusult的重复的取掉
    
		return result;
        
    }
    
    
    /*****
     * 
     * 第三类题目   3Sum Closest 的问题，给一个数组和一个目标值，返回距离这个目标值结果最接近的三个数的和。并假设 只会返回一个结果！
     * 例如-1,2,1,4 目标值1   结果最接近的是 -1+2+1 =2；
     * @param args
     */
    /***
     * 
     * 思路1 ：还是利用设置收尾指针的思路
     * @param num
     * @param target
     * @return
     */
    public int threeSumClosest(int[] num, int target) {
		int n  = num.length;
    	assert(n>=3);
    	Arrays.sort(num);
    	int closet = 0;
    	boolean first = true;
    	for(int i=0;i<n;i++){
    		if(i>0&&num[i]==num[i-1])
    			continue;
    		int left = i+1,right = n-1;
    		while(left<right){
    			if(left>i+1&&num[left]==num[left-1])
    			{
    				left++;
    				continue;
    			}
    			if(right<n-1&&right==num[right+1]){
    				right--;
    				continue;
    			}
    			int sum = num[i]+num[left]+num[right];
    			if(first==true){
    				closet = sum;
    				first = false;
    			}else{
    				if(Math.abs(sum-target)<Math.abs(closet-target))
    				{
    					closet = sum;
    				}
    			}
    			if(closet==target){
    				return closet;
    			}else if(closet>target){
    				right--;
    			}else{
    				left++;
    			}
    		}
    	}
    	
    	return closet;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
