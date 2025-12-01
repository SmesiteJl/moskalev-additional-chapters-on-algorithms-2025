package com.smesitejl.Dynamic.VaccineProduction;

import java.util.*;

public class VaccineProduction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];
        int[] aToB = new int[n];
        int[] bToA = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n; i++) b[i] = sc.nextInt();
        for (int i = 1; i < n; i++) aToB[i] = sc.nextInt();
        for (int i = 1; i < n; i++) bToA[i] = sc.nextInt();

        int[] TA = new int[n];
        int[] TB = new int[n];
        int[] pathA = new int[n];
        int[] pathB = new int[n];

        TA[0] = a[0];
        TB[0] = b[0];

        for (int i = 1; i < n; i++) {
            int stayA = TA[i-1] + a[i];
            int switchA = TB[i-1] + bToA[i] + a[i];
            if (stayA <= switchA) {
                TA[i] = stayA;
                pathA[i] = 0;
            } else {
                TA[i] = switchA;
                pathA[i] = 1;
            }

            int stayB = TB[i-1] + b[i];
            int switchB = TA[i-1] + aToB[i] + b[i];
            if (stayB <= switchB) {
                TB[i] = stayB;
                pathB[i] = 1;
            } else {
                TB[i] = switchB;
                pathB[i] = 0;
            }
        }

        int totalTime;
        int line;
        if (TA[n-1] <= TB[n-1]) {
            totalTime = TA[n-1];
            line = 0;
        } else {
            totalTime = TB[n-1];
            line = 1;
        }

        int[] solution = new int[n];
        solution[n-1] = line;
        for (int i = n-1; i > 0; i--) {
            if (solution[i] == 0) solution[i-1] = pathA[i];
            else solution[i-1] = pathB[i];
        }

        System.out.println(totalTime);
        for (int x : solution) System.out.print((x == 0 ? "A" : "B") + " ");
    }
}

