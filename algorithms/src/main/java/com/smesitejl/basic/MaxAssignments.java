package com.smesitejl.basic;

import java.util.Random;
import javax.swing.JFrame;

public class MaxAssignments {
    public static void main(String[] args) {
        int n = 1_000_000;
        int experiments = 50;

        int[] assignmentCounts = new int[experiments];
        Random random = new Random();

        for (int exp = 0; exp < experiments; exp++) {
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt();
            }

            int currentMax = array[0];
            int assignmentCount = 0;

            for (int i = 1; i < n; i++) {
                if (array[i] > currentMax) {
                    currentMax = array[i];
                    assignmentCount++;
                }
            }

            assignmentCounts[exp] = assignmentCount;
        }

        int minCount = assignmentCounts[0];
        int maxCount = assignmentCounts[0];
        long sumCounts = 0;
        for (int count : assignmentCounts) {
            if (count < minCount) {
                minCount = count;
            }
            if (count > maxCount) {
                maxCount = count;
            }
            sumCounts += count;
        }

        double averageCount = (double) sumCounts / experiments;

        int range = maxCount - minCount + 1;
        int[] histogram = new int[range];
        for (int count : assignmentCounts) {
            int index = count - minCount;
            histogram[index]++;
        }

        System.out.println("Number of experiments: " + experiments);
        System.out.println("Array size: " + n);
        System.out.println("Average number of assignments: " + averageCount);

        JFrame frame = new JFrame("Assignments Histogram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new HistogramPanel(minCount, histogram));
        frame.setVisible(true);
    }
}

