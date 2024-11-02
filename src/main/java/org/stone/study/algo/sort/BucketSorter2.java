package org.stone.study.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 考虑一个长度为 n 的数组，其元素是范围为[0, 1)内的浮点数。桶排序的流程如下所示。

 1, 初始化 k 个桶，将 n 个元素分配到 k 个桶中。 n > k
 2, 对每个桶分别执行排序（这里采用编程语言的内置排序函数）。
 3, 按照桶从小到大的顺序合并结果。
**/
 public class BucketSorter2 {

    public static void main(String[] args) {
        float[] data = new float[]{0.49f, 0.96f, 0.82f, 0.09f, 0.57f, 0.43f, 0.91f, 0.75f, 0.15f, 0.37f};
        BucketSorter2 sorter = new BucketSorter2();
        sorter.bucketSort(data);

        System.out.println(Arrays.toString(data));

    }    /* 桶排序 */
    void bucketSort(float[] nums) {
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }
        // 1. 将数组元素分配到各个桶中
        for (float num : nums) {
            // 输入数据范围为 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
            int i = (int) (num * k);
            // 将 num 添加进桶 i
            buckets.get(i).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Float> bucket : buckets) {
            // 使用内置排序函数，也可以替换成其他排序算法
            Collections.sort(bucket);
        }
        // 3. 遍历桶合并结果
        int i = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[i++] = num;
            }
        }
    }

}
