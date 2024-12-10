package org.stone.study.algo.ex202411;

import java.util.Scanner;

public class DigitBaseConversion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(); // 购买的物品价值
        int n = scanner.nextInt(); // 幸运数字
        int m = scanner.nextInt(); // 进制

        if(n >= m) {
            // 参数异常
            return;
        }
        int ans = getLuckyNumberCnt2(k, n, m);
        System.out.println(ans);
    }

    /**
     * 求k的m进制表示中，数位上的数字n的个数。进制直接转换法
     * @param k
     * @param n
     * @param m
     * @return
     */
    private static int getLuckyNumberCnt(int k, int n, int m) {
        int cnt = 0;
        while(k != 0) {
            int digit = k % m;
            if(digit == n) {
                cnt++;
            }
            k = k / m;
        }

        return cnt;
    }

    /**
     * 通过转化为字符串求解
     * @param k
     * @param n
     * @param m
     * @return
     */
    private static int getLuckyNumberCnt2(int k, int n, int m) {
        String str = Integer.toString(k, m);
        String searchStr = String.valueOf(n);
        int pos = 0;
        int cnt = 0;
        while(pos != -1) {
            pos = str.indexOf(searchStr, pos);
            if(pos != -1) {
                cnt++;
                pos += searchStr.length();
            }
        }

        return cnt;
    }
}
