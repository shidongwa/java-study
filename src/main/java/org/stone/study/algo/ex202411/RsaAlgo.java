package org.stone.study.algo.ex202411;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * RSA加密算法在网络安全世界中无处不在，
 * 它利用了极大整数因数分解的困难度，数据越大，安全系数越高，
 * 给定一个 32 位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
 */
public class RsaAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();
        int[] arr = RsaDecompose(p);
        System.out.println(arr[0] + " " + arr[1]);
    }

    /**
     * 分解 RSA 质数：埃氏筛法
     * @param p
     * @return
     */
    private static int[] RsaDecompose(int p) {
        int[] arr = new int[2];
        Set<Integer> factors = new HashSet<>();
        int temp = p;
        int n = 2;
        while ( temp != 1) {
            if (temp % n == 0) { // 2和 2 的倍数，3和 3 的倍数，5和 5 的倍数，也就是埃氏筛法
                factors.add(n);
                temp /= n;
            } else {
                ++n;
            }
        }

        for (Integer factor1 : factors) {
            for (Integer factor2 : factors) {
                if (factor1 * factor2 == p) {
                    arr[0] = Math.min(factor1, factor2);
                    arr[1] = Math.max(factor1, factor2);
                    return arr;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
