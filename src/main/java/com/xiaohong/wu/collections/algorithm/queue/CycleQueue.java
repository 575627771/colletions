package com.xiaohong.wu.collections.algorithm.queue;

/**
 * 循环队列 相比于非循环队列 优势在于不需要搬迁数据，但是会浪费一个数组位置
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/1 17:12
 **/
public class CycleQueue {

    private static final Integer DEFAULT_CAPACITY = 10;

    private String[] queue;
    private int capacity;
    private int head;
    private int tail;

    public CycleQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CycleQueue(Integer capacity) {
        this.queue = new String[capacity];
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 入队  主要是循环计算最后一个节点位置公式
     *
     * @param data
     * @return
     */
    public boolean enQueue(String data) {
        //判断是否已经满了
        if ((tail + 1) % capacity == head) {
            return false;
        }
        this.queue[tail] = data;
        tail = (tail + 1) % capacity;
        return true;
    }

    public String deQueue() {
        //判断队列是否为空
        if (this.head == this.tail) {
            return null;
        }
        String s = this.queue[head];
        head = (head + 1) % capacity;
        return s;
    }

}
