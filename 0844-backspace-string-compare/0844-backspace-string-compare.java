// 문자열에서 스캔하다가 #이 나타나면 바로 이전 문자를 삭제하는 것이다.
// 스택에 문자를 push하고 #이 나오면 pop을 해서 # 이전 문자 삭제할수 있다.

// 1. 문자열을 하나씩 스캔한다.
// 2. #을 제외하고 문자를 push한다.
// 3. #이면 문자를 pop해서 제거한다.
// 4. 문자열 스캔이 끝나면 빈 스택이 될때까지 값을 비교하고 불일치하면 곧바로 false를 반환하고, 모드 같으면 true를 반환한다.

class Solution {
    private Stack<Character> doBackspace(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                    stack.pop();
            }
        }
        return stack;
    }

    private boolean isEqual(Stack<Character> stack1, Stack<Character> stack2) {
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public boolean backspaceCompare(String s, String t) {
        return isEqual(doBackspace(s), doBackspace(t));
        
    }   
}