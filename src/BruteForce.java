public class BruteForce {

    public static int[] bruteForce(Point[] points) {
        int n = points.length;
        int[] closestPair = new int[2];
        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < minDistance) {
                    minDistance = distance;
                    closestPair[0] = i;
                    closestPair[1] = j;
                }
            }
        }

        return closestPair;
    }
}