package org.stone.study.algo.ex202411;

import java.util.*;

public class ArchaeologistProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        scanner.nextLine(); // int m 所在行的换行符
        String[] fragments = scanner.nextLine().split(" ");
        // 用set去重，保证结果有序
        LinkedHashSet<String> result = new LinkedHashSet<>();
        Deque<String> oneSolution = new ArrayDeque<>();
        // 标记是否访问过，避免重复访问。比交换元素的算法容易理解一些
        boolean visited[] = new boolean[fragments.length];
        Arrays.fill(visited, false);
        // 先排序，相同的字符串在一起
        Arrays.sort(fragments);
        backtrack(fragments, visited, result, oneSolution);

        result.forEach(System.out::println);
    }

    /**
     * 有重复的全排列，套用回溯模板
     * @param fragments
     * @param visited
     * @param result
     * @param oneSolution
     */
    private static void backtrack(String[] fragments, boolean[] visited, Set<String> result,
                                  Deque<String> oneSolution) {
        if(oneSolution.size() == fragments.length) {
            result.add(String.join("", oneSolution));
            return;
        }

        for(int i = 0; i < fragments.length; i++) {
            if(visited[i]) {
                continue;
            }
            // 去重
            if(i > 0 && fragments[i].equals(fragments[i-1]) && !visited[i-1]) {
                continue;
            }

            visited[i] = true;
            // 选择 Deque 的原因是方便这里处理
            oneSolution.addLast(fragments[i]);
            backtrack(fragments, visited, result, oneSolution);
            visited[i] = false;
            oneSolution.removeLast();
        }
    }
}
