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

 // 원형에서 속도 차이를 사용해서 접점을 발생시켜 원형인지 확인 가능
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