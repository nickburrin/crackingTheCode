package app;

public class Factorial {
    long result;
    
    public Factorial(int n) {
        result = calculate(n);
    }

    private long calculate(int n) {
        if (n == 1)
            return 1;
        
        return n * calculate(n - 1);
    }
}