package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 选择排序
 *
 * 和 插入排序 一样 也分为两个区，未排序区和排序区
 * 和 插入排序 不同的是，插入排序是在排序区进行比较排序，
 * 而选择排序是在未排序区比较找到最小的然后放在排序区末尾
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/2 16:19
 **/
public class SelectionSort {

    public static void main(String[] args) {
        int[] sort = new int[]{1, 5, 2, 4, 6, 9, 3, 8};

        for (int i = 0; i < sort.length; i++) {

            int s = i;
            for (int i1 = i; i1 < sort.length-1; i1++) {
                if (sort[s]>sort[i1+1]){
                    s = i1+1;
                }
            }
            int i1 = sort[i];
            sort[i] = sort[s];
            sort[s] = i1;
        }

        for (int i : sort) {
            System.out.print(i + ",");
        }
    }
}
