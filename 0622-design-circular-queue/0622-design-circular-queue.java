class MyCircularQueue {
    // 모듈러 연산을 통해 인덱스 front와 rear 관리
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    private int[] data;

    public MyCircularQueue(int k) {
        data = new int[k];
    }
    
    public boolean enQueue(int value) {
        if (size == data.length) return false;
        rear = (rear + 1) % data.length;
        data[rear] = value;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if (size == 0) return false;
        front = (front + 1) % data.length;
        size--;
        return true;
    }
    
    public int Front() {
        return size == 0 ? -1 : data[front];
    }
    
    public int Rear() {
        return size == 0 ? -1 : data[rear];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == data.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */