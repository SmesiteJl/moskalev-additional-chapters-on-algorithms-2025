package com.smesitejl.geometry;

import java.io.*;
import java.util.*;

public class PolygonArea {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        int n = Integer.parseInt(line.trim());
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }
        double s = 0.0;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            s += x[i] * y[j] - x[j] * y[i];
        }
        double area = Math.abs(s) / 2.0;
        System.out.println(area);
    }
}

