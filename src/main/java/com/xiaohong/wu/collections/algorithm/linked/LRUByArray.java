package com.xiaohong.wu.collections.algorithm.linked;

import java.util.Scanner;

/**
 * 数组实现 LRU缓存队列
 *
 * @author xiaohong.wu
 * @version 1.0
 * @date 2019/6/21 17:32
 **/
public class LRUByArray<T> {
    private final static int DEFAULT_SIZE = 10;

    private T[] head;

    private Integer size;

    private Integer capacity;

    public LRUByArray() {
        this(DEFAULT_SIZE);
    }

    public LRUByArray(Integer capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = (T[]) new Object[capacity];
    }

    public void add(T data) {
        //判断当前数据是否在缓存中
        Integer j = null;
        for (int i = 0; i < head.length; i++) {
            if (data.equals(head[i])) {
                j = i;
                break;
            }
        }
        //如果存在，则删除当前缓存中的数据，将当前数据放入缓存中
        if (j != null) {
            //删除最后一个保留
            if (size >= capacity) {
                for (int i = capacity - 1; i > 0; i--) {
                    head[i] = head[i - 1];
                }
                //将数据放在第一个
                head[0] = data;
            } else {
                for (int i = j; i > 0; i--) {
                    head[i] = head[i - 1];
                }
                head[0] = data;
            }
        } else {
            for (int i = capacity - 1; i > 0; i--) {
                head[i] = head[i - 1];
            }
            head[0] = data;
            size++;
        }
    }

    public void printAll() {
        for (T t : head) {
            System.out.print(t + ",");
        }
    }

    public static void main(String[] args) {
        LRUByArray<Integer> list = new LRUByArray<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}
