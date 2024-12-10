package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有一种特殊的加密算法，明文为一段数字串，经过密码本查找转换，生成另一段密文数字串。
 * 规则如下：
 * 1. 明文为一段数字串由 0\~9 组成
 * 2. 密码本为数字 0\~9 组成的二维数组
 * 3. 需要按明文串的数字顺序在密码本里找到同样的数字串，密码本里的数字串是由相邻的单元格数字组成，上下和左右是相邻的，注意：对角线不相邻，同一个单元格的数字不能重复使用。
 * 4. 每一位明文对应密文即为密码本中找到的单元格
 * 所在的行和列序号（序号从0开始）组成的两个数宇。
 * 如明文第 i 位 Data[i] 对应密码本单元格为 Book[x][y]，
 * 则明文第 i 位对应的密文为X Y，X和Y之间用空格隔开。
 * 如果有多条密文，返回字符序最小的密文。
 * 5. 如果密码本无法匹配，返回"error"。
 */
public class SpecialEncryption {
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static String minPath;
    private static boolean found;
    private static int[][] book;
    private static int[] data;
    private static boolean[][] visited;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 2
        int n = scanner.nextInt();
        scanner.nextLine();
        // 0 3
        String[] strArr = scanner.nextLine().split(" ");
        data = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();
        // 3
        int m = scanner.nextInt();
        scanner.nextLine();
        // 0 0 2
        // 1 3 4
        // 6 6 4
        book = new int[m][m];
        visited = new boolean[m][m];
        for(int i = 0; i < m; i++) {
            book[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(visited[i], false);
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(book[i][j] == data[0]) {
                    dfs(0, i, j, "");
                }
            }

        }
        // 0 1 1 1
        System.out.println(minPath);
    }

    /**
     * 深度递归回溯
     * @param index
     * @param i
     * @param j
     * @param path
     */
    private static void dfs(int index, int i, int j, String path) {
        int n = data.length;
        int m= book.length;

        if(index == n) {
            if(!found || path.length() < minPath.length()) {
                minPath = path;
            }

            found = true;
            return;
        }

        if(i < 0 || i >= m || j < 0 || j >= m || visited[i][j] || book[i][j] != data[index]) {
            return;
        }

        visited[i][j] = true;
        String newPath = path + i + " " + j + " ";
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            dfs(index + 1, x, y, newPath);
        }
        visited[i][j] = false;
    }
}
