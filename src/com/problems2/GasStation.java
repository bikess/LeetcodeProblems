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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
