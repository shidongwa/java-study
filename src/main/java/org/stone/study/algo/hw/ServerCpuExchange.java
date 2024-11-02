package org.stone.study.algo.hw;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 现有两组服务器A和B，每组有多个算力不同的CPU，其中A[i]是A组第i个CPU的运算能力，B[i]是B组第i个CPU的运算能力。<span>一组服务器的总算力是各CPU的算力之和。</span>为了让两组服务器的算力相等，允许从每组各选出一个CPU进行一次交换，求两组服务器中，用于交换的CPU的算力，并且要求从A组服务器中选出的CPU，算力尽可能小。
 *
 *  * 输入描述
 * 第一行输入为L1和L2，以空格分隔，L1表示A组服务器中的CPU数量，L2表示B组服务器中的CPU数量
 * 第二行输入为A组服务器中各个CPU的算力值，以空格分隔.
 * 第三行输入为B组服务器中各个CPU的算力值，以空格分隔 1 ≤ L1, L2 ≤ 10000 1 ≤ A[i], B[i] ≤ 100000
 *
 * * 输出描述
 * 对于每组测试数据，输出两个整数，以空格分隔，依次表示A组选出的CPU算力，B组选出的CPU算力。要求从A组选出的CPU的算力尽可能小。 **备注**:保证两组服务器的初始总算力不同，答案肯定存在。
 *
 * 输入：
 * 	2 2
 * 	1 1
 * 	2 2
 * 输出：
 * 	1 2
 * 说明：从A组中选出算力为1的CPU，与B组中算力为2的进行交换，使两组服务器的算力都等于3。
 *
 * 输入：
 * 	2 2
 * 	1 2
 * 	2 3
 * 输出：
 * 	1 2
 *
 *
 * 输入：
 * 	1 2
 * 	2
 * 	1 3
 * 输出：
 * 	2 3
 *
 * 输入：
 * 	3 2
 * 	1 2 5
 * 	2 4
 * 输出：
 * 	5 4
 *
 */
public class ServerCpuExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int L1 = scanner.nextInt();
        int L2 = scanner.nextInt();

        int[] A = new int[L1];
        int[] B = new int[L2];

        for (int i = 0; i < L1; i++) {
            A[i] = scanner.nextInt();
        }

        for (int i = 0; i < L2; i++) {
            B[i] = scanner.nextInt();
        }

        scanner.close();

        int[] result = findExchangeCPUs(L1, L2, A, B);
        System.out.println(result[0] + " " + result[1]);
    }

    private static int[] findExchangeCPUs(int L1, int L2, int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;

        for (int a : A) {
            sumA += a;
        }

        for (int b : B) {
            sumB += b;
        }

        int diff = (sumA - sumB) / 2;

        Set<Integer> BSet = new HashSet<>();
        for (int b : B) {
            BSet.add(b);
        }

        for (int a : A) {
            int b = a - diff;
            if (BSet.contains(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{-1, -1};  // 如果没有找到合适的交换对
    }
}

