package org.stone.study.core.generics;

import java.util.ArrayList;
import java.util.List;

public class Pair<T> {
    private T first;
    private T last;
    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    // 不允许直接 new T类型变量，可以采用`Class<T> clazz` 来创建
    /*    public Pair() {
        // Compile error:
        first = new T();
        last = new T();
    }*/
    public Pair(Class<T> clazz) {
        try {
            first = clazz.newInstance();
            last = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }

    // 对静态方法使用<T>，不允许。T 是一个类型变量。原因：
    // 1. 静态方法在类加载的时候就已经存在了，而泛型是在对象创建的时候才确定的，所以静态方法不能使用泛型。
    // 2. 类型参数是与类实例关联的
/*    public static Pair<T> create1(T first, T last) {
        return new Pair<T>(first, last);
    }*/

    // 静态泛型方法应该使用其他类型区分（泛型方法用变量 K，泛型类用 T）
    public static <K> Pair<K> create2(K first, K last) {
        return new Pair<K>(first, last);
    }

    public static void wildcard() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        List<Integer> list2 = new ArrayList<>();
        list2.add(100);
        list2.add(666);
        //报错，List<?>不能添加任何类型
        List<?> list3 = new ArrayList<>();
//        list3.add(666);
    }
    public static void main(String[] args) {
        // <?>通配符有一个独特的特点，就是：Pair<?>是所有Pair<T>的超类
        Pair<Integer> p = new Pair<>(123, 456);
        Pair<?> p2 = p; // 安全地向上转型
    }
}
