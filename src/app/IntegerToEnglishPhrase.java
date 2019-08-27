package app;

import java.util.HashMap;
import java.util.Map;

class IntegerToEnglishPhrase {
    private static final Map<Integer, String> singleDigit = new HashMap<Integer, String>(){
        {
            put(1, "one");
            put(2, "two");
            put(3, "three");
            put(4, "four");
            put(5, "five");
            put(6, "six");
            put(7, "seven");
            put(8, "eight");
            put(9, "nine");
        }
    };

    private static final Map<Integer, String> doubleDigit = new HashMap<Integer, String>(){
        {
            put(10, "ten");
            put(11, "eleven");
            put(12, "twelve");
            put(13, "thirteen");
            put(14, "fourteen");
            put(15, "fifteen");
            put(16, "sixteen");
            put(17, "seventeen");
            put(18, "eighteen");
            put(19, "nineteen");
            put(20, "twenty");
            put(30, "thirty");
            put(40, "forty");
            put(50, "fifty");
            put(60, "sixty");
            put(70, "seventy");
            put(80, "eighty");
            put(90, "ninety");
        }
    };

    private static final Map<Integer, String> rollingCounterMap = new HashMap<Integer, String>(){
        {
            put(0, "");
            put(1, "thousand");
            put(2, "million");
            put(3, "billion");
        }
    };    
    
    int integer;


    public IntegerToEnglishPhrase(int n) {
        this.integer = n;
    }

    public String toString() {
        String builder = new String();
        boolean isNegative = this.integer < 0;
        int rollingCounter = 0;
        int num = this.integer;
        while (num > 0) {
            int next3Digits = num % 1000;
            
            String comma;
            if (rollingCounter > 0) 
                comma = rollingCounterMap.get(rollingCounter) + ", ";
            else
                comma = "";
            
            builder = intToEnglish(next3Digits) + " " + comma + builder;
            
            // Add comma if there is more
            num -= next3Digits;
            num /= 1000;
            rollingCounter++;
        }

        if (isNegative)
            builder = "Negative " + builder;

        return builder.toString();
    }

    private String intToEnglish(int next3Digits) {
        int tens = next3Digits % 100;
        int hundreds = next3Digits / 100;

        StringBuilder builder = new StringBuilder();
        if (hundreds >= 1) {
            builder.append(singleDigit.get(hundreds)).append(" ").append("hundred");
        }

        if (tens > 9 && tens < 20) {
            builder.append(" ").append(doubleDigit.get(tens));
        } else {
            int ones = tens % 10;
            tens -= ones;
            
            if (tens > 20) {
                builder.append(" ").append(doubleDigit.get(tens));
                if (ones != 0)
                    builder.append("-");
            }
            
            if (ones != 0)
                builder.append(singleDigit.get(ones));
        }

        return builder.toString();
    }
}