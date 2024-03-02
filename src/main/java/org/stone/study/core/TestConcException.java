package org.stone.study.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Java 8 HashMap使用尾插法，对最后一个节点处理不会造成ConcurrentModificationException；
 * Java 7 使用头插法，正好相反
 * refer to: https://stackoverflow.com/questions/35100455/why-iterator-is-not-throwing-concurrentmodification-exception
 */
public class TestConcException {

    public static void main(String[] args) {
        /*
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            String key = it.next().getKey();
//            if("3".equals(key)) {
            if("1".equals(key)) { // throw ConcurrentModificationException here
                map.remove(key);
            }
        }

        System.out.println(map);
         */

        ArrayList<String> collection = new ArrayList<String>();
        collection.add("Apple");
        collection.add("Banana");
        collection.add("Cherry");
        for (String element : collection) {
            if (element.equals("Banana")) {
                collection.remove(1);
//                collection.add("test");
                collection.add("test");
            }
        }
        System.out.println(collection);
    }
}
