package com.smesitejl.graph.Kruskal;

import java.io.*;
import java.util.*;

public class Kruskal {
    static class Edge {
        int u, v;
        long w;
        Edge(int u, int v, long w) { this.u = u; this.v = v; this.w = w; }
    }

    static int[] p, r;

    static int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        if (r[a] < r[b]) p[a] = b;
        else if (r[b] < r[a]) p[b] = a;
        else {
            p[b] = a;
            r[a]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] e = new Edge[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());
            e[i] = new Edge(u, v, w);
        }

        Arrays.sort(e, Comparator.comparingLong(x -> x.w));

        p = new int[n];
        r = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        long mst = 0;
        int cnt = 0;

        for (Edge ed : e) {
            if (union(ed.u, ed.v)) {
                mst += ed.w;
                cnt++;
                if (cnt == n - 1) break;
            }
        }

        System.out.println(mst);
    }
}

