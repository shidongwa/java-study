package org.stone.study.algo.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
	public static void sort(int[] datas, int low, int high) {
		// 枢纽元,一般以第一个元素为基准进行划分 
		int pivotKey = datas[low];
		// 进行扫描的指针i,j;i从左边开始,j从右边开始 
		int i = low; 
		int j = high; 
		if (low < high) { 
			// 从数组两端交替地向中间扫描 
			while (i < j) { 
				while (i < j && datas[j] > pivotKey) {
					j--; 
				}// end while 
				
				if (i < j) { 
					// 比枢纽元素小的移动到左边 
					datas[i] = datas[j];
					i++; 
				}// end if 
				
				while (i < j && datas[i] < pivotKey) {
					i++; 
				}// end while 
				
				if (i < j) { 
					// 比枢纽元素大的移动到右边 
					datas[j] = datas[i];
					j--; 
				}// end if 
			}// end while 
			
			// 枢纽元素移动到正确位置 
			datas[i] = pivotKey;
			
			// 前半个子表递归排序 			
			sort(datas, low, i - 1);
			
			// 后半个子表递归排序 
			sort(datas, i + 1, high);
			
		}// end if 
		
	}// end sort 
	
	public static void main(String[] args) {
		int[] datas = { 4, 9, 23, 1, 23, 45, 27, 5, 2 };
		sort(datas, 0, datas.length - 1);
		System.out.println(Arrays.toString(datas));
	} 
}

