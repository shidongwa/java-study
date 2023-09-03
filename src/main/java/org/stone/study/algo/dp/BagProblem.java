package org.stone.study.algo.dp;

/**
 * 0/1背包问题
 */
public class BagProblem {

    public static void main(String[] args) {
        int n = 3, w = 4;
        int[] weights = new int[] {2, 1, 3};
        int[] vals = new int[] {4, 2, 3};
//        int ans = bag01(n, w, weights, vals);
//        int ans = bag01_rolling_array(n, w, weights, vals);
        int ans = complete_bag(n, w, weights, vals);
        System.out.println("bag total value:" + ans);
    }

    private static int bag01(int n, int w, int[] weights, int[] vals) {
        // 物品从0开始索引；重量从1开始索引。选择前i个物品装满（不超过）重量j时的最大价值
        int[][] dp = new int[n][w+1];
        // 选择物品0时的价值
        for(int j = weights[0]; j <= w; j++) {
            dp[0][j] = vals[0];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= w; j++) {
                if(j - weights[i] >= 0) {
                    // 注意：如果i从1开始索引，weights和vals对应的是i-1而不是i。原因是dp[i][j]中的i对应的是第i-1个物品。这种
                    // 情况下初始化dp数组会简单一些，全0就行。
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]] + vals[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 第一种方法基础上，采用滚动数组降维（i这一维）；注意j采用从后往前迭代，避免使用dp数组前值（同一个背包使用多次， 0/1背包要求只能使用一次）
     * 如果采用二维dp数组i，j遍历先后顺序没关系；采用一维必须先遍历物品，再遍历重量。
     * @param n
     * @param w
     * @param weights
     * @param vals
     * @return
     */
    private static int bag01_rolling_array(int n, int w, int[] weights, int[] vals) {
        int[] dp = new int[w+1];
        for(int i = 0; i < n; i++) {
            for(int j = w; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-weights[i]] + vals[i]);
            }
        }

        return dp[w];
    }


    /**
     * 完全背包问题。每个背包可以无限次获取。对比0/1背包问题，只要把重量的遍历修改为从小到大，原因参考bag01_rolling_array
     * @param n
     * @param w
     * @param weights
     * @param vals
     * @return
     */
    private static int complete_bag(int n, int w, int[] weights, int[] vals) {
        int[] dp = new int[w+1];
        for(int i = 0; i < n; i++) {
            for(int j = weights[i]; j <= w; j++) {
                dp[j] = Math.max(dp[j], dp[j-weights[i]] + vals[i]);
            }
        }

        return dp[w];
    }
}
