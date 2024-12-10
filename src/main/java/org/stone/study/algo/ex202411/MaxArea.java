package org.stone.study.algo.ex202411;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaxArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        // 需要显式读取行尾的换行符
        scanner.nextLine();

        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
            //
            scanner.nextLine();
        }

        int ans = maxArea(m, n, grid);
        System.out.println(ans);
    }

    /**
     * 最大面积
     * @param m 行数
     * @param n 列数
     * @param grid 矩阵
     * @return 最大面积
     */
    public static int maxArea(int m, int n, int[][] grid) {
        // 记录每个数字的最小行、最大行、最小列、最大列
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    if(map.containsKey(grid[i][j])) { // 再次出现更新边界
                        int[] arr = map.get(grid[i][j]);
                        arr[0] = Math.min(arr[0], i); // 最小行
                        arr[1] = Math.max(arr[1], i); // 最大行
                        arr[2] = Math.min(arr[2], j); // 最小列
                        arr[3] = Math.max(arr[3], j); // 最大列
                        map.put(grid[i][j], arr);
                    } else { // 第一次出现初始化边界
                        map.put(grid[i][j], new int[]{i, i, j, j}); // 最小行，最大行，最小列，最大列
                    }
                }
            }
        }

        // 计算每个数字的面积并更新最大面积
        int maxArea = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            int area = (arr[1] - arr[0] + 1) * (arr[3] - arr[2] + 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
