package org.stone.study.algo.tree;

/**
 * Fenwick Tree，也被称为Binary Indexed Tree（BIT），是一种数据结构，用于高效地解决涉及加法的问题，特别是对于累积频率表的处理。
 * 它由Peter M. Fenwick提出，因此得名。以下是Fenwick Tree的一些主要特点：
 * 1 单点更新：可以在O(log n)时间内更新数组中某个特定位置的值。
 * 2 区间查询：可以快速计算任意区间内元素的累积和，同样在O(log n)时间内完成。
 * 3 空间效率：Fenwick Tree使用与原数组相同数量级的额外空间。
 * 4 范围更新：虽然Fenwick Tree主要用于单点更新，但也可以扩展到范围更新，尽管这通常需要更复杂的操作。
 */
public class FenwickTree {
    private int[] tree;
    private int size;

    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }

    public void update(int idx, int val) {
        while (idx <= size) {
            tree[idx] += val;
            idx += idx & -idx;
        }
    }

    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5};
        FenwickTree ft = new FenwickTree(arr.length);

        // 更新位置3的值为10
        ft.update(3, 10);

        // 查询区间[1, 4]的总和
        int sum = ft.query(4) - ft.query(1 - 1);

        System.out.println("Sum of elements in range [1, 4]: " + sum);
    }
}