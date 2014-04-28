package com.problems4;

/****
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n)
 to hold additional elements from B. The number of elements initialized in A and B are m and n respectively
 * @author bike
 *
 */
public class MergeSortedArray {

	/***
	 * 方法1：定义两个指针,
	 * 本题注意由于A的长度为m+n，为了避免移位操作，从两个数组的末位元素，分别设置一个指针，从末位开始比较，插入到A数组的中，从末位开始。！！
	 * @param A
	 * @param m
	 * @param B
	 * @param n
	 */
	public void merge(int A[], int m, int B[], int n) {
        if(m<=0||n<=0)
        	return;
        int pla = m+n-1;
        while(m>=1&&n>=1){
        	if(A[m-1]>B[n-1]){
        		A[pla] = A[m-1];
        		m=m-1;
        	}else{
        		A[pla] = B[n-1];
        		n=n-1;
        	}
        	pla = pla-1;
        }
        while(n>=1){
        	A[pla--] = B[n-1];
        	n=n-1;
        }
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m =0;
		int n =1;
		int A[] = {0};
		int B[] = {1};
		MergeSortedArray t = new MergeSortedArray();
		
		t.merge(A, m, B, n);
		
		System.out.println(A[0]);
	}

}
