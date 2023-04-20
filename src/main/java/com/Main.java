package com;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long argsL = 12352342342233L;
        long[] result = new long[(argsL + "").length()];
        solve(argsL, result, 0);
        System.out.println(Arrays.toString(result));
    }

    /**
     * @param args   整数
     * @param result 结果数组
     * @param length 已经处理了几个数，进递归时为0，结束时为n-1
     * @return
     */
    static void solve(long args, long[] result, int length) {
        if (args == 0) {
            return;
        }
        result[length++] = args % 10;
        args = args / 10;
        solve(args, result, length);
    }
}
