package org.stone.study.core.generics;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {

    public static void main(String[] args) {
        // 声明两个泛型明确的list集合
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // 调用使用了<？>通配符的方法
        test1(list1);
        test1(list2);

        // 注意下列List的泛型
        List<Double> list3 = new ArrayList<>();
        List<Number> list4 = new ArrayList<>();
        // 调用使用了泛型的方法
//        test3(list1);	// 编译错误，因为list1的泛型为String，不是Number的子类
        test3(list2);	// 编译通过，因为list2的泛型为Integer，是Number的子类
        test3(list3);	// 编译通过，因为list3的泛型为Double，是Number的子类
        test3(list4);	// 编译通过，因为list4的泛型为Number，是Number的本身

        // 调用使用了泛型的方法
        test4(list2);	// 编译正确，因为list2的泛型为Integer，等价于参数中泛型的下界
        test4(list4);	// 编译正确，因为list4的泛型为Number，是Integer的基类
//        test4(list3);	// 编译错误，因为list3的泛型为Double，不是Integer的基类
    }

    // 在参数中使用无界通配符
    public static void test1(List<?> list) {
        Object o = list.get(1);
    }

    // 在参数中使用无界通配符
    public static void test2(List<?> list) {
        // 均编译错误，因为使用了无界通配符，编译器无法确定具体是什么类型。这里和 List 不一样。
//         list.add(1111);
        // list.add("aaa");
        // list.add(new Object());
    }

    public static void test3(List<? extends Number> list) {
        Number number = list.get(1);
        // 下列均编译错误：list中元素的类型可以是任意Number的子类，所有无法确定list存储的具体是哪一种类型
        //	list.add(11);
        //	list.add(new Integer(1));
        //	list.add(new Double(1));
    }

    public static void test4(List<? super Integer> list) {
        // 只能通过Object接收
        Object object = list.get(1);

        // 编译正确，允许以下操作。因为我们在泛型中使用了super这个限定了泛型的下界为Integer，这表示在list这个集合中，
        // 所有的元素一定是Integer类型，或者Integer类型的基类型，比如说Number；
        // 这表明，我们在集合中添加一个Integer类型的元素，一定是合法的，因为Integer类型的对象，肯定也是一个Integer的基类型的对象
        list.add(111);
        list.add(new Integer(1));
        // 编译错误，1.5不是Integer
//        list.add(1.5);
    }

}
