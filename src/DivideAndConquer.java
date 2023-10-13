import java.util.*;

public class DivideAndConquer {
    public static int[] divideAndConquer(Point[] points) {
        // Sort the points on x-coordinate
        Arrays.sort(points, Comparator.comparingDouble(point -> point.x));

        // Recursive function that finds the closest pair
        double distance = cpRecursive(points, 0, points.length - 1);

        // Find the closest pair indices
        int[] closestPairIndices = findClosestPairIndices(points);

        return closestPairIndices;
    }

    public static double cpRecursive(Point[] points, int left, int right) {
        // Base case to return a large distance
        if (left >= right) {
            return Double.MAX_VALUE;
        }

        // This divides the points into two subsets with the middle
        int middle = (left + right) / 2;

        // Recursive call to find the closest pair from the left and right subsets
        double mLeft = cpRecursive(points, left, middle);
        double mRight = cpRecursive(points, middle + 1, right);

        // mDistance finds the minimum distance between the two subsets
        double mDistance = Math.min(mLeft, mRight);

        // Strip will create a strip of points in the mDistance
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - points[middle].x) < mDistance) {
                strip.add(points[i]);
            }
        }

        // Sort the strip by y-coordinate
        strip.sort(Comparator.comparingDouble(point -> point.y));

        // This will compare the distance between the points in the strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && strip.get(j).y - strip.get(i).y < mDistance; j++) {
                double distance = distance(strip.get(i), strip.get(j));
                mDistance = Math.min(mDistance, distance);
            }
        }

        return mDistance;
    }

    // Calculate the distance between 2 Point objects
    public static double distance(Point point1, Point point2) {
        double distancex = point1.x - point2.x;
        double distancey = point1.y - point2.y;
        return Math.sqrt((distancex * distancex) + (distancey * distancey));
    }

    // Find the indices of the closest pair in the original Point[] array
    public static int[] findClosestPairIndices(Point[] points) {
        int n = points.length;
        int[] closestPairIndices = new int[2];
        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < minDistance) {
                    minDistance = distance;
                    closestPairIndices[0] = i;
                    closestPairIndices[1] = j;
                }
            }
        }

        return closestPairIndices;
    }
}
