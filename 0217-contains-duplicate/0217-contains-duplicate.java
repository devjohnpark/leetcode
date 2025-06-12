class Solution {
    // Array(O(n^2)): target을 잡고 나머지 배열 요소 조회하면서 동일 값이 있으면 return ture, 없으면 다음 순회할 요소를 조회한 다음 요소로 전환
    // HashSet(O(n)): 값 없으면 put, 있으면 return true
    // Sort(nlog(n)): 요소 카운팅, 요소 달라지면 카운팅 0부터 시작

    // public boolean containsDuplicate(int[] nums) {
    //     int n = nums.length;
    //     for (int i = 0; i < n - 1; i++) {
    //         for (int j = i + 1; j < n; j++) {
    //             if (nums[i] == nums[j])
    //                 return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num: nums) {
            if(seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    // public boolean containsDuplicate(int[] nums) {
    //     Arrays.sort(nums);
    //     int n = nums.length;
    //     for (int i = 1; i < n; i++) {
    //         if (nums[i-1] == nums[i]) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}