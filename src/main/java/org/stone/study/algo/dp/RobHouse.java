package org.stone.study.algo.dp;

import java.util.Arrays;

/**
 * 打家劫舍，不能抢窃相邻的房子, 问抢的最大值
 */
public class RobHouse {
    private static int[] memo;
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 1};
        int[] arr = {2, 7, 9, 3, 1};
        int total = rob2(arr);
        System.out.println("total=" + total);
    }

    /**
     * 带备忘录的从顶到下
     * @param arr
     * @return
     */
    public static int rob1(int[] arr) {
        memo = new int[arr.length];
        Arrays.fill(memo, -1);
        return dfs(arr, 0);
    }

    /**
     * 从底到上迭代
     * @param arr
     * @return
     */
    public static int rob2(int[] arr) {
        int n = arr.length;
        if(n <= 2) return Math.max(arr[0], arr[1]);
        // dp2 当前位置往前2个位置， dp1 当前位置往前1个位置
        int dp2 = arr[0], dp1 = Math.max(arr[0], arr[1]);
        int dp = 0;
        for(int i = 2; i < n; i++) {
            dp = Math.max(dp1, arr[i] + dp2);
            dp2 = dp1;
            dp1 = dp;
        }

        return dp;
    }

    private static int dfs(int[] arr, int from) {
        if(from >= arr.length) {
            return 0;
        }
        if(memo[from] != -1) return memo[from];
        int val = Math.max(dfs(arr, from + 1), arr[from] + dfs(arr, from + 2));
        memo[from] = val;

        return val;
    }
}

