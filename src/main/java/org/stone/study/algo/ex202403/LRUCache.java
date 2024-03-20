package org.stone.study.algo.ex202403;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        // accessOrder:true按照访问顺序，false 按照插入顺序
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1) ;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        int val = cache.get(1);       // 返回  1
        System.out.println("val:" + val);
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        val = cache.get(2);       // 返回 -1 (未找到)
        System.out.println("val:" + val);
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        val = cache.get(1);       // 返回 -1 (未找到)
        System.out.println("val:" + val);
        val = cache.get(3);       // 返回  3
        System.out.println("val:" + val);
        val = cache.get(4);       // 返回  4
        System.out.println("val:" + val);
    }
}
