class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        char[] prefix = new char[minLength];
        int prefixLen = 0;
        for (int j = 0; j < minLength; j++) {
            char ch = strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(j) != ch) {
                    return new String(prefix, 0, prefixLen);
                }
            }
            prefix[prefixLen++] = ch;
        }
        return new String(prefix, 0, prefixLen);
    }
}