package com.problems4;

public class SortColors {

	/**
	 * @param args
	 */
	/***
	 * 
	 * 方法1思路：先记录每种颜色的数量，然后根据颜色的数量将相应的位置置为相应的元素
	 * @param A
	 */
	public void sortColors1(int[] A) {
		int len = A.length;
		if(len<=1)
			return;
		int red = 0,white = 0,blue=0;
		for(int i=0;i<len;i++){
			switch(A[i]){
			case 0:red++;break;
			case 1:white++;break;
			case 2:blue++;break;
			}
		}
		for(int i=0;i<len;i++){
			if(i<red){
				A[i] = 0;
			}
			else if(i<red+white){
				A[i] =1;
			}else{
				A[i] = 2;
			}
		}
	}
	public void sortColors2(int[] A) {
	     int len = A.length;
	     if(len<=1)
	    	 return ;
	     int e0,e1;
	     e0 = e1 = len-1;
	     for(int cur = len-1;cur>=0;cur--){
	    	 if(A[cur] == 1){
	    		 int temp = A[cur];
	    		 A[cur] = A[e0];
	    		 A[e0] = temp;
	    		 e0--;
	    	 }else if(A[cur] == 2){
	    		 int temp = A[e0];
	    		 A[e0] = A[cur];
	    		 A[cur] = temp;
	    		 A[e0] = A[e1];
	    		 A[e1] = temp;
	    		 e0--;
	    		 e1--;
	    	 }
	     }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {0,1,2,0,1,2};
		SortColors m = new SortColors();
		m.sortColors1(A);
		for(int i=0;i<A.length;i++){
			System.out.println(A[i]);
		}
	}

}
