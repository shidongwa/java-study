package org.stone.study.algo.ex202412;
import java.util.Arrays;
import java.util.Scanner;
public class ProjectScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] workLoads = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(sc.nextLine());

        int ans = new ProjectScheduler().minCompletionTimeByDFS(workLoads, n);
//        int ans = new ProjectScheduler().minCompletionTimeByDP(workLoads, n);
        System.out.println(ans);
    }

    /**
     * 二分查找最小的工期
     * @param workLoads 每个任务的工作量
     * @param n 工人数量
     * @return
     */
    public int  minCompletionTimeByDFS(int[] workLoads, int n) {
        // 最长的工作量给一个人做，作为项目整体工期的最小值
        int left = Arrays.stream(workLoads).max().orElse(0);
        // 工作量总和给一个人做，作为项目整体工期的最大值
        int right = Arrays.stream(workLoads).sum();
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(check(workLoads, mid, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 检查是否可以完成任务
     * @param workLoads 每个任务的工作量
     * @param limit 每个人最大的工作量，超过这个值，就需要另一个工人
     * @param n 工人数量
     * @return
     */
    public boolean check(int[] workLoads, int limit, int n) {
        int[] allocates = new int[n];
        // 贪心算法没能解决所有 case，需要回溯算法遍历所有可能
        return dfs(workLoads, 0, limit, allocates);
    }

    /**
     * 深度优先搜索
     * @param workLoads 每个任务的工作量
     * @param i 当前任务的索引
     * @param limit 每个人最大的工作量
     * @param allocates 每个人已经分配的工作量
     * @return
     */
    private boolean dfs(int[] workLoads, int i, int limit, int[] allocates) {
        if(i == workLoads.length) {
            return true;
        }
        // 当前任务
        int curWorkLoad = workLoads[i];
        // 遍历每个人，找到一个合适的工人
        for(int j = 0; j < allocates.length; j++) {
            if (allocates[j] + curWorkLoad <= limit) {
                allocates[j] += curWorkLoad;
                if (dfs(workLoads, i + 1, limit, allocates)) {
                    return true;
                }
                allocates[j] -= curWorkLoad;
            }
        }

        return false;
    }

    /**
     * 动态规划解法,得不到最优解。这玩意儿是玄学，不确定原因
     * @param tasks
     * @param n
     * @return
     */
    public int minCompletionTimeByDP(int[] tasks, int n) {
        int m = tasks.length;
        int[] prefixSum = new int[m + 1];

        // 计算前缀和，prefixSum[i] 表示前 i 个任务的总工作量
        for (int i = 0; i < m; i++) {
            prefixSum[i + 1] = prefixSum[i] + tasks[i];
        }

        // dp[i][j] 表示前 i 个任务分配给 j 个开发人员的最小化最大工作量
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 初始条件：一个开发人员负责所有任务
        for (int i = 0; i <= m; i++) {
            dp[i][1] = prefixSum[i];  // 所有任务由一个开发者完成
        }

        // 填充 DP 表
        for (int j = 2; j <= n; j++) {  // j 个开发人员
            for (int i = 1; i <= m; i++) {  // i 个任务
                for (int k = 0; k < i; k++) {  // 划分点
                    int currentMaxWorkload = Math.max(dp[k][j - 1], prefixSum[i] - prefixSum[k]);
                    dp[i][j] = Math.min(dp[i][j], currentMaxWorkload);
                }
            }
        }

        return dp[m][n];
    }
}
