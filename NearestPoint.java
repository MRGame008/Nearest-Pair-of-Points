package AlgorithimDesign;

import javax.swing.*;
import java.awt.*;
import java.util.*;
//Sara Nikoui 400149076
//Fatemieh Nikkhah 400149075
//Pouria Hosseini Dokht 400149019

public class NearestPoint extends JPanel{
    public static void TestUnit() throws InterruptedException {
        //AlgorithimDesign.NearestPoint tt = new AlgorithimDesign.NearestPoint();
        int size_array = 10;
        int size_random = 100;
        Point[] point = new Point[size_array];
        int[] xValues = new int[size_array];
        int[] yValues = new int[size_array];
        Random rd = new Random(); // creating Random object
        int x = 0,y;
        for (int i = 0; i < size_array; i++) {
            x = rd.nextInt(size_random);
            y = rd.nextInt(size_random);
            System.out.println("AlgorithimDesign.Point " + "(" + x + " ," + y + ")"); // printing each array element
            java.lang.Thread.sleep(1000);
            System.out.println("Added");
            point[i] = new Point(x,y);

            //For Drawing Graphical Chart
            xValues[i] = x;
            yValues[i] = y;
        }


        Point[] closest = findClosestPoints(point);

        java.lang.Thread.sleep(100);
        System.out.println("Nearest Distant : " + closest[0].distance(closest[1]));
        java.lang.Thread.sleep(100);
        System.out.println("Points Are : " + closest[0].VerytoString() + " " + closest[1].VerytoString());

        //This will create Graphical Design of number chart only for
        //size less or equal than 20 points
        if (size_array <= 20){
            String title = "Nearest AlgorithimDesign.Point";
            JFrame frame = new JFrame("Nearest AlgorithimDesign.Point");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            NumberChart scatterPlot = new NumberChart(xValues, yValues, title,closest[0].x
            ,closest[0].y,closest[1].x,closest[1].y);
            scatterPlot.setPreferredSize(new Dimension(400, 300));
            frame.getContentPane().add(scatterPlot);
            frame.pack();
            frame.setVisible(true);
        }
    }
    //O(n * log n)
    public static Point[] findClosestPoints(Point[] points) {
        if (points.length < 2) {
            throw new IllegalArgumentException("At least two points are required.");
        }

        Arrays.sort(points, (a, b) -> Integer.compare((int) a.x, (int) b.x));

        return findClosestPointsRecursive(points, 0, points.length - 1);
    }

    public static Point[] findClosestPointsRecursive(Point[] points, int left,
                                                     int right) {
        if (left == right) {
            //One AlgorithimDesign.Point
            return new Point[]{points[left], null};
        } else if (left + 1 == right) {
            //Two Points
            return new Point[]{points[left], points[right]};
        } else {
            //Divide and conquer
            int mid = (left + right) / 2;
            Point[] closestLeft = findClosestPointsRecursive(points, left, mid);
            Point[] closestRight = findClosestPointsRecursive(points, mid + 1, right);
            double minDistance = closestLeft[0].distance(closestLeft[1]);
            Point[] closest = closestLeft;

            if (closestRight[0] != null && closestRight[1] != null) {
                double distance = closestRight[0].distance(closestRight[1]);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = closestRight;
                }
            }

            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    double distance = points[i].distance(points[j]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closest[0] = points[i];
                        closest[1] = points[j];
                    }
                }
            }

            return closest;
        }
    }

    public static void getFromPointsUser(){
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        Point[] points = new Point[(int) num];

        for (int i = 0; i < points.length; i++) {

            int x = input.nextInt();
            int y = input.nextInt();
            points[i] = new Point(x,y);
        }

        Point[] closest = findClosestPoints(points);

        System.out.println(String.format("%.3f",closest[0].distance(closest[1])));
        System.out.println(closest[0].toString());
        System.out.println(closest[1].toString());
    }

    public static void main(String[] args) throws InterruptedException {


        TestUnit();
        //getFromPointsUser();

    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        return  x + " " + y;
    }
    public String VerytoString() {
        return  "(" + x + ", " + y + ")";
    }
}


