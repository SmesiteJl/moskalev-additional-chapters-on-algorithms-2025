package com.smesitejl.numbertheory;

import java.math.BigInteger;

public class MoscowPiterHappyTickets {
    public static void main(String[] args) {
        int n = 50;
        int maxSum = 9 * n;

        BigInteger[] dp = new BigInteger[maxSum + 1];
        for (int i = 0; i <= maxSum; i++) {
            dp[i] = BigInteger.ZERO;
        }
        dp[0] = BigInteger.ONE;

        for (int pos = 0; pos < n; pos++) {
            BigInteger[] next = new BigInteger[maxSum + 1];
            for (int i = 0; i <= maxSum; i++) {
                next[i] = BigInteger.ZERO;
            }
            for (int sum = 0; sum <= maxSum; sum++) {
                if (!dp[sum].equals(BigInteger.ZERO)) {
                    for (int digit = 0; digit <= 9; digit++) {
                        int ns = sum + digit;
                        if (ns <= maxSum) {
                            next[ns] = next[ns].add(dp[sum]);
                        }
                    }
                }
            }
            dp = next;
        }

        BigInteger result = BigInteger.ZERO;
        for (int sum = 0; sum <= maxSum; sum++) {
            result = result.add(dp[sum].pow(2));
        }

        System.out.println("Number of 100-digit happy tickets (Moscow / Piter):");
        System.out.println(result);
    }
}
