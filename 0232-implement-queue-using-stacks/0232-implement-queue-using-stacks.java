// pop 하기전까지 모든 데이터를 input stack에 push
// pop 하면 output stack의 데이터가 empty가 아닐시 output stack에서 pop해서 반환, output가 비었다면 input stack의 모든 데이터를 pop해서 output stack에 넣고 마지막만 요소만 반환

// [1, 2], []
// [], [2, 1] -> return 1
// [], [2] -> return 1
// [], [2] -> false

class MyQueue {

    private final Stack<Integer> input;
    private final Stack<Integer> output;
 
    public MyQueue() {        
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        // output stack 비었으면, input stack에서 모조리 데이터 가져오기
        inputToOutput();
        // output stack 안비었으면, 그대로 pop()
        return output.pop();
    }
    
    public int peek() {
        inputToOutput();
        return output.peek();
    }

    private void inputToOutput() {
        if (output.isEmpty()) {
            while(!input.isEmpty()) {
                output.push(input.pop());
            }
        }        
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */