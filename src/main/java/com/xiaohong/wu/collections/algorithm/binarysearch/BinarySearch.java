package com.xiaohong.wu.collections.algorithm.binarysearch;

/**
 * 二分查找
 * <p>
 * 时间复杂度是O(logn)因此查找效率极高
 * 但是二分查找对数据有要求：
 * 首先必须是有序表结构  ---》只能是数组
 * 因为二分查找需要数据支持随机访问
 * 其次必须有序，不然需要先排序 ---》数据不能有频繁的删除插入
 * 最后 数据量太大 太小都不适合二分查找  太大  对内存的要求比较苛刻
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/5 13:49
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] sort = new int[]{1, 2, 3, 5, 5, 6, 6, 7, 8};

        /*int s = binary(sort, 8);
        System.out.println(s);

        int i = recursiveBinary(sort, 0, 8, 6);
        System.out.println(i);

        int i1 = firstBinary(sort, 8);
        System.out.println(i1);

        int lastBinary = findLastBinary(sort, 6);
        System.out.println(lastBinary);

        int firstBigBinary = findFirstBigBinary(sort, 7);
        System.out.println(firstBigBinary);*/

        int firstLessBinary = findFirstLessBinary(sort, 8);
        System.out.println(firstLessBinary);

    }

    /**
     * 一般实现
     *
     * @param sort
     * @param i
     * @return
     */
    private static int binary(int[] sort, int i) {

        int length = sort.length;
        int s = 0;
        while (s < length) {
            int i1 = s + (length - s) / 2;
            if (sort[i1] < i) {
                s = i1;
            } else if (sort[i1] > i) {
                length = i1;
            } else {
                return i1;
            }
        }
        return -1;
    }


    /**
     * 递归实现
     *
     * @param sort
     * @param l
     * @param h
     * @param s
     * @return
     */
    private static int recursiveBinary(int[] sort, int l, int h, int s) {
        if (l > h) {
            return -1;
        }
        int m = l + (h - l) / 2;
        if (sort[m] == s) {
            return m;
        } else if (sort[m] > s) {
            return recursiveBinary(sort, l, m, s);
        } else {
            return recursiveBinary(sort, m, h, s);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param ints
     * @return
     */
    private static int firstBinary(int[] ints,int value) {
        int length = ints.length;
        int s = 0;
        while (s <= length) {
            int i1 = s + (length - s) / 2;
            if (ints[i1] < value) {
                s = i1 + 1;
            } else if (ints[i1] > value) {
                length = i1 - 1;
            } else {
                if (ints[i1 - 1] == value) {
                    length = i1 - 1;
                } else {
                    return i1;
                }
            }
        }
        return -1;
    }

    /**
     * 找到指定元素的最后一个值
     *
     * @param ints
     * @param value
     * @return
     */
    public static int findLastBinary(int[] ints, int value) {
        int length = ints.length;
        int i = 0;
        while (i <= length) {
            int i1 = i + (length - i) / 2;
            if (ints[i1] > value) {
                length = i1 - 1;
            } else if (ints[i1] < value) {
                i = i1 + 1;
            } else {
                if (ints[i1 + 1] == value) {
                    i = i1 + 1;
                } else {
                    return i1;
                }
            }
        }
        return -1;
    }

    /**
     * 找到第一个大于等于给定值的元素
     * @param ints
     * @param value
     * @return
     */
    public static int findFirstBigBinary(int[] ints, int value) {
        int length = ints.length-1;
        int i = 0;
        while (i <= length) {
            int i1 = i + (length - i) / 2;
            if (ints[i1] >= value) {
                if (i1 == 0 || ints[i1 - 1] < value) {
                    return i1;
                } else {
                    length = i1 - 1;
                }
            } else {
                i = i1 + 1;
            }
        }
        return -1;
    }


    /**
     * 找到最后一个小于等于指定值的值
     * @param ints
     * @param value
     * @return
     */
    public static int findFirstLessBinary(int[] ints, int value) {
        int length = ints.length-1;
        int i = 0;
        while (i <= length) {
            int i1 = i + (length - i) / 2;
            if (ints[i1] > value) {
                length = i1-1;
            } else {
                if (i1 == length||ints[i1+1]>value){
                    return i1;
                }else {
                    i = i1+1;
                }
            }
        }
        return -1;
    }
}
