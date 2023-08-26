package org.stone.study.algo.sort;

/**
 * @author yovn
 */
public class InsertSorter {
    public void sort(int[] array, int from, int len) {
        int tmp;
        for (int i = from + 1; i < from + len; i++) {
            tmp = array[i];
            int j = i;
            for (; j > from; j--) {
                if (tmp < array[j - 1]) {
                    array[j] = array[j - 1];
                } else break;
            }
            array[j] = tmp;
        }
    }
}
