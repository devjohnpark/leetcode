class Solution {
    // 기존에 정렬안되어잇음 -> 내가 정렬해야되나?
    // 홀수인 알파벳은 가운데 하나 들어갈수 있음, 짝수는 양쪽 끝
    // 문자를 대문자 소문자를 합친 알파멧 배열에 저장
    // 홀수인 알파벳이 있다면 + 1 해서 output
    public int longestPalindrome(String s) {
        // 1. 배열 크기: 대소문자의 범위 총합
        // 2. int 배열에 []
        int[] arr = new int[('z' - 'a' + 1) * 2];
        // 3 - 1 = 2 (0 1 2) -> 3
        for (char c: s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                ++arr[c - 'A'];
            } else if (c >= 'a' && c <= 'z') {
                ++arr[c - 'a' + 'Z' - 'A' + 1]; 
            }
        }

        // c
        // a b c b d
        // ccc
        // ccccc
        // aaa bbb aaa
        // aaa bbb ccc
        // abccccdd: 짝수가 있으면, dccaccd = 6 + 1 (홀수는 하나로 추가 호함 가능)
        // a b ccc eee dd: 짝수가 있으면, dceeecd = 7 -> 홀수 알파벳은 하나만 추가 포함 가능하다. 따라서 홀수 알파벳은 - 1 해서 추가하여 짝수 개수만 포함)
        // 마지막 홀수 알파벳은 가운데에 추가 가능하므로, 홀수 개수가 있는 알파벳이 있다면 flag한다.
        int cnt = 0;
        boolean flag = false;
        for (int n: arr) {
            if (n % 2 > 0) { // 홀수 
                cnt += n - 1; // 짝수개만 더함
                flag = true; 
            } else { //  짝수
                cnt += n;
            }
        }
        return flag == true ? cnt+1 : cnt; 
    }
}