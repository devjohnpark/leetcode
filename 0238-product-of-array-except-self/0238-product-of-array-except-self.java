class Solution {
    // 문제 정의
    // answer[i]: 자기 자신을 제외한 모든 배열 요소의 곱셈을 반환
    // O(n) 내에 동작: for 문으로 n x n -1 연산을 수행하면 안됨 -> 배열 조회의 시간복잡도인 O(n) 내에 연산 해야한다.

    // 핵심 알고리즘
    // 접근법 부터 다시 생각해야한다.
    // 반복문 1: 출력할 배열에 주어진 배열 요소의 왼쪽의 요소들을 모두 곱한다.
    // 반복문 2: 출력할 배열에 주어진 배열 요소의 오른쪽 요소들을 모두 곱한다.
    // 반복문 1: O(n) + 반복문 2: O(n) => O(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = 1;
        }
        for (int i = 0, left = 1; i < n; i++) {
            answer[i] *= left; // 현재 요소의 왼쪽 요소 누적 곱
            left *= nums[i]; 
        }
        for (int i = n-1, right = 1; i >= 0; i--) {
            answer[i] *= right; // 현재 요소의 오른쪽 요소 누적 곱
            right *= nums[i];
        }
        return answer;
    }
}