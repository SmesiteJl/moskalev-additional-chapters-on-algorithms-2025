package com.smesitejl.Graph.DominoTiling;

import java.io.*;
import java.util.*;

public class DominoTiling {
    static int h, w;
    static char[][] g;
    static int[][] id;
    static List<Integer>[] adj;
    static int[] mt;
    static boolean[] used;

    static boolean dfs(int v) {
        for (int to : adj[v]) {
            if (used[to]) continue;
            used[to] = true;
            if (mt[to] == -1 || dfs(mt[to])) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        g = new char[h][w];
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            g[i] = line.toCharArray();
        }

        id = new int[h][w];
        int leftCount = 0, rightCount = 0;
        int[][] leftId = new int[h][w];
        int[][] rightId = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (g[i][j] == '#') continue;
                if ((i + j) % 2 == 0) {
                    leftId[i][j] = leftCount++;
                } else {
                    rightId[i][j] = rightCount++;
                }
            }
        }

        adj = new ArrayList[leftCount];
        for (int i = 0; i < leftCount; i++) adj[i] = new ArrayList<>();

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int freeCells = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (g[i][j] == '#') continue;
                freeCells++;
                if ((i + j) % 2 == 0) {
                    int v = leftId[i][j];
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni < 0 || nj < 0 || ni >= h || nj >= w) continue;
                        if (g[ni][nj] == '#') continue;
                        int u = rightId[ni][nj];
                        adj[v].add(u);
                    }
                }
            }
        }

        mt = new int[rightCount];
        Arrays.fill(mt, -1);
        int match = 0;

        for (int v = 0; v < leftCount; v++) {
            used = new boolean[rightCount];
            if (dfs(v)) match++;
        }

        if (match * 2 == freeCells) System.out.println("YES");
        else System.out.println("NO");
    }
}

