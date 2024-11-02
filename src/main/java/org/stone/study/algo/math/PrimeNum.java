package org.stone.study.algo.math;

import java.util.Arrays;

public class PrimeNum {

    public static void main(String[] args) {
        int n = 20;

        boolean[] res = calcPrimeNum(n);
        for(int i = 0; i <= n; i++) {
            if(res[i]) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 小于等于 n 的所有质数, 埃氏筛算法
     * @param n
     * @return
     */
    private static boolean[] calcPrimeNum(int n) {
        boolean[] isPrime = new boolean[n+1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i * i <= n; i++) {
            if(isPrime[i]) {
                for(int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
