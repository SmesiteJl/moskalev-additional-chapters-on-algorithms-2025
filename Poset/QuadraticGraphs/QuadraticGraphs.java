package com.smesitejl.Poset.QuadraticGraphs;

import java.io.*;
import java.util.*;

public class QuadraticGraphs {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static boolean ok(Point a, Point b, String mode) {
        if (mode.equals("INC")) return b.y > a.y;
        if (mode.equals("NINC")) return b.y >= a.y;
        if (mode.equals("DEC")) return b.y < a.y;
        return b.y <= a.y;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            p[i] = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        String mode = br.readLine().trim();

        Arrays.sort(p, Comparator.comparingInt(a -> a.x));

        List<List<Point>> chains = new ArrayList<>();

        for (Point pt : p) {
            boolean used = false;
            for (List<Point> c : chains) {
                if (ok(c.get(c.size() - 1), pt, mode)) {
                    c.add(pt);
                    used = true;
                    break;
                }
            }
            if (!used) {
                List<Point> nc = new ArrayList<>();
                nc.add(pt);
                chains.add(nc);
            }
        }

        System.out.println(chains.size());
    }
}
