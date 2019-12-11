package com.xiaohong.wu.collections.algorithm.stringmatching;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/2 15:33
 **/
public class StringMatching {

    public static void main(String[] args) {

        System.out.println(KMP("qqwwababwwcsabababcrwvcsdvcs", "ababa"));
    }

    public static boolean BFMatching(String master, String n) {
        int masterL = master.length();
        int nL = n.length();
        if (masterL < nL) {
            return false;
        }
        for (int i = 0; i < masterL - nL + 1; i++) {
            boolean flag = false;

            for (int i1 = 0; i1 < nL; i1++) {
                char c = master.charAt(i + i1);
                char c1 = n.charAt(i1);
                if (c != c1) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    public static int KMP(String s, String e) {
        int sl = s.length();
        int el = e.length();
        char[] sChars = s.toCharArray();
        char[] eChars = e.toCharArray();
        int[] next = getNext(eChars, el);
        int j = 0;
        for (int i = 0; i < sl; i++) {
            while (j > 0 && sChars[i] != eChars[j]) {
                j = next[j - 1] + 1;
            }
            if (sChars[i] == eChars[j]) {
                ++j;
            }
            if (j == el) {
                return i - el + 1;
            }
        }
        return -1;

    }

    private static int[] getNext(char[] eChars, int el) {
        int[] ints = new int[el];
        ints[0] = -1;
        int k = -1;
        for (int i = 1; i < el; i++) {
            while (k != -1 && eChars[k + 1] != eChars[i]) {
                k = ints[k];
            }
            if (eChars[k + 1] == eChars[i]) {
                ++k;
            }
            ints[i] = k;
        }
        return ints;
    }


}
