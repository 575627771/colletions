package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 归并排序
 * 使用分治的思想，用递归的方式把数据不断的拆分为两份，直到不能拆分，然后再排序合并
 *
 * 稳定排序
 * 非原地排序
 *
 * 空间复杂度为O(n) -----》正是因为空间复杂度为O(n)导致是非原地排序，所以没有得到大规模应用
 * 时间复杂度为O(nlogn)---》时间复杂度很稳定 不管是最好、最坏、还是均摊都是一样的，且与有序度无关
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/2 17:20
 **/
public class MergeSort {
    public static void main(String[] args) {

        int[] sort = new int[]{1, 5, 2, 4, 6, 9, 3, 8};

        sort(sort,0,sort.length-1);
        for (int i : sort) {
            System.out.print(i + ",");
        }
    }

    public static void sort(int[] ints, int s, int e) {
        if (s >= e) {
            return;
        }
        System.out.println(s+","+e);
        int i = s + (e - s) / 2;
        sort(ints, s, i);
        sort(ints, i + 1, e);
        merge(ints, s, i, e);

    }

    private static void merge(int[] ints, int s, int i, int e) {
        System.out.println(s+","+i+","+e);
        int a = s;
        int b = i + 1;
        int k = 0;
        int[] t = new int[e - s + 1];
        while (a <= i && b <= e) {
            if (ints[a] <= ints[b]) {
                t[k++] = ints[a++];
            } else {
                t[k++] = ints[b++];
            }
        }

        int st = a;
        int ed = i;
        if (b <= e) {
            st = b;
            ed = e;
        }
        while (st <= ed) {
            t[k++] = ints[st++];
        }

        for (int j = 0; j <= e - s; j++) {
            ints[s + j] = t[j];
        }
    }
}
