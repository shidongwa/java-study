package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串拼接
 *
 * 带约束的排列问题：有相同字符，相同字符不能相邻
 */
public class StringConcatenation {
    private int ans = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int n = scanner.nextInt();

        int ans = new StringConcatenation().countPermutations(str, n);
        System.out.println(ans);
    }

    public int countPermutations(String str, int n) {
        char[] arr = str.toCharArray();
        int m = arr.length;
        if(m < n) {
            return 0;
        }
        Arrays.sort(arr);
        boolean[] used = new boolean[m];
        Arrays.fill(used, false);
        dfs(arr, -1, 0, used, n);

        return ans;
    }

    /**
     * 回溯求排列，考虑有相同字符，并且
     * @param arr
     * @param pre：递归上一层的字符下标，排列中前一个字符的下标
     * @param count：当前排列中字符个数
     * @param used：访问数组
     * @param n：目标排列字符数
     */
    private void dfs(char[] arr, int pre, int count, boolean[] used, int n) {
        if(count == n) {
            ans++;
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(used[i]) {
                continue;
            }
            // 递归父子层次中相同字符不相邻（不同递归深度，控制排列结果中的相邻字符）
            if(pre >= 0 && arr[i] == arr[pre]) {
                continue;
            }
            // 相同字符在递归过程中被选择的顺序，也需要保持前后相对的顺序不变（同一递归深度位置的字符）
            if(i > 0 && arr[i] == arr[i-1] &&!used[i-1]) {
                continue;
            }

            used[i] = true;
            dfs(arr, i, count + 1, used, n);
            used[i] = false;
        }
    }
}
