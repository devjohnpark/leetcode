class Solution {
    // 알파벳 별로 개수를 카운팅
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int size = 'z' - 'a' + 1;
        int[] arr = new int[size];
 
        // String.charAt(index)는 매번 호출이 필요해서 시간 더 걸림
        // String.toCharArray() 한번의 호출로 배열로 변환하는 것이 더 빠름
        for (char c: s.toCharArray()) {
            arr[c - 'a'] += 1;
        }

        // for (int i = 0; i < s.length(); i++) {
        //     arr[s.charAt(i) - 'a'] += 1;
        // }

        // for (int i = 0; i < t.length(); i++) {
        //     arr[t.charAt(i) - 'a'] -= 1;
        // }

        for (char c: t.toCharArray()) {
            arr[c - 'a'] -= 1;
        }

        for (int n: arr) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}