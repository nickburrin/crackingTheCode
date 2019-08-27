package app;

class Operators {
    public Operators() {}

    public static int multiply(int a, int b) {
        if (b < 0) {
            b = negate(b);
            a = negate(a);
        }

        int sum = 0;
        while (b > 0) {
            sum += a;
            b--;
        }

        return sum;
    }

    public static int subtract(int a, int b) {
        b = negate(b);
        return a + b;
    }

    public static int divide(int a, int b) {
        if (a == 0)
            return 0;
        if (b == 0)
            throw new ArithmeticException("Cannot divide by 0");
        
        boolean isNegativeResult = false;
        if (a < 0) {
            a = negate(a);
            if (b < 0) {
                b = negate(b);
            } else {
                isNegativeResult = true;
            }
        } else if (a > 0) {
            if (b < 0) {
                b = negate(b);
                isNegativeResult = true;
            }
        }

        int count = 0;
        
        while (a > b) {
            a = subtract(a, b);
            count++;
        }

        return isNegativeResult ? negate(count) : count;
    }

    public static int negate(int a) {
        return ~a + 1;
    }
}