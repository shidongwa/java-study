package org.stone.study.algo.ex202411;

import java.util.*;

public class DedupAndSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1,3,3,3,2,4,4,4,5
        String[] arr = scanner.nextLine().split(",");
        // 从字符串数组转化为整数数组
        int[] nums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        int[] res = dedupAndSort(nums);
        System.out.println(Arrays.toString(res));
    }

    public static int[] dedupAndSort(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return new int[0];
        }

        // 统计每个数字出现的次数和第一次出现的位置
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        HashMap<Integer, Integer> firstOccurMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            cntMap.put(nums[i], cntMap.getOrDefault(nums[i], 0) + 1);
            firstOccurMap.putIfAbsent(nums[i], i);
        }

        // 对 map 按 value 降序排序，再按 key 在数组中的位置升序排序
        List<Integer> keyList = new ArrayList<>(cntMap.keySet());
        keyList.sort((a, b) -> {
            int freqDiff = cntMap.get(b) - cntMap.get(a);
            if(freqDiff == 0) {
                return firstOccurMap.get(a) - firstOccurMap.get(b);
            } else {
                return freqDiff;
            }
        });
        // 从 List 转化为数组
        return keyList.stream().mapToInt(Integer::intValue).toArray();
    }
}
