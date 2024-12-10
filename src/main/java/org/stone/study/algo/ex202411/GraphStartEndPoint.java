package org.stone.study.algo.ex202411;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 有向图起点和终点
 */
public class GraphStartEndPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();// 跳过第一行
        String line = scanner.nextLine();
        List<Integer> edgeList = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        Set<Integer> nodes = new HashSet<>(edgeList);
        int n = nodes.size(); // 节点数

        // 初始化和构造邻接矩阵和入度表
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < edgeList.size() - 1; i += 2) {
            int u = edgeList.get(i);
            int v = edgeList.get(i + 1);
            graph.get(u).add(v);
            inDegree[v]++;
        }

        // 找起点和终点
        List<Integer> ans = findStartAndEnd(graph, inDegree);
        // 对列表进行拼接输出，以空格分割
        System.out.println(ans.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    /**
     * 找有向图起点和终点。有环时直接返回-1
     * @param graph
     * @param inDegree
     * @return
     */
    private static List<Integer> findStartAndEnd(List<List<Integer>> graph, int[] inDegree) {
        List<Integer> ans = new ArrayList<>();
        // 先判定是否有环
        boolean hasCycle = hasCycle(graph, inDegree);
        if(hasCycle) {
            ans.add(-1);
            ans.add(-1);
            return ans;
        }

        // 无环找起点和终点
        int start = -1;
        List<Integer> endList = new ArrayList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                start = i;
            }
            if (graph.get(i).isEmpty()) {
                endList.add(i);
            }
        }

        ans.add(start);
        ans.addAll(endList);

        return ans;
    }

    /**
     * BFS判断是否有环
     * @param graph：邻接表
     * @param inDegree：入度表
     * @return
     */
    private static boolean hasCycle(List<List<Integer>> graph, int[] inDegree) {
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        // 下面算法需要修改 inDegree 数组，所以需要拷贝一份
        int[] inDegreeCopy = Arrays.copyOf(inDegree, inDegree.length);
        for(int i = 0; i < inDegreeCopy.length; i++) {
            if(inDegreeCopy[i] == 0) {
                queue.offer(i); // 找到起点，且根据题意只有一个
            }
        }
        // BFS 遍历邻节点，修改入度，入度为 0 则入队
        while(!queue.isEmpty()) {
            int u = queue.poll();
            visited.add(u);
            for(int v : graph.get(u)) {
                inDegreeCopy[v]--;
                if(inDegreeCopy[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 遍历完节点数量小于总节点数，说明有环。环中的节点没有入队
        return visited.size() < inDegreeCopy.length;
    }
}
