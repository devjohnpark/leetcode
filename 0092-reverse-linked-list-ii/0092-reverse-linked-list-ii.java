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
    // 1 -> 2 -> 3 -> 4 -> 5
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        // left가 1일 경우 left 이전 노드로 설정이 안됨
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode leftPrev = dummy;
        
        // 1. left 노드 전까지 순회
        for (int i = 0; i < left - 1; i++) {
            leftPrev = leftPrev.next;
        }

        ListNode leftNode = leftPrev.next;
        ListNode cur = leftNode;
        ListNode prev = null; 

        // 2. left 부터 right 노드까지 뒤집기
        for (int i = 0; i < right - left + 1; i++) { 
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next; // 3, 4, 5
        }

        // 3. 재연결
        leftPrev.next = prev; // left 이전 노드와 right 노드 연결 (1 -> 4)
        leftNode.next = cur; // left 노드와 right 이후 노드 연결 (2 -> 5)

        return dummy.next;
    }
}
