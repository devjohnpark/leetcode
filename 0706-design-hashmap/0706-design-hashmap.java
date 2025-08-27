// HashMap using LinkedList
// 해시 충돌시 이어서 연결리스트에 노드 연결
// 0 -> n, n, n
// 1 -> n, n, n
// 2
class Node {
    int key;
    int val;
    Node next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

// 입력크기: 1000001 
// bucket 1000개이면, 평균 연결리스트에 약 1000개가 저장될수있다.
class MyHashMap {
    private Node[] bucket;

    private int hash(int key) {
        return key % 1000; 
    }

    public MyHashMap() {
        bucket = new Node[1000];
        // head를 가리키는 더미 노드 초기화
        for (int i = 0; i < 1000; i++) {
            bucket[i] = new Node(-1, -1); // 입력 상수가 0이상이므로 -1로 초기화
        }
    }
    
    public void put(int key, int value) {
        // 키 존재하면 값 없데이트
        // hash 함수로 돌린다.
        // 시작 노드부터 순회해서 key가 있는지 찾는다.
        // 없으면 노드를 생성하고 마지막 노드에 연결시킨다.
        Node cur = bucket[hash(key)]; 
        while (cur.next != null) {
            if (key == cur.next.key) { // 삽입할 이전 노드까지 순회
                cur.next.val = value;
                return;
            }
            cur = cur.next;
        }
        cur.next = new Node(key, value); // 마지막 노드에 연결
    }
    
    public int get(int key) {
        // 맵핑되는 값 없으면 -1 반환
        Node cur = bucket[hash(key)].next; // 더미 노드의 다음 노드로 지정
        while (cur != null) {
            if (key == cur.key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        // 매핑되는 키 있으면 삭제
        Node cur = bucket[hash(key)];
        while (cur.next != null) {
            if (key == cur.next.key) { // 삭제할 이전 노드까지 순회
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }
}

// juse use array (is not hashmap)
// class MyHashMap {
//     private int[] bucket;

//     public MyHashMap() {
//         bucket = new int[1000001];
//         Arrays.fill(map, -1); // for 문 보다 속도 빠름
//         // for (int i = 0; i < 1000001; i++) 
//         //     bucket[i] = -1;
//     }
    
//     public void put(int key, int value) {
//         // 키 존재하면 값 없데이트
//         bucket[key] = value;
//     }
    
//     public int get(int key) {
//         // 맵핑되는 값 없으면 -1 반환
//         return bucket[key];
//     }
    
//     public void remove(int key) {
//         // 매핑되는 키 있으면 삭제
//         bucket[key] = -1;
//     }
// }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */