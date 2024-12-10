package org.stone.study.algo.ex202411;

import java.util.*;

/**
 * 最长的指定瑕疵度的元音子串
 */
public class MaxStringWithConstraint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 0
        int num = sc.nextInt(); // 输入瑕疵度
        sc.nextLine();
        // asdbuiodevauufgh
        String str = sc.nextLine(); // 输入字符串
        // 3
        int ans = getMaxLengthWithConstraint(str, num);
        System.out.println(ans);
    }

    private static int getMaxLengthWithConstraint(String str, int num) {
        // 元音字符集合
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        // 记录所有元音字符出现的位置
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (vowelSet.contains(str.charAt(i))) {
                positions.add(i);
            }
        }

        int res = 0;
        // 注意：滑动窗口处理的是 元音字符位置数组，不是原字符串
        int n = positions.size();

        // 滑动窗口
        int l = 0;
        int r = 0;
        while (r < n) {
            // 瑕疵度计算, 满足条件的 子串长度 - 元音字符个数 （注意两个长度都比实际值小 1，所有都不用写了）
            int diff = positions.get(r) - positions.get(l) - (r - l);
            // positions[i]值的变化一定比 i 的变化大，所以 diff 一定是单调递增的
            // 保证下面left和right指针的移动是正确的
            if (diff > num) {
                l++;
            } else if (diff < num) {
                r++;
            } else {
                // 更新答案
                res = Math.max(res, positions.get(r) - positions.get(l) + 1);
                r++;
            }
        }

        return res;
    }
}
