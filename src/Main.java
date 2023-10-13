import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String algorithm;

        Random random = new Random();
        int n = 1000;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble(10000);
            double y = random.nextDouble(10000);
            points[i] = new Point(x, y);
        }

        System.out.println("What algorithm would you like to use?");
        algorithm = scan.nextLine();

        switch(algorithm) {
            case "brute force":
                int[] closestPair1 = BruteForce.bruteForce(points);

                System.out.println("Closest Pair Indices: (" + closestPair1[0] + ", " + closestPair1[1] + ")");

                Point point1 = points[closestPair1[0]];
                Point point2 = points[closestPair1[1]];

                System.out.println("List of Points:");
                for (int i = 0; i < n; i++) {
                    System.out.println("Point " + (i + 1) + ": (" + points[i].x + ", " + points[i].y + ")");
                }

                System.out.println("Closest Pair Distance: " + getDistance(point1, point2));
                return;

            case "divide and conquer":
                int[] closestPair2 = DivideAndConquer.divideAndConquer(points);

                System.out.println("Closest Pair Indices: (" + closestPair2[0] + ", " + closestPair2[1] + ")");

                Point point3 = points[closestPair2[0]];
                Point point4 = points[closestPair2[1]];

                System.out.println("List of Points:");
                for (int i = 0; i < n; i++) {
                    System.out.println("Point " + (i + 1) + ": (" + points[i].x + ", " + points[i].y + ")");
                }

                System.out.println("Closest Pair Distance: " + getDistance(point3, point4));
                return;

            default:
                return;
        }
    }
    public static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}


