package org.stone.study.algo.ex202412;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalRoad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int[][] grid = new int[n-1][2];
        for(int i = 0; i < n - 1; i++) {
            String[] strs = sc.nextLine().split(" ");
            // 以 1 开始的节点转化为以 0 开始的
            grid[i][0] = Integer.parseInt(strs[0]) - 1;
            grid[i][1] = Integer.parseInt(strs[1]) - 1;
        }

        List<Integer> ans = new OptimalRoad().getMinRoad(n, grid);
        // 打印列表并以空格分隔
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    // 每次去掉一个节点，剩下节点加入并查集，找并查集中最大的集合；
    // 所有节点中最小的集合即为答案
    private List<Integer> getMinRoad(int n, int[][] grid) {
        int minDp = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            UnionFind uf = new UnionFind(n);
            for(int[] pair : grid) {
                int x = pair[0], y = pair[1];
                if(x != i && y != i) {
                    uf.union(x, y);
                }
            }

            if(minDp > uf.getMaxSize(n)) {
                minDp = uf.getMaxSize(n);
                ans.clear();
                ans.add(i + 1); // 索引以 0 开始，题目中索引以 1 开始
            } else if(minDp == uf.getMaxSize(n)) {
                ans.add(i + 1); // 索引以 0 开始，题目中索引以 1 开始
            }
        }

        return ans;
    }

    // 并查集
    static class UnionFind {
        int n;
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if(parent[x]!= x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            size[rootY] += size[rootX];
            --n;
        }

        // 这里参数 n 和并查集中的 n 不一样。并查集中的 n 表示合并后集合的个数，但是不能直接知道剩下集合的根节点
        // 这里需要知道所有集合中最大大小的集合
        public int getMaxSize(int n) {
            int maxSize = 1;
            for(int i = 0; i < n; i++) {
                maxSize = Math.max(maxSize, size[i]);
            }

            return maxSize;
        }
    }
}
