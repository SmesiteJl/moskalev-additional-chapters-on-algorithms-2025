package com.smesitejl.NumberTheory.DiophantineSearch;

import java.io.*;
import java.util.*;

public class DiophantineSearch {
    static boolean check(long x, long y, long z) {
        return (x/y+z) + (y/x+z) + (z/x+y) == 4;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long maxX = Long.parseLong(st.nextToken());
        long maxY = Long.parseLong(st.nextToken());
        long maxZ = Long.parseLong(st.nextToken());

        for (long x = 1; x <= maxX; x++) {
            for (long y = 1; y <= maxY; y++) {
                for (long z = 1; z <= maxZ; z++) {
                    if (check(x, y, z)) {
                        System.out.println(x + " " + y + " " + z);
                        return;
                    }
                }
            }
        }
        System.out.println("NO");
    }
}

