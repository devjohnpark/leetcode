class Solution {
    // 문제 정의: 배열을 순회하면서 현재 지정된 배열 요소보다 큰값이 몇 번째 뒤에 위치하는지 저장하는 배열 반환
    // 시간 복잡도: 입력크기가 100000이므로 O(N)내에 끝내야한다.
    
    // 브루트 포스
    // O(N^2)
    // 배열 순회
    // 배열 요소 지정
    // 지정된 요소의 이후 요소중에서 가장 가까운 요소의 인덱스 저장

    // 핵심 문제 풀이
    // 스택에 인덱스를 입출력하면 배열 요소에 대해 위치를 다룰수있으므료, 현재 요소와 스택에 저장된 요소가 몇칸 차이나는지 구할수있다.
    // 지정한 배열 요소보다 스택의 top에 있는 값이 작으면 pop해서 인덱스 가져온다.
    // answer[stack.pop()] = i - stack.pop();
    public int[] dailyTemperatures(int[] temperatures) {
        int size = temperatures.length;
        int answer[] = new int[size];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            } 
            stack.push(i);
        }
        return answer;
    }
}