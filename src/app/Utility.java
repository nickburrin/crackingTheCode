package app;

import java.util.Arrays;
import java.util.List;

public class Utility {
    public Utility() {}

    public static int[] arrayConcat(int[] sorted1, int[] sorted2) {
        int[] both = Arrays.copyOf(sorted1, sorted1.length + sorted2.length);
        System.arraycopy(sorted2, 0, both, sorted1.length, sorted2.length);
        return both;
    }
    
    public static List<Integer> generateRandomList(int count, int maxNum) {
        List<Integer> list = Arrays.asList(new Integer[count]);

        for (int i = 0; i < count; i++)
            list.set(i, (int) (Math.random() * maxNum));

        return list;
    }

    public static int[] generateRandomArray(int count, int maxNum) {
		return generateRandomArray(count, maxNum, false);
    }
    
    public static int[] generateRandomArray(int count, int maxIntAbs, boolean withNegatives) {
        int[] list = new int[count];

        for (int i = 0; i < count; i++) {
            list[i] = (int) (Math.random() * maxIntAbs);
            list[i] *= withNegatives ? Utility.negativeCoinFlip() : 1;
        }

        return list;
    }

	private static int negativeCoinFlip() {
        return Math.random() > 0.5 ? 1 : -1;
    }

    public static ListNode createList(int count, int maxInt) {
        ListNode head = new ListNode((int) (Math.random() * maxInt));
        ListNode curr = head;
        count--;

        for (int i = 0; i < count; i++) {
            curr.next = new ListNode((int) (Math.random() * maxInt));
            curr = curr.next;
        }

        return head;
	}

	public static int[] generateZeroArray(int n) {
        int[] zeros = new int[n];
        for(int i = 0; i < n; i++)
            zeros[i] = 0;

        return zeros;
	}
}