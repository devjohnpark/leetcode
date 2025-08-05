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
   // 문제 정의: 연결리스트를 순회하면서 숫자를 더해서 새로운 연결리스트를 생성
   // 주의점
   // 올림수가 있을경우 기존 두개의 연결리스트보다 노드개수가 많은 연결리스트가 생성될수있다. -> 반복문에서 탈출조건이 두 연결리스트의 이동한 노드가 null이면 탈출이 아니다.
   // 두개의 연결리스트이 개수가 다를 수 있다. -> 긴 연결리스트가 null이 될때까지는 최소 반복해야한다.

   // 연결 순회 반복믄 실행 조건
   // 연결리스트가 하나라도 null이 아니다.
   // 올림수가 존재한다.
   // 따라서 연결리스트가 하나라도 null이 아니거나 올림수가 존재하면 반복한다.

   // 시간 복잡도: O(N)
   // 핵심 문제 풀이 로직
   // 반환할 연결리스트 생성 로직 반복
   // 0. 연결리스트가 하나라도 null이 아니거나 올림수가 존재하면 반복한다.
   // 1. A, B 연결리스트를 순회하면서 두개의 수와 올림수를 더한 합을 계산한다.
   // 2. 합에서 10을 나눈수를 올림수에 저장한다.  
   // 3. 합에서 10으로 나눈 나머지를 가진 노드를 생성한다.
   // 4. 반환할 연결리스트에 노드를 연결한다.
   // 5. A, B 연결리스트의 순회중인 노드가 null이 아니면 다음 노드로 이동한다.
   // 6. 생성한 연결리스틀르 반환
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            cur.next = new ListNode(digit);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        } 
        return dummy.next;
    }
}