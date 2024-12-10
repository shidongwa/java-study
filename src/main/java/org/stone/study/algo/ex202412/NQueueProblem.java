package org.stone.study.algo.ex202412;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后问题，位运算解法
 * Q 代表放皇后的位置，.代表空位
 * 回溯
 */
public class NQueueProblem {

    public static void main(String[] args) {
        int n = 8;
        // 记录每行放皇后的位置（列号）
        int[] queue = new int[n];
        Arrays.fill(queue, -1);
        List<List<String>> ans = new ArrayList<>();
        backtrack(0, n, 0, 0, 0, queue, ans);

        // 92
        //System.out.println("total:" + ans.size());
        for (List<String> board : ans) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    /**
     * 回溯
     * @param row 当前行
     * @param n 总行数
     * @param col 列的限制
     * @param pie 撇的限制
     * @param na 捺的限制
     * @param queue 记录每行放皇后的位置（列号）
     * @param ans 最终结果
     */
    public static void backtrack(int row, int n, int col, int pie, int na, int[] queue, List<List<String>> ans) {
        if (row == n) {
            List<String> board = generateBoard(queue, n);
            ans.add(board);
            return;
        }

       // 得到当前所有的空位
       int availPos = ((1 << n) - 1) & (~(col | pie | na));
        // 遍历空位
       while (availPos != 0) {
           // 取最低位的1
           int pos = availPos & (-availPos);
           // 将最低位位置1置为0，表示该位置已经放置了皇后
           availPos = availPos & (availPos - 1);
           // 放置皇后
           int column = Integer.bitCount(pos - 1);
           queue[row] = column;
           // pie往左移，下一行中位置变小，位运算往右移合理一些；na 相反
           backtrack(row + 1, n, col | pos, (pie | pos) >> 1, (na | pos) << 1, queue, ans);

           // 回溯，将皇后移除
           queue[row] = -1;
       }
    }

    private static List<String> generateBoard(int[] queue, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queue[i]] = 'Q';
            board.add(new String(row));
        }

        return board;
    }
}
