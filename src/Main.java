import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int algorithm;

        Random random = new Random();
        int n = 1000;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble() * 10000;
            double y = random.nextDouble() * 10000;
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

            long startTime = System.nanoTime(); // Start the timer

            switch (algorithm) {
                case 1:
                    int[] closestPair1 = BruteForce.bruteForce(points);
                    displayResult(points, closestPair1);
                    break;

                case 2:
                    int[] closestPair2 = DivideAndConquer.divideAndConquer(points);
                    displayResult(points, closestPair2);
                    break;

                case 3:
                    int[] closestPair3 = DecreaseAndConquer.decreaseAndConquer(points);
                    displayResult(points, closestPair3);
                    break;

                case 4:
                    int[] closestPair4 = Randomized.randomized(points);
                    displayResult(points, closestPair4);
                    break;

                case 5:
                    i = -3;
                    return;

                default:
                    return;
            }

            long endTime = System.nanoTime(); // Stop the timer
            double elapsedTime = (endTime - startTime) / 1e6; // Convert to milliseconds
            System.out.println("Time taken: " + elapsedTime + " ms\n");
        }
    }

    public static void displayResult(Point[] points, int[] closestPairIndices) {
        System.out.println("Closest Pair Indices: (" + closestPairIndices[0] + ", " + closestPairIndices[1] + ")");

        Point point1 = points[closestPairIndices[0]];
        Point point2 = points[closestPairIndices[1]];

        System.out.println("List of Points:");
        for (int j = 0; j < points.length; j++) {
            System.out.println("Point " + (j + 1) + ": (" + points[j].x + ", " + points[j].y + ")");
        }

        System.out.println("Closest Pair Distance: " + getDistance(point1, point2));
    }

    public static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
