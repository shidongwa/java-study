package org.stone.study.algo.sort;

import java.util.Arrays;

/**
 * Shell排序可以理解为插入排序的变种，它充分利用了插入排序的两个特点：
 * 1）当数据规模小的时候非常高效
 * 2）当给定数据已经有序时的时间代价为O(N)
 * 所以，Shell排序每次把数据分成若个小块，来使用插入排序，而且之后在这若个小块排好序的情况下把
 * 它们合成大一点的小块，继续使用插入排序，不停的合并小块，知道最后成一个块，并使用插入排序。
 * <p>
 * 这里每次分成若干小块是通过“增量” 来控制的，开始时增量较大，接近N/2,从而使得分割出来接近N/2
 * 个小块，逐渐的减小“增量“最终到减小到1。
 * <p>
 * 一直较好的增量序列是2^k-1,2^(k-1)-1,.....7,3,1,这样可使Shell排序时间复杂度达到O(N^1.5)
 * 所以我在实现Shell排序的时候采用该增量序列
 *
 * @author yovn
 */
public class ShellSorter {

    public static void main(String[] args) {
        int[] datas = { 4, 9, 23, 1, 23, 45, 27, 5, 2 };
        sort(datas, 0, datas.length);
        System.out.println(Arrays.toString(datas));
    }
    public static void sort(int[] array, int from, int len) {

        //1.calculate  the first delta value;
        int value = 1;
        while ((value + 1) * 2 < len) {
            value = (value + 1) * 2 - 1;
        }

        for (int delta = value; delta >= 1; delta = (delta + 1) / 2 - 1) {
            for (int i = 0; i < delta; i++) {
                modify_insert_sort(array, from + i, len - i, delta);
            }
        }
    }

    private static void modify_insert_sort(int[] array, int from, int len, int delta) {
        if (len <= 1) return;
        int tmp;
        for (int i = from + delta; i < from + len; i += delta) {
            tmp = array[i];
            int j = i;
            for (; j > from; j -= delta) {
                if (tmp < array[j - delta]) {
                    array[j] = array[j - delta];
                } else break;
            }
            array[j] = tmp;
        }
    }
}
