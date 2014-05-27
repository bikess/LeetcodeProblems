package com.problems6;
/***
 * 题意：给两个已经排序的数组，返回这两个数组的所有数的中位数，要求时间复杂度为0（log（m+n））
 * @author bike
 *
 */
public class MedianofTwoSortedArrays {
	/***
	 * 方法1：就是归并，然后输出中位数就可以了，时间复杂度0（m+n），空间复杂度o（m+n）
	 * 方法2，时间复杂度0（（m+n）/2）
	 * 思路，在两个排序数组头部分别设置两个指针，当两个指针分别共同移动（m+n）/2步时，就可以得到中位数
	 * @param A
	 * @param B
	 * @return
	 */
    public double findMedianSortedArrays(int A[], int B[]) {
		int len1 = A.length;
		int len2 = B.length;
		assert(len1>=0);
		assert(len2>=0);
		int step = 0;
//		此时一共走(len1+len2)/2+1步，此时指针指向的就是中位数
//		step = (len1+len2)/2+1;
//		设置两个指针
		int pre1 = 0,pre2 = 0;
		double c1=0,c2=0;
		while(pre1<len1&&pre2<len2){
			double tem = 0;
			if(A[pre1]<B[pre2]){
				tem = A[pre1];
				pre1++;
			}else{
				tem = B[pre2];
				pre2++;
			}
			step++;
			if(step==(len1+len2)/2){
				c1 = tem;
			}
			if(step==(len1+len2)/2+1){
				c2 = tem;
				break;
			}
		}
    	if(step<(len1+len2)/2+1){
    		if(pre2==len2){
    			if(step!=(len1+len2)/2){
    				c1 = A[pre1+(len1+len2)/2-step-1];
    			}
    			c2 = A[pre1+(len1+len2)/2-step];
    		}
    		if(pre1==len1){
    			if(step!=(len1+len2)/2){
    				c1 = B[pre2+(len1+len2)/2-step-1];
    			}
    			c2 = B[pre2+(len1+len2)/2-step];
    		}
    	}
    	if((len1+len2)%2==1){
    		return c2;
    	}else
    		return (c1+c2)/2;
        
    }
    
    /***
     * 方法3： 时间复杂度0（log（m+n））
     * 每次剔除一定比在中位数之前的元素,类似于二分查找的思路
     * @param args
     */
    public double findMedianSortedArrays2(int A[], int B[]) {
		int total = A.length+B.length;
		if(total%2==1){
			return findKth(A,0,B,0,total/2+1);
		}
    	return (findKth(A,0,B,0,total/2)+findKth(A,0,B,0,total/2+1))/2;
    	
    }
    /***
     * 此方法，就是返回2个有序数组中，的第k大个数的方法！！！！！
     * @param A
     * @param start1
     * @param B
     * @param start2
     * @param pla
     * @return
     */
	private double findKth(int[] A, int start1,int[] B,int start2, int pla) {
		// TODO Auto-generated method stub
//		若A目前为判断的数大于B目前未判断的数，则交换两个数组进行判断
		if(A.length-start1>B.length-start2){
			return findKth(B,start2, A,start1,pla);
		}
//		若次数数组A已经没有数组需要判断，了此时直接返回pla-1的位置就是需要求解的第pla个数
		if(A.length-start1==0){
			return B[start2+pla-1];
		}
//		若此时A，B两个数组中均只有一个元素，直接返回两个数的最小值
		if(pla==1){
			return Math.min(A[start1], B[start2]);
		}
//		这就是本算法的重点，采取了剔除方法
		int c1 = Math.min(pla/2, A.length-start1),c2 = pla - c1;
//		若A数组的第c1个数与B数组的第c2个数比较
//		若A的第c1个比B的第c2个数小，则说明A的到c1的这部分数据一定是第k大数的前面部分，这部分数据不用找了，同时pla-c1，在剩下的数据中寻找第pla-c1大的数据
		if(A[start1+c1-1]<B[start2+c2-1]){
			return findKth(A, start1+c1, B, start2, pla-c1);
//		若A的第c1个比B的第c2个数大，则可以确定数组B的到c2这部分的数据一定是第k大个数的前面部分，这部分数据不用找了，同时pla-c2,在剩下的数据中寻找第pla-c2大的数据
		}else if(A[start1+c1-1]>B[start2+c2-1]){
			return findKth(A, start1, B, start2+c2, pla-c2);
//			二个数相等的话，说明第pla大的数肯定是这个值，无需再确定位置，直接返回这个数。
		}else{
			return A[start1+c1-1];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/***
	 * 题目扩展，返回这两个这两个有效数组的第k大个元素
	 */
}
