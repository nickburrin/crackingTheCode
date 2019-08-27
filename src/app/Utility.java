package app;

import java.util.Arrays;
import java.util.List;

public class Utility {
    public Utility() {}

    public static List<Integer> generateRandomList(int count, int maxNum) {
        List<Integer> list = Arrays.asList(new Integer[count]);

        for (int i = 0; i < count; i++) {
            list.set(i, (int) (Math.random() * maxNum));
        }

        return list;
    }
}