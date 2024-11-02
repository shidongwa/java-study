package org.stone.study.algo.hw;

import java.util.Scanner;

/**
 * 题目描述
 * 有位客人来自异国，在该国使用m进制计数。该客人有个幸运数字n(n<m)，每次购物时，其总是喜欢计算本次支付的花费(折算为异国的价格后)中存在多少幸运数字。问：当其购买一个在我国价值k的产品时，其中包含多少幸运数字？
 *
 * 输入描述
 * 第一行输入为 k, n, m。
 *
 * 其中：
 * k 表示 该客人购买的物品价值（以十进制计算的价格）
 * n 表示 该客人的幸运数字
 * m 表示 该客人所在国度的采用的进制
 *
 * 输出描述
 * 输出幸运数字的个数，行末无空格。当输入非法内容时，输出0
 *
 * 输入：
 * 10 2 4
 * 输出：
 * 2
 *
 * 输入：
 * 10 4 4
 * 输出：
 * 0
 */
public class FortuneNumCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if(k < 0 || n < 0 || m <= 1 || n >= m) {
            System.out.println("0");
            return;
        }

        int ans = 0;
        while(k > 0) {
            if((k % m) == n) {
                ++ans;
            }

            k /= m;
        }

        System.out.println(ans);
    }
}
