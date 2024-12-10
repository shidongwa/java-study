package org.stone.study.algo.ex202411;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 大厂算法：解密犯罪时间
 * 下一个最近的时间
 **/
public class NextClosestTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String time = sc.nextLine();

        String ans = getNextClosestTime(time);
        System.out.println(ans);
    }

    public static String getNextClosestTime(String time) {
        // 提取时间中非:的字符，并将其转换为数字放到set 中
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i)!= ':') {
                digits.add(time.charAt(i) - '0');
            }
        }
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        // 从当前时间开始，逐个尝试下一个时间是否有效。最多尝试1440次（24小时），即一天的时间。
        for (int delta = 1; delta <= 1440; delta++) {
            int nextTime = (hours * 60 + minutes + delta) % 1440;
            int newHours = nextTime / 60;
            int newMinutes = nextTime % 60;

            if(!validTime(newHours, newMinutes, digits)) {
                continue;
            }

            return String.format("%02d:%02d", newHours, newMinutes);
        }

        return "";
    }

    // 判断时间是否有效时间，数字是否在允许的数字集合中
    public static boolean validTime(int hours, int minutes, Set<Integer> digits) {
        if (hours > 23 || minutes > 59) {
            return false;
        }

        int[] time = {hours / 10, hours % 10, minutes / 10, minutes % 10};
        for (int digit : time) {
            if (!digits.contains(digit)) {
                return false;
            }
        }

        return true;
    }
}
