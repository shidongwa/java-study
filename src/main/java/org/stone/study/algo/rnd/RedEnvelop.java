package org.stone.study.algo.rnd;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 一个人在群里发了100块钱的红包，群里有10个人一起来抢红包，每人抢到的金额随机分配。要求：
 * 1. 所有人抢到的金额之和要等于红包金额，不能多也不能少。
 * 2. 每个人至少抢到1分钱。
 * 3. 要保证红包拆分的金额尽可能分布均衡，不要出现两极分化太严重的情况。
 */
public class RedEnvelop {

    public static void main(String[] args) {
        // 二倍均值法： 100块，10个人抢
        List<Integer> amountList = divideRedPackage(10000, 10);
        for (Integer amount : amountList) {
            System.out.println(" 抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }

        // 线段切割法：100块，10个人抢
        List<Integer> list = hongbao(100, 10);
        System.out.println(list);
        System.out.println(list.stream().mapToInt(x -> x).sum());
    }

    /**
     * 二倍均值法：拆分红包
     *
     * @param totalAmount    总金额（以分为单位）
     * @param totalPeopleNum 总人数
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的2倍-1] 分
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount = restAmount - amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);

        return amountList;
    }

    /**
     * 线段切割法：当N个人一起抢红包的时候，就需要确定N-1个切割点。因此，当N个人一起抢总金额为M的红包时，我们需要做N-1次随机运算，
     * 以此确定N-1个切割点。随机的范围区间是（1， M）。当所有切割点确定以后，子线段的长度也随之确定。这样每个人来抢红包的时候，
     * 只需要顺次领取与子线段长度等价的红包金额即可。
     * @param totalAmount
     * @param totalNumber
     * @return
     */
    public static List<Integer> hongbao(int totalAmount, int totalNumber) {
        List<Integer> list = new ArrayList<>();
        if (totalAmount <= 0 || totalNumber <= 0) {
            return list;
        }

        Set<Integer> set = new HashSet<>();
        while (set.size() < totalNumber - 1) {
            //生成一个1~totalAmount的随机数
            int random = ThreadLocalRandom.current().nextInt(1, totalAmount);
            set.add(random);
        }

        //使用set.toArray(new Integer[0])是为了保证转成数组后不用转型。因为不带Integer[0]的话，转过后是Object[]
        Integer[] amounts = set.toArray(new Integer[0]);
        //排序之后首先把数组中的第一位数放入List中
        Arrays.sort(amounts);
        list.add(amounts[0]);

        /**
         * 对排序后的数组进行如下操作。假如排序后的数组为{x1, x2, x3, x4, x5, x6}
         * 下面的规则就相当于是x2-x1+x3-x2+x4-x3+x5-x4+x6-x5=x6-x1。而x1已经在上面被添加到list中，因此现在list中数据总大小为x6。
         * 因此最后list.add(totalAmount - amounts[amounts.length - 1])时，也就=list.add(totalAmount - x6)，总数为totalAmount
         */
        for (int i = 1; i < amounts.length; i++) {
            list.add(amounts[i] - amounts[i - 1]);
        }

        list.add(totalAmount - amounts[amounts.length - 1]);
        return list;
    }
}
