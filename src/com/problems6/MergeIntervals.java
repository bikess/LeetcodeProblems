package com.problems6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * 题意：
 * 给一系列的值对，合并这些值对
 * For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 * @author bike
 *
 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
   Interval(int s, int e) { start = s; end = e; }
}
public class MergeIntervals {


/***
 * 思路1 ，先对列表的所有intervals按照start从小到大排序，然后在
 * @param intervals
 * @return
 */
	public List<Interval> merge(List<Interval> intervals) {
		int len = intervals.size();
		if(len<=1){
			return intervals;
		}
//		将所有的值对按照start从小到大排列
		Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start-o2.start;
			}
		});
		Interval pre = intervals.get(0);
		int i =1;
		while(i<intervals.size()){
//			合并,当当前第i个元素start值<=pre的end值，则合并两个元素，而且合并后的元素的start是pre.start,而end是二个元素的end的最大值
			if(intervals.get(i).start<=pre.end){
				pre.end = Math.max(intervals.get(i).end, pre.end);
//				移除被合并的元素
				intervals.remove(i);
			}else{
//				否则改变pre的指向，i值向后移动
				pre = intervals.get(i);
				i++;
			}
			
		}
		return intervals;
		        
		    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
