package org.stone.study.algo.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), find the minimum number of conference rooms required.
 * using two ways: 1, priority queue 2, sweeping line
 */
public class Solution253 {

    public static void main(String[] args) {
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
        Solution253 mysolution = new Solution253();
        // ans: 2
//        System.out.println("ans:" + mysolution.minMeetingRooms(meetings));
        System.out.println("ans:" + mysolution.sweepingLine(meetings));

        meetings = new int[][]{{7, 10}, {2, 4}};
        // ans: 1
//        System.out.println("ans:" + mysolution.minMeetingRooms(meetings));
        System.out.println("ans:" + mysolution.sweepingLine(meetings));

        meetings = new int[][] {{1, 2}, {2,3}};
//        System.out.println("ans:" + mysolution.minMeetingRooms(meetings));
        System.out.println("ans:" + mysolution.sweepingLine(meetings));
    }

    /**
     * priorityQueue way
     * @param meetings
     * @return
     */
    public int minMeetingRooms(int[][] meetings) {
        // by default, it is min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // sort by start point
        Arrays.sort(meetings, (o1, o2) -> o1[0]-o2[0]);
        pq.add(meetings[0][1]);
        for(int i = 1; i < meetings.length; i++) {
            if(meetings[i][0] >= pq.peek()) {
                pq.poll();
            }

            pq.add(meetings[i][1]);
        }

        return pq.size();
    }

    /**
     * using sweeping line way
     * @param meetings
     * @return
     */
    private int sweepingLine(int[][] meetings) {
        int n = meetings.length;
        Point[] points = new Point[2 * n];
        int j = 0;
        // convert interval to points
        for(int i = 0; i < n; i++) {
            points[j] = new Point(meetings[i][0], 1);
            points[j+1] = new Point(meetings[i][1], 0);
            j += 2;
        }

        // calc maximum overlap number
        int ans = 0;
        int overlap = 0;
        Arrays.sort(points, (o1, o2) -> o1.time == o2.time ? o1.type - o2.type : o1.time - o2.time);
        for(int i = 0; i < 2 * n; i++) {
            if(points[i].type == 1) ++overlap;
            else if(points[i].type == 0) --overlap;

            ans = Math.max(ans, overlap);
        }

        return ans;
    }

    class Point {
        public Point(int time, int type) {
            this.time = time;
            this.type = type; // 1:start, 0:end, for resolving the case { {1,2}, {2, 3}}
        }

        private int time;
        private int type;


    }
}
