package com.smesitejl.graph;

import java.io.*;
import java.util.*;

public class GraphCenterMedian {
    static class Edge {
        int to;
        double w;
        Edge(int t, double w) {
            this.to = t;
            this.w = w;
        }
    }

    static final double INF = 1e18;

    static double[] dijkstra(int s, List<Edge>[] g) {
        int n = g.length;
        double[] dist = new double[n];
        Arrays.fill(dist, INF);
        dist[s] = 0.0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> Double.longBitsToDouble(a[1])));
        pq.add(new long[]{s, Double.doubleToLongBits(0.0)});
        boolean[] used = new boolean[n];
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int v = (int) cur[0];
            if (used[v]) continue;
            used[v] = true;
            double dv = dist[v];
            for (Edge e : g[v]) {
                int to = e.to;
                double nd = dv + e.w;
                if (nd < dist[to]) {
                    dist[to] = nd;
                    pq.add(new long[]{to, Double.doubleToLongBits(nd)});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Edge>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            double w = Double.parseDouble(st.nextToken());
            g[u].add(new Edge(v, w));
        }

        double[][] dist = new double[n][];
        for (int i = 0; i < n; i++) dist[i] = dijkstra(i, g);

        double bestEcc = INF;
        double bestSum = INF;
        double[] ecc = new double[n];
        double[] sum = new double[n];

        for (int i = 0; i < n; i++) {
            double e = 0.0;
            double s = 0.0;
            boolean bad = false;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] >= INF / 2 && i != j) {
                    bad = true;
                    break;
                }
                if (i != j) {
                    e = Math.max(e, dist[i][j]);
                    s += dist[i][j];
                }
            }
            if (bad) {
                ecc[i] = INF;
                sum[i] = INF;
                continue;
            }
            ecc[i] = e;
            sum[i] = s;
            if (e < bestEcc) bestEcc = e;
            if (s < bestSum) bestSum = s;
        }

        List<Integer> centers = new ArrayList<>();
        List<Integer> medians = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Math.abs(ecc[i] - bestEcc) < 1e-9) centers.add(i + 1);
            if (Math.abs(sum[i] - bestSum) < 1e-9) medians.add(i + 1);
        }

        StringBuilder out = new StringBuilder();
        out.append("centers: ").append(centers.size());
        for (int v : centers) out.append(" ").append(v);
        out.append("\n");
        out.append("medians: ").append(medians.size());
        for (int v : medians) out.append(" ").append(v);
        System.out.println(out);
    }
}

