package org.stone.study.algo.ex202403;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 滑动窗口内的最大值
 */
public class MaxInSlidingWin {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, -1, -3, 5, 3, 6};
        int k = 3;
        System.out.println("max sliding win:" + Arrays.toString(maxSlide(arr, k)));
    }

    /**
     * 长度为 K 的滑动窗口的最大值，结果为arr.length - k + 1的数组
     * 双端队列 deque 中保存的是滑动窗口中递减的元素值
     * @param arr
     * @param k
     * @return
     */
    private static int[] maxSlide(int[] arr, int k) {
        if(arr.length < k) return new int[0];
        int[] ans = new int[arr.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < arr.length; i++) {
            // 双端队列中最左元素超过 K 范围时清理掉
            if(!deque.isEmpty() && i - deque.peek() >= k) deque.remove();
            // 双端队列右侧加入元素时把前面小于当前元素的都出队
            while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) deque.removeLast();

            deque.add(i);
            // 至少 k 个元素时把双端队列最左元素加入答案中
            if(i + 1 >= k) {
                ans[i + 1 - k] = arr[deque.peek()];
            }
        }
        return ans;
    }
}
