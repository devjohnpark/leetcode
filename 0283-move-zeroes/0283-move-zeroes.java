class Solution {
    // 순차대로 조회
    // 0 발견, 다음 인덱스의 값과 위치 변경

    // 순서 유지과 관건: 인덱스를 순서대로 조회해서 값 바꿔치기
    // 0 1 0 2 3 4 0 5 -> 1 2 3 4 5 0 0 0

    // O(n^2)
    // 1 0 0 2 3 4 0 5
    // 1 2 0 0 3 4 0 5
    // 1 2 3 0 0 4 0 5
    // 1 2 3 


    // 1 0 0 2 3 4 0 5
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        // 0 발견
        // 다음 인덱스 부터 조회하면서 0이 아닌값 발견하면 변경
        for (int i=0; i<len; i++) {
            if (nums[i] == 0) {
                for (int j=i+1; j<len; j++) {
                    if (nums[j] != 0) {
                        int tmp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = tmp; 
                        break;
                    }
                }
            }
        }
    }
}