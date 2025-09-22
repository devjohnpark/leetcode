// 시간 복잡도 고려
// O(nlogn) 이하
// 힌트: 분할 정복으로는 O(n)으로 끝낼수 있음 -> 따로 연습

// 문제 풀이
// O(n) 풀이
// 배열을 순회한다.
// 총합이 0이상 이면 계속더한다.
// 만일 총합이 0보다 작으면 0으로 업데이트한다.
// 총합에 배열 요소를 더한다.
// 총합이 최대값 보다 크면 최대값을 업데이트한다.
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int n = nums.length;
        int max = nums[0];
        for (int i = 0; i < n; i++) {
            if (sum < 0) sum = 0;
            sum += nums[i];
            max = Math.max(sum, max);
        }
        return max;
    }
}
