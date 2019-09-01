package app;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class LeetCode {
    public static void doProblems() {
        // runMergeTwoLists();
        // runSearchInsert();
        // runLongestCommonPrefix();
        // runValidParentheses();
        // runStrStr();
        // runMaxSubarray();
        runCountAndSay();
    }

    private static void runCountAndSay() {
        int n = 5;

        out.println(String.format("The %dth iteration of the count-and-say sequence is %s", 
            n, countAndSay(n, "1")));
    }

    private static String countAndSay(int n, String say) {
        if (n == 0) return "";
        if (n == 1) return say;

        int count = 0;
        char last = say.charAt(0);
        StringBuilder builder = new StringBuilder();
        
        for(char c: say.toCharArray()) {
            if (c == last) {
                count++;
            } else {
                builder.append(count).append(last);
                last = c;
                count = 1;
            }
        }
        builder.append(count).append(last);

        return countAndSay(--n, builder.toString());
    }

    private static void runMaxSubarray() {
        int[] array = Utility.generateRandomArray(5, 20, true);

        out.println(String.format("The max sum of a subarray is %d in the array=%s", 
            maxSubArray(array), Arrays.toString(array)));
    }

    private static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] maxSumToEnd = new int[nums.length]; // memo(nums);
        int max = nums[nums.length - 1];
        maxSumToEnd[nums.length - 1] = max;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            maxSumToEnd[i] = Math.max(nums[i], nums[i] + maxSumToEnd[i+1]);
            max = Math.max(max, maxSumToEnd[i]);
        }

        return max;
    }

    private static void runStrStr() {
        String search = "hello";
        String find = "lo";
        
        out.println(String.format("\"%s\" found within \"%s\" at index=%d", 
            find, search, strStr(search, find)));
    }

    private static Object strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;

        return haystack.indexOf(needle);
    }

    private static void runValidParentheses() {
        String test1 = "()";
        String test2 = "()[]{}";
        String test3 = "(]";
        String test4 = "([)]";
        String test5 = "{[]}";

        out.println(validParentheses(test1));
        out.println(validParentheses(test2));
        out.println(validParentheses(test3));
        out.println(validParentheses(test4));
        out.println(validParentheses(test5));
    }

    private static boolean validParentheses(String s) {
        if (s.length() == 0) return true;
        
        final List<Character> openSet = Arrays.asList(new Character[]{'(', '[', '{'});
        final List<Character> closeSet = Arrays.asList(new Character[]{')', ']', '}'});
        Stack<Character> stack = new Stack<Character>();

        for (Character c : s.toCharArray()) {
            if (openSet.contains(c)) {
                stack.push(c);
            } else if(closeSet.contains(c)) {
                if (stack.isEmpty() || !isSameType(stack.pop(), c))
                    return false;
            }
        }

        return stack.isEmpty() ? true : false;
    }

    private static boolean isSameType(Character open, Character close) {
        if (open == '[' && close == ']')
            return true;
        if (open == '{' && close == '}')
            return true;
        if (open == '(' && close == ')')
            return true;

        return false;
    }

    private static void runLongestCommonPrefix() {
        String[] strings = new String[] {"flower","flow","flight"};

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
            int prefixHash = prefix.hashCode();

            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i || strs[j].substring(0, i).hashCode() != prefixHash)
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