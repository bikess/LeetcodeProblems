package com.examination;

import java.util.Deque;
import java.util.LinkedList;

public class Baidu {

	/**
	 * @param args
	 */
	/***
	 * 
	 * 问题1：输入：一个数组，和一个 整型数k，输出要求数组中每k个位置的数字的均值替换
	 */
	public void tihuan(float [] A,int k){
		int temp[] = new int[k/2+1];
		Deque<Float> d = new LinkedList<>();
		int i;
		
		for(i=0;i<=A.length-k;i++){
			float sum = 0;
			for(int j=0;j<k;j++){
				sum = sum + A[i+j];
			}
//			替换的位置为k/2+1;
			d.add((float)sum/k);
//			替换最后一个位置的
			if(i+k==A.length){
				A[(i+A.length-1)/2]=sum/k;
				break;
			}
			if(i>=k/2){
				A[i]=d.poll();
			}
		}
		if(!d.isEmpty()){
			int p = k/2;
			while(!d.isEmpty())
			{ 
				A[p]=d.poll();
				p++;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float a[]={7,2,8,4,5,6,3,7,8};
		Baidu b = new Baidu();
		b.tihuan(a, 5);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}

}
