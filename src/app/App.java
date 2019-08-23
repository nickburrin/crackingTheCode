package app;

import static java.lang.System.*;

public class App {
    public static void main(String[] args) throws Exception {
        // moderate_16_3();
        moderate_16_5();
        
    }

    private static void moderate_16_5() {
        int n = 10;
        Factorial fact = new Factorial(n);
        out.println(String.format("There are %d zero's in %d! = %d.", factorialZeros(fact.result), n, fact.result));
    }

    private static int factorialZeros(int n) {
        if (n == 0)
            return 0;
        
        int count = 0;

        while (n % 10 == 0) {
            count++;
            n /= 10;
        }

        return count;
    }

    private static void moderate_16_3() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(0, 10);
        Point p4 = new Point(10, 0);
        
        out.println(doLinesIntersect(p1, p2, p3, p4));
    }

    private static Point doLinesIntersect(Point start1, Point end1, Point start2, Point end2) {
        if (start1.getX() > end1.getX()) Point.swap(start1, end1);
        if (start2.getX() > end2.getX()) Point.swap(start2, end2);
        if(start1.getX() > start2.getX()) {
            Point.swap(start1, start2);
            Point.swap(end1, end2);
        }
        
        Equation eq1 = new Equation(start1, end1);
        Equation eq2 = new Equation(start2, end2);
        System.out.println("New equation: " + eq1.toString());
        System.out.println("New equation: " + eq2.toString());
        
        if (eq1.slope == eq2.slope) {
            if (eq1.yIntercept == eq2.yIntercept && start2.isBetween(start1, end1)) {
                return start2;
            }
            return null;
        }

        double x = eq1.calcXIntersection(eq2);
        double y = x * eq1.slope + eq1.yIntercept;
        Point intersection = new Point(x, y);
        out.println("Found intersection @ point: " + intersection);

        if (intersection.isBetween(start1, end1) && intersection.isBetween(start2, end2)) {
            return intersection;
        }
        
        return null;
    }
}