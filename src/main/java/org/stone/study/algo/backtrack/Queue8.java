package org.stone.study.algo.backtrack;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 八皇后实现的2种算法：回溯算法+位运算解法
 */
public class Queue8 {
    public static final int MAX = 8; // 几行几列的棋盘
    public static int count = 0; // 一共有多少解法
    private static int[] arr = new int[MAX];

    public static void main(String[] args) {
        // 回溯解法
        backtrace(0);
        System.out.println("total:" + count);

        // 位运算解法
/*        bitSolution(MAX);
        System.out.println("total:" + count);*/
    }

    /**
     * 回溯解法
     * @param row
     */
    public static void backtrace(int row) {
        if(row == MAX) {
            ++count;
            printResult();
            return;
        }
        for(int i = 0; i < MAX; i++) {
            arr[row] = i;
            if(valid(row)) {
                backtrace(row + 1);
            }
            arr[row] = 0; // 这一步可以不要，不是回溯算法的套路啊？？？？？
        }
    }

    /**
     * 位运算解法
     * @param n
     */
    public static void bitSolution(int n) {
        count = 0;
        Arrays.fill(arr, 0);
        LinkedList<Integer> oneSolution = new LinkedList<>();
        dfs(n, 0, 0, 0, 0, oneSolution);
    }

    private static void dfs(int n, int row, int col, int pie, int na, LinkedList<Integer> oneSolution) {
        if(row >= n) {
            ++count;
            System.out.println(oneSolution);
            return;
        }

        int bits = (~(col | pie | na)) & ((1 << n) - 1); // 当前所有的空位
        while(bits > 0) {
            int p = bits & -bits; // 取最低位1开始代表的数（包括后面的0）
            oneSolution.addLast(getPosOfLastOne(p)); // 放置1的位置（从1开始）
            dfs(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1, oneSolution);
            oneSolution.removeLast();
            bits = bits & (bits -1); // 打掉最后一位1
        }
    }

    /**
     * 检查当前选择是否合法
     * @param row
     * @return
     */
    private static boolean valid(int row) {
        for(int j = 0; j < row; j++) {
            if(Math.abs(arr[row]-arr[j]) == 0 || Math.abs(j - row) == Math.abs(arr[j] - arr[row])) {
                return false;
            }
        }

        return true;
    }

    private static void printResult() {
        for(int i = 0; i < MAX; i++) {
            System.out.print((arr[i] + 1) + ",");
        }
        System.out.println();
    }

    /**
     * 获取最后一个1所在的位置
     * @param num
     * @return
     */
    private static int getPosOfLastOne(int num) {
        int pos = 0;
        while(num != 0) {
            ++pos;
            num = (num >> 1);
        }

        return pos;
    }
}
