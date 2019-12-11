package com.xiaohong.wu.collections.algorithm.thinking;

/**
 * 回溯算法
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/8 16:55
 **/
public class BacktrackingAlgorithm {

    public static void main(String[] args) {
        BacktrackingAlgorithm algorithm = new BacktrackingAlgorithm();
        /*algorithm.cal8Queens(0);*/
        int[] a = new int[]{1,20,34,5,16,27,8,59,10,15,11};
        algorithm.f(0, 0, a, 10, 100);
    }


    int[] result = new int[8];

    /**
     * 8皇后问题
     *
     * @param row
     */
    public void cal8Queens(int row) {
        if (8 == row) {
            printQueens(result);
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; column++) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第row行的棋子放到了column列
                result[row] = column;
                // 考察下一行
                cal8Queens(row + 1);
            }
        }

    }

    //判断row行column列放置是否合适
    private boolean isOk(int row, int column) {
        int leftup = column - 1;
        int rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; i--) {
            // 第i行的column列有棋子吗？
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //存储背包中物品总重量的最大值
    public int maxW = Integer.MIN_VALUE;

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }
}
