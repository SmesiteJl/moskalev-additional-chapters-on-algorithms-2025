package com.smesitejl.Graph.CurrencyArbitrage;

import java.io.*;
import java.util.*;

public class CurrencyArbitrage {
    static class Edge {
        int from, to;
        double w;
        Edge(int f, int t, double w) {
            this.from = f;
            this.to = t;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        int n = Integer.parseInt(line.trim());
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double w = Math.log(a[i][j]);
                edges.add(new Edge(j, i, w));
            }
        }

        double[] dist = new double[n];
        Arrays.fill(dist, 0.0);

        boolean changed = false;
        for (int iter = 0; iter < n; iter++) {
            changed = false;
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.w) {
                    dist[e.to] = dist[e.from] + e.w;
                    changed = true;
                }
            }
            if (!changed) break;
        }

        boolean hasNegativeCycle = false;
        if (changed) {
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.w) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        System.out.println(hasNegativeCycle ? "YES" : "NO");
    }
}

