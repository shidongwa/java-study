package org.stone.study.algo.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
	public static void sort(int[] datas) {
		// 数组长度 
		int len = datas.length;
		for (int i = 0; i < len - 1; i++) { 
			// 临时变量 
			int temp;
			// 交换标志,false表示未交换 
			boolean isExchanged = false; 
			for (int j = len - 1; j > i; j--) { 
				// 如果data[j]小于data[j - 1],交换 
				if (datas[j] < datas[j - 1]) {
					temp = datas[j];
					datas[j] = datas[j - 1];
					datas[j - 1] = temp;
					
					// 发生了交换,故将交换标志置为真 
					isExchanged = true; 
				}// end if 
			}// end for 
			
			// 本趟排序未发生交换,提前终止算法,提高效率 
			if (!isExchanged) { 
				return; 
			}// end if 
		}// end for 
	}// end sort 
	
	public static void main(String[] args) {
		int[] datas = {4, 9, 23, 1, 45, 27, 5, 2};
		sort(datas);
		System.out.println(Arrays.toString(datas));
	} 
}
