package com.xiaohong.wu.collections.algorithm.skiplist;

import java.util.Random;
import java.util.Scanner;

/**
 * 跳表
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/10 14:23
 **/
public class SkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();

    private Random r = new Random();

    /**
     * 插入元素
     *
     * @param value
     */
    public void insert(int value) {

        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        Node head = this.head;
        for (int i = level - 1; i >= 0; --i) {
            while (head.forwards[i] != null && head.forwards[i].data < value) {
                head = head.forwards[i];
            }
            update[i] = head;
        }
        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        if (levelCount < level) {
            levelCount = level;
        }
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    private class Node {

        private int data;

        private Node[] forwards = new Node[MAX_LEVEL];

        private int maxLevel = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }


    public static void main(String[] args) {
        SkipList list = new SkipList();
        Scanner sc = new Scanner(System.in);
        while (true){
            list.insert(sc.nextInt());
            list.printAll();
        }
    }
}
