package org.stone.study.algo.sort;

import java.util.Arrays;

/**
 * @author yovn
 *
 * 选择排序，非稳定排序
 */
public class SelectSorter {

    public static void main(String[] args) {
        int[] datas = { 4, 9, 23, 1, 23, 45, 27, 5, 2 };
        sort(datas, 0, datas.length);
        System.out.println(Arrays.toString(datas));
    }
    public static void sort(int[] array, int from, int len) {
        for (int i = 0; i < len; i++) {
            int smallest = i;
            int j = i + from;
            for (; j < from + len; j++) {
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            swap(array, i, smallest);
        }
    }

    private static void swap(int[] arr, int i , int smallest) {
        int tmp = arr[i];
        arr[i] = arr[smallest];
        arr[smallest] = tmp;
    }

}
