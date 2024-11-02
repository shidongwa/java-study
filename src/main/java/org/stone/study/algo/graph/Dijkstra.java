package org.stone.study.algo.graph;

import java.util.Scanner;

/**
 * 迪杰斯特拉算法计算图的最短路径
 *
 * 样例输入：
 * 7 10
 * 0 1 6
 * 1 2 5
 * 0 3 2
 * 3 1 7
 * 3 4 5
 * 1 2 5
 * 1 5 3
 * 4 5 5
 * 5 4 2
 * 4 6 1
 * 0
 */
public class Dijkstra {
    //不能设置为Integer.MAX_VALUE，否则两个Integer.MAX_VALUE相加会溢出导致出现负权
    public static int MaxValue = 100000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入顶点数和边数:");
        //顶点数
        int n = input.nextInt();
        //边数
        int edge = input.nextInt();
        // 邻接矩阵表示图
        int[][] matrix = new int[n][n];
        //初始化邻接矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = MaxValue;
            }
        }
        for (int i = 0; i < edge; i++) {
            System.out.println("请输入第" + (i + 1) + "条边与其权值:");
            int source = input.nextInt();
            int target = input.nextInt();
            int weight = input.nextInt();
            matrix[source][target] = weight;
        }

        //起点
        int source = input.nextInt();

        //调用dijkstra算法计算最短路径
        dijkstra(matrix, source);
    }

    /**
     * 从 source 起点出发，到每一个顶点的最短路径
     * @param matrix
     * @param source：起点
     */
    public static void dijkstra(int[][] matrix, int source) {
        //每个顶点到起点的最短路径长度
        int[] shortest = new int[matrix.length];
        //判断该点的最短路径是否求出
        int[] visited = new int[matrix.length];
        //存储输出路径
        String[] path = new String[matrix.length];

        //初始化输出路径
        for (int i = 0; i < matrix.length; i++) {
            path[i] = source + "->" + i;
        }

        //初始化源节点
        shortest[source] = 0;
        visited[source] = 1;

        for (int i = 1; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;

            for (int j = 0; j < matrix.length; j++) {
                //已经求出最短路径的节点不需要再加入计算并判断加入节点后是否存在更短路径
                if (visited[j] == 0 && matrix[source][j] < min) {
                    min = matrix[source][j];
                    index = j;
                }
            }

            //更新最短路径
            shortest[index] = min;
            visited[index] = 1;

            //直接更新从index跳到其它节点的较短路径
            for (int m = 0; m < matrix.length; m++) {
                if (visited[m] == 0 && matrix[source][index] + matrix[index][m] < matrix[source][m]) {
                    matrix[source][m] = matrix[source][index] + matrix[index][m];
                    // 会覆盖以前设置的较长的路径
                    path[m] = path[index] + "->" + m;
                }
            }

        }

        //打印最短路径
        for (int i = 0; i < matrix.length; i++) {
            if (i != source) {
                if (shortest[i] == MaxValue) {
                    System.out.println(source + "到" + i + "不可达");
                } else {
                    System.out.println(source + "到" + i + "的最短路径为：" + path[i] + "，最短距离是：" + shortest[i]);
                }
            }
        }
    }
}
