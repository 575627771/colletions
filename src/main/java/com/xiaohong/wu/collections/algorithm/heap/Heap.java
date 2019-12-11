package com.xiaohong.wu.collections.algorithm.heap;

/**
 * 堆
 * <p>
 * 完全二叉树
 * 节点比子节点大或者小
 * <p>
 * 大顶堆
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/16 16:39
 **/
public class Heap {

    private static final Integer DEFAULT_CAPACITY = 64;

    private int[] heap;

    private Integer capacity;

    private Integer count;

    public Heap() {
        this(DEFAULT_CAPACITY);
    }

    public Heap(Integer capacity) {
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    public void insert(int data) {
        if (count > capacity) {
            return;
        }
        count++;
        heap[count] = data;
        Integer count = this.count;
        while (count / 2 > 0 && heap[count] > heap[count / 2]) {
            count = count / 2;
            swap(count);
        }
    }

    private void swap(Integer count) {
        int i = heap[count];
        heap[count] = heap[count / 2];
        heap[count / 2] = i;
    }

}