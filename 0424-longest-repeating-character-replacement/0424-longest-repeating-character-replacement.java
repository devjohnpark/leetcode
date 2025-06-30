class Solution {
    // 핵심 알고리즘: 윈도우 범위 내에서 가장 많이 등장한 문자 하나를 제외한 나머지 전부를 바꾼다고 가정 -> 문자를 순차적으로 조회 할때마다 매번 가장 많이 등장한 문자를 가져온다.

    // 1. 알파벳의 총 길이 만큼 배열 초기화
    // 2. 배열을 순차적으로 조회하면서 알파벳의 개수 카운팅
    // 3. 윈도우 범위 내에서 가장 많이 등장한 문자 하나를 제외한 나머지 전부를 바꾼다고 가정 -> 문자를 검증할때 매번 가장 많이 등장한 문자를 가져온다. (`maxCnt`)
    // 4. 윈도우 범위내 조건 위반: (윈도우 길이) - (윈도우 내 가장 빈도 높은 문자 개수) > k이면, 왼쪽 포인터를 이동
    // 5. 최대 길이 업데이트: 최대 길이 값과 현재 윈도우 범위를 비교해서 큰값 설정 (maxLen)

    // case: s = "ABAB", k = 2
    // edge case: s = "ABAB", k = 0 -> 1
    // edge case: s = "ABCAB", k = 2 -> 4
    // edge case: s = "A", k = 0 -> 1
    // edge case: s = "AB", k = 0 -> 1
    // edge case: s = "AB", k = 1 -> 2
    public int characterReplacement(String s, int k) {
        int[] counts = new int['Z' - 'A' + 1];
        int maxCnt = 0;     
        int maxLen = 0;     
        int left = 0;   
        int len = s.length();
        for (int right = 0; right < len; right++) {
            counts[s.charAt(right) - 'A']++;
            maxCnt = Math.max(maxCnt, counts[s.charAt(right) - 'A']);
            // 유효 범위: 윈도우 범위 - 가장 많이 나타낸 수의 개수 <= k 
            // 조건 위반: 윈도우 범위 - 가장 많이 나타낸 수의 개수 > k -> 왼쪽 포인터 이동
            if (right - left + 1 - maxCnt > k) {
                counts[s.charAt(left) - 'A']--; // k를 초과했으므로 현재 타켓으로 설정한 알파벳의 카운팅 값 -1
                left++; // 다음 타겟의 값에 대한 윈도우 범위 조정
            }

            // 최대 길이 업데이트
            maxLen = Math.max(maxLen, right - left + 1); 
        }
        return maxLen;
    }
}