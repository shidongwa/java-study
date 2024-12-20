package org.stone.study.algo.ex202411;

import java.util.*;

/**
 * 小华和小为是很要好的朋友，他们约定周末一起吃饭
 *
 * 通过手机交流，他们在地图上选择了多个聚餐地点
 * (由于自然地形等原因，部分聚餐地点不可达)
 * 求小华和小为都能到达的聚餐地点有多少个?
 *
 * 第一行输入m和n，m代表地图的长度，
 * n代表地图的宽度第二行开始具体输入地图信息，
 * 地图信息包含:
 * 0 为通畅的道路
 * 1 为障碍物 (且仅1为障碍物)
 * 2 为小华或者小为，地图中必定有且仅有2个(非障碍物)
 * 3 为被选中的聚餐地点 (非障碍物)
 */
public class ReachableRestauran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        // 显式读取行尾的换行符
        scanner.nextLine();

        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
            // 显式读取行尾的换行符
            scanner.nextLine();
        }

        int ans = reachableRestaurant(m, n, grid);
        System.out.println(ans);
    }

    private static int reachableRestaurant(int m, int n, int[][] grid) {
        // 记录小华和小为的位置，刚好 2 个
        int[][] starts = new int[2][2];
        int k = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    starts[k][0] = i;
                    starts[k][1] = j;
                    ++k;
                }
            }
        }

        Set<Long> set1 = bfs(grid, starts[0][0], starts[0][1]);
        Set<Long> set2 = bfs(grid, starts[1][0], starts[1][1]);
        // 求交集，结果放在 set1 中
        set1.retainAll(set2);
        return set1.size();
    }

    /**
     * 从(x, y)开始 BFS 遍历，返回所有能到达的 3 的位置的集合
     * @param grid
     * @param x
     * @param y
     * @return
     */
    private static Set<Long> bfs(int[][] grid, int x, int y) {
        // 存放所有能到达的 3 的位置的集合
        Set<Long> res = new HashSet<>();

        int m = grid.length;
        int n = grid[0].length;
        // 标记已经访问过的位置
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }
        // 方向数组
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // BFS 队列
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            // 能走到一家餐厅，加入结果集
            if(grid[i][j] == 3) {
                // 二维坐标转一维坐标，原因是 Set 中不建议放数组，转换为不变量Long
                res.add((long)i * n + j);
            }
            for(int[] dir : dirs) {
                int nextX = i + dir[0];
                int nextY = j + dir[1];
                if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]
                        && grid[nextX][nextY] != 1) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }


        return res;
    }
}
