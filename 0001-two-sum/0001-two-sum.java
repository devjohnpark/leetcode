class Solution {
    // public int[] twoSum(int[] nums, int target) {
    //     int[] arr = new int[nums.length];
    //     O(n^2)
    //     for (int i=0; i<nums.length; i++) {
    //         for (int j=i+1; j<nums.length; j++) {
    //             if (nums[i] + nums[j] == target) {
    //                 return new int[]{i, j}; 
    //             }
    //         }
    //     }
    //     return new int[]{};
    // }

    // using hashmap for O(n)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i=0; i<nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index != null) {
                return new int[]{i, index}; 
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}