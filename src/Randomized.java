import java.util.*;

  // Will often fail to select the true closest pair for two possible reasons: 
    // 1) Point values can repeat (preventing the smallest pair from being compared)
      // This can be resolved by having non-repeating random generators or an array with n values randomized
    // 2) The same points will be compared giving a false closest pair of 0.
      // This can be resolved by having the if loop for distance checking only occur if (&& rand1 != rand2)
    // For the purpose of this program, these solutions will not be implemented

public class Randomized {
    public static int[] randomized(Point[] points) {
        int n = points.length;
        int[] closestPair = new int[2];
        double minDistance = Double.POSITIVE_INFINITY;

        Random rand = new Random();
        int rand1, rand2;
        for (int i = 0; i < n; i++) { // Run for loops until it matches n, so if n is 1000, it runs from 0-999 (or 1000)
          {
            rand1 = rand.nextInt(n-1); // Generates a random value between 0 to n-1 (For N = 1000, it would generate 0-999)
            rand2 = rand.nextInt(n-1); // Generates a random value between 0 to n-1 (For N = 1000, it would generate 0-999)
            double dx = points[rand1].x - points[rand2].x;
            double dy = points[rand1].y - points[rand2].y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < minDistance) {
              minDistance = distance;
              closestPair[0] = rand1;
              closestPair[1] = rand2;
            }
          }
        }

      return closestPair;
    }
}
