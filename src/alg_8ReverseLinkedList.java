/**
 * LeetCode 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * <p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class alg_8ReverseLinkedList {

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
        System.out.println("Reverse Linked List: " + reverseList(listNode).toString());

        listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        System.out.println("Reverse Linked List: " + reverseList(listNode).toString());

        System.out.println("Reverse Linked List: " + reverseList(null));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = new ListNode(head.val);
        ListNode currentNode = head.next;
        while (currentNode != null) {
            ListNode headNode = new ListNode(currentNode.val);
            headNode.next = result;
            result = headNode;
            currentNode =  currentNode.next;
        }
        return result;
    }
}
