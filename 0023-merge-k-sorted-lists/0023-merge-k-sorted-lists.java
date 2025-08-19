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
    // 시간복잡도: 10000 -> O(nlogn) 이내

    // 부루트포스 문제 풀이
    // 리스트들을 모두 순회하면서 순차적 병합 -> ListNode[] 순회 x 각 리스트 순회 = O(KN)

    // 핵심 문제 풀이
    // 1) 리스트들을 분할 정복의 병합 정렬로 O(klogn)으로 풀수있다.
    // 2) 리스트들의 값을 배열로 저장하고 정렬해서 O(klogn)으로 풀수있다. 그러나 이미 정렬되어있는걸 정렬 알고리즘을 쓰면 손해이다.
    // 3) 각 리스트들의 값이 이미 오름차순으로 정렬되어 있는 것을 이용해서 최소힙을 사용할수있다. 만일, 리스트들이 정렬되어있지 않다면, 우선순위 큐에서 삭제해서 값을 가져와서 결과값 리스트에 추가했을시 정렬이 되어잇지 못한다.
    //    각 리스트의 head를 우선순위 큐(최소힙)에 넣고, 우선순위 큐에서 삭제해서 가져온 노드를 반환할 연결리스트에 추가하고, 삭제된 노드의 다음 노드를 우선순위 큐에 삽입한다.
    //    1. 각 연결리스트의 head 우선순위 큐에 삽입: O(k) x O(logn) = O(klogn)
    //    2. 우선순위 큐에서 노드 삭제후, 삭제한 노드의 다음 노드 우선 순쉬 큐에 삽입: O(logn) + O(logn) 
    //       * 우선순위 큐에서 노드 삭제: O(logn) 
    //       * 삭제한 노드의 다음 노드 우선 순쉬 큐에 삽입: O(logn) 

    public ListNode mergeKLists(ListNode[] lists) {
        // if (lists.length == 0 || lists == null) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val); // 오름차순 정렬
        
        // PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
        //     @Override
        //     public int compare(ListNode o1,ListNode o2){
        //         if (o1.val<o2.val) // 오름 차순
        //             return -1;
        //         else if (o1.val==o2.val)
        //             return 0;
        //         else 
        //             return 1;
        //     }
        // });

        for (ListNode head: lists) {
            if (head != null) pq.offer(head);
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // 현재 k개 중 최소값을 가진 노드 O(log k)
            tail.next = node; // 결과 리스트에 연결
            tail = tail.next; 

            if (node.next != null) {  // 같은 리스트의 다음 노드를 힙에
                pq.offer(node.next); // 각 리스트의 다음 노드 삽입
            }
        }
        return dummy.next;
    }
}