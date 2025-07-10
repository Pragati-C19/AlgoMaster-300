import java.util.*;

public class MinAreaFreeRect {
    
    // Driver Function
    public double minAreaFreeRect(int[][] points) {
        
        // Declare variables
        int n = points.length;
        Map<String, List<int[]>> diagonalsMap = new HashMap<>();
        double minArea = Double.MAX_VALUE;


        // Phase1 : Store all point pairs as possible diagonals
        for (int i = 0; i < n; i++) {

            int[] p1 = points[i];

            for (int j = i + 1; j < n; j++) {
                
                int[] p2 = points[j];

                // Midpoint key(x1 + x2, y1 + y2) to avoid float division
                int midX = p1[0] + p2[0];
                int midY = p1[1] + p2[1];

                // Squared length of diagonal
                int distSq = distanceSquare(p1, p2);

                // Build key as string: "midX,midY,distSq"
                String key = midX + ", " + midY + ", " + distSq;

                // Add that key and value in map
                if (!diagonalsMap.containsKey(key)) {
                
                    diagonalsMap.put(key, new ArrayList<>());
                }

                diagonalsMap.get(key).add(p1);
            }

        }

        // Debugger Sout
        for (Map.Entry<String, List<int[]>> entry : diagonalsMap.entrySet()) {
                
            System.out.println("  - Key: " + entry.getKey());
                
            for (int[] p : entry.getValue()) {
                    
                System.out.println("    Point: " + Arrays.toString(p));
            }
        }


        // Phase 2 : For each group with the same midpoint and dist
        for (List<int[]> group : diagonalsMap.values()) {
            
            int size = group.size();

            // Try every pair in this group
            for (int i = 0; i < size; i++) {
                
                int[] p1 = group.get(i);

                for (int j = i + 1; j < size; j++) {
                
                    int[] p2 = group.get(j);

                    System.out.println(" Checking pair: ");
                    System.out.println("    p1 = " + Arrays.toString(p1));
                    System.out.println("    p2 = " + Arrays.toString(p2));


                    // side1 = distance between p1 and p2
                    double side1 = Math.sqrt(distanceSquare(p1, p2));
                    System.out.println("    side1 (p1 to p2) = " + side1);


                    // Compute midpoint manually
                    double midX = (p1[0] + p2[0]) / 2.0;
                    double midY = (p1[1] + p2[1]) / 2.0;
                    System.out.println("    Midpoint = (" + midX + ", " + midY + ")");


                    // side2 = distance from p1 to midpoint (we can compute midpoint manually)
                    double dx = p1[0] - midX;
                    double dy = p1[1] - midY;
                    double side2 = Math.sqrt(dx * dx + dy * dy) * 2; // since midpoint is halfway
                    System.out.println("    side2 (p1 to midpoint ×2) = " + side2);
                    
                    
                    // Get area of rectangle
                    double area = side1 * side2;
                    System.out.println("    Area = " + area);

                    minArea = Math.min(minArea, area);
                    System.out.println("    minArea = " + minArea);
                }
            }
        }


        // if out minArea is still Double.Max_Value 
        // means we can't create rectangle
        if (minArea == Double.MAX_VALUE) {
            
            System.out.println(" This point don't form rectangle");
            return 0;
        }

        return minArea;
    }

    // Helper Function : to get distance square for points
    private int distanceSquare(int[] a, int[] b) {

        // Distance formula :   d(AB) = (x1 - x2)^2 + (y1 - y2)^2

        // let's declare variable name for (x1 - x2) and (y1 - y2)
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        // take square of it 
        int distAB = (dx * dx) + (dy * dy);

        System.out.println("    - Distance of point A" + Arrays.toString(a) + " and B" + Arrays.toString(b) + " : " + distAB);
        
        return distAB;
    }

    public static void main(String[] args) {

        MinAreaFreeRect solution = new MinAreaFreeRect();

        int[][] points1 = {
            {1,2},
            {2,1},
            {1,0},
            {0,1}
        };
        System.out.println(" Result1 -> " + solution.minAreaFreeRect(points1) + "\n");   // 2.00000

        int[][] points2 = {
            {0,1},
            {2,1},
            {1,1},
            {1,0},
            {2,0}
        };
        System.out.println(" Result2 -> " + solution.minAreaFreeRect(points2) + "\n");    // 1.00000

        int[][] points3 = {
            {0,3},
            {1,2},
            {3,1},
            {1,3},
            {2,1}
        };
        System.out.println(" Result3 -> " + solution.minAreaFreeRect(points3) + "\n");    // 0

        int[][] points4 = {
            {3,1},
            {1,1},
            {0,1},
            {2,1},
            {3,3},
            {3,2},
            {0,2},
            {2,3}
        };
        System.out.println(" Result4 -> " + solution.minAreaFreeRect(points4) + "\n");    // 2.0
        
    }

}

/*
 * Intuitions :
 
    1. we have given an array points 
        where points[i] = [x, y]
    2. We need to return the minimum area of any rectangle formed from these points
 
 * Pattern :
    
    1. Brute force approach I'm thinking :
        - get distance between all nums 
        - then create a map and store it's freq 
        - take two distance with lower value
        - multiply them and return 
            why? bcoz area of rectangle is length * breath 

    2. I did lot of browsing and learn the optimal way
        it's same as mine but little different

        - For all pairs of points :
            treat the pair as the ends of diagonal

            Check midPoint = (x1 + x2, y1 + y2)
            check distSq = (x1 - x2)^2 + (y1 - y2)^2

            will store this info in map
                key : string of (midX, midY, distSq)
                value : list of points (diagonal end)
 
        - For each group of diagonals with the same midpoint and length :
            try all combinations of 2 diagonals ends: (p1, p2)

            use those 2 points (p1, p2) and midpoint to compute area:
                side1 = dist between p1 and p2
                side2 = dist from midpoint to either p1 or p2
                area = side1 * side2

        - Keep track of the minimum area found

        - Return 0 if no rectagle, 
            else return the minimum area
 
 
 * Pseudo Code :
 




 */