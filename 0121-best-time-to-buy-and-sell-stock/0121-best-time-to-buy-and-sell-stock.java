class Solution {
    // target: min element
    // compare: array element - min element
    public int maxProfit(int[] prices) {
        int profit = 0;
        int buy = prices[0];
        // 2 4 1 7
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }
}