package org.stone.study.algo.ex202411;

import java.util.*;

/**
 * 会议室安排: 合并重叠的区间
 */
public class MeetingRoom {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 4
        int m = scanner.nextInt();
        // 1 4
        // 2 5
        // 7 9
        // 14 18
        int[][] intervals = new int[m][2];
        for(int i = 0; i < m; i++) {
            intervals[i][0] = scanner.nextInt();
            intervals[i][1] = scanner.nextInt();
        }

        int[][] res = merge(intervals);
        // 1 5
        // 7 9
        // 14 18
        Arrays.stream(res).forEach(e -> System.out.println(e[0] + " " + e[1]));
    }

    /**
     * 合并重叠区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        // 按区间左端点升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        int m = intervals.length;
        int[] cur = intervals[0];
        res.add(cur);
        for(int i = 1; i < m; i++) {
            int[] next = intervals[i];
            if(cur[1] >= next[0]) {
                // 合并区间
                cur[1] = Math.max(cur[1], next[1]);
            } else {
                // 不重叠，更新 cur，增加一条新的区间
                cur = next;
                res.add(cur);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
