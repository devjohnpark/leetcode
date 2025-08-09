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


// 1 -> 2 -> 2 -> 1
// 1. 반복문
// 중간 노드를 찾으며 리스트 역순 정렬
// 1) 중간 노드 찾기: 속도 2배 차이나는 포인터를 사용 (2칸씩 이동하는 포인터와 1칸씩 이동하는 포인터)
// 2) 리스트 역순 정렬: 중간 노드까지 역순 정렬 (1 <- 2, 2 -> 1)

// 2. 반목분
// 중간 노드 부터 혹은 중간 노드 빼고, 값 동일한지 비교한 후에 다르다면 false 반환
class Solution {
    public boolean isPalindrome(ListNode head) {    
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            // slow 역순 연결
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // 1 2 3 2 1
        // 노드의 개수가 홀수시 fast != null이며, slow 인덱스를 3번에서 2번으로 이동시킨다.
        if (fast != null) {
            slow = slow.next; // move next node
        }

        // 1 2 2 1
        // slow 2 -> 1
        // prev 2 -> 1
        while(prev != null && slow != null) {
            if (prev.val != slow.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }
}