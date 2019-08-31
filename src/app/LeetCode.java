package app;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class LeetCode {
    public static void doProblems() {
        // runMergeTwoLists();
        runSearchInsert();
    }

    private static void runSearchInsert() {
        int maxInt = 40;
        int[] array = Utility.generateRandomArray(6, maxInt);
        Arrays.sort(array);

        int x = (int) (Math.random() * maxInt);
        out.println(String.format("%d would be inserted at index=%d in the array: %s", 
            x, searchInsert(array, x), Arrays.toString(array)));
    }

    private static Object searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        
        int i;
        for(i = 0; i < nums.length; i++) {
            if (nums[i] >= target)
                break;
        }
        
        return i;
    }

    private static void runMergeTwoLists() {
        ListNode l1 = Utility.createList(4, 20);
        ListNode l2 = Utility.createList(6, 30);
        l1 = ListNode.sort(l1);
        l2 = ListNode.sort(l2);
        out.println(l1.toString());
        out.println(l2.toString());

        l1 = mergeTwoLists(l1, l2);
        out.println(l1.toString());
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        
        ListNode head; 
        if (l1.value <= l2.value) {
            return mergeTwoLists(l1.next, l2);
        }
        else {
            return mergeTwoLists(l1, l2.next);
        }
    }
}