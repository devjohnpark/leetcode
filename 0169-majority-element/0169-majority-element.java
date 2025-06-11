class Solution {
    // 2 2 1 0 1 1 2 2
    // 0 1 1 1 2 2 2 2

    // 2 2 1 1 1 2 2
    // 1 1 1 2 2 2 2

    // majority가 ⌊n / 2⌋ 이상 저장되어있다는것이 힌트
    public int majorityElement(int[] nums) {
        Arrays.sort(nums); // aver: O(n log n), worst: O(n²)
        return nums[nums.length/2];
    }

    // public int majorityElement(int[] nums) {
    //     int n = nums.length;
    //     Map<Integer, Integer> map = new HashMap<>();
        
    //     for (int i = 0; i < n; i++) {
    //         map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    //     }
        
    //     n = n / 2;
    //     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //         if (entry.getValue() > n) {
    //             return entry.getKey();
    //         }
    //     }
        
    //     return 0;
    // }
}