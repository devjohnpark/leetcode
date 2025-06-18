class Solution {
    // 0 1 0 2 3 4 0 5 -> 1 2 3 4 5 0 0 0

    // O(N)
    // // 0이 아닌값은 배열의 0번 인덱스부터 순차대로 삽입
    // // 나머지 삽입하지 않은 인덱스부터 배열의 맨 끝 인덱스까지 0으로 저장
    // public void moveZeroes(int[] nums) {
    //     int len = nums.length;
    //     int pos = 0;
    //     for (int num: nums) {
    //         if (num != 0) {
    //             nums[pos++] = num;
    //         }
    //     }
    //     while(pos < len) {
    //         nums[pos++] = 0;
    //     }
    // }

    // O(N)
    // 0이 아닌 값이면 새로 저장하는 인덱스의 요소값과 바꿔치기
    public void moveZeroes(int[] nums) {
        int pos = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int tmp = nums[pos]; 
                nums[pos] = nums[i];
                nums[i] = tmp;
                pos++;
            }
        }
    }
    
    // 순차대로 조회
    // 0 발견, 다음 인덱스의 값과 위치 변경

    // // O(n^2)
    // // 1 0 0 2 3 4 0 5
    // // 1 2 0 0 3 4 0 5
    // // 1 2 3 0 0 4 0 5
    // public void moveZeroes(int[] nums) {
    //     int len = nums.length;
    //     // 0 발견
    //     // 다음 인덱스 부터 조회하면서 0이 아닌값 발견하면 변경
    //     for (int i=0; i<len; i++) {
    //         if (nums[i] == 0) {
    //             for (int j=i+1; j<len; j++) {
    //                 if (nums[j] != 0) {
    //                     int tmp = nums[j];
    //                     nums[j] = nums[i];
    //                     nums[i] = tmp; 
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    // }
}