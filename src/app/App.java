package app;

import static java.lang.System.*;

public class App {
    public static void main(String[] args) throws Exception {
        // moderate_16_3();
        
    }

    private static void moderate_16_3() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(0, 10);
        Point p4 = new Point(10, 0);
        
        out.println(doLinesIntersect(p1, p2, p3, p4));
    }

    private static Point doLinesIntersect(Point start1, Point end1, Point start2, Point end2) {
        if (start1.getX() > end1.getX()) swap(start1, end1);
        if (start2.getX() > end2.getX()) swap(start2, end2);
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

    private static void swap(Point start1, Point end1) {
        double tempX = start1.getX();
        double tempY = start1.getY();

        start1.setLocation(end1.getX(), end1.getY());
        end1.setLocation(tempX, tempY);
    }
}