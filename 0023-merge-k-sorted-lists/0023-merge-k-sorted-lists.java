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
        // 리스트들의 개수가 0개면 그대로 반환
        // if (lists.length == 0) return new ListNode;
        
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
















// class Solution {
//     // 시간복잡도: 10000 -> O(nlogn) 이내
//     // 부루트포스 문제 풀이
//     // 리스트들을 모두 순회하면서 순차적 병합 -> ListNode[] 순회 x 각 리스트 순회 = O(KN)
//     // 핵심 문제 풀이
//     // 1) 리스트들을 분할 정복의 병합 정렬로 O(klogn)으로 풀수있다.
//     // 2) 리스트들의 값을 배열로 저장하고 정렬해서 O(klogn)으로 풀수있다. 그러나 이미 정렬되어있는걸 정렬 알고리즘을 쓰면 손해이다.
//     // 3) 각 리스트들의 값이 이미 오름차순으로 정렬되어 있는 것을 이용해서 최소힙을 사용할수있다. 만일, 리스트들이 정렬되어있지 않다면, 우선순위 큐에서 삭제해서 값을 가져와서 결과값 리스트에 추가했을시 정렬이 되어잇지 못한다.
//     //    각 리스트의 head를 우선순위 큐(최소힙)에 넣고, 우선순위 큐에서 삭제해서 가져온 노드를 반환할 연결리스트에 추가하고, 삭제된 노드의 다음 노드를 우선순위 큐에 삽입한다.
//     //    1. 각 연결리스트의 head 우선순위 큐에 삽입: O(n) x O(logk) = O(nlogk)
//     //    2. 우선순위 큐에서 노드 삭제후, 삭제한 노드의 다음 노드 우선 순쉬 큐에 삽입: O(logn) + O(logn) 
//     //       * 우선순위 큐에서 노드 삭제: O(logk) 
//     //       * 삭제한 노드의 다음 노드 우선 순쉬 큐에 삽입: O(logk) 
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length == 0) return null;

//         PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

//         // PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
//         //     @Override
//         //     public int compare(ListNode o1,ListNode o2){
//         //         if (o1.val<o2.val) // 오름 차순
//         //             return -1;
//         //         else if (o1.val==o2.val)
//         //             return 0;
//         //         else 
//         //             return 1;
//         //     }
//         // });

//         // 각 리스트의 head만 힙에 넣기
//         for (ListNode head : lists) {
//             if (head != null) pq.offer(head);
//         }

//         ListNode dummy = new ListNode(0);
//         ListNode tail = dummy;

//         while (!pq.isEmpty()) {
//             ListNode node = pq.poll();     // 현재 k개 중 최소
//             tail.next = node;              // 결과 리스트에 연결
//             tail = tail.next;

//             if (node.next != null) {       // 같은 리스트의 다음 노드를 힙에
//                 pq.offer(node.next);
//             }
//         }
//         return dummy.next;
//     }
// }