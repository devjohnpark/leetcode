class Solution {
    int count = 0;    

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) { 
            countPalindrome(s, i, i); // substring odd length;
            countPalindrome(s, i, i + 1); // substring even length
        }
        return count;
    }
    
    private void countPalindrome(String s, int left, int right) {
        // 중앙값을 두고 palindromic을 찾으면 여러개 발견될수 있다.
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}