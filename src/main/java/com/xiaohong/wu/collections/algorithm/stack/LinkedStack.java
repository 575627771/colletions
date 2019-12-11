package com.xiaohong.wu.collections.algorithm.stack;

/**
 * 链式栈
 *
 * @author xiaohong.wu
 * @version 1.0
 * @date 2019/6/28 16:02
 **/
public class LinkedStack {

    private Node stack;

    private Integer size;

    public LinkedStack() {
        this.stack = new Node();
        this.size = 0;
    }

    /**
     * 入栈 这里可以放在链表的前面也可以放在链表的后面
     *
     * @param data
     * @return
     */
    public boolean push(String data) {
        Node node = new Node(data, this.stack);
        this.stack = node;
        size++;
        return true;
    }

    /**
     * 出栈
     *
     * @return
     */
    public Node pop() {
        Node stack = this.stack;
        if (this.stack.next != null) {
            this.stack = this.stack.next;
            size--;
        }
        return stack;
    }

    private class Node {
        String data;
        Node next;

        public Node() {
        }

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
