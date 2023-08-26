package org.stone.study.algo.sort;

import java.util.Arrays;

/**
 * 算法思想是每次把待排序列分成两部分，分别对这两部分递归地用归并排序，
 * 完成后把这两个子部分合并成一个
 * 序列。
 * 归并排序借助一个全局性临时数组来方便对子序列的归并，该算法核心在于归并。
 *
 * @author yovn
 */
public class MergeSorter {

    public static void main(String[] args) {
        int[] datas = { 4, 9, 23, 1, 23, 45, 27, 5, 2 };
        new MergeSorter().sort(datas, 0, 9);
        System.out.println(Arrays.toString(datas));
    }
    public void sort(int[] array, int from, int len) {
        if (len <= 1) return;
        int[] tmp = new int[len];
        System.arraycopy(array, 0, tmp, 0, len);
        merge_sort(array, from, from + len - 1, tmp);

    }

    private final void merge_sort(int[] array, int from, int to, int[] tmp) {
        if (to <= from) {
            return;
        }
        int middle = (from + to) / 2;
        merge_sort(array, from, middle, tmp);
        merge_sort(array, middle + 1, to, tmp);
        merge(array, from, to, middle, tmp);
    }

    private final void merge(int[] array, int from, int to, int middle, int[] tmp) {
        int k = 0, leftIndex = 0, rightIndex = to - from;
        System.arraycopy(array, from, tmp, 0, middle - from + 1);
        for (int i = 0; i < to - middle; i++) {
            tmp[to - from - i] = array[middle + i + 1];
        }
        while (k < to - from + 1) {
            if (tmp[leftIndex] < tmp[rightIndex]) {
                array[k + from] = tmp[leftIndex++];

            } else {
                array[k + from] = tmp[rightIndex--];
            }
            k++;
        }

    }

}