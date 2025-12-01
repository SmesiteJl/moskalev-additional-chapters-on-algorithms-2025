package com.smesitejl.numbertheory;

import java.io.*;
import java.math.*;
import java.security.*;
import java.util.*;

public class PrimalityTests {
    static boolean fermat(BigInteger n, int iters) {
        if (n.compareTo(BigInteger.TWO) <= 0) return false;
        if (!n.testBit(0)) return n.equals(BigInteger.TWO);
        SecureRandom rnd = new SecureRandom();
        BigInteger nMinus1 = n.subtract(BigInteger.ONE);
        for (int i = 0; i < iters; i++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rnd);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(nMinus1) >= 0);
            if (!a.modPow(nMinus1, n).equals(BigInteger.ONE)) return false;
        }
        return true;
    }

    static boolean millerRabin(BigInteger n, int iters) {
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) return true;
        if (!n.testBit(0)) return false;
        BigInteger d = n.subtract(BigInteger.ONE);
        int s = 0;
        while (!d.testBit(0)) {
            d = d.shiftRight(1);
            s++;
        }
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < iters; i++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rnd);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.TWO)) > 0);
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;
            boolean cont = false;
            for (int r = 1; r < s; r++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    cont = true;
                    break;
                }
            }
            if (cont) continue;
            return false;
        }
        return true;
    }

    static boolean isPrimeInt(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) if (n % i == 0) return false;
        return true;
    }

    static boolean isCarmichael(int n) {
        if (n < 3 || isPrimeInt(n)) return false;
        int m = n;
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p * p <= m; p++) {
            if (m % p == 0) {
                int cnt = 0;
                while (m % p == 0) {
                    m /= p;
                    cnt++;
                }
                if (cnt > 1) return false;
                primes.add(p);
            }
        }
        if (m > 1) primes.add(m);
        if (primes.size() < 2) return false;
        int nm1 = n - 1;
        for (int p : primes) {
            if (nm1 % (p - 1) != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int limit = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        for (int n = 3; n <= limit; n++) {
            if (isCarmichael(n)) {
                BigInteger bn = BigInteger.valueOf(n);
                boolean f = fermat(bn, 5);
                boolean mr = millerRabin(bn, 5);
                sb.append(n).append(" ").append(f ? "FERMAT_OK" : "FERMAT_FAIL")
                        .append(" ").append(mr ? "MR_OK" : "MR_FAIL").append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}

