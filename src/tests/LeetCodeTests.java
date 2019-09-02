package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import app.*;

public class LeetCodeTests {

    @Test
    public void shouldAnswerWithTrue() {
        assert(true);
    }

    @Test
    public void testMergeSortedLists() {
        int m = 4;
        int n = 4;
        
        int[] sorted1 = Utility.generateRandomArray(m, 10, true);
        int[] sorted2 = Utility.generateRandomArray(n, 15, true);
        Arrays.sort(sorted1);
        Arrays.sort(sorted2);
        int[] expected = Utility.arrayConcat(sorted1, sorted2);
        int[] arrayOfZeros = Utility.generateZeroArray(n);
        int[] sorted1_withZeros = Utility.arrayConcat(sorted1, arrayOfZeros);
        Arrays.sort(expected);

        assertArrayEquals(expected, LeetCode.mergeSortedArrays(sorted1_withZeros, m, sorted2, n));
    }

    @Test
    public void testClimbStairs() {
        assertEquals(5, LeetCode.climbStairs(4));
    }

    @Test
    public void testCountAndSay(){
        String initial = "1";
        assertEquals("1", LeetCode.countAndSay(1, initial));
        assertEquals("11", LeetCode.countAndSay(2, initial));
        assertEquals("21", LeetCode.countAndSay(3, initial));
        assertEquals("1211", LeetCode.countAndSay(4, initial));
        assertEquals("111221", LeetCode.countAndSay(5, initial));
    }

    @Test
    public void testMergeTwoLists() {
        ListNode l1 = Utility.createList(4, 20);
        ListNode l2 = Utility.createList(6, 30);
        l1 = ListNode.sort(l1);
        l2 = ListNode.sort(l2);

        ListNode head = LeetCode.mergeTwoLists(l1, l2);
        assertThat(head, isInAscendingOrder());
    }

    private Matcher<? super ListNode> isInAscendingOrder()
    {
        return new TypeSafeMatcher<ListNode>()
        {
            @Override
            public void describeTo (Description description)
            {
                description.appendText("ListNode's are not in ascending order");
            }

            @Override
            protected boolean matchesSafely(ListNode head)
            {
                while (head != null) {
                    if (head.next == null) return true;
                    if(head.value > head.next.value) return false;
                    head = head.next;
                }

                return true;
            }
        };
    }

    @Test
    public void testSearchInsert() {
        assertEquals(0, LeetCode.searchInsert(new int[]{}, 10));
        
        int[] arr = new int[]{1, 3, 5, 6};
        assertEquals(2, LeetCode.searchInsert(arr, 5));
        assertEquals(1, LeetCode.searchInsert(arr, 2));
        assertEquals(0, LeetCode.searchInsert(arr, 0));
        assertEquals(4, LeetCode.searchInsert(arr, 10));
    }
    
    @Test
    public void testLongestCommonPrefix() {
        assertEquals("", LeetCode.longestCommonPrefix(new String[]{}));
        assertEquals("a", LeetCode.longestCommonPrefix(new String[]{"a"}));
        assertEquals("abc", LeetCode.longestCommonPrefix(new String[]{"abc"}));
        assertEquals("", LeetCode.longestCommonPrefix(new String[]{"", "hello", "hell"}));

        String[] strings = new String[] {"flower","flow","flight"};
        assertEquals("fl", LeetCode.longestCommonPrefix(strings));
    }
    
    @Test
    public void testValidParentheses_allTrue() {
        assertTrue(LeetCode.validParentheses(""));
        assertTrue(LeetCode.validParentheses("()"));
        assertTrue(LeetCode.validParentheses("{}"));
        assertTrue(LeetCode.validParentheses("()[]{}"));
        assertTrue(LeetCode.validParentheses("([]){{([])}}"));
    }

    @Test
    public void testValidParentheses_allFalse() {
        assertFalse(LeetCode.validParentheses("{{{{"));
        assertFalse(LeetCode.validParentheses("{((}}}"));
        assertFalse(LeetCode.validParentheses("{]}"));
    }
    
    @Test
    public void testStrStr() {
        assertEquals(0, LeetCode.strStr("hello", ""));
        assertEquals(0, LeetCode.strStr("", ""));
        assertEquals(-1, LeetCode.strStr("", "a"));
        
        String search = "hello";
        String find = "lo";
        assertEquals(3, LeetCode.strStr(search, find));
    }
    
    @Test
    public void testMaxSubarray() {
        assertEquals(0, LeetCode.maxSubArray(new int[]{}));
        assertEquals(4, LeetCode.maxSubArray(new int[]{4}));
        assertEquals(-1, LeetCode.maxSubArray(new int[]{-3, -5 ,-10, -1}));
        
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, LeetCode.maxSubArray(array));
    }
}