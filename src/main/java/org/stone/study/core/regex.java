package org.stone.study.core;

import java.util.Arrays;

public class regex {

    public static void main(String[] args) {
        String ip = "255.255.255.255";

        // 对于特殊字符.需要做 escape
        String[] arr = ip.split(".");
        // 不符合预期
        System.out.println(Arrays.toString(arr));

        arr = ip.split("\\.");
        System.out.println(Arrays.toString(arr));
    }
}
