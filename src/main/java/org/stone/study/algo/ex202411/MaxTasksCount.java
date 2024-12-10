package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 可以处理的最多任务数
 */
public class MaxTasksCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] tasks = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] strArr = scanner.nextLine().split(" ");
            tasks[i][0] = Integer.parseInt(strArr[0]);
            tasks[i][1] = Integer.parseInt(strArr[1]);
        }

        int ans = maxTasksCount(n, tasks);
        System.out.println(ans);
    }

    public static int maxTasksCount(int n, int[][] tasks) {
        // 按照结束时间升序排序
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        // 执行过的最大任务数
        int ans = 0;
        // 当前天
        int currentDay = 0;
        for (int i = 0; i < n; i++) {
            // 更新当前天，可以是下一天或者直接跳到当前任务开始时间
            currentDay = Math.max(tasks[i][0], currentDay + 1);
            // 当前任务可以执行
            if(currentDay <= tasks[i][1]) {
                ++ans;
            }
        }

        return ans;
    }
}
