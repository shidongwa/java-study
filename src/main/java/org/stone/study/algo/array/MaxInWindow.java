package org.stone.study.algo.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 滑动窗口的最大值
 */
public class MaxInWindow {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6};
        // 滑动窗口是3
        int[] ans = max(arr, 3);
        System.out.println("ans:" + Arrays.toString(ans));
    }

    /**
     * 求数组arr中滑动窗口k内的最大值
     * @param arr
     * @param k
     * @return
     */
    public static int[] max(int[] arr, int k) {
        if(arr == null || arr.length < k) return new int[0];

        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[arr.length - k + 1];
        int j = 0;
        for(int i = 0; i < arr.length; i++) {
            // 窗口中的最大值是否要移出滑动窗口
            if(i - k >= 0 && arr[i-k] == arr[deque.peekFirst()]) deque.removeFirst();

            // 维护降序的双端队列
            while(deque.size() > 0 && arr[deque.peekLast()] < arr[i]) {
                deque.removeLast();
            }

            // 新原始入队。考虑元素有重复，队列中入队的是索引值
            deque.addLast(i);

            // i位置满足活动窗口大小后求最大值
            if(i >= k - 1) {
                ans[j++] = arr[deque.peekFirst()];
            }
        }

        return ans;
    }
}
