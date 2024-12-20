package org.stone.study.algo.ex202411;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拓扑排序
 *
 * 一个应用启动时，会有多个初始化任务需要执行，并且任务之间有依赖关系，例如A任务依赖B任务，那么必须在B任务执行完成之后，才能开始执行A任务。
 *
 * 现在给出多条任务依赖关系的规则，请输入任务的顺序执行序列，规则采用贪婪策略，即一个任务如果没有依赖的任务，则立刻开始执行，如果同时有多个任务要执行，则根据任务名称字母顺序排序。
 *
 * 例如：B任务依赖A任务，C任务依赖A任务，D任务依赖B任务和C任务，同时，D任务还依赖E任务。那么执行任务的顺序由先到后是：
 * A任务，E任务，B任务，C任务，D任务
 *
 * 这里A和E任务都是没有依赖的，立即执行。
 */
public class DependencySort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 依赖关系：B->A C->A D->B D->C D->E
        String dependency = scanner.nextLine();
        String res = sortByDependency(dependency);
        // A E B C D
        System.out.println(res);
    }

    /**
     * 对字符串表示的依赖关系进行拓扑排序，并返回排序后的结果（如果存在环则返回 ERROR），以空格分隔
     * @param dependencyStr
     * @return
     */
    private static String sortByDependency(String dependencyStr) {
        String[] arr = dependencyStr.split(" ");
        Map<Character, List<Character>> dependencyMap = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        Set<Character> taskSet = new HashSet<>();

        // 构建依赖图和入度表
        for (String dep : arr) {
            String[] depArr = dep.split("->");
            // dependent 依赖于 dependency
            char dependent = depArr[0].charAt(0);// 被依赖的任务
            char dependency = depArr[1].charAt(0); // 依赖的任务
            dependencyMap.putIfAbsent(dependency, new ArrayList<>());
            dependencyMap.get(dependency).add(dependent);
            // 两个任务的入度都要更新
            inDegree.put(dependent, inDegree.getOrDefault(dependent, 0) + 1);
            if(!inDegree.containsKey(dependency)) {
                inDegree.put(dependency, 0);
            }
            // 记录任务总数
            taskSet.add(dependent);
            taskSet.add(dependency);
        }

        // 入度为 0 的任务入队
        Queue<Character> queue = new LinkedList<>();
        for (char task : inDegree.keySet()) {
            if (inDegree.get(task) == 0) {
                queue.offer(task);
            }
        }
        // 拓扑排序
        List<Character> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            ans.add(cur);
            // 更新依赖任务的入度
            for (Character dependent : dependencyMap.getOrDefault(cur, Collections.emptyList())) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.offer(dependent);
                }
            }
        }

        // 判断是否有环
        if(ans.size() == taskSet.size()) {
            return ans.stream().map(String::valueOf).collect(Collectors.joining(" "));
        } else {
            return "ERROR";
        }
    }
}
