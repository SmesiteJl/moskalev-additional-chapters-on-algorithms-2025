package com.smesitejl.Graph.Matchmaker;

import java.io.*;
import java.util.*;

public class Matchmaker {
    static List<Integer>[] g;
    static int[] matchR;
    static boolean[] used;
    static double[] c;

    static boolean dfs(int v) {
        for (int to : g[v]) {
            if (used[to]) continue;
            used[to] = true;
            if (matchR[to] == -1 || dfs(matchR[to])) {
                matchR[to] = v;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        c = new double[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) c[i] = Double.parseDouble(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            g[u].add(v);
        }

        matchR = new int[m];
        Arrays.fill(matchR, -1);

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Double.compare(c[b], c[a]));

        for (int v : order) {
            used = new boolean[m];
            dfs(v);
        }

        double sum = 0;
        boolean[] chosen = new boolean[n];
        for (int v : matchR) if (v != -1) chosen[v] = true;
        for (int i = 0; i < n; i++) if (chosen[i]) sum += c[i];

        System.out.println(sum);
    }
}

