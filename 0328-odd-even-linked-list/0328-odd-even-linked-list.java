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
 // 문제 정의: 짝수 인덱스인 노드를 연결하고 홀수 인덱스인 노드를 연결
 // 시간 복잡도: O(N)
 // 공간 복잡도: O(1)
 // 문제 풀이
 // 1. 
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (odd.next != null && even.next != null) {
            ListNode oddNext = odd.next.next;
            ListNode evenNext = even.next.next; 
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = oddNext;
            even = evenNext;
        }
        odd.next = evenHead;
        return head;
    }
}