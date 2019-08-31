package app;

import java.lang.System.*;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // doTextBookProblems();
        LeetCode.doProblems();
    }

    private static void doTextBookProblems() {
        // moderate_16_3();
        // moderate_16_5();
        // addTwoLinkedLists();
        // amazonInterview();
        // moderate_16_6();
        // moderate_16_8();
        // moderate_16_9();
        // moderate_16_10();
        // moderate_16_11();
    }

    private static void moderate_16_11() {
        int k = 3;
        HashSet<Integer> lengths = new HashSet<Integer>(k);
        HashSet<String> visited = new HashSet<String>(k);
        int shorter = 1;
        int longer = 5;

        // generateAllLengths_recursive(k, shorter, longer, lengths, visited, 0);
        generateAllLengths_iterative(k, shorter, longer, lengths);
        out.println(String.format("All of the possible lengths using %d boards of either length=%d or length=%d are: %s", 
            k, shorter, longer, lengths.toString()));
    }

    private static void generateAllLengths_iterative(int k, int shorter, int longer, HashSet<Integer> lengths) {
        for (int numShort = 0; numShort <= k; numShort++) {
            int length = numShort*shorter + (k-numShort)*longer;
            lengths.add(length);
        }
    }

    private static void generateAllLengths_recursive(int k, int shorter, int longer, HashSet<Integer> lengths,
            HashSet<String> visited, int length) {
        if (k == 0) {
            lengths.add(length);
            return;
        }

        String key = k + " " + length;

        if (visited.contains(key))
            return;

        generateAllLengths_recursive(k-1, shorter, longer, lengths, visited, length + shorter);
        generateAllLengths_recursive(k-1, shorter, longer, lengths, visited, length + longer);

        visited.add(key);
    }

    private static void moderate_16_10() {
        int begin = 1900;
        int end = 2000;
        int n = 5;

        List<Person> people = createRandomPeople(n, begin, end);

        int maxAliveYear = maxAliveYear(people, begin, end);
        out.println(String.format("The most people were alive in year: %d", maxAliveYear));
    }

    private static int maxAliveYear(List<Person> people, int begin, int end) {
        int count = people.size();
        int[] births = new int[count];
        int[] deaths = new int[count];
        initArrays(people, births, deaths);
        out.println("Birth years: " + Arrays.toString(births));
        out.println("Death years: " + Arrays.toString(deaths));

        int year = begin;
        int alive = 0;
        int maxAlive = 0;
        int maxYear = 0;
        int birthIndex = 0;
        int deathIndex = 0;
        while (birthIndex < count) {
            if (births[birthIndex] <= deaths[deathIndex]) {
                alive++;
                if (alive > maxAlive) {
                    maxAlive = alive;
                    maxYear = births[birthIndex];
                }
                birthIndex++;
            } else if (deaths[deathIndex] < births[birthIndex]) {
                alive--;
                deathIndex++;
            }
        }

        return maxYear;
    }

    private static void initArrays(List<Person> people, int[] births, int[] deaths) {
        int n = people.size();
        List<Integer> tempBirths = new ArrayList<Integer>(n);
        List<Integer> tempDeaths = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) {
            tempBirths.add(people.get(i).birth);
            tempDeaths.add(people.get(i).death);
        }

        Collections.sort(tempBirths);
        Collections.sort(tempDeaths);

        for (int i = 0; i < n; i++) {
            births[i] = tempBirths.get(i);
            deaths[i] = tempDeaths.get(i);
        }
    }

    private static List<Person> createRandomPeople(int n, int begin, int end) {
        List<Person> list = new ArrayList<Person>(n);
        while (n > 0) {
            list.add(Person.create(begin, end));
            n--;
        }

        return list;
    }

    private static void moderate_16_9() {
        int a = -5;
        int b = -6;

        out.println(String.format("%d * %d = %d", a, b, Operators.multiply(a, b)));
        out.println(String.format("%d - %d = %d", a, b, Operators.subtract(a, b)));
        out.println(String.format("%d / %d = %d", a, b, Operators.divide(a, b)));
    }

    private static void moderate_16_8() {
        int n = 1000000;
        IntegerToEnglishPhrase integerPhrase = new IntegerToEnglishPhrase(n);
        System.out.println(String.format("The Integer %d is spoken: %s", n, integerPhrase.toString()));
    }

    private static void addTwoLinkedLists() {
        int maxDigits = 4;
        ListNode node1 = new ListNode(0);
        node1.createdLinkedListNum(maxDigits);
        ListNode node2 = new ListNode(0);
        node2.createdLinkedListNum(maxDigits);

        ListNode resultHead = addTwoNumbers(node1, node2);
        out.println(String.format("Sum of %s + %s = %s", node1.toString(), node2.toString(), resultHead.toString()));
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.value : 0;
            int y = (q != null) ? q.value : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    private static void moderate_16_6() {
        List<Integer> list1 = Utility.generateRandomList(2, 20);
        List<Integer> list2 = Utility.generateRandomList(2, 20);

        out.println("List 1: " + list1.toString());
        out.println("List 2: " + list2.toString());
        out.println(String.format("The smallest positive difference in the arrays is %d", findSmallestDifference(list1, list2)));
    }

    private static int findSmallestDifference(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        int a = 0;
        int b = 0;
        int minDiff = Integer.MAX_VALUE;
        while (a < list1.size() && b < list2.size()) {
            int diff = Math.abs(list1.get(a) - list2.get(b));
            minDiff = (diff < minDiff) ? diff : minDiff;

            if (list1.get(a) > list2.get(b)) b++;
            else if (list1.get(a) < list2.get(b)) a++;
            else return minDiff;
        }
        
        return minDiff;
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
        out.println(
                String.format("There are %d trailing zero's in %d! = %d.", factorialZeros_optimize(n), n, fact.result));
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
        if (start1.getX() > end1.getX())
            Point.swap(start1, end1);
        if (start2.getX() > end2.getX())
            Point.swap(start2, end2);
        if (start1.getX() > start2.getX()) {
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