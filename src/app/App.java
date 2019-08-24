package app;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // moderate_16_3();
        // moderate_16_5();
        
        amazonInterview();
    }

    private static void amazonInterview() {
        int n = 5;
        double maxPrice = 100;
        List<StockRecord> records = new ArrayList<StockRecord>();
        for (int i = 0; i < n; i++) {
            records.add(StockRecord.generateRandom(i, maxPrice));
        }
        out.println(records);

        StockRecord[] result = bestTimeToBuyAndSell(records);
        out.println(String.format("Bought stock %s and sold at %s for a return of %.2f", 
            result[0], result[1], result[1].price - result[0].price));
    }

    private static StockRecord[] bestTimeToBuyAndSell(List<StockRecord> timeSortedRecords) {
        List<StockRecord> futureMaxRecord = memo(timeSortedRecords);

        double maxReturn = Double.MIN_VALUE;
        StockRecord buy = null;
        StockRecord sell = null;

        for (int i = 0; i < timeSortedRecords.size(); i++) {
            double ret = futureMaxRecord.get(i).price - timeSortedRecords.get(i).price;
            if(ret > maxReturn) {
                maxReturn = ret;
                buy = timeSortedRecords.get(i);
                sell = futureMaxRecord.get(i);
            }
        }

        StockRecord[] buySellTime = new StockRecord[2];
        buySellTime[0] = buy;
        buySellTime[1] = sell;
        return buySellTime;
    }

    private static List<StockRecord> memo(List<StockRecord> timeSortedRecords) {
        int n = timeSortedRecords.size();
        ArrayList<StockRecord> futureMaxes = new ArrayList<StockRecord>(n);
        StockRecord max = timeSortedRecords.get(n-1);

        for (int i = n - 1; i >= 0; i--) {
            if (timeSortedRecords.get(i).price > max.price)
                max = timeSortedRecords.get(i);

            futureMaxes.add(0, max);
        }

        return futureMaxes;
    }

    private static void moderate_16_5() {
        int n = 20;
        Factorial fact = new Factorial(n);
        out.println(String.format("There are %d trailing zero's in %d! = %d.", factorialZeros_optimize(n), n, fact.result));
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

    private static int factorialZeros_optimize(int num) {
        if (num < 0)
            return -1;

        int count = 0;
        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
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