class Solution {
    // 알파벳 별로 개수를 카운팅
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int size = 'z' - 'a' + 1;
        int[] arr = new int[size];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'a'] -= 1;
        }
        
        for (int n: arr) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}