package com.smesitejl.Dynamic.GlassBalls;

import java.io.*;
import java.math.*;
import java.util.*;

public class GlassBalls {
    static BigInteger coverage(int k, BigInteger m, BigInteger limit) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger c = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            BigInteger num = m.subtract(BigInteger.valueOf(i - 1L));
            if (num.signum() <= 0) break;
            c = c.multiply(num).divide(BigInteger.valueOf(i));
            sum = sum.add(c);
            if (sum.compareTo(limit) >= 0) return sum;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        BigInteger N = new BigInteger(st.nextToken());

        BigInteger lo = BigInteger.ZERO;
        BigInteger hi = N;

        while (lo.compareTo(hi) < 0) {
            BigInteger mid = lo.add(hi).shiftRight(1);
            if (mid.equals(BigInteger.ZERO)) {
                lo = BigInteger.ONE;
                continue;
            }
            BigInteger cov = coverage(k, mid, N);
            if (cov.compareTo(N) >= 0) {
                hi = mid;
            } else {
                lo = mid.add(BigInteger.ONE);
            }
        }

        System.out.println(lo.toString());
    }
}

