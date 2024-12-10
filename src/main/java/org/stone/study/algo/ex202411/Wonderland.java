package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.Scanner;

public class Wonderland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 5 14 30 100
        String[] line1 = scanner.nextLine().split(" ");
        int[] costs = Arrays.stream(line1).mapToInt(Integer::parseInt).toArray();
        // 1 3 15 20 21 200 202 230
        String[] line2 = scanner.nextLine().split(" ");
        int[] days = Arrays.stream(line2).mapToInt(Integer::parseInt).toArray();

        int minCost = calcMinCost(costs, days);
        System.out.println(minCost);
    }

    /**
     * 计算最小花费:动态规划，反向计算更容易理解
     * @param costs
     * @param days
     * @return
     */
    private static int calcMinCost(int[] costs, int[] days) {
        boolean[] visited = new boolean[366];
        Arrays.fill(visited, false);
        for(int day : days) {
            visited[day] = true;
        }

        // dp[i] 表示从第i天开始，到365天结束，需要的最小花费
        int[] dp = new int[366];
        // dp值初始化：如果最后一天不需要旅游，花费为0，否则花费为1天票
        dp[365] = visited[365] ? costs[0] : 0;
        // 反向计算更容易理解
        for (int i = 364; i >= 1; --i) {
            if(!visited[i]) {
                dp[i] = dp[i+1];
                continue;
            }

            dp[i] = dp[i + 1] + costs[0]; // 买 1 天票
            dp[i] = Math.min(dp[i], dp[Math.min(i + 3, 365)] + costs[1]); // 买 3 天票
            dp[i] = Math.min(dp[i], dp[Math.min(i + 7, 365)] + costs[2]); // 买 7 天票
            dp[i] = Math.min(dp[i], dp[Math.min(i + 30, 365)] + costs[3]); // 买 30 天票
        }

        return dp[1];
    }
}
