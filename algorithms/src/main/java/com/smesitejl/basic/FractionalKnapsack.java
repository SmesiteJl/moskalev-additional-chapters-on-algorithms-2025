package com.smesitejl.basic;

import java.io.*;
import java.util.*;

public class FractionalKnapsack {
    static class Item {
        double w, c, ratio;
        Item(double w, double c) {
            this.w = w;
            this.c = c;
            this.ratio = c / w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double W = Double.parseDouble(st.nextToken());

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double wi = Double.parseDouble(st.nextToken());
            double ci = Double.parseDouble(st.nextToken());
            items[i] = new Item(wi, ci);
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double total = 0.0;
        double rem = W;

        for (int i = 0; i < n && rem > 0; i++) {
            if (items[i].w <= rem) {
                total += items[i].c;
                rem -= items[i].w;
            } else {
                total += items[i].ratio * rem;
                rem = 0;
            }
        }

        System.out.println(total);
    }
}

