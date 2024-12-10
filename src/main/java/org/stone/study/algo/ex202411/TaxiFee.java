package org.stone.study.algo.ex202411;

import java.util.Scanner;

/**
 * 程序员小明打了一辆出租车去上班。
 * 出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。
 * 出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。
 * 比如：
 * 23再多一块钱就变为25；
 * 39再多一块钱变为50；
 * 399再多一块钱变为500；
 * 小明识破了司机的伎俩，准备利用自己的学识打败司机的阴谋。
 * 给出计费表的表面读数，返回实际产生的费用。
 *
 * 示例输入/输出：
 * 5
 * 4
 *
 * 17
 * 15
 *
 * 100
 * 81
 */
public class TaxiFee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = getActualFee2(n);
        System.out.println(m);
    }

    /**
     * 计算实际产生的费用
     * @param n 计费表的表面读数
     * @return 实际产生的费用
     */
    private static int getActualFee1(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(String.valueOf(i).contains("4")) {
                cnt++;
            }
        }

        return n - cnt;
    }

    /**
     * 利用进制转换的思想，不是很好理解。10进制跳过数字 4 相当于 9 进制
     * @param n 计费表的表面读数
     * @return 实际产生的费用
     */
    private static int getActualFee2(int n) {
        int ans = 0;

        int multiplier = 1;
        while(n > 0) {
            int m = n % 10;
            n /= 10;

            if (m > 4) {
                m -= 1;
            }
            ans += m * multiplier;
            multiplier *= 9;
        }

        return ans;
    }
}
