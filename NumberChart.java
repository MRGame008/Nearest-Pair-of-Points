package AlgorithimDesign;

import java.awt.*;
import javax.swing.*;

public class NumberChart extends JPanel {
    private int[] xValues;
    private int[] yValues;
    private String title;

    private int xDest1;
    private int yDest1;
    private int xDest2;
    private int yDest2;


    public NumberChart(int[] xValues, int[] yValues, String title,double xDest1
            ,double yDest1,double xDest2,double yDest2) {
        this.xValues = xValues;
        this.yValues = yValues;
        this.title = title;
        this.xDest1 = (int) xDest1;
        this.yDest1 = (int) yDest1;
        this.xDest2 = (int) xDest2;
        this.yDest2 = (int) yDest2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int padding = 25;
        int width = getWidth() - padding * 2;
        int height = getHeight() - padding * 2;
        int maxValueX = getMaxValue(xValues);
        int maxValueY = getMaxValue(yValues);
        double xScale = ((double) width - padding) / (double) maxValueX;
        double yScale = ((double) height - padding) / (double) maxValueY;

        // Draw x-axis and y-axis
        g.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);
        g.drawLine(padding, getHeight() - padding, padding, padding);

        // Draw tick marks on the x-axis
        for (int i = 0; i < xValues.length; i++) {
            int x1 = (int) (xValues[i] * xScale) + padding;
            int y1 = getHeight() - padding;
            int x2 = x1;
            int y2 = y1 + 5;
            g.drawLine(x1, y1, x2, y2);
            g.drawString(Integer.toString(xValues[i]), x1 - 10, y1 + 20);
        }

        // Draw tick marks on the y-axis
        for (int i = 0; i < maxValueY + 1; i += 10) {
            int x1 = padding;
            int y1 = (int) (getHeight() - padding - i * yScale);
            int x2 = x1 - 5;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
            g.drawString(Integer.toString(i), x1 - 20, y1 + 5);
        }

        // Draw title
        g.drawString(title, getWidth() / 2 - 50, 20);

        // Draw points
        for (int i = 0; i < xValues.length; i++) {
            int x = (int) (xValues[i] * xScale) + padding;
            int y = (int) ((maxValueY - yValues[i]) * yScale) + padding;
            g.fillOval(x - 3, y - 3, 6, 6);
        }

        //Making The Two Final Points Red
        for (int i = 0; i < xValues.length; i++) {
            g.setColor(Color.RED);
            if (xValues[i] == xDest1 && yValues[i] == yDest1){
                int x = (int) (xValues[i] * xScale) + padding;
                int y = (int) ((maxValueY - yValues[i]) * yScale) + padding;
                g.fillOval(x - 3, y - 3, 6, 6);
            }

            if (xValues[i] == xDest2 && yValues[i] == yDest2){
                int x = (int) (xValues[i] * xScale) + padding;
                int y = (int) ((maxValueY - yValues[i]) * yScale) + padding;
                g.fillOval(x - 3, y - 3, 6, 6);
            }
        }
    }

    private int getMaxValue(int[] values) {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}


