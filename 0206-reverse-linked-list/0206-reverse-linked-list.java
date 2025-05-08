/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null; 
        while(cur != null) {
            // 1. 다음 노드를 이전 노드로 연결
            // 2. 이전 노드 저장
            // 3. 다음 노드 이동 
            ListNode tmpNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmpNext;
        }
        return prev;
    }
}