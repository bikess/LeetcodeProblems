package com.problems5;

/***
 * 题目大意
 * Container With Most Water
 * 给n个非负的整数a1，a2,a3,...,每一个都代表了在坐标中的一个点（i，ai）。
 * 这样既可以在图中得到n条竖线，其中每条竖线的两个端点分别是(i, ai) 和 (i, 0)，
        找到2条线，这两条线与x轴形成一个容器，找出能够装最多的水的两条直线。
        
        
        装的水的容量= 两条直线之间的距离与较低直线的长度的乘积
 * @author bike
 *
 */
public class ContainerWithMostWater {

	/**
	 * @param args
	 */
	/***
	 * 方法1 笨方法，循环遍历数组两遍
	 * 求出任意两条直线的装的水的容量，求出其中的最大值即可
	 * @param args
	 */
	
	
	/***
	 * 方法2 ，贪心的思路解法,时间复杂度0（n)
	 * 每次选取当前高度最长的进行保留
	 * 类似于2Sum的思想，两边设一个指针，然后计算area，如果height[i] <= height[j]，
	 * 那么i++，因为在这里height[i]是瓶颈，j往里移只会减少面积，不会再增加area。
                   这是一个贪心的策略，每次取两边围栏最矮的一个推进，希望获取更多的水。
	 * @param args
	 */
	public int maxArea(int[] height) {
		int len = height.length;
		int left = 0;
		int right = len-1;
		int max = Math.min(height[left], height[right])*(right-left);
		while(left<right){
			if(height[left]<height[right]){
				left++;
			}
			else{
				right--;
			}
			int temp = Math.min(height[left], height[right])*(right-left);
			if(temp>max)
				max = temp;
		}
		
		return max;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
