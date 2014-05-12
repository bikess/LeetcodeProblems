package com.problems1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
  }
public class MaxPointsSolution {

	/*
	 * 
	 * 思路：
	 * 就是以某个点，遍历其他的任何所有点与此点的斜率，若斜率相同则两个点在同一直线上，则此斜率的点的数目增1.
	 * 注意考虑几种特殊情况：1是重复的点，而是斜率为无穷的点。重复的点应该认为是
	 */
	public static int maxPoints(Point[] points) {
		int maxNum = 0;
		int len = points.length;
		if(len == 1){
			return 1;
		}
		HashMap<Float,Integer> xieliu = new HashMap<>();
		for(int i=0;i<len;i++){
			xieliu.clear();
			int duplicate = 1;
			Point p = points[i];
			for(int j=0;j<len;j++){
				Point p1 = points[j];
				if(i==j)
					continue;
//				判断2个点是否为重复的点
				if(p.x==p1.x && p.y==p1.y){
					duplicate++;
					continue;
				}
				if(p.x-p1.x!=0)
				{
					float k = (p.y-p1.y)/(float)(p.x-p1.x);
					int num=1;
					if(xieliu.containsKey(k)){
						num = num+xieliu.get(k);
					}
					xieliu.put(k, num);
				}else{
					int num=1;
					if(xieliu.containsKey(Float.MIN_VALUE))
					{
						num = num+xieliu.get(Float.MIN_VALUE);
					}
					xieliu.put(Float.MIN_VALUE, num);
				}
			}
			if(xieliu.isEmpty()){
				maxNum = duplicate;
			}
			else{
				Iterator iter = xieliu.entrySet().iterator();
				while(iter.hasNext()){
					java.util.Map.Entry entry = (java.util.Map.Entry)iter.next();
					int n = (int) entry.getValue();
					if(n+duplicate>maxNum){
						maxNum = n+duplicate;
					}
				}
			}
		}
		return maxNum;
	    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point[] points = new Point[2];
		points[0] = new Point(0, 1);
		points[1] = new Point(0, 1);
		int result = maxPoints(points);
		System.out.println(result);
	}

}
