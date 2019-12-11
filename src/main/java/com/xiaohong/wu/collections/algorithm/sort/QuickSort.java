package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 快速排序
 *
 * 排序思想主要是 通过一个指定（一般最后一个）数据将数据分为两个区域
 * 一、比指定数据小的数据加入到比指定数据小的数据后面（交换位置）
 * 需要指出的是这里的几个指针
 * ①是比指定数据小数组的末尾指针
 * ②是遍历数据的指针
 * 每次遍历结束需要把指定数据放在比他小的数据的后面（相当于指定数据的顺序位置已经找到）
 *
 * 不是稳定排序
 * 是原地排序
 *
 * 同样是分治的思想 快速排序和归并排序的主要区别是：
 * 归并排序是由下到上合并排序的，而快速排序是由上至下排序
 * 虽然两个时间复杂度都是O(nlogn),但是归并排序并不是原地排序，再合并过程中需要申请额外的内存来帮助合并，
 * 而快速排序巧妙的使用了自身数据原地分区，实现原地排序
 *
 * 极端情况下 时间复杂度会退化为O(n^2)数据本身有序
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/4 14:08
 **/
public class QuickSort {

    public static void main(String[] args) {

        int[] sort = new int[]{1, 5, 2, 4, 6, 9, 3, 8,7,6};

        quick(sort, 0, sort.length - 1);
        for (int i : sort) {
            System.out.print(i + ",");
        }
    }

    public static void quick(int[] ints, int s, int e) {
        if (s >= e) {
            return;
        }
        int a = partition(ints, s, e);
        quick(ints, s, a - 1);
        quick(ints, a + 1, e);
    }

    private static int partition(int[] ints, int s, int e) {
        System.out.println(s+","+e);
        int anInt = ints[e];
        int s1 = s;
        for (int i = s; i < e; i++) {
            if (ints[i] < anInt) {
                int anInt1 = ints[s1];
                ints[s1]  = ints[i];
                ints[i] = anInt1;
                s1++;
            }
        }
        int anInt1 = ints[s1];
        ints[s1]  = ints[e];
        ints[e] = anInt1;
        for (int i : ints) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(s1);
        return s1;
    }
}
