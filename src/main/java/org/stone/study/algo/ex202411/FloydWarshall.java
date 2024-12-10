package org.stone.study.algo.ex202411;

import java.util.*;
import java.io.*;

/**
 *
 * 快递公司每日早晨，给每位快递员推送需要淡到客户手中的快递以及路线信息，快递员自己又查找了一些客户与客户之间的路线距离信息，请你依据这些信息，给快递员设计一条最短路径，告诉他最短路径的距离。
 * 不限制快递包裹送到客户手中的顺序，但必须保证都送到客户手中；
 * 用例保证一定存在投递站到每位客户之间的路线，但不保证客户与客户之间有路线，客户位置及投递站均允许多次经过；
 * 所有快递送完后，快递员需回到投递站
 * 示例输入：
 5 1
 5 1000
 9 1200
 17 300
 132 700
 500 2300
 5 9 400
 输出：
 9200
 */
public class FloydWarshall {
    public static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        Map<Integer, Integer> custToIndex = new HashMap<>();

        // 初始化图,这里假定所有客户节点都在输入中，虽然不一定从 1 开始，连续增加
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        int index = 1;
        // 读取投递站到客户的距离
        for (int i = 0; i < n; i++) {
            line = scanner.nextLine().split(" ");
            int id = Integer.parseInt(line[0]);
            int distance = Integer.parseInt(line[1]);
            dist[0][index] = distance;
            dist[index][0] = distance;
            custToIndex.put(id, index);
            ++index;
        }

        // 读取客户与客户之间的距离
        for (int i = 0; i < m; i++) {
            line = scanner.nextLine().split(" ");
            int id1 = custToIndex.get(Integer.parseInt(line[0]));
            int id2 = custToIndex.get(Integer.parseInt(line[1]));
            int distance = Integer.parseInt(line[2]);
            dist[id1][id2] = distance;
            dist[id2][id1] = distance;
        }

        int minDistance = getMinDistance(dist, n);
        System.out.println(minDistance);
    }

    /**
     * 计算从投递站出发，经过所有客户，返回投递站的最短路径距离
     * @param dist 距离矩阵
     * @param n 客户节点数量（不包括 0 号投递站节点）
     * @return 最短路径距离
     */
    private static int getMinDistance(int[][] dist, int n) {

        // Floyd-Warshall算法求任意 2 点之间的最短路径
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 动态规划求解
        // dp[i][j] 表示状态 i 在节点 j 时走过的最短路径长度
        // 状态 i 表示已经访问过的节点集合，1（01）表示走过0号节点（投递站），2（10）表示走过 1 号节点（第一个客户），
        // 3（11）表示走过 0 号和 1 号节点，以此类推
        // n + 1个节点，全部选中的状态值为为 2 ^ (n + 1) - 1
        int[][] dp = new int[1 << (n + 1)][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        // 初始状态，在第 i 个客户节点时（状态中不包括 0 号节点），走过的最短路径长度
        for (int i = 1; i <= n; i++) {
            dp[1 << i][i] = dist[0][i];
        }
        // 遍历所有状态（选中不同的客户节点组合）
        for (int mask = 1; mask < (1 << (n + 1)); mask++) {
            // 检查每一个节点
            for (int i = 1; i <= n; i++) {
                // i 节点包括在状态中
                if ((mask & (1 << i)) != 0) {
                    // 遍历状态中其他节点，找最短路径
                    for (int j = 0; j <= n; j++) {
                        //  j 节点包括在状态中，且 不等于 i 节点
                        if (i != j && (mask & (1 << j)) != 0) {
                            // 状态 mask 去掉 i 节点（异或），再加上从 j 节点到 i 节点的距离
                            dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] + dist[j][i]);
                        }
                    }
                }
            }
        }

        // 计算从所有客户返回投递站的最短路径距离
        int minDistance = INF;
        for (int i = 1; i <= n; i++) {
            // 特别注意：(1 << (n + 1)) - 1 表示所有节点都选中的状态，
            // 因为 0 号节点是投递站，当前状态未包括，所以状态为 (1 << (n + 1)) - 2
            minDistance = Math.min(minDistance, dp[(1 << (n + 1)) - 2][i] + dist[i][0]);
        }

        return minDistance == INF ? -1 : minDistance;
    }
}
