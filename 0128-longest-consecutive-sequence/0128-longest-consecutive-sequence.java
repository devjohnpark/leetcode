class Solution {
    // 문제 정의: 주어진 배열에서 연속된 숫자들의 개수 반환
    // 시간 복잡도: O(n) -> 배열 탐색 시간내에 연속된 숫자를 카운팅 해야한다.

    // 핵심 문제 풀이
    // (1) 해시 테이블에 배열 값 모두 저장: O(n)
    // (2) 해시 테이블의 모든 값을 조회하면서 시작점(연속된 값중에 최소값)인 경우에만 카운팅: O(n)
    // (3) 카운팅한 값중에 가장 큰값을 저장해서 반환한다.

    // edge case: 1 1 1 1 1 2 3 4 5
    public int longestConsecutive(int[] nums) {
       Set<Integer> numSet = new HashSet<>(); 
       for (int num: nums) {
            numSet.add(num);
       }
       int max = 0;
       // 만일, 주어진 배열이 1 1 1 1 1 2 3 4 5이고 for 문을 주어진 배열로 탐색한다면, 배열 중복된 값때문에 1마다 [1,2,3,4]씩 탐색해서 시간 복잡도가 O(N^2)이 나올수있음
       for (int num: numSet) { 
            if (!numSet.contains(num - 1) ) { // 시작점인 경우만 계산하면 O(n)을 유지할수있다.  
                int cnt = 1;
                while (numSet.contains(num + cnt)) { 
                    cnt++;
                }
                max = Math.max(max, cnt);
            }
       }
       return max;
    }
}