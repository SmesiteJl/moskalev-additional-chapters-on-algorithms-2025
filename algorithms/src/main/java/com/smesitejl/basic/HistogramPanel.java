package com.smesitejl.basic;

import javax.swing.*;
import java.awt.*;

public class HistogramPanel extends JPanel {
    private final int minValue;
    private final int[] frequencies;

    public HistogramPanel(int minValue, int[] frequencies) {
        this.minValue = minValue;
        this.frequencies = frequencies;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int padding = 50;
        int axisBottom = height - padding;
        int axisLeft = padding;
        int axisRight = width - padding;

        int maxFrequency = 0;
        for (int f : frequencies) {
            if (f > maxFrequency) {
                maxFrequency = f;
            }
        }

        int binCount = frequencies.length;
        int plotWidth = axisRight - axisLeft;
        int barWidth = Math.max(1, plotWidth / binCount);

        g.setColor(Color.BLACK);
        g.drawLine(axisLeft, axisBottom, axisRight, axisBottom);
        g.drawLine(axisLeft, axisBottom, axisLeft, padding);

        g.setFont(new Font("SansSerif", Font.PLAIN, 12));

        for (int i = 0; i < binCount; i++) {
            int value = minValue + i;
            int freq = frequencies[i];

            int barHeight = 0;
            if (maxFrequency > 0) {
                barHeight = (int) ((double) freq / maxFrequency * (axisBottom - padding));
            }

            int x = axisLeft + i * barWidth;
            int y = axisBottom - barHeight;

            g.setColor(new Color(153, 236, 149));
            g.fillRect(x, y, barWidth - 2, barHeight);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth - 2, barHeight);

            String label = Integer.toString(value);
            int labelWidth = g.getFontMetrics().stringWidth(label);
            int labelX = x + (barWidth - labelWidth) / 2;
            int labelY = axisBottom + 15;
            g.drawString(label, labelX, labelY);
        }

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("Assignments count", (axisLeft + axisRight) / 2 - 60, height - 10);
        g.drawString("Frequency", 10, padding - 10);
    }
}
