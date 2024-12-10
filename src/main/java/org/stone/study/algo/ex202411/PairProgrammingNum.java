package org.stone.study.algo.ex202411;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PairProgrammingNum {

    public static void main(String[] args) {
        int n = 4;
        int[] arr = new int[] {1, 2, 3, 4};

        int ans = pairProgrammingNum(n, arr, 3);
        System.out.println(ans);
    }

    /**
     * 从 n 个数中选 3 个数
     * @param n：一共 n 个数
     * @param arr
     * @param k：选 3 个数
     * @return
     */
    public static int pairProgrammingNum(int n, int[] arr, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();

        backtrack(arr, 0, temp, ans, k);

        System.out.println(ans);
        return ans.size();
    }

    /**
     * 回溯法求所有组合
     * @param arr
     * @param start
     * @param temp
     * @param ans
     * @param k
     */
    public static void backtrack(int[] arr, int start, List<Integer> temp, List<List<Integer>> ans, int k) {
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            temp.add(arr[i]);
            backtrack(arr, i + 1, temp, ans, k);
            temp.remove(temp.size() - 1);
        }
    }
}
