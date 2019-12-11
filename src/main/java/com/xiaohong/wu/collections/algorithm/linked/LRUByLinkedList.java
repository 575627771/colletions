package com.xiaohong.wu.collections.algorithm.linked;

import java.util.Scanner;

/**
 * 单向链表实现 LRU缓存
 *
 * @author xiaohong.wu
 * @version 1.0
 * @date 2019/6/21 15:52
 **/
public class LRUByLinkedList<T> {

    private final static int DEFAULT_SIZE = 10;

    private Node<T> head;

    private Integer size;

    private Integer capacity;

    public LRUByLinkedList() {
        //使用哨兵
        this.head = new Node<>();
        this.size = 0;
        this.capacity = DEFAULT_SIZE;
    }

    public LRUByLinkedList(Integer capacity) {
        this();
        this.capacity = capacity;
    }

    public void add(T data) {
        //1.找到当前数据是否在缓存中
        Node node = head;
        //找到相同的缓存数据或者直到最后一个节点
        while (node.getNext() != null && !data.equals(node.getNext().getData())) {
            node = node.getNext();
        }
        if (null == node.getNext() && !data.equals(node.getData())) {
            node = null;
        }
        //2.如果缓存中有当前数据，删除缓存中的数据，将当前数据放在链表头部
        if (null != node) {
            node.setNext(node.getNext().getNext());
            head.setNext(new Node<>(data,head.getNext()));
        } else {//缓存中不存在当前数据，判断缓存是否已满，满了删除，然后将数据放在链表头部
            if (size >= capacity){
                Node node1 = head;
                while (node1.getNext().getNext() != null){
                    node1 = node1.getNext();
                }
                node1.setNext(null);
                head.setNext(new Node<>(data,head.getNext()));
            }else {
                head.setNext(new Node<>(data,head.getNext()));
                size++;
            }

        }
    }

    private void printAll() {
        Node node = head.getNext();
        while (node != null) {
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    private static class Node<T> {
        T data;
        Node next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUByLinkedList<Integer> list = new LRUByLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            list.add(scanner.nextInt());
            list.printAll();
        }
    }
}
