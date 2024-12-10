package org.stone.study.algo.ex202412;

import java.util.Scanner;
import java.util.stream.Stream;

public class ReflectAndCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int[] nums = Stream.of(arr).mapToInt(Integer::parseInt).toArray();
        int m = nums[1];
        int n = nums[0];
        int[][] grid = new int[m][n];

        for(int i = 0; i < m; i++) {
            char[] chars = sc.nextLine().toCharArray();
            for(int j = 0; j < n; j++) {
                grid[i][j] = chars[j] - '0';
            }
        }

        int ans = countOne(grid, m, n, nums[2], nums[3], nums[4], nums[5], nums[6]);
        System.out.println(ans);
    }

    /**
     * 计算镜面反射后经过的 1 的个数
     * @param grid
     * @param m：行
     * @param n：列
     * @param x：列
     * @param y：行
     * @param dx：列的增量
     * @param dy：行的增量
     * @param t：时间线
     * @return
     */
    private static int countOne(int[][] grid, int m, int n, int x, int y, int dx, int dy, int t) {
        int ans = 0;
        while(t-- >= 0) {
            // 遇到 1 就计数, 注意起点 x 是列，y 是行
            if (grid[y][x] == 1) {
                ans++;
            }

            int x2 = x + dx, y2 = y + dy;
            if (x2 < 0 || x2 >= n) {
                dx = -dx;
            }
            if (y2 < 0 || y2 >= m) {
                dy = -dy;
            }

            x += dx;
            y += dy;
        }

        return ans;
    }
}
