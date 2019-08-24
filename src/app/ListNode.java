package app;

public class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public void createdLinkedListNum(int maxDigits) {
        this.value = (int) (Math.random() * 10);

        ListNode prev = this;
        for (int i = 1; i < maxDigits; i++) {
            prev.next = new ListNode((int) (Math.random() * 10));
            prev = prev.next;
        }
    }

    @Override
    public String toString() {
        ListNode node = this;
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append(node.value);
            node = node.next;
        }

        return builder.reverse().toString();
    }
}