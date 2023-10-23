import java.util.*;
public class DecreaseAndConquer {
    public static int[] decreaseAndConquer(Point[] points) {

        Arrays.sort(points, Comparator.comparingDouble(point -> point.x));

        return closestPairRecursive(points, 0, points.length - 1);
    }

    public static int[] closestPairRecursive(Point[] points, int left, int right) {
        if (left >= right) {
            return new int[]{-1, -1}; // No valid pair
        }

        if (left + 1 == right) {
            return new int[]{left, right}; // Only two points
        }


        int mid = (left + right) / 2;

        int[] lClosest = closestPairRecursive(points, left, mid);
        int[] rClosest = closestPairRecursive(points, mid + 1, right);

        double leftDistance = dist(points[lClosest[0]], points[lClosest[1]]);
        double rightDistance = dist(points[rClosest[0]], points[rClosest[1]]);

        if (leftDistance < rightDistance) {
            return lClosest;
        } else {
            return rClosest;
        }
    }
    public static double dist(Point point1, Point point2) {
        double dx = point1.x - point2.x;
        double dy = point1.y - point2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}