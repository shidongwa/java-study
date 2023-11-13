package org.stone.study.algo.backtrack;

import java.util.*;

/**
 * 数组中有重复元素的全排列，不包括重复的排列。
 * selected记录访问过的数组元素；duplicated记录一次回溯中，不选择相同的元素（只控制当前回溯，比如选择排列中第 2 个元素的时候）
 */
public class Permutation3 {

    public static void main(String[] args) {
        Permutation3 p3 = new Permutation3();
        List<List<Integer>> ans = p3.permutate(new int[]{1, 1, 2});
        for(List<Integer> i : ans) {
            System.out.println(i);
        }
    }
    /* 回溯算法：全排列 II */
    void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<Integer>(state));
            return;
        }
        // 遍历所有选择
        Set<Integer> duplicated = new HashSet<Integer>();
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素 且 不允许重复选择相等元素
            if (!selected[i] && !duplicated.contains(choice)) {
                // 尝试：做出选择，更新状态
                duplicated.add(choice); // 记录选择过的元素值
                selected[i] = true;
                state.add(choice);
                // 进行下一轮选择
                backtrack(state, choices, selected, res);
                // 回退：撤销选择，恢复到之前的状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }

    /* 全排列 II */
    List<List<Integer>> permutate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
        return res;
    }

}


