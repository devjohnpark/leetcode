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


// 1 3 3 4 3 3 1

// 1. 반복문
// 리스트 역순 정렬
// 중간 노드 찾기

// 2. 반목분
// 중간 노드 부터 값 동일한지 비교한 후에 다르다면 false 반환
class Solution {
    public boolean isPalindrome(ListNode head) {    
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;

            // 1 2 3 4 3 2 1
            // slow 역순 연결
            // 1. 현재 노드애 연결된 다음 노드를 임시 저장
            // 2. 현재 노드를 이전 노드로 연결
            // 3. 이전 노드를 현재 노드로 업데이트
            // 4. 현재 노드를 임시로 저장한 다음 노드로 업데이트
            ListNode tmp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = tmp;
        }
        // now slow is middle

        // 1 2 3 2 1
        // 홀수시 slow 인덱스는 2번 인덱스를 가리키고 fast != null이다.
        if (fast != null) {
            slow = slow.next; // move next node
        }

        // 1 2 2 1
        // 짝수시 slow 2번 인덱스를 가리키고 fast == null이다.

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