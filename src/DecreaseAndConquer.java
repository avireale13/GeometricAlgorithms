import java.util.*;

public class DecreaseAndConquer {
    public static int[] decreaseAndConquer(Point[] points) {
        if (points == null || points.length < 2) {
            return null; //Returns if there are not enough points tp form a pair
        }

        Random random = new Random();
        for (Point point : points) {
            point.x += random.nextDouble() * 0.000001; //Random offset to x-coordinate
            point.y += random.nextDouble() * 0.000001; //Random offset to y-coordinate
        }


        Arrays.sort(points, Comparator.comparingDouble(p -> p.x)); //Sorts array of points by x-coordinate.
        int[] closestPairIndices = new int[2];
        closestPair(points, 0, points.length - 1, closestPairIndices);
        return closestPairIndices;
    }
    private static double closestPair(Point[] points, int left, int right, int[] closestPairIndices) {
        if (left == right) {
            return Double.MAX_VALUE; //Returns if there are no points to compare
        } else if (left + 1 == right) {
            return getDistance(points[left], points[right]); //Returns 2 points to compare
        }

        int middle = (left + right) / 2;
        int[] leftPair = new int[2];
        int[] rightPair = new int[2];

        double lDistance = closestPair(points, left, middle, leftPair);
        double rDistance = closestPair(points, middle + 1, right, rightPair);
        double minDistance = Math.min(lDistance, rDistance);

        double splitDistance = closestSplitPair(points, left, middle, right, minDistance, closestPairIndices);

        if (splitDistance < minDistance) {
            return splitDistance;
        } else if (lDistance < rDistance) {
            closestPairIndices[0] = leftPair[0];
            closestPairIndices[1] = leftPair[1];
            return lDistance;
        } else {
            closestPairIndices[0] = rightPair[0];
            closestPairIndices[1] = rightPair[1];
            return rDistance;
        }
    }

    private static double closestSplitPair(Point[] points, int left, int mid, int right, double minDistance, int[] closestPairIndices) {
        Point[] strip = new Point[right - left + 1]; //In order to find the closest split pair, the points are sorted bt their y-coordinates in the split range
        int stripSize = 0;
        double splitDistance = minDistance;

        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - points[mid].x) < minDistance) {
                strip[stripSize] = points[i];
                stripSize++;
            }
        }

        Arrays.sort(strip, 0, stripSize, Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && strip[j].y - strip[i].y < splitDistance; j++) {
                double distance = getDistance(strip[i], strip[j]);
                if (distance < splitDistance) {
                    splitDistance = distance;
                    closestPairIndices[0] = Arrays.asList(points).indexOf(strip[i]);
                    closestPairIndices[1] = Arrays.asList(points).indexOf(strip[j]);
                }
            }
        }

        return splitDistance;
    }

    private static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
