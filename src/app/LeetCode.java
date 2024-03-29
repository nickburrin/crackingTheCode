package app;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LeetCode {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }
    
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 1) return new ListNode(1);

        ListNode ret = null;
        if (l1 != null || l2 != null) {
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);
            
            int result = l1.value + l2.value + carry;
            carry = result / 10;
            ret = new ListNode(result % 10);
            ret.next = addTwoNumbers(l1.next, l2.next, carry);
        }

        return ret;
    }

    public static int[] mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int idx = m+n-1;
        int i = m-1;
        int j = n-1;
        while (i >= 0 && j >= 0) {
            if (nums2[j] > nums1[i]) {
                nums1[idx] = nums2[j];
                j--;
            } else {
                nums1[idx] = nums1[i];
                i--;
            }
            idx--;
        }

        while (idx >= 0 && j >= 0) {
            nums1[idx] = nums2[j];
            j--;
            idx--;
        }

        return nums1;
    }
    
    private static void swap(int[] nums1, int i, int[] nums2, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }

    public static int climbStairs(int steps) {
        if (steps == 0) return 0;
        if (steps == 1) return 1;
        
        int[] stepCount = new int[steps+1];
        stepCount[0] = 1;
        stepCount[1] = 1;
        
        for (int i = 2; i <= steps; i++) {
            stepCount[i] = stepCount[i-1] + stepCount[i-2];
        }

        return stepCount[steps];
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