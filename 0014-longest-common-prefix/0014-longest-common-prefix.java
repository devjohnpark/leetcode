class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLength = 200;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        for (int j = 0; j < minLength; j++) {
            char ch = strs[0].charAt(j); // a
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(j) != ch) { 
                    return strs[0].substring(0, j);
                }
            }
        }
        return strs[0].substring(0, minLength);
    }
}