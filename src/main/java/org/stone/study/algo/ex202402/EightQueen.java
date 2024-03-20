package org.stone.study.algo.ex202402;

import java.util.Arrays;

/**
 * 分别采用位运算和回溯法求解八皇后的所有解法个数问题
 */
public class EightQueen {
    // N皇后中的 N
    private int n;

    public EightQueen(int n) {
        this.n = n;
    }
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen(8);
        // 八皇后所有解为92个
//         int count = eightQueen.dfs1(0, 0, 0, 0);
        int count = eightQueen.backtrack();
        System.out.println("count:" + count);
    }

    /**
     * 位运算方式计算八皇后的解法个数
     * @param row：当前行数
     * @param col：一个整数表示所有列上放置皇后的情况
     * @param pie：一个整数表示所有斜线上放置皇后的情况
     * @param na：一个整数表示所有反向斜线上放置皇后的情况
     */
    public int dfs1(int row, int col, int pie, int na) {
        int count = 0;
        if(row == n) {
            return 1;
        }

        // 一共有多少个位置可以放
        int bits = (~(col | pie | na)) & ((1 << n) - 1);

        while(bits > 0) {
            // 取最后一个 1 表示的数
            int bit = bits & -bits;
            // pie 下一行左移1位的位置已经被占用了，na 下一行右移1位的位置也被占用了
            count += dfs1(row + 1, col | bit, (pie | bit) << 1, (na | bit) >> 1);
            // 这个位置已经被选择过了，需要去掉
            bits = bits & (bits - 1);
        }

        return count;
    }

    /**
     * 回溯法求解 N 皇后的解法个数
     * @return
     */
    public int backtrack() {
        int[][] board = new int[8][8];
        for(int i = 0; i < 8; i++) {
            Arrays.fill(board[i], 0);
        }

        return dfs2(board, 0);
    }

    public int dfs2(int[][] board, int row) {
        if(row == n) {
            return 1;
        }

        int count = 0;
        for(int j = 0; j < n; j++) {
            //剪枝 + 回溯
            if(validMove(board, row, j)) {
                board[row][j] = 1;
                count += dfs2(board, row + 1);
                board[row][j] = 0;
            }
        }

        return count;
    }

    /**
     * 判断当前走的一步是否合法：列上没有皇后；左上斜线没有皇后；右上斜线没有皇后
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean validMove(int[][] board, int row, int col) {
        // 同一列上不能有皇后
        for(int i = 0; i < row; i++) {
            if(board[i][col] == 1) return false;
        }

        // 左上的反斜线上不能有皇后
        for(int i = row - 1, j = col -1; i >= 0 && j >= 0; i--,j--) {
            if(board[i][j] == 1) return false;
        }

        // 右上的反斜线上不鞥有皇后
        for(int i = row -1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if(board[i][j] == 1) return false;
        }

        return true;
    }
}
