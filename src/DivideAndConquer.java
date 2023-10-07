
import java.util.*;

    //The DivideAndConquer class represents 2D points
     class DivideAndConquer{
        public double x, y;

        public DivideAndConquer(double x, double y){
            this.x=x;
            this.y=y;
        }
    }

    public class ClosestPairProblem{
        public static double closestPairProblem(DivideAndConquer[] points){
            //Sorts the point on x coordinate to get ready for the divide and conquer
            Arrays.sort(points, Comparator.comparingDouble(point -> point.x));
            //Recursive function that finds the closest pair
            return cpRecursive(points, 0, points.length-1);
        }

        public static double cpRecursive(DivideAndConquer[] points, int left, int right){
            //Base case to return large distance
            if (left>=right){
                return Double.MAX_VALUE;
            }
            //This divides point into 2 subsets with the middle
            int middle = (left+right)/2;
            //Recursive call to find closest pair from the left subset and right subset
            double mLeft = cpRecursive(points,left,middle);
            double mRight = cpRecursive(points, middle+1,right);
            //mDistance finds the minimum distance between the 2 subsets
            double mDistance = Math.min(mLeft,mRight);

           //strip will create strip of points in the mDistance
            DivideAndConquer[] strip = new DivideAndConquer[right-left+1];
            int stripSize = 0;
            for(int i = left; i <= right; i++){
                if(Math.abs(points[i].x - points[middle].x)<mDistance){
                    strip[stripSize++] = points[i];
                }
            }

            //Sorts the strip by y coordinate
            Arrays.sort(strip, 0, stripSize, Comparator.comparingDouble(point -> point.y));
            //This will compare the distance between the points
            for(int i = 0; i < stripSize; i++){
                for (int j = i + 1; j < stripSize && strip[j].y - strip[i].y < mDistance; j++){
                    mDistance = Math.min(mDistance, distance(strip[i], strip[j]));
                }
            }
            return mDistance;
        }
        //Calculate the distance between 2 points
        public static double distance(DivideAndConquer point1, DivideAndConquer point2){
            double distancex = point1.x - point2.x;
            double distancey = point1.y - point2.y;
            return Math.sqrt(((distancex*distancex) + (distancey*distancey)));
        }
    }
