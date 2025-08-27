// class Solution {
//     // 문제 정의: jewels의 문자가 stones에 있는 동일 문자가 몇개가 있는지 카운팅
//     // 입력 크기: 50 -> O(N)
//     // 부루트포스
//     // jewels의 각 문자를 stones의 각 문자와 동일한지 확인해야하므로 jewels x stones = O(N^2)
//     // 핵심 문제 풀이
//     // stones의 문자를 순회하면서 해시맵에 문자별 카운팅한 값을 저장
//     // jewels의 문자를 key로 해시맵에 접근해서 카운팅한 값 가져와서 최종값에 더하기
//     public int numJewelsInStones(String jewels, String stones) {
//         HashMap<Character, Integer> map = new HashMap<>();
//          for (int i = 0; i < stones.length(); i++) {
//             char c = stones.charAt(i);
//             map.put(c, map.getOrDefault(c, 0) + 1); // 0으로 초기화 + 1
//         }
//         int sum = 0;
//         for (int i = 0; i < jewels.length(); i++) {
//             sum += map.getOrDefault(jewels.charAt(i), 0);
//         }
//         return sum;
//     }
// }


class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int count=0;
        for(char c:stones.toCharArray()){
            if(jewels.indexOf(c)!=-1)count++;
        }
        return count;
    }
}