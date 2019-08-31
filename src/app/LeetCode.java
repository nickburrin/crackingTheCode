package app;

import static java.lang.System.*;

class LeetCode {
    public static void doProblems() {
        runMergeTwoLists();
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