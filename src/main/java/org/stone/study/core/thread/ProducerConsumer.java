package org.stone.study.core.thread;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    final Lock lock = new ReentrantLock();
    // condition 实例依赖于 lock 实例
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];

    int putPtr, takePtr, count;

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                try {
                    pc.put("Task " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            try {
                while(true) {
                    System.out.println("consume " + pc.take());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Scanner(System.in).nextLine();
    }

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            //  put 时判断是否已经满了
            // 则线程在 notFull 条件上排队阻塞
            while (count == items.length) {
                notFull.await();
            }
            items[putPtr] = x;
            if (++putPtr == items.length) {
                putPtr = 0;
            }
            ++count;
            // put 成功之后，队列中有元素
            // 唤醒在 notEmpty 条件上排队阻塞的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // take 时，发现为空
            // 则线程在 notEmpty 的条件上排队阻塞
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takePtr];
            if (++takePtr == items.length) {
                takePtr = 0;
            }
            --count;
            // take 成功，队列不可能是满的
            // 唤醒在 notFull 条件上排队阻塞的线程
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
