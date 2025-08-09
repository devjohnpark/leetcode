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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode(-1);
        ListNode cur = list; 

        while(list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next; // 다음 연결할 노드 설정
        }

        // 남은 연결 리스트 병합
        if (list1 != null) {
            cur.next = list1;
        } else if (list2 != null) {
            cur.next = list2;
        }

        return list.next; 
    }

    // recursive version
    // public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    //     if (list1 == null) return list2;
    //     if (list2 == null) return list1;

    //     if (list1.val < list2.val) {
    //         list1.next = mergeTwoLists(list1.next, list2);
    //         return list1;
    //     } else {
    //         list2.next = mergeTwoLists(list2.next, list1);
    //         return list2;
    //     } 
    // }
}