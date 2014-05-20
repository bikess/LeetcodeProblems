package com.problems2;

public class GasStation {

	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int len1 = gas.length;
//		int len2 = cost.length;
		if(gas.length==0||cost.length==0)
			return -1;
		int tank=0;
		int j,k;
		for(int i =0;i<len1;i++){
			tank=0;
			for(k=i;k<len1;k++){
				tank +=gas[k];
				if(cost[k]>tank){
					break;
				}
				tank = tank - cost[k];
			}
//			若前面的没有走到加油点gaslen-1,那么证明这个起点i不满足条件
			if(k!=len1)
				continue;
			for(j=0;j<i;j++){
				tank+=gas[j];
				if(cost[j]>tank){
					break;
				}
				tank = tank - cost[j];
			}
			if(j==i){
				return i;
			}
		}
		return -1;
	}
	/**
	 * @param args
	 */
	/**
	 * 方法2:
	 * (1)如果所有站的代价和大于等于0，则所求的路线必定存在。如果总代价〉=0,从序号0开始求代价和，如果代价和小于0，
	 * 则不是从本站或者本站之前的某一个代价大于0的站开始，必从下一站即之后的站开始，而且这样的站必定存在O（n）
	 * @param args
	 */
	public int canCompleteCircuit2(int[] gas, int[] cost) {
		int len = gas.length;
		int sum = 0;
		int total = 0;
		int start = 0;
		for(int i=0;i<len;i++){
			sum = sum+gas[i]-cost[i];
			total = total + gas[i]-cost[i];
			if(sum<0){
				start = (i+1)%len;
				sum=0;
			}
		}
		if(total>=0){
			return start;
		}
		else{
			return -1;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
