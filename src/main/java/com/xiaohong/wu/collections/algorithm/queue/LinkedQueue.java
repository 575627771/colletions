package com.xiaohong.wu.collections.algorithm.queue;

import java.util.Scanner;

/**
 * 链式队列 基于链表  需要无限队列吗？？？
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/1 16:05
 **/
public class LinkedQueue {

    private Node queue;

    private Node tail;

    private Node head;

    public LinkedQueue() {
        this.queue = new Node();
        this.tail = queue;
        this.head = queue;
    }

    /**
     * 入队
     *
     * @param data
     * @return
     */
    public boolean enQueue(String data) {
        Node node = new Node(data,null);
        tail.next = node;
        tail = node;
        return true;
    }

    public Node deQueue(){
        Node head = this.head;
        this.head = this.head.next;
        return head;
    }

    public void printAll(){
        Node head = this.head;
        while (head != null){
            System.out.print(head.data+",");
            head = head.next;
        }
        System.out.println();
    }


    /**
     * 单项链表节点
     */
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

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        Scanner sc = new Scanner(System.in);
        while (true){
            String next = sc.next();
            queue.enQueue(next);
            queue.printAll();
            if ("10".equals(next)){
                queue.deQueue();
                queue.deQueue();
            }
            queue.printAll();
        }
    }
}
