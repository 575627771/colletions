package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 冒泡排序  冒牌排序的主要思想是
 * 遍历两次数据第一层循环 单纯的是因为要将所有数据遍历一遍，
 * 第二层遍历是找到当前数据中没有正确排序的最大数据进行位置交换
 * 每次一定会有一个最大的数据放在正确的位置上，
 * 因此二层的循环次数可以逐次减少；
 * 由于二层循环每次都是从第一个数据开始，
 * 因此当二层循环没有数据交换的时候说明数据已经正确排序 可以提前结束排序程序。
 *
 * 只需要一个临时空间 --》原地排序
 * 只有大于的时候才交换数据 --》稳定排序
 * 时间复杂度  最好O(n) 最坏O(n^2)
 *
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/2 14:49
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] sort = new int[]{1, 5, 2, 4, 6, 9, 3, 8};
        for (int i = 0; i < sort.length; i++) {
            boolean flag = false;
            for (int i1 = 0; i1 < sort.length - i - 1; i1++) {
                if (sort[i1] > sort[i1 + 1]) {
                    int i2 = sort[i1];
                    sort[i1] = sort[i1 + 1];
                    sort[i1 + 1] = i2;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        for (int i : sort) {
            System.out.print(i + ",");
        }
    }
}
