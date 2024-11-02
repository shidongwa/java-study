package org.stone.study.algo.ex202403;

import java.util.Arrays;

/**
 * [875. 爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class MinEatingSpeed {
    /**
     * 解法 1：二分查找最左值边界处理好理解一点。
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();

        int left = 1, right = max;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            long spent = spentHours(piles, mid);

            if(spent > h) {
                left = mid + 1;
            } else if(spent < h){
                right = mid - 1;
            } else {
                //System.out.println("spent:" + spent + ", left:" + left + ", right:" + right);
                if(mid == 1 || spentHours(piles, mid - 1) > h) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        //System.out.println("left:" + left + ", right:" + right);
        return left;
    }

    /**
     * 以速度 speed 依次消耗 piles 数组。不足去整（往大的取）
     */
    private long spentHours(int[] piles, int speed) {
        long spent = 0;
        for(int pile : piles) {
            spent += (pile + speed - 1) / speed;
        }

        return spent;
    }


    /**
     * 解法 2，更简洁，但是二分查找最左值边界理解上有点困难
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed2(int[] piles, int h) {
        int max = 0;
        for(int p : piles) {
            max = Math.max(max, p);
        }

        int l = 1, r = max, ans = max;
        while(l <= r) {
            int total = 0;
            int m = l + (r -l) / 2;
            for(int p : piles) {
                total += (p-1)/m + 1; //向上取整
            }

            if(total > h) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }
}


