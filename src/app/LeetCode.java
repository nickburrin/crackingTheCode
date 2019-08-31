package app;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class LeetCode {
    public static void doProblems() {
        // runMergeTwoLists();
        // runSearchInsert();
        runLongestCommonPrefix();
    }

    private static void runLongestCommonPrefix() {
        String[] strings = new String[] {"c", "bcc", "abc"};

        out.println(String.format("Longest common prefix is \"%s\"", longestCommonPrefix(strings)));
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        
        int i = strs[0].length();
        String prefix = strs[0].substring(0, i);

        while (i > 0) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i || !strs[j].substring(0, i).equals(prefix))
                    break;

                if (j == strs.length - 1)
                    return prefix;
            }
            i--;
            prefix = strs[0].substring(0, i);
        }

        return "";
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
        
        int left = 0;
        int right = nums.length;
        int mid;
        while (right - left > 1) {
            mid = left + (right - left)/2;

            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                right = mid;
            else
                left = mid+1;
        }

        return left;
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