package com.smesitejl.greedy;

import java.io.*;
import java.util.*;

public class ExamSchedule {
    static class Task {
        int d;
        long f;
        Task(int d, long f) { this.d = d; this.f = f; }
    }

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Task[] a = new Task[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long f = Long.parseLong(st.nextToken());
            a[i] = new Task(d, f);
        }

        Arrays.sort(a, (x, y) -> Long.compare(y.f, x.f));

        parent = new int[k + 1];
        for (int i = 0; i <= k; i++) parent[i] = i;

        long res = 0;

        for (Task t : a) {
            int freeDay = find(t.d);
            if (freeDay > 0) {
                union(freeDay, freeDay - 1);
            } else {
                res += t.f;
            }
        }

        System.out.println(res);
    }
}

