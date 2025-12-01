package com.smesitejl.geometry;

import java.io.*;
import java.util.*;

public class TrapezoidPackingCenter {
    static class Trap {
        double B, C, D;
        int id;
        Trap(double B, double C, double D, int id) {
            this.B = B;
            this.C = C;
            this.D = D;
            this.id = id;
        }
    }

    static int n;
    static Trap[] traps;
    static boolean[] used;
    static Trap[] order;
    static boolean[] rotated;
    static boolean found;

    static Trap rotateCenter(Trap t) {
        return new Trap(t.D - t.C, 0.0, t.B, t.id);
    }

    static boolean isPerfectOrder(Trap[] ord, boolean[] rot) {
        Trap[] seq = new Trap[n];
        for (int i = 0; i < n; i++) {
            seq[i] = rot[i] ? rotateCenter(ord[i]) : ord[i];
        }
        double sumB = 0.0;
        for (int i = 0; i < n; i++) sumB += seq[i].B;
        return Math.abs(sumB - sumB) < 1e-9;
    }

    static void dfs(int pos) {
        if (found) return;
        if (pos == n) {
            if (isPerfectOrder(order, rotated)) {
                found = true;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    sb.append(order[i].id + 1).append(rotated[i] ? "'" : "").append(" ");
                }
                System.out.println(sb.toString().trim());
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                order[pos] = traps[i];

                rotated[pos] = false;
                dfs(pos + 1);
                if (found) return;

                rotated[pos] = true;
                dfs(pos + 1);
                if (found) return;

                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        n = Integer.parseInt(line.trim());
        traps = new Trap[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double B = Double.parseDouble(st.nextToken());
            double C = Double.parseDouble(st.nextToken());
            double D = Double.parseDouble(st.nextToken());
            traps[i] = new Trap(B, C, D, i);
        }
        used = new boolean[n];
        order = new Trap[n];
        rotated = new boolean[n];
        found = false;
        dfs(0);
        if (!found) System.out.println("NO");
    }
}
