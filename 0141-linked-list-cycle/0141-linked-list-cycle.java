/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 // Using two pointer
 // speed of pointer2 x 2 -> pointer 1 reach pointer 2
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) { return false; }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}