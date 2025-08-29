class Solution {
    // 중복 확인: 문자 순회 하면서 일치하는 문자 hashset에 존재하는 지 확인, 없으면 hashset에 저장
    // 중복된 문자 제거(lopwwkpew -> lopw, wkpe -> p): 중복된 문자열을 처음 조회할때, hashset에 저장된 모든 값을 제거해야한다. -> 어떻게 제거하나? -> substring 시작 인덱스를 저장하고 현재 문자가 삭제될때까지 시작 인덱스를 증가시키면서 삭제
    // substring의 최대길이: 끝 - 시작 인덱스 

    // case: abcabcbb -> abc, a에서 중복된 문자를 조회했을때, substring에 대한 시작 인덱스를 다시 설정
    // case: pwwkew -> pw, wke, w에서 
    // edge case: lopwwkpew -> lopw, wkpe에서 각 substring 이전에 중복된 문자는 제거해야한다.

    // time complexcity 2n -> O(n)
    // space complexcity: 중복 없는 문자의 수 k -> O(k)
    // public int lengthOfLongestSubstring(String s) {
    //     int n = s.length();
    //     int left = 0, maxLen = 0;
    //     HashSet<Character> set = new HashSet<>();
        
    //     for (int right = 0; right < n; right++) {
    //         char c = s.charAt(right);
            
    //         // 중복 문자가 있으면 left를 이동시켜 중복 제거
    //         while (set.contains(c)) {
    //             set.remove(s.charAt(left));
    //             left++;
    //         }
            
    //         set.add(c);
    //         maxLen = Math.max(maxLen, right - left + 1);
    //     }
        
    //     return maxLen;
    // }

    // // 슬라이딩 윈도우 + HashMap (최적화된 버전)
    // public int lengthOfLongestSubstring(String s) {
    //     int n = s.length();
    //     int left = 0, maxLen = 0;
    //     HashMap<Character, Integer> map = new HashMap<>();
        
    //     for (int right = 0; right < n; right++) {
    //         char c = s.charAt(right);
            
    //         // 현재 문자가 윈도우 내에 있으면 left를 바로 이동
    //         if (map.containsKey(c) && map.get(c) >= left) {
    //             left = map.get(c) + 1;
    //         }
            
    //         map.put(c, right);
    //         maxLen = Math.max(maxLen, right - left + 1);
    //     }
        
    //     return maxLen;
    // }






    // 문제 정의: 주어진 문자여에서 중복되지 않는 문자로 구성된 가장긴 substring을 구해라. -> 연속된 문자열에서 동일한 문자가 있으면 안된다.
    // 시간복잡도: 50000이므로 O(nlogn) 이내로 풀어야한다.
    // 부르트 포스
    // 문자열의 문자를 순회중에 

    // 핵심 문제 풀이
    // 문자열 구간 지정
    // 중복 확인을 위해 문자를 순회하면서 hashmap에 <문자, 인덱스> 저장
    // 오른쪽 포인터를 이동하면서 hashmap에서 중복되는 문자가 있는지 확인
    // hashmap에서 가져온 문자의 인덱스가 왼쪽 포인터보다 작으면 true
    // hashmap에서 가져온 문자의 인덱스가 왼쪽 포인터보다 작으면 false -> 구간 변경 -> 왼쪽 포인터를 현재 지정된 인덱스로 이동, 오른쪽 포인터는 왼쪽 포인터 +1
    // 문제 다시 봐야됨
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int length = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 1;
        for (int right = 0; right < length; right++) {
            // maxLen = Math.max(maxLen, right - left + 1); 
            char c = s.charAt(right);
            Integer idx = map.get(c);
            if (idx != null && idx >= left) {
                // 구간 업데이트, rigth가 아니라 hashmap에 중복 저장된 인덱스의 바로 다음 인덱스부터 left로 시작해야한다.
                // left = right;
                left = idx + 1;
            } 
            // 다음 구간에서 중복되는 값 찾기 위해 저장
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1); 
        }
        return maxLen;
    }









}