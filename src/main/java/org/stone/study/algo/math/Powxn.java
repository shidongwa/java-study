package org.stone.study.algo.math;

/**
 * 计算x的n次方
 */
public class Powxn {

    public static void main(String[] args) {
        int x = 2, n = 5;
        System.out.println("pow(2, 5)=" + pow2(2, 5));
        x = 2;
        n = 4;
        System.out.println("pow(2, 4)=" + pow2(2, 4));

    }

    /**
     * 递归实现pow(x, n)
     * @param x
     * @param n
     * @return
     */
    public static double pow(int x, int n) {
        if(n < 0) return 1/pow(x, -n);
        if(n == 0) return 1;
        if(n == 1) return x;

        if((n & 1) == 1) {
            return pow(x, n - 1) * x;
        } else {
           return pow(x * x, n / 2);
        }
    }

    /**
     * 非递归方式计算pow(x, n)
     * @param x
     * @param n
     * @return
     */
    public static double pow2(int x, int n) {
        if(n < 0) return 1/pow(x, -n);
        if(n == 0) return 1;
        if(n == 1) return x;

        int pow = 1;
        while(n >= 1) {
            if((n & 1) == 1) {
                pow = pow * x;
            }
            x = x * x;

            n = (n >> 1);
        }

        return pow;
    }
}
