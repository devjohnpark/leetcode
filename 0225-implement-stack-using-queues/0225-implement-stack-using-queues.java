class MyStack {

    private Queue<Integer> queue = new LinkedList<>();
    // 맨앞 객체 제거 메서드
    // remove(): 큐가 비어있을때 예외를 던짐
    // poll(): 큐가 비어있을때 예외를 던지지 않고 null 반환
     
    public MyStack() {
        
    }
    
    // Quue는 FIFO 이므로 push 된 값의 이전 모든 값을 remove 후에 add 시켜야한다.
    // 기존: 1 2 3 4 
    // 푸시: 1 2 3 4 5 -> 마지막 값빼고 remove 후 add
    // 변경: 5 4 3 2 1 
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.remove());
        }
    }
    
    public int pop() {
        return queue.remove();
    }
    
    public int top() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */