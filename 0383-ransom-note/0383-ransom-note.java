class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
		if (ransomNote.length() > magazine.length()) return false;
        int[] alphabets_counter = new int[26];
        
        for (char c : magazine.toCharArray())
            alphabets_counter[c-'a']++;

        for (char c : ransomNote.toCharArray()){
            if (alphabets_counter[c-'a'] == 0) return false;
            alphabets_counter[c-'a']--;
        }
        return true;
    }

    // hash map은 평균 O(1)
    // 1. magazine의 <알파벳, 카운팅> 저장
    // 2. ransomNote의 알파벳을 순회하면서 카운트 제거, null이거나 0이면 return false 
    // 3. ransomNote의 알파벳이 모두 포함이면 return true
    // public boolean canConstruct(String ransomNote, String magazine) {
    //     HashMap<Character, Integer> hashMap = new HashMap<>();
    //     Integer n;
    //     Character c;
    //     for (int i=0; i<magazine.length(); i++) {
    //         c = magazine.charAt(i);
    //         n = hashMap.get(c);
    //         hashMap.put(c, n == null ? 1 : ++n);
    //     }
    //     for (int i=0; i<ransomNote.length(); i++) {
    //         c = ransomNote.charAt(i);
    //         n = hashMap.get(c);
    //         if (n == null || n == 0) {
    //             return false;
    //         }
    //         hashMap.put(c, --n);
    //     }
    //     return true;
    // }
}