package org.stone.study.algo.ex.ex20241023;

/**
 * https://leetcode.cn/problems/target-sum/description/
 */
public class NegPosSign {

    /**
     * leetcode 494 目标和
     * 给定一个数组，只包括非负整数。给定一个目标值 k，在数组元素中添加正号和负号，使得数组的和为 k，求添加正负号的方法数
     * 比如数组[1 1 1 1 1]，目标值为 3，有 5 种方法
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        int result = findTargetSumWays(nums, target);
        System.out.println(result);
    }

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (target > sum) {
            return 0;
        }
        int pos = (sum + target) / 2;

        int ans = backtrack(nums, 0, pos);
        System.out.println(ans);

        return ans;
    }

    /**
     * 回溯算法实现从 nums 数组中找到和为 sum 的组合数。类似求组合/集合数的方法
     * @param nums
     * @param start
     * @param sum
     * @return
     */
    public static int backtrack(int[] nums, int start, int sum) {
        if(sum == 0) {
            return 1;
        }

        if(sum < 0) {
            return 0;
        }

        int ans = 0;
        for(int i = start; i < nums.length; i++) {
            sum -= nums[i];
            ans += backtrack(nums, i + 1, sum);
            sum += nums[i];
        }

        return ans;
    }
}
