class Solution {
    // 정수 범위의 오름차순으로 정렬되어 있따.
    // 절대값 기준 양끝쪽에 가장 큰값이 있다.
    // 따라서 양끝 투 포인터에서 절대값 기준이 크면, 제곱값을 새로운 배열의 맨끝 값으로 삽입한다.
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int pos = 0;
        int left = 0;
        int right = n-1;
        for (int i=n-1;i>=0; i--) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                result[i] = nums[right] * nums[right];
                right--;
            } else {
                result[i] = nums[left] * nums[left];
                left++;
            }
        }
        return result;
    }
}