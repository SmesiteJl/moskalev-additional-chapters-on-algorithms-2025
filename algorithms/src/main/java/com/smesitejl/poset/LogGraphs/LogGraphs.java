package com.smesitejl.poset.LogGraphs;

import java.io.*;
import java.util.*;

public class LogGraphs {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Point[] p = new Point[n];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            p[i] = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }

        Arrays.sort(p, (a, b) -> a.x != b.x ? a.x - b.x : b.y - a.y);

        ArrayList<Integer> piles = new ArrayList<>();

        for (Point pt : p) {
            int y = pt.y;
            int l = 0, r = piles.size();
            while (l < r) {
                int m = (l + r) / 2;
                if (piles.get(m) < y) l = m + 1;
                else r = m;
            }
            if (l == piles.size()) piles.add(y);
            else piles.set(l, y);
        }

        System.out.println(piles.size());
    }
}

