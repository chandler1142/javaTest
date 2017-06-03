package alg;

import java.util.Stack;

/**
 * Created by i325622 on 5/15/17.
 */
public class ListNodeTest {

    public void addNewNode(ListNode head, int value) {
        ListNode node = new ListNode();
        node.value = value;

        if(head == null) {
            head = node;
        } else {
            ListNode cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    public void removeNode(ListNode head, int value) {
        if(head == null) {
            return;
        }

        ListNode toBeDel = null;

        if(head.value == value) {
            toBeDel = head;
            head = head.next;
        } else {
            ListNode cur = head;
            while(cur.next != null && cur.next.value != value){
                cur = cur.next;
            }

            if(cur.next != null && cur.next.value == value) {
                toBeDel = cur.next;
                cur.next = cur.next.next;
            }

            if(toBeDel != null) {
                toBeDel = null;
            }
        }
    }

    public void reversePrintLinkList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.value);
            cur = cur.next;
        }

        while(stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void reversePrintLinkList2(ListNode head) {
        if(head == null) {
            return;
        }

        if(head.next == null) {
            System.out.println(head.value);
        } else {
            reversePrintLinkList2(head.next);
        }
    }

}
