package com.xiaohong.wu.collections.algorithm.stack;

/**
 * 基于数组实现的栈-------------》顺序栈
 * 主要包括两个操作：入栈和出栈
 * 会不会自动扩容？--->自动扩容 最好时间复杂度O(1) 最坏O(n) 均摊O(1)
 *
 * @author xiaohong.wu
 * @version 1.0
 * @date 2019/6/28 14:50
 **/
public class OrderStack {

    /**
     * 定义默认的初始容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;
    private String[] stack;
    private Integer size;
    private Integer capacity;

    public OrderStack() {
        this(DEFAULT_CAPACITY);
    }

    public OrderStack(Integer capacity) {
        this.stack = new String[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * 入栈操作
     *
     * @param data 入栈元素
     */
    public boolean push(String data) {
        if (size >= capacity) {
            return false;
        }
        this.stack[size] = data;
        size++;
        return true;
    }

    /**
     * 出栈 按照顺序 后进先出
     *
     * @return 栈中的元素
     */
    public String pop() {
        if (size <= 0) {
            return null;
        }
        String result = this.stack[size - 1];
        size--;
        return result;
    }

}
