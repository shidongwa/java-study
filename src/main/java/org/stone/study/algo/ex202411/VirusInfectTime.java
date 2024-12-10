package org.stone.study.algo.ex202411;

import java.util.*;

public class VirusInfectTime {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 4
        int n = Integer.parseInt(scanner.nextLine());
        // 3
        int m = Integer.parseInt(scanner.nextLine());

        int[][] conn = new int[m][3];
        //  2 1 1
        //  2 3 1
        //  3 4 2
        for(int i = 0; i < m; i++) {
            conn[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 2
        int src = scanner.nextInt();
        int ans = getMinInfectTime(n, m, conn, src);
        // 3
        System.out.println(ans);
    }

    /**
     * djikstra 算法找到遍历有向图全部节点最短路径。不能遍历全部节点时返回-1；能遍历全部时找花费时间最长的节点就是图全部遍历的最小时间
     * 没有考虑有环的有向图
     * @param n
     * @param m
     * @param conn
     * @param src
     * @return
     */
    private static int getMinInfectTime(int n, int m, int[][] conn, int src) {
        // 构建有向图的邻接表
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] edge : conn) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, w});
        }

        // dijkstra 算法找到最短路径
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e[0]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        queue.add(new int[]{0, src});
        dist[src] = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curTime = cur[0];
            int curNode = cur[1];

            // 剪枝:其他路径可能修改过最短距离
            if(curTime > dist[curNode]) {
                continue;
            }
            for(int[] next : graph.getOrDefault(curNode, Collections.emptyList())) {
                int nextNode = next[0];
                int nextTime = next[1];
                if(curTime + nextTime < dist[nextNode]) {
                    dist[nextNode] = curTime + nextTime;
                    queue.add(new int[]{dist[nextNode], nextNode});
                }
            }

        }

        // 找到最长的最短路径，如果有节点未遍历到返回-1
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }

}
