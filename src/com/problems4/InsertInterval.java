package com.problems4;

import java.util.ArrayList;
import java.util.ListIterator;

public class InsertInterval {

 class Interval {
  int start;
  int end;
  Interval() { start = 0; end = 0; }
Interval(int s, int e) { start = s; end = e; }
 }


    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> result = new ArrayList<Interval>();
    	if(newInterval==null){
    		return intervals;
    	}
    	if(intervals.size()==0){
    		result.add(newInterval);
    		return result;
    	}
    	for(int i=0;i<intervals.size();i++){
    		if(intervals.get(i).start<newInterval.start){
    			if(intervals.get(i).end<newInterval.start){
    				result.add(intervals.get(i));
    				if(i+1==intervals.size())
    					result.add(newInterval);
    				continue;
    			}
    			else if(intervals.get(i).end>newInterval.end){
    				result.add(new Interval(intervals.get(i).start, intervals.get(i).end));
    			}else{
    				Interval temp = new Interval(intervals.get(i).start, newInterval.end);
    				i++;
    				while(i<intervals.size()){
    					if(intervals.get(i).start>newInterval.end){
    						temp.end = newInterval.end;
    					}else{
    						if(intervals.get(i).end>newInterval.end){
    							temp.end = intervals.get(i).end;
    						}
    					}
    					i++;
    				}
    				intervals.add(temp);
    			}
    		}else{
    			Interval temp = newInterval;
    			if(intervals.get(i).end>newInterval.end){
    				temp.end = intervals.get(i).end;
    				result.add(temp);
    			}else{
    				i++;
    				while(i<intervals.size()){
    					if(intervals.get(i).start>newInterval.end){
    						temp.end = newInterval.end;
    					}else{
    						if(intervals.get(i).end>newInterval.end){
    							temp.end = intervals.get(i).end;
    						}
    					}
    					i++;
    				}
    				intervals.add(temp);
    			}
    		}
    		if(i<intervals.size())
    			result.add(intervals.get(i));
    	}
    	return result;
		        
}
    /***
     * 方法2
     *  这个方法好，上面的方法过于繁琐，考虑了各种情况，而且还没有利用额外的空间
     *  只要分为三种情况
     *  （1）当新插入的值的low比当前值的end还大，则不用再考虑，直接在最后插入这个新的值
     *  （2）当新插入的值的end比当前值的low还小，则需要在当前值的前面插入这个新的值，然后直接返回就可以。
     *  （3）当新插入的值的low与end，与当前值的low与end有交叉，则更新要插入的新值的low为两个low中的最小值，要插入的新值的high是两个high中的最大值
     *  并且把当前的值从数组中移除。
     *  然后在链表循环完成后，插入当前的要插入的值就可以了。
     */
    public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
    	int sz  = intervals.size();
    	int low = newInterval.start;
    	int high = newInterval.end;
    	ListIterator<Interval> iter = intervals.listIterator();
    	while(iter.hasNext()){
    		Interval it = iter.next();
    		if(high<it.start){
    			iter.previous();
    			iter.add(new Interval(low, high));
    			return intervals;
    		}
    		if(low>it.end){
    			continue;
    		}else{
    			low = Math.min(low, it.start);
    			high = Math.max(high,it.end);
    			iter.remove();
    		}
    	}
    	intervals.add(new Interval(low, high));
    	return intervals;
    }
	/**
	 * @param args
	 */
    /***
     * 方法3 
     * @param args
     */
    public ArrayList<Interval> insert3(ArrayList<Interval> intervals, Interval newInterval) {
    	int low = newInterval.start;
    	int high = newInterval.end;
    	boolean flag = false;
    	for(int i=0;i<intervals.size();i++){
    		if(intervals.get(i).start<=low&&(i==intervals.size()-1||intervals.get(i+1).start>low)){
    			if(intervals.get(i).end>=low){
    				newInterval.start = intervals.get(i).start;
    				intervals.remove(i);
    			}
    			flag = true;
    		}
    		if(flag==true){
    			intervals.remove(i);
    		}
    		if(intervals.get(i).end>=high){
    			if(intervals.get(i).start<=high){
    				newInterval.end = intervals.get(i).end;
    				intervals.remove(i);
    			}
    			flag = false;
    			intervals.add(newInterval);
    		}
    	}
    	return intervals;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
