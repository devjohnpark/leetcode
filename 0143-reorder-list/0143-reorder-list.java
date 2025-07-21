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
    // 문제: 두합이 n인 노드 쌍을 순차적으로 연결
    // 시간복잡도: 50000 -> O(NlogN)이하
    // 문제 풀이
    // 양쪽 끝 투 포인터 사용
    // 핵심 로직은 중요한건 끝에 노드를 이동시켜서 reorder 해야한다. -> Stack or 중간값부터 Reverse 사용

    // 내가 떠올린 아이디어는 stack
    // stack은 맨 마지막에 넣은 값부터 꺼내서 쓸수있다.
    // 1 2 3 4 5 6 7, 1 7 
    // 1 7 2 3 4 5 6, 2 6
    // 1 7 2 6 3 4 5, 3 5
    // 1 7 2 6 3 5 4

    // reverse는 1 2 3 4 7 6 5 -> 고정된 오른쪽 포인터가 아니라 7 -> 6 ->5 이동시킬수있다. 따라서 오른쪽 포인터의 트래킹이 가능하다.
    // [1 2 3], [6, 5, 4] 분리
    // 첫번째 노드끼리 하나씩 연결
    // 1 → 6 → 2 → 5 → 3 → 4

    // 1. using stack for tracking the last node
    // 왼쪽 노드를 이동시키며 오른쪽 끝 노드를 병합하면서 reorder
    // 오른쪽 끝 노드를 트래킹할려면, reorder 할때마다 매번 right를 맨끝까지 순회해서 찾아야한다. -> 스택을 사용해서 맨끝 노드 트래킹
    // 노드 개수의 반만 슨회해서 병합하면된다. 노드의 개수는 연결리스트를 끝까지 탐색해야알수있으므로 스택의 size()를 사용해서 개수를 안다.

    // 2. using reverse linked list for tracking the right pointer
    // 1 2 3 4 7 6 5 -> reverse -> 고정된 오른쪽 포인터가 아니라 7 -> 6 ->5 이동시킬수있다. 따라서 오른쪽 포인터의 트래킹이 가능하다.
    // 중간 노드를 찾기 위해 2배 빠른 fast 포인터와 1배수 속도인 slow포인터를 사용해서 fast.next가 null일때 slow는 중간 포인터르 가리킴
    // 그다움 reverse를 하고나서 탐색하며 병합한다.

    // right = stack.pop();
    // next = left.next;
    // right.next = left.next;
    // left.next = right;
    // left = next;
    
    // // 1. using stack for tracking the last node
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        // 1. push in stack
        ListNode cur = head; 
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 2. 리스트 노드 개수의 반만 순회하면서 병합
        int size = stack.size();
        ListNode left = head; 
        for (int i = 0; i < size / 2; i++) {
            ListNode right = stack.pop(); // 4, 3
            ListNode next = left.next; // 2, 3
            left.next = right; // 1 -> 4. 2 -> 3
            right.next = next; // 4 -> 2, 3 -> 3
            left = next; // 2, 3
        }

        // 주의: 마지막 노드 처리 (중간 노드의 next를 끊음, 중간노드가 맨 끝으로 감) 
        left.next = null;
    }
}


