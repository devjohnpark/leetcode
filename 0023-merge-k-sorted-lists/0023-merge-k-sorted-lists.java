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
    // 문제정의: 주어진 오름차순 연결리스트를 오름차순 리스트 하나로 병합해라.
    // 시간복잡도: 10000 -> O(nlogn) 이내

    // 부루트포스 문제 풀이
    // 리스트들을 모두 순회하면서 순차적 병합 
    // for(0~k) x (for(0~n₁) + for(0~n₂) + for(0~nₖ)): O(kn)

    // 핵심 문제 풀이
    // 1. 배열이 아니라 연결리스트이므로 병합 정렬로 풀수있다. 하지만 연결리스트가 여러개이므로 코드 작성이 어렵다.
    // 2. 주어진 연결리스트가 정렬되어있는것이 힌트가 있을것이다.
    // 3. 이미 정렬되어있으므로 주어진 연결리스트들의 순회중 현재 지정된 노드의 값만 비교하면된다.
    // 4. 각 연결리스트의 head 부터 시작해서 가장 작은 값진 노드를 결과값 연결리스트에 추가한다. 그리고 해당 연결리스트에서 순회중인 가장 작은값을 가진 다음 노드로 번경한다.
    // 5. 이때, 각 연결리스트를 순회중인 노드를 비교할려면 O(k)가 걸린다.
    // 6. 순회중이 노드 값 비교인 O(k)에 대한 시간 복잡도를 줄어야한다. 각 연결리스트의 개수는 모두 순회해야하므로 O(N)은 고정이다.
    // 7. 그러면 k개의 값에서 최소값을 찾는데 O(k)보다 낮은 알고리즘을 갖는 최소힙을 사용할수있다.
    // 8. 자바에서는 PriorityQueue를 사용해서 최소힙을 구현체를 만들수있다.
    // 9. 배열 기반 최소/최대힙 PriorityQueue를 사용할수있다. 
    // 10. 최소힙은 삽입과 삭제 O(logn) 연산이 걸린다. 따라서 k개의 값을 삽입/삭제시 O(logk)가 걸린다. 이를 통해 O(Nlogk) 시간복잡도가 걸릴수있다.
    public ListNode mergeKLists(ListNode[] lists) {
        // 리스트들의 개수가 0개거나 null이면 null 반환
        if (lists == null || lists.length == 0) return null;
        
        // Input: lists = []
        // ListNode[] lists = new ListNode[0];

        // Input: lists = [[]]
        // ListNode[] lists = { null }; 
        // ListNode[] lists = new ListNode[1];
        
        // 우선순위 큐를 최소값으로 정렬하도록 생성
        // a.val < b.val 이면 음수 → a가 b보다 먼저(우선순위 높음) → 작은 값이 루트로 감
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val); 

        // 우선순위 큐에 연결리스트들의 head 삽입
        for (ListNode head: lists) {
            if (head != null) minHeap.add(head);
        }

        // 결과값 저장할 연결리스트
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        // 최소힙의 루트 노드 삭제후, 삭제한 다음 노드 삽입
        // 우선순위큐가 빌때까지 반복
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.remove();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minHeap.add(node.next);
            }
        }
        return dummy.next;
    }
}