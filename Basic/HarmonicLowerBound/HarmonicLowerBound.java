package com.smesitejl.Basic.HarmonicLowerBound;

public class HarmonicLowerBound {
    public static void main(String[] args) {
        int n = 1_000_000;

        double harmonicSum = 0.0;
        for (int k = 1; k <= n; k++) {
            harmonicSum += 1.0 / k;
        }

        double lowerBound = Math.log(n + 1.0);

        System.out.println("n = " + n);
        System.out.println("H_n(numeric value) = " + harmonicSum);
        System.out.println("Lower bound ln(n+1) = " + lowerBound);
        System.out.println("Difference H_n - ln(n+1) = " + (harmonicSum - lowerBound));
    }
}
