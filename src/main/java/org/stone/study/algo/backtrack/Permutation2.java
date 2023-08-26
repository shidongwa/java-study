package org.stone.study.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation2 {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3);
        List<List<Integer>> ans = permute(arr);
        System.out.println("size:" + ans.size());
        ans.forEach(System.out::println);
    }
    public static List<List<Integer>> permute(List<Integer> arr) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(ans, arr, temp);
        return ans;
    }

    public static void dfs(List<List<Integer>> ans, List<Integer> arr, List<Integer> temp) {
        if(temp.size() == arr.size()) {
            ans.add(new ArrayList<>(temp));
        }

        for(int i = 0; i < arr.size(); i++) {
            if(temp.contains(arr.get(i))) continue;
            temp.add(arr.get(i));
            dfs(ans, arr, temp);
            temp.remove(arr.get(i));
        }
    }
}

