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
    // 문제: 마지막 노드로부터 n번째 노드를 삭제하고 head를 반환해라.
    // 시간복잡도: O(N)
    // 문제 풀이
    // 1. 문제는 삭제하는 메서드가 없다.
    // 2. [1, 2, 3, 4, 5] 스택에 push -> pop을 n번하면 4번째 노드 제거 가능
    // 3. 끝에서 n번재까지 pop 수행후 n+1 번째 노드를 가져와서 next 필드로 n-1번째 노드와 연결
    // 4. 만일 노드의 개수가 n과 동일할 경우에는 head 삭제해야하므로, head = head.next 저장한후에 head.next 반환
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        // 1. 노드의 개수가 n과 동일할 경우에는 head 삭제해야하므로, head = head.next 저장한후에 head.next 반환
        if (n == stack.size()) {
            return head.next; // head를 삭제해야 하는 경우
        }

        // 2. 끝에서 n번재까지 노드까지 pop
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        // 3. 끝에서 n+1 번째 노드를 가져와서 next 필드로 n-1번째 노드와 연결
        ListNode prev = stack.peek();
        prev.next = prev.next.next;

        return head;
    }

}