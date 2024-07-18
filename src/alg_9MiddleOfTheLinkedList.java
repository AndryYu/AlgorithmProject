/**
 * LeetCode 876. 链表的中间结点
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 */
public class alg_9MiddleOfTheLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            buffer.append(val);
            ListNode currentNode = next;
            while (currentNode != null) {
                buffer.append(",").append(currentNode.val);
                currentNode = currentNode.next;
            }
            return buffer.toString();
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println("Reverse Linked List: " + middleNode(listNode).toString());

        listNode.next.next.next.next.next = new ListNode(6);
        System.out.println("Reverse Linked List: " + middleNode(listNode).toString());
    }


    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int size = 1;
        ListNode result = head;
        while (result.next != null) {
            ++size;
            result = result.next;
        }

        result = head;
        int index = size /2;
        while (index > 0) {
            result = result.next;
            --index;
        }
        return result;
    }

}
