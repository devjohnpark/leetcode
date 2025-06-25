class Solution {
    // // 중복되는 문자 존재하면 안됨
    // // 문자 순회 하면서 일치하는 문자 hashet에 존재하는 지 확인, 없으면 hashset에 저장
    // // 문자열에서 시작 인덱스와 끝 인덱스 파악 -> 위에서 없으면 시작인덱스 저장, 끝 인덱스는 일치하는 값이 hashset에 있을때 -1 인덱스 값으로 설정하면됨
    // // sliding window
    // public int lengthOfLongestSubstring(String s) {
    //     HashSet<Character> hashSet = new HashSet<>();
    //     int st = 0, en = 0;
    //     int max = 0;
    //     int n = s.length();

    //     // ldopw wkpe w
    //     while (en < n) {
    //         char c = s.charAt(en);
    //         if (!hashSet.contains(c)) { // 값 없을때 
    //             hashSet.add(c); // 값 추가
    //             en++;
    //             max = Math.max(max, en - st);
    //         } else { // 값 있을때 
    //             hashSet.remove(s.charAt(st)); // st를 삭제해버림
    //             st++;
    //         }
    //     }
    //     return max;
    // }

    // 중복 확인: 문자 순회 하면서 일치하는 문자 hashset에 존재하는 지 확인, 없으면 hashset에 저장
    // 중복된 문자 제거(lopwwkpew -> lopw, wkpe -> p): 중복된 문자열을 처음 조회할때, hashset에 저장된 모든 값을 제거해야한다. -> 어떻게 제거하나? -> substring 시작 인덱스를 저장하고 현재 문자가 삭제될때까지 시작 인덱스를 증가시키면서 삭제
    // substring의 최대길이: 끝 - 시작 인덱스 

    // case: abcabcbb -> abc, a에서 중복된 문자를 조회했을때, substring에 대한 시작 인덱스를 다시 설정
    // case: pwwkew -> pw, wke, w에서 
    // edge case: lopwwkpew -> lopw, wkpe에서 각 substring 이전에 중복된 문자는 제거해야한다.
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                do {
                    set.remove(s.charAt(left++));
                } while(set.contains(c));
            } else {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            set.add(c);
        }
        return maxLen;
    }
}