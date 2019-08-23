package app;

public class Factorial {
    int result;
    
    public Factorial(int n) {
        result = calculate(n);
    }

    private int calculate(int n) {
        if (n == 1)
            return 1;
        
        return n * calculate(n - 1);
    }
}