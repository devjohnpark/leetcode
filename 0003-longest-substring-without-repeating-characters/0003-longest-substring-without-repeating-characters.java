class Solution {
    // 중복 확인: 문자 순회 하면서 일치하는 문자 hashset에 존재하는 지 확인, 없으면 hashset에 저장
    // 중복된 문자 제거(lopwwkpew -> lopw, wkpe -> p): 중복된 문자열을 처음 조회할때, hashset에 저장된 모든 값을 제거해야한다. -> 어떻게 제거하나? -> substring 시작 인덱스를 저장하고 현재 문자가 삭제될때까지 시작 인덱스를 증가시키면서 삭제
    // substring의 최대길이: 끝 - 시작 인덱스 

    // case: abcabcbb -> abc, a에서 중복된 문자를 조회했을때, substring에 대한 시작 인덱스를 다시 설정
    // case: pwwkew -> pw, wke, w에서 
    // edge case: lopwwkpew -> lopw, wkpe에서 각 substring 이전에 중복된 문자는 제거해야한다.

    // 2n -> O(n)
    // O(k) < O(n) -> 중복 없는 문자의 수 k (HashSet에 저장하는 값의 개수)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            
            // 중복 문자가 있으면 left를 이동시켜 중복 제거
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

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
}