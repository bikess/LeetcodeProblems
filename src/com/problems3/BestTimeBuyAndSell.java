package com.problems3;

/***
 * Best Time to Buy and Sell Stock 类问题，就是何时买股票何时卖出股票收益最大
 * @author bike
 *
 */
public class BestTimeBuyAndSell {

	/***
	 * 下面是卖股票类问题的第一类问题，就是股票只进行一次交易的最大收益，也就是整个过程只买一次，然后在某个时间卖出！！求出最大收益，找出何时买何时卖的时候，收益最大
	 */
	/**
	 * 
	 * @param prices
	 * @return
	 */
	/**
	 * 解法1：采取分治法（递归实现），此方法的时间复杂度近似为0（n），但是空间复杂度也比较大
	 * 思路
	 * （1）求解何时卖出收益最大，也就是找2个数，减数必须在被减数的后面，而二者的差值，是最大的，因此可以转换成求这个数组的最小值和最大值的问题。
	 * （2）分治法划分
	 * 数组分成左右两部分，则出现最大的差值只有三张情况
	 * 一种是：最大的差值在左边的部分，也就是左边数组的绝对差是最大值
	 * 一种是：最大的差值在右边的部分，也就是左边数组存在绝对差的最大值
	 * 一种是：最小值在左边数组，最大值在右边的数组，然后绝对差的最大值是两部分的差值
	 * @param prices
	 * @return
	 */
	public int maxProfit1(int[] prices) {
		int len = prices.length;
		if(len<2)
			return 0;
//		int result = Find_max_profit(prices, 0,len-1);
		int Dresult[] = Find_max_profit2(prices, 0,len-1);
//	    return result;    
		return Dresult[2];
	  }
	/***
	 * 方法1 的改变，实际上在计算crossDiff的时候重复的计算了左边数组的最大值与右边数组的最大值，因此我们可以通过记录左右两边数组的
	 * 最小值与最大值类避免这个重复运算
	 */
	private int[] Find_max_profit2(int[] prices, int start, int end) {
		// TODO Auto-generated method stub
		int result[] = new int[3];
		if(start>=end)
		{
			result[0] = result[1] = prices[start];
			result[2] = 0;
			return result;
		}
		int mid = (start+end)/2;
		int leftDiff[] = Find_max_profit2(prices, start, mid);
		int rightDiff[] = Find_max_profit2(prices, mid+1, end);
		if(leftDiff[2]>=rightDiff[2]){
			result[0] = leftDiff[0];
			result[1] = leftDiff[1];
			result[2] = leftDiff[2];
		}else{
			result[0] = rightDiff[0];
			result[1] = rightDiff[1];
			result[2] = rightDiff[2];
		}
		if(result[2]<rightDiff[1]-leftDiff[0]){
			result[0] = leftDiff[0];
			result[1] = rightDiff[1];
			result[2] = rightDiff[1]-leftDiff[0];
		}
		return result;
	}
	public int Find_max_profit(int[] prices, int start,int end){
//		递归结束的条件
		if(start>=end)
		{
			return 0;
		}
		int mid = (start+end)/2;
		int leftDiff = Find_max_profit(prices, start, mid);
		int rightDiff = Find_max_profit(prices, mid+1,end);
		int crossDiff = MaxDiff_Crossing_find(prices, start, mid, end);
		
		int MaxDiff;
		if(leftDiff>=rightDiff){
			MaxDiff = leftDiff;
		}else{
			MaxDiff = rightDiff;
		}
		if(MaxDiff<crossDiff){
			MaxDiff = crossDiff;
		}
		return MaxDiff;
		
	}
//	此方法是找出跨中点节点的最大值，也就是最小值在左边数组，最大值在右边的数组
	private int MaxDiff_Crossing_find(int[] prices, int start,int mid, int end) {
		// TODO Auto-generated method stub
		int leftMax = 0;
		int rightMax = 0;
//		int left = mid;
//		int right = mid;
		for(int i =mid;i>=start;i--){
			if(prices[mid]-prices[i]>=leftMax){
//				left = i;
				leftMax = prices[mid]-prices[i];
			}
		}
		for(int i=mid;i<=end;i++){
			if(prices[i]-prices[mid]>=rightMax){
//				right = i;
				rightMax = prices[i]-prices[mid];
			}
		}
//		int result[]= new int[3];
//		result[0] = left;
//		result[1] = right;
//		result[2] = leftMax+rightMax;
		return leftMax+rightMax;
	}

	
	/***
	 * 方法2 转化法  可以转化为求子数组的最大和问题
	 * 构建一个长度为n-1的数组A;A[i] = array[i+1]-array[i];
	 * 
	 * 分析得到的这个数组可以得到，求解最大数对之差，就是求解新的数组的
	 * 最大的连续子数组的和
	 * @param args
	 */
	
	public int maxProfit2(int[] prices){
		int maxProfit = 0;
		int dp = 0;
		for(int i=prices.length-2;i>=0;i--){
//			若2个数之差大于等于0，则之差累加
			if(dp>=0)
				dp = dp + prices[i+1]-prices[i];
//			若2个数之差小于0，则此时把dp清零，重新开始计算连续最大子数组的和
			else{
				dp = Math.max(0, prices[i+1]-prices[i]);
			}
//			存储当前的最大利益
			maxProfit = Math.max(dp, maxProfit);
		}
		return maxProfit;
		
	}
	/***
	 * 方法三 ：采用动态规划的方法，
	 *(1) 我们定义 maxDiff[i] 是以数组中的第i个数为减数的所有数对之差的最大值，
	 * 也就是说对于任意的j（j>i),maxDiff[i]>=array[j]-array[i];而MaxDiff[i]中的最大值
	 * 就是整个目前长度数组最大的数对之差
	 * (2) 假设已经求的了maxDiff[i],那么如何求解maxDiff[i-1]，maxDiff[i-1]的值有2种可能。一种是
	 * 一种可能maxDiff[i-1]就是最大值
	 * 一种可能array[i-1] 是当前数组的最小值，array[j]-array[i-1]是最大的数对之差
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit3(int[] prices){
		int len = prices.length;
		if(len<2){
			return 0;
		}
		int maxDiff[] = new int[len];
		int max = Integer.MIN_VALUE;
		for(int i=len-1;i>=0;i--){
			if(i==len-1)
			{
				maxDiff[len-1] = 0;
				max = prices[len-1];
				continue;
			}
			if(prices[i]>max){
				max = prices[i];
			}
			if(max-prices[i]>maxDiff[i+1]){
				maxDiff[i] = max-prices[i];
			}else{
				maxDiff[i] = maxDiff[i+1];
			}
		}
		return maxDiff[0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 买股票扩展类题目1：就是也就是求买卖股票的最大的收益，但是可以多次买卖股票
	 * 这个题目非常简单，就是利用贪心算法，把所有的正收益都加起来，就是最大的收益
	 */
	public int maxProfitExa(int[] prices){
		int len = prices.length;
		if(len<2)
			return 0;
		int maxProfit = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<len-1;i++){
			if(prices[i]<min){
				min = prices[i];
			}
			if(prices[i+1]-min>0){
				min = prices[i+1];
				maxProfit = maxProfit+prices[i+1]-prices[i];
			}
		}
		return maxProfit;
	}
	
	
	/**
	 * 买股票的扩展类题目2：就是你买股票的最大的收益，但是你最多可以进行两次交易，也就是进行两次交易
	 * 的最大利益；第一次的卖可以和第二次的买在同一时间，但第二次的买不能在第一次的卖左边
	 * 
	 * 思路：利用动态规划，思路同上面的动态规划
	 * 求出某个位置i，此位置左边所能获得的最大收益，然后计算此位置右边所能获得的最大的收益，比较任何一个位置左右最大收益的和，
	 * 最大的收益可得。
	 */
	
	public int maxProfitExa2(int[] prices){
		int len = prices.length;
		if(len<2){
			return 0;
		}
//		maxDiff[]数组存储的是到i位置的右边数组的最大收益
		int maxDiff[] = new int[len];
		int max = Integer.MIN_VALUE;
		for(int i=len-1;i>=0;i--){
			if(i==len-1)
			{
				maxDiff[len-1] = 0;
				max = prices[len-1];
				continue;
			}
			if(prices[i]>max){
				max = prices[i];
			}
			if(max-prices[i]>maxDiff[i+1]){
				maxDiff[i] = max-prices[i];
			}else{
				maxDiff[i] = maxDiff[i-1];
			}
		}
//		maxDiff2[]数组存储的是当i位置的左边的数组的最大收益
		int maxDiff2[] = new int[len];
		int min = Integer.MAX_VALUE;
		for(int i=0;i<len;i++){
			if(i==0){
				maxDiff2[0] = 0;
				min = prices[0];
				continue;
			}
			if(prices[i]<min){
				min = prices[i];
			}
			if(prices[i]-min>maxDiff2[i-1]){
				maxDiff2[i] = prices[i]-min;
			}else{
				maxDiff[i] = maxDiff[i+1];
			}
		}
		int maxprofit = 0;
		for(int i=0;i<len;i++){
			if(maxprofit<maxDiff[i]+maxDiff2[i]){
				maxprofit = maxDiff[i]+maxDiff2[i];
			}
		}
		return maxprofit;
		
	}
}
