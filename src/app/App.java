package app;

import java.lang.System.*;

import static java.lang.System.*;

public class App {
    public static void main(String[] args) throws Exception {
        // moderate_16_3();
        // moderate_16_5();
        addTwoLinkedLists();
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