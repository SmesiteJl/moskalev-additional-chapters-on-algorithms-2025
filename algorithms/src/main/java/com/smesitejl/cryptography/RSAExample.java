package com.smesitejl.cryptography;

import java.io.*;
import java.math.*;
import java.security.*;

public class RSAExample {
    static class KeyPair {
        BigInteger n;
        BigInteger e;
        BigInteger d;
    }

    static class EncData {
        BigInteger[] blocks;
        int[] lens;
    }

    static KeyPair generate(int bits) {
        SecureRandom rnd = new SecureRandom();
        BigInteger e = BigInteger.valueOf(65537);
        while (true) {
            int pBits = bits / 2;
            int qBits = bits - pBits;
            BigInteger p = BigInteger.probablePrime(pBits, rnd);
            BigInteger q = BigInteger.probablePrime(qBits, rnd);
            BigInteger n = p.multiply(q);
            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            if (!phi.gcd(e).equals(BigInteger.ONE)) continue;
            BigInteger d = e.modInverse(phi);
            KeyPair kp = new KeyPair();
            kp.n = n;
            kp.e = e;
            kp.d = d;
            return kp;
        }
    }

    static EncData encryptBlocks(byte[] data, BigInteger n, BigInteger e) {
        int blockSize = (n.bitLength() - 1) / 8;
        int blocks = (data.length + blockSize - 1) / blockSize;
        BigInteger[] out = new BigInteger[blocks];
        int[] lens = new int[blocks];
        int pos = 0;
        for (int i = 0; i < blocks; i++) {
            int len = Math.min(blockSize, data.length - pos);
            byte[] block = new byte[len];
            System.arraycopy(data, pos, block, 0, len);
            pos += len;
            lens[i] = len;
            BigInteger m = new BigInteger(1, block);
            out[i] = m.modPow(e, n);
        }
        EncData res = new EncData();
        res.blocks = out;
        res.lens = lens;
        return res;
    }

    static byte[] decryptBlocks(EncData enc, BigInteger n, BigInteger d) {
        BigInteger[] blocks = enc.blocks;
        int[] lens = enc.lens;
        int total = 0;
        for (int x : lens) total += x;
        byte[] res = new byte[total];
        int pos = 0;
        for (int i = 0; i < blocks.length; i++) {
            BigInteger m = blocks[i].modPow(d, n);
            byte[] b = m.toByteArray();
            int need = lens[i];
            byte[] block = new byte[need];
            if (b.length >= need) {
                System.arraycopy(b, b.length - need, block, 0, need);
            } else {
                int pad = need - b.length;
                System.arraycopy(b, 0, block, pad, b.length);
                for (int j = 0; j < pad; j++) block[j] = 0;
            }
            System.arraycopy(block, 0, res, pos, need);
            pos += need;
        }
        return res;
    }

    static EncData signBlocks(byte[] data, BigInteger n, BigInteger d) {
        int blockSize = (n.bitLength() - 1) / 8;
        int blocks = (data.length + blockSize - 1) / blockSize;
        BigInteger[] out = new BigInteger[blocks];
        int[] lens = new int[blocks];
        int pos = 0;
        for (int i = 0; i < blocks; i++) {
            int len = Math.min(blockSize, data.length - pos);
            byte[] block = new byte[len];
            System.arraycopy(data, pos, block, 0, len);
            pos += len;
            lens[i] = len;
            BigInteger m = new BigInteger(1, block);
            out[i] = m.modPow(d, n);
        }
        EncData res = new EncData();
        res.blocks = out;
        res.lens = lens;
        return res;
    }

    static boolean verifyBlocks(byte[] data, EncData sig, BigInteger n, BigInteger e) {
        byte[] rec = decryptBlocks(sig, n, e);
        if (rec.length != data.length) return false;
        for (int i = 0; i < data.length; i++) if (data[i] != rec[i]) return false;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int bits = Integer.parseInt(line.trim());
        String msg = br.readLine();
        if (msg == null) msg = "";
        KeyPair kp = generate(bits);
        byte[] data = msg.getBytes("UTF-8");
        EncData enc = encryptBlocks(data, kp.n, kp.e);
        byte[] dec = decryptBlocks(enc, kp.n, kp.d);
        EncData sig = signBlocks(data, kp.n, kp.d);
        boolean ok = verifyBlocks(data, sig, kp.n, kp.e);
        System.out.println(kp.n.toString());
        System.out.println(kp.e.toString());
        System.out.println(new String(dec, "UTF-8"));
        System.out.println(ok ? "OK" : "FAIL");
    }
}

