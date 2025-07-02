class Solution {
    // 1. How to access
    // (1) input value
    // input size 1000 -> n^2로 풀어도 무방해 보임
    // input character: digit for english letter

    // (2) normal case
    // babad -> bab (aba)
    // babab -> babab (bab, aba)
    // bb -> bb (b)
    // bbb -> bbb (b, bb) 

    // (3) edge case
    // baabc -> baab (c)
    // abb -> bb
    // abbb -> bbb

    // (4) access: data structure & algorithms
    // 0 ~ n 까지 순회하면서 시작지점을 설정하고 left, right 인덱스를 양쪽 끝까지 이동시키면서 substring을 찾아낸다. 
    // O(N): 0 ~ n 까지 순회하면서 시작지점을 설저
    // O(N): left, right 인덱스를 양쪽 끝까지 이동시키면서 substring을 찾아냄
    // Time Complexity: O(N^2)

    // 2. Debuging
    // babab -> babab (bab, aba)
    // baabc -> baab (c)
    // abb -> bb
    // abbb -> bbb
    // public String longestPalindrome(String s) {
    //     int len = s.length();
    //     if (len <= 1) return s;
    //     String maxStr = "";
    //     // for문으로 substring 탐색할 시작지점 설정 (0 ~ s.length())
    //     // left: 왼쪽 탐색 인덱스
    //     // right: 오른쪽 탐색 인덱스
    //     // abb -> left는 1, right 2 -> substring이 짝수이면, left + 1 == right
    //     // abbb -> left는 2, right 2 -> substring이 홀수이면, left == rights
    //     for (int i = 0; i < len - 1; i++) {
    //         String oddStr = findPalindromic(s, i, i);
    //         String evenStr = findPalindromic(s, i, i + 1);

    //         if (maxStr.length() < oddStr.length()) {
    //             maxStr = oddStr;
    //         } 

    //         if (maxStr.length() < evenStr.length()) {
    //             maxStr = evenStr;
    //         }
    //     }
    //     return maxStr;
    // }

    // private String findPalindromic(String s, int left, int right) {
    //     int len = s.length();
    //     // 양쪽의 문자가 비대칭 할때까지 반복
    //     while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
    //         left--;
    //         right++;
    //     }
    //     return s.substring(left + 1, right); // "hamburger".substring(4, 8) returns "urge"
    // }

    private int resultLeft = 0, resultLen = 1;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) return s;
        for (int i = 0; i < len - 1; i++) {
            findPalindromic(s, i, i);
            findPalindromic(s, i, i + 1);
        }
        return s.substring(resultLeft, resultLeft + resultLen);
    }

    private void findPalindromic(String s, int left, int right) {
        int len = s.length();
        // 양쪽의 문자가 비대칭 할때까지 반복
        while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // 최대 길이(반환할 결과값) 업데이트 
        int curLen = right - left - 1;
        if (resultLen < curLen) {
            resultLeft = left + 1;
            resultLen = curLen;
        }
    }
}