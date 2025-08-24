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
class MyHashMap {
    private Node[] map;

    private int hash(int key) {
        return key % 1000;
    }

    public MyHashMap() {
        map = new Node[1000];
        for (int i = 0; i < 1000; i++) {
            map[i] = new Node(-1, -1);
        }
    }
    
    public void put(int key, int value) {
        // 키 존재하면 값 없데이트
        // hash 함수로 돌린다.
        // 시작 노드부터 순회해서 key가 있는지 찾는다.
        // 없으면 노드를 생성하고 마지막 노드에 연결시킨다.
        Node cur = map[hash(key)];
        while (cur.next != null) {
            if (key == cur.next.key) {
                cur.next.val = value;
                return;
            }
            cur = cur.next;
        }
        cur.next = new Node(key, value);
    }
    
    public int get(int key) {
        // 맵핑되는 값 없으면 -1 반환
        Node cur = map[hash(key)].next;
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
        Node cur = map[hash(key)];
        while (cur.next != null) {
            if (key == cur.next.key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }
}

// juse use array
// class MyHashMap {
//     private int[] map;

//     public MyHashMap() {
//         map = new int[1000001];
//         Arrays.fill(map, -1); // for 문 보다 속도 빠름
//         // for (int i = 0; i < 1000001; i++) 
//         //     map[i] = -1;
//     }
    
//     public void put(int key, int value) {
//         // 키 존재하면 값 없데이트
//         map[key] = value;
//     }
    
//     public int get(int key) {
//         // 맵핑되는 값 없으면 -1 반환
//         return map[key];
//     }
    
//     public void remove(int key) {
//         // 매핑되는 키 있으면 삭제
//         map[key] = -1;
//     }
// }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */