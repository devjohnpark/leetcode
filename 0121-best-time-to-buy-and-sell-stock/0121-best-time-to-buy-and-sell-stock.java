class Solution {
    // 문제 정의: 배열의 순회하면서 가장 작은값과 최대 값의 차이를 구해라. 만일, 0보다 작다면 0을 반환해라.
    // 시간복잡도: 입력 크기가 100000 이므로 O(N)이내에 풀어야한다.
    // 부루트포스: 배열 요소를 하나 지정하고 나머지 배열 요소를 순회하는 방식으로 O(N^2) 내에 풀수있다.
    // 핵심 문제 풀이: 
    // 1. 배열을 순회하면서 이익 최대값 업데이트
    // 2. 배열을 순회하면서 요소값중 최솟값 업데이트
    // 3. 이익 최댓값 반환
    public int maxProfit(int[] prices) {
        int maxProfit = 0; 
        int min = prices[0];
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            maxProfit = Math.max(prices[i] - min, maxProfit); 
            min = Math.min(prices[i], min);
        }
        return maxProfit;
    }
}