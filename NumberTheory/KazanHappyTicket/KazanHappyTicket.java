package com.smesitejl.NumberTheory.KazanHappyTicket;

import java.util.*;

public class KazanHappyTicket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = s.charAt(i) - '0';

        int total = 0;
        for (int d : digits) total += d;
        if (total % 2 != 0) {
            System.out.println("Not happy");
            return;
        }

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int d : digits) {
            for (int sum = target; sum >= d; sum--) {
                if (dp[sum - d]) dp[sum] = true;
            }
        }

        if (dp[target]) System.out.println("Happy");
        else System.out.println("Not happy");
    }
}

