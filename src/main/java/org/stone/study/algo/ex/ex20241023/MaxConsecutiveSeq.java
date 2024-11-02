package org.stone.study.algo.ex.ex20241023;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续递增序列长度。不考虑顺序，和最长连续子序列，子数组不同
 * 比如 {10, 9, 2, 1, 3, 4}返回 4，不考虑 2 和 1 的先后顺序
 *
 * 解法：https://www.geeksforgeeks.org/maximum-consecutive-numbers-present-array/
 */
public class MaxConsecutiveSeq {

    public static void main(String[] args) {
        //int[] nums = {10, 9, 2, 1, 3, 4};
        int[] nums = {10, 9, 2, 1, 4};
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            int j = nums[i];
            while(j < nums.length && set.contains(j)) {
                ++j;
            }
            ans = Math.max(ans, j - nums[i]);
        }

        System.out.println(ans);

        /*
        int ans = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];

            while(!stack.isEmpty() && n > stack.peek()) {
                stack.pop();
            }

            stack.push(n);
            ans = Math.max(ans, stack.size() + 1);
        */
        // query max value
        /*
        int max = IntStream.of(nums).max().getAsInt();
        int[] count = new int[max + 1];
        for(int num : nums) {
            count[num]++;
        }

        int ans = 1;
        int tmpCnt = 0;
        for(int i = 1; i < count.length; i++) {

            if(count[i] > 0 && count[i-1] > 0) {
                ++tmpCnt;
                ans = Math.max(ans, tmpCnt);
            } else {
                tmpCnt = 1;
            }

        }

        System.out.println(ans);
        */

    }
}
