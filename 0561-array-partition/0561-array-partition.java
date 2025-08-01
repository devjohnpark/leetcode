class Solution {
    // 문제 정의: 두개의 요소를 쌍을 지은 모든 경우의 수에서, 각 쌍의 최소값을 모두 더한 최대값을 구해라.
    // 시간 복잡도: 입력크기가 10000개이므로 시간복잡도는 O(NlogN) 이내이다.
    // 브루트 포스: 브루트 포스로 풀수 있는 방법을 모르겠음. 모든 배열의 요소를 쌍으로 묶을수있는 방법을 모르겠음
    // 핵심 문제 풀이: 부르트 포스 아이디어가 생각나지 않는다면 아이디어가 필요하다. 
    // 모든 쌍의 요소중 최소값의 합이 최대가 되야하므로, 쌍의 차이가 적을수록 최대값이 커진다. 따라서 오름 차순 정렬후에 쌍에서 첫번째 요소를 반복적으로 더한값이 최대값이다.
    // 1. 오름차순으로 정렬한다.
    // 2. 2씩 인덱스를 움직인다.
    // 3. 첫번째 요소가 min 값이다.
    public int arrayPairSum(int[] nums) {
        int n = nums.length;
        int max = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i+=2) {
            max += nums[i];
        } 
        return max;
    }
}