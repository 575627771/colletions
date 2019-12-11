package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 插入排序
 * 核心思想是将数据分为两个区 排序区和未排序区 排序区初始默认是待排序数据的第一个数据
 * 然后待排序区的元素和排序区的元素比较，插入对应位置  直到未排序区没有数据
 * 复杂度
 * 稳定性
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/2 15:31
 **/
public class InsertionSort {

    public static void main(String[] args) {
        int[] sort = new int[]{1, 5, 2, 4, 6, 9, 3, 8};
        for (int i = 1; i < sort.length; i++) {
            int j = i - 1;
            int i1 = sort[i];
            for (; j >= 0; --j) {
                if (sort[j] > i1) {
                    sort[j + 1] = sort[j];
                }else {
                    break;
                }
            }
            sort[j+1] = i1;
        }

        for (int i : sort) {
            System.out.print(i + ",");
        }
    }
}
