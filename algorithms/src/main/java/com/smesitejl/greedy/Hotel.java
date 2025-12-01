package com.smesitejl.greedy;

import java.io.*;
import java.util.*;

public class Hotel {
    static class Req {
        int b, e;
        Req(int b, int e) { this.b = b; this.e = e; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Req[] a = new Req[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            a[i] = new Req(b, e);
        }

        Arrays.sort(a, Comparator.comparingInt(x -> x.e));

        int cnt = 0;
        int last = Integer.MIN_VALUE;

        for (Req r : a) {
            if (r.b >= last) {
                cnt++;
                last = r.e;
            }
        }

        System.out.println(cnt * 1000L);
    }
}

