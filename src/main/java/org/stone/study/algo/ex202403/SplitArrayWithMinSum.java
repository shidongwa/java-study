package org.stone.study.algo.ex202403;

/**
 * https://leetcode.cn/problems/split-array-largest-sum/description/
 *
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 */
public class SplitArrayWithMinSum {

    public static void main(String[] args) {
        int[] nums = new int[] {7,2,5,10,8};
        int k = 2;
        // ans: 18
        System.out.println("ans:" + new SplitArrayWithMinSum().splitArray(nums, k));
    }

    /**
     * 给定一个非负整数数组 nums 和一个整数 k ，把这个数组分成 k 个非空的连续子数组。
     * 找到一种分组方式，使得返回k 个子数组各自和的最大值最小
     * @param nums
     * @param k
     * @return
     */
    public int splitArray(int[] nums, int k) {
        int max = 0, sum = 0;
        for(int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int left = max, right = sum;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            int groups = split(nums, mid);
            //System.out.println("left:" + left + ", right:" + right + ", mid:" + mid + ", groups:" + groups);
            if(groups > k) { // 分组和太小，分组数大于预定值
                left = mid + 1;
            } else if(groups < k) { // 分组和太大，分组数小于预定值
                right = mid - 1;
            } else { // 可能分组数 < k 走不到这里，因为 split 是贪心算法。但实际上可以从 < k 拆分到 k 组
                if(mid == max || split(nums, mid -1) != k) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return left;
    }

    /**
     * 拆分数组，每组和最大为maxSum。返回可以拆分的组数
     * 直接贪心算法实现
     */
    private int split(int[] nums, int maxSum) {
        int curSum = 0;
        int splitNum = 1;

        for(int num : nums) {
            if(curSum + num > maxSum) {
                ++splitNum;
                curSum = 0;
            }

            curSum += num;
        }

        return splitNum;
    }
}