import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int algorithm;

        Random random = new Random();
        int n = 1000;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble(10000);
            double y = random.nextDouble(10000);
            points[i] = new Point(x, y);
        }

        for (int i = 0; i > -1; i++) {

            System.out.println("What algorithm would you like to use?\n" +
                    "1. Brute Force\n" +
                    "2. Divide and Conquer\n" +
                    "3. Decrease and Conquer\n" +
                    "4. Random\n" +
                    "5. End");
            algorithm = scan.nextInt();

            switch (algorithm) {
                case 1:
                    int[] closestPair1 = BruteForce.bruteForce(points);

                    System.out.println("Closest Pair Indices: (" + closestPair1[0] + ", " + closestPair1[1] + ")");

                    Point point1 = points[closestPair1[0]];
                    Point point2 = points[closestPair1[1]];

                    System.out.println("List of Points:");
                    for (int j = 0; j < n; j++) {
                        System.out.println("Point " + (j + 1) + ": (" + points[j].x + ", " + points[j].y + ")");
                    }

                    System.out.println("Closest Pair Distance: " + getDistance(point1, point2));
                    break;

                case 2:
                    int[] closestPair2 = DivideAndConquer.divideAndConquer(points);

                    System.out.println("Closest Pair Indices: (" + closestPair2[0] + ", " + closestPair2[1] + ")");

                    Point point3 = points[closestPair2[0]];
                    Point point4 = points[closestPair2[1]];

                    System.out.println("List of Points:");
                    for (int j = 0; j < n; j++) {
                        System.out.println("Point " + (j + 1) + ": (" + points[j].x + ", " + points[j].y + ")");
                    }

                    System.out.println("Closest Pair Distance: " + getDistance(point3, point4));
                    break;

                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    i=-3;

                default:
                    return;
            }
            System.out.println();
        }
    }
    public static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}


