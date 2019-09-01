package app;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LeetCode {
    public static int climbStairs(int steps) {
        if (steps == 0) return 1;
        if (steps == 1) return 1;

        return climbStairs(steps-1) + climbStairs(steps-2);
    }
    
    public static String countAndSay(int n, String say) {
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

    public static int maxSubArray(int[] nums) {
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

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;

        return haystack.indexOf(needle);
    }

    public static boolean validParentheses(String s) {
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

    public static String longestCommonPrefix(String[] strs) {
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

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        
        int left = 0;
        int right = nums.length;
        int mid;
        while (right - left > 1) {
            mid = left + (right - left)/2;

            if (nums[mid] > target)
                right = mid;
            else if (nums[mid] < target)
                left = mid;
            else
                return mid;
        }
        
        if (target <= nums[left])
            return left;
        else
            return right;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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