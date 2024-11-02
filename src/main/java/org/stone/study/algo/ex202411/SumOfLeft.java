package org.stone.study.algo.ex202411;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumOfLeft {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1 2 3 4 5 6 7 8 9
        int[]  arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 4
        int jump = scanner.nextInt();
        // 3
        int left = scanner.nextInt();

        int ans = sumLeft(arr, jump, left);
        // 13
        System.out.println("ans:" + ans);
    }

    /**
     * 跳跃求剩余和
     * @param arr 数组
     * @param jump 跳跃步长
     * @param left 剩余个数
     * @return 求剩余和
     */
    public static int sumLeft(int[] arr, int jump, int left) {
        // 把 arr 变成 list 方便操作
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        int index = 0;
        while(list.size() > left) {
            index = (index + jump + 1) % list.size();
            // 依次删除 6,2,8,5,4,7
            list.remove(index);
            System.out.println("del: " + list.get(index));

            // 因为删除了一个元素，所以 index 要减 1
            --index;
        }

        // list:[1, 3, 9]
        System.out.println("list:" + list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
