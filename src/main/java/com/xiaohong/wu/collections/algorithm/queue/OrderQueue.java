package com.xiaohong.wu.collections.algorithm.queue;

import java.util.Scanner;

/**
 * 顺序队列 基于数组实现  队列 和栈的区别在于栈的出口的入口是同一个，队列是两个，
 * 因此在实现栈的时候我们可以借助当前栈的大小变量来作为指针，队列这里需要定义两个变量
 *
 * @author xiaohong.wu
 * @version 1.0
 * @date 2019/6/28 16:36
 **/
public class OrderQueue {


    private static final Integer DEFAULT_CAPACITY = 10;

    private String[] queue;

    private Integer capacity;

    private Integer tail;

    private Integer head;


    public OrderQueue() {
        this(DEFAULT_CAPACITY);
    }

    public OrderQueue(Integer capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 入队
     *
     * @param data
     * @return
     */
    public boolean enQueue(String data) {
        //队列满了
        if (tail - head >= capacity) {
            return false;
        }
        //这里不能简单的把数据放进去就好了，需要判断是不是到达队列尾部，如果到达需要迁移数据
        if (tail < capacity) {
            this.queue[tail] = data;
            ++tail;
        } else {
            for (int i = head; i < tail; i++) {
                queue[i - head] = queue[i];
            }
            tail = tail - head;
            head = 0;
            queue[tail] = data;
            ++tail;
        }
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String deQueue() {
        if (head.equals(tail)) {
            return null;
        }
        String s = queue[head];
        ++head;
        return s;
    }

    public void printAll(){
        String[] queue = this.queue;
        for (String s : queue) {
            System.out.print(s+",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        OrderQueue queue = new OrderQueue();
        Scanner sc = new Scanner(System.in);
        while (true){
            queue.enQueue(sc.next());
            queue.printAll();
            if (queue.tail == 10){
                System.out.println(queue.deQueue());
            }
            queue.printAll();
        }
    }


}
