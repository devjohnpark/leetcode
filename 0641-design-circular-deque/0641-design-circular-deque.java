// insertFront: head -1 이동 
// deleteFront: head +1 이동 
// insertLast: tail +1 이동 
// deleteLast: tail -1 이동 

class MyCircularDeque {
    private final int[] a;
    private final int cap;
    private int head; // front index (유효 원소가 있다면 a[head]가 front)
    private int tail; // rear 다음 칸 (a[(tail-1+cap)%cap]가 rear)
    private int size;

    public MyCircularDeque(int k) {
        this.cap = k;
        this.a = new int[k];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) return false;
        head = (head - 1 + cap) % cap; // 음수 안나오기위해 배열의 최대 크기를 더해서 모둘러 연산 
        a[head] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;
        a[tail] = value;
        tail = (tail + 1) % cap; 
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;
        head = (head + 1) % cap;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;
        tail = (tail - 1 + cap) % cap; 
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return a[head];
    }

    public int getRear() {
        if (isEmpty()) return -1;
        int idx = (tail - 1 + cap) % cap;
        return a[idx];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }
}
