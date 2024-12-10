package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 部门人力分配
 */
public class ManPowerAllocation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        String[] strArr = scanner.nextLine().split(" ");
        int[] requirements = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();

        int ans = allocateManPower(m, requirements);
        System.out.println(ans);
    }

    /**
     *
     * @param m: 完成所有任务的工期
     * @param requirements
     * @return
     */
    public static int allocateManPower(int m, int[] requirements) {
        Arrays.sort(requirements);

        int n = requirements.length;
        int left = requirements[n-1]; // 最小的工作量：每月至少完成一个需求（不能一个需求拆分为 2 个月内完成）
        int right = requirements[n-1] + requirements[n-2]; // 最大的工作量:每月最多完成 2 个需求

        int ans = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(m, requirements, mid)) {
                ans = mid; // 满足条件，尝试更小的工作量
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    /**
     *
     * @param m: 完成所有任务要求的月数
     * @param requirements
     * @param maxWorkload：待试探的每个月的工作量或者人力
     * @return
     */
    public static boolean check(int m, int[] requirements, int maxWorkload) {
        int left = 0;
        int right = requirements.length - 1;

        // 实际花费的月数
        int needs = 0;
        while (left <= right) {
            if (requirements[left] + requirements[right] <= maxWorkload) {
                left++;
                right--;
            } else {
                right--;
            }

            ++needs;
        }

        return needs <= m;
    }
}
