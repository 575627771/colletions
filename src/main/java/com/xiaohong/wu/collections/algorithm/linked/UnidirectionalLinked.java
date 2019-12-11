package com.xiaohong.wu.collections.algorithm.linked;

/**
 * 单向链表
 * 以及常用的链表操作
 *
 * @author wu
 * @version 1.0
 * @date 2019/6/23 11:10
 **/
public class UnidirectionalLinked<T> {

    private Integer size;

    private Node<T> head;

    public UnidirectionalLinked() {
        this.size = 0;
        this.head = new Node<>();
    }

    /**
     * 向链表头添加元素
     * 将原来的链表放在当前元素的下一个元素
     *
     * @param data
     */
    public void addFirst(T data) {
        final Node node = head;
        final Node<T> tNode = new Node<>(data, node);
        head = tNode;
        size++;
    }

    /**
     * 向链表尾添加元素
     * 找到最后一个元素，将当前元素添加在最后
     *
     * @param data
     */
    public void addLast(T data) {
        Node<T> tNode = new Node<>(data, null);
        Node<T> h = head;
        //找到最后一个节点
        while (h.getNext() != null) {
            h = h.getNext();
        }
        //tNode.setNext(h.getNext());
        h.setNext(tNode);
    }

    /**
     * 链表反转
     * 定义一个变量来存储反转后的元素，遍历每一个元素，把反转后的元素放在当前元素后面
     */
    public void reverse() {
        Node<T> head = this.head;
        Node prev = null;
        while (head != null) {
            Node next = head.getNext();
            head.setNext(prev);
            prev = head;
            head = next;
        }
        this.head = prev;
    }

    /**
     * 找链表的中间结点
     * 使用快慢指针
     *
     * @return
     */
    public Node<T> intermediateNode() {
        Node<T> fast = this.head;
        Node<T> slow = this.head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 删除链表的倒数第n个元素
     * 先排除掉需要删除的k个节点，然后遍历剩下的结点，从原始链表中找到需要删除元素的上一个元素，然后进行删除
     *
     * @param k
     */
    public void delReciprocalNum(int k) {
        //首先遍历链表得到链表元素个数
        Node<T> fast = this.head;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }

        if (fast == null) {
            return;
        }

        Node slow = this.head;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            head = this.head.next;
        } else {
            prev.next = prev.next.next;
        }
    }

    public boolean checkRing() {
        Node<T> slow = this.head;
        if (slow == null) {
            return false;
        }
        Node<T> fast = this.head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 合并两个有序链表
     *
     * @param n1
     * @param n2
     * @return
     */
    public Node<Integer> merge(Node<Integer> n1, Node<Integer> n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        Node<Integer> node;
        Integer data = n1.getData();
        Integer data1 = n2.getData();
        if (data < data1) {
            node = n1;
            n1 = n1.getNext();
        } else {
            node = n2;
            n2 = n2.getNext();
        }
        while (n1 != null && n2 != null) {
            if (n1.getData() < n2.getData()) {
                node.setNext(n1);
                n1 = n1.getNext();
            } else {
                node.setNext(n2);
                n2 = n2.getNext();
            }
        }
        if (n1 != null) {
            node.setNext(n1);
        } else {
            node.setNext(n2);
        }
        return node;
    }


    public void printAll() {
        Node<T> node = head;
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

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        UnidirectionalLinked<String> list = new UnidirectionalLinked<>();
        /*Scanner sc = new Scanner(System.in);
        while (true) {
            list.addLast(sc.nextLine());
            list.printAll();
        }*/
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");
        list.addFirst("5");
        list.printAll();
        //Node<String> stringNode = list.intermediateNode();
        list.delReciprocalNum(6);
        list.printAll();
    }
}
