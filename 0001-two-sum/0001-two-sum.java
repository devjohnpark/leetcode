class Solution {
    // 1. 문제 정의
    // 배열의 요소 두개의 합이 target인 인덱스 두개를 반환해라. 단, 답은 하나이다.
    
    // 2. 시간 복잡도
    // 입력크기가 약 10000이므로 O(NlogN) 이내어야한다.

    // 3. 브루트포스로 문제 풀이 도출(문제 해결법 파악)
    // 배열을 순회하면서 하나를 기준으로 잡고 나머지 요소를 순회하면서 합이 target인 요소이면 두개의 인덱스를 반환한다.
    // 두개의 요소를 찾기 위해서 이중 순회를 해야하므로 시간복잡도는 O(N^2)이다.

    // 4. 핵심 문제 풀이 도출(문제 의도 파악): 어떻게 하면 시간복잡도 내에 풀수있을까?
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>(); // 인덱스가 반환할 데이터이므로, 요소 값을 key로 요소 인덱슬르 value로 저장
        for (int i = 0; i < n; i++) {
            // 값을 먼저 출력하고 입력하므로 동일한 인덱스 값의 합은 경우의 수에서 제거
            Integer idx = hashMap.get(target - nums[i]);
            if (idx != null) {
                return new int[]{i, idx};
            }
            hashMap.put(nums[i], i); 
        }
        return new int[]{}; // 문제의 답은 항상 하나 존재
    }
}