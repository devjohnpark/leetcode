// 입력크기 30000 -> O(nlogn) 이내
// 주식 사고 팔기 누적 값

// 아직 팔지 않았다면 작은값으로 업데이트
// 자기 보다 큰값이 나오면 판다.
class Solution {
    public int maxProfit(int[] prices) {
        // 순회중 산 값을 지정하고 보다 작은 값 있으면 업데이트
        // 산값 보다 큰값이 있으면 판다. 그리고 판값을 이익에 누적 합
        // 판값을 산값으로 업데이트
        int n = prices.length;
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (buy < prices[i]) {
                profit += prices[i] - buy;
            } 
            buy = prices[i];
        }
        return profit;
    }
}