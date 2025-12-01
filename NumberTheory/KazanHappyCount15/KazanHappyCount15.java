package com.smesitejl.NumberTheory.KazanHappyCount15;

import java.math.BigInteger;

public class KazanHappyCount15 {
    static BigInteger[] fact = new BigInteger[16];
    static BigInteger total = BigInteger.ZERO;

    public static void main(String[] args) {
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= 15; i++) fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        int[] cnt = new int[10];
        generate(cnt, 0, 15);
        System.out.println(total);
    }

    static void generate(int[] cnt, int digit, int remain) {
        if (digit == 10) {
            if (remain == 0) process(cnt);
            return;
        }
        for (int x = 0; x <= remain; x++) {
            cnt[digit] = x;
            generate(cnt, digit + 1, remain - x);
        }
    }

    static void process(int[] cnt) {
        int sum = 0;
        for (int d = 0; d <= 9; d++) sum += cnt[d] * d;
        if (sum % 2 != 0) return;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int d = 0; d <= 9; d++) {
            for (int t = 0; t < cnt[d]; t++) {
                for (int s = target; s >= d; s--) {
                    if (dp[s - d]) dp[s] = true;
                }
            }
        }

        if (!dp[target]) return;

        BigInteger ways = fact[15];
        for (int d = 0; d <= 9; d++) ways = ways.divide(fact[cnt[d]]);
        total = total.add(ways);
    }
}

