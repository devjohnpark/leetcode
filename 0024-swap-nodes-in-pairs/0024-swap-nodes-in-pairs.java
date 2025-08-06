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
    // 1 2 3 4
    // 2 1 3 4
    // 2 1 4 3
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head; // 다음 코드와 같음: ListNode dummy = new ListNode(0, head); 
        ListNode dummy = new ListNode(); // head와의 연결하기 위해 필요한 변수
        ListNode prev = dummy; // dummy -> 2, 1-> 4 연결을 위해 필요한 변수
        ListNode cur = head; // 연결리스트 순회를 위한 변수
        while (cur != null && cur.next != null) {
            ListNode third = cur.next.next;
            ListNode second = cur.next;
            second.next = cur;
            cur.next = third;
            prev.next = second;
            prev = cur;
            cur = third;
        }
        return dummy.next; // 새로 연결된 연결리스트의 head 반환
    }
}