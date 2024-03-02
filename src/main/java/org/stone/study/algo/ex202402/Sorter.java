package org.stone.study.algo.ex202402;

import java.util.Arrays;

public class Sorter {

    public static void main(String[] args) {
        int[] arr = new int[] {2, 10, 7, 3, 8, 15, 1};
//        int[] arr = new int[] {2, 1};
        System.out.println("origin arr:" + Arrays.toString(arr));
        Sorter sorter = new Sorter();
//        sorter.quicksort(arr, 0, arr.length - 1);
//        sorter.mergeSort(arr, 0, arr.length - 1);
//        sorter.mergeSort2(arr, new int[arr.length], 0, arr.length - 1);
        sorter.shellSort(arr);
        System.out.println("after sort:" + Arrays.toString(arr));

    }

    /**
     * 快速排序，先找到 pivot 元素在数组中排序后的最终位置；再对 pivot 左边区间和右边区间的子数组继续进行快速排序
     * @param arr
     * @param start
     * @param end
     */
    public void quicksort(int[] arr, int start, int end) {
        if(start >= end) return;
        int left = start, right = end;
        int pivot = arr[left];
        while(left < right) {
            while(left < right && arr[right] >= pivot) {
                --right;
            }

            if(left < right) {
                arr[left] = arr[right];
            }
            while(left < right && arr[left] <= pivot) {
                ++left;
            }

            if(left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = pivot;

        quicksort(arr, start, left -1);
        quicksort(arr, left + 1, end);
    }

    /**
     * 归并排序 1:切分前备份原数组，内存分配比归并排序 2多
     * @param arr
     * @param start
     * @param end
     */
    public void mergeSort(int[] arr, int start, int end) {
        if(start >= end) return;

        int mid = start + (end - start) / 2;
        int[] left = new int[mid - start + 1];
        int[] right = new int[end - mid];
        System.arraycopy(arr, start, left, 0, mid - start + 1);
        System.arraycopy(arr, mid + 1, right, 0, end - mid);

        mergeSort(left, 0, mid - start);
        mergeSort(right, 0, end - mid - 1);

        merge(arr, start, left, right);
    }

    /**
     * 归并排序 2：内存只分配一次
     * @param arr：原数组
     * @param temp：临时数组，和原数组大小一致
     * @param left：待排序数组左边界。需要指定的原因是递归调用需要修改待排序子数组边界
     * @param right：待排序数组右边界
     */
    public void mergeSort2(int[] arr, int[] temp, int left, int right) {
        if(left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort2(arr, temp, left, mid);
        mergeSort2(arr, temp, mid + 1, right);

        merge2(arr, temp, left, mid, right);
    }


    /**
     * 希尔排序，插入排序的优化。找一个增量序列，数组按增量分组后做插入排序。
     * @param arr
     */
    public void shellSort(int[] arr) {
        // 步长为 k，这个增量序列不是最优的，合适的增量序列是希尔排序的关键
        int gap = arr.length / 2;
        while(gap > 0) {
            // 步长为 gap，则分为 gap 组。每组做插入排序
            for(int k = 0; k < gap; k++) {
                // 找到每组的数
                for(int i = gap + k; i < arr.length; i += gap) {
                    // 插入排序
                    int temp = arr[i];
                    int pre = i - gap;
                    while(pre >= 0 && arr[pre] > temp) {
                        arr[pre + gap] = arr[pre];
                        pre -= gap;
                    }
                    arr[pre + gap] = temp;
                }

            }

            gap /= 2;
        }
    }

    /**
     * 合并数组，对应归并算法 1
     * @param arr
     * @param start
     * @param left
     * @param right
     */
    private void merge(int[] arr, int start, int[] left, int[] right) {
        int i = 0, j = 0, k = start;

        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while(i < left.length) {
            arr[k++] = left[i++];
        }

        while(j < right.length) {
            arr[k++] = right[j++];
        }
    }

    /**
     * 合并数组，对应归并数组 2。temp 数组 k 位置也可以从 0 开始，只要保证来回复制时位置一致即可（程序没有并发）
     * @param arr：原始数组
     * @param temp：临时数组
     * @param start：左子数组开始位置
     * @param mid：二分的中间位置，并归入左子数组右边界
     * @param end：右子数组结束位置
     */
    private void merge2(int[] arr, int[] temp, int start, int mid, int end) {
        int i = start, j = mid + 1, k = start;
        while(i <= mid && j <= end) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while(i <= mid) temp[k++] = arr[i++];
        while(j <= end) temp[k++] = arr[j++];

        // 原始数组从临时数组中还原
        System.arraycopy(temp, start, arr, start, end - start + 1);
    }

    /**
     * 交换数组中的元素
     * @param arr
     * @param l
     * @param r
     */
    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
