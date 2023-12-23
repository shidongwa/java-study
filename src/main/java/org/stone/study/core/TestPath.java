package org.stone.study.core;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestPath {
    public static void main(String[] args) throws URISyntaxException {
        List<Integer> myIntegers = Arrays.asList(1, 1, 2, 3, 4, 2);
        Map<Integer, Long> myIntegerMap = myIntegers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long sum = myIntegerMap.values().stream().mapToLong(v -> v/2).sum();
        System.out.println("sum:" + sum);

    }
}
