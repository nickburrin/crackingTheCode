package app;

import java.util.Stack;

public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public static ListNode createdLinkedListNum(int maxDigits) {
        ListNode head = new ListNode((int) (Math.random() * 10));

        ListNode prev = head;
        for (int i = 1; i < maxDigits; i++) {
            prev.next = new ListNode((int) (Math.random() * 10));
            prev = prev.next;
        }

        return head;
    }

    public static ListNode sort(ListNode head) {
        if (head == null)
            return null;
        
        Stack<ListNode> unsorted = new Stack<ListNode>();
        Stack<ListNode> sorted = new Stack<ListNode>();

        while (head != null) {
            unsorted.push(head);
            head = head.next;
        }
        
        ListNode x;
        while (unsorted.size() > 0) {
            x = unsorted.pop();
            while (sorted.size() > 0 && x.value > sorted.peek().value)
                unsorted.push(sorted.pop());

            sorted.push(x);
        }

        x = new ListNode(sorted.pop().value);
        head = x;
        while (sorted.size() > 0) {
            x.next = new ListNode(sorted.pop().value);
            x = x.next;
        }

        return head;
    }

    @Override
    public String toString() {
        ListNode node = this;
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append(node.value).append(" ");
            node = node.next;
        }

        return builder.toString();
    }

    public String toStringReverse() {
        ListNode node = this;
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append(node.value);
            node = node.next;
        }

        return builder.reverse().toString();
    }
}