// c
// a b c b d
// ccc
// ccccc
// aaa bbb aaa
// aaa bbb ccc
// abccccdd: 짝수가 있으면, dccaccd = 6 + 1 (홀수는 하나로 추가 호함 가능)
// a b ccc eee dd: 짝수가 있으면, dceeecd = 7 -> 홀수 알파벳은 하나만 추가 포함 가능하다. 따라서 홀수 알파벳은 - 1 해서 추가하여 짝수 개수만 포함)
// 마지막 홀수 알파벳은 가운데에 추가 가능하므로, 홀수 개수가 있는 알파벳이 있다면 flag한다.

class Solution {
    public int longestPalindrome(String s) {
        int[] arr = new int[('z' - 'a' + 1) * 2];
        for (char c: s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                ++arr[c - 'A'];
            } else if (c >= 'a' && c <= 'z') {
                ++arr[c - 'a' + 'Z' - 'A' + 1]; 
            }
        }
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

    // // More suitable data structure HashSet
    // public int longestPalindrome(String s) {
    //     if(s==null || s.length()==0) return 0;
    //     HashSet<Character> hs = new HashSet<Character>();
    //     int count = 0;
    //     for(int i=0; i<s.length(); i++){
    //         if(hs.contains(s.charAt(i))){
    //             hs.remove(s.charAt(i));
    //             count++;
    //         }else{
    //             hs.add(s.charAt(i));
    //         }
    //     }
    //     if(!hs.isEmpty()) return count*2+1;
    //     return count*2;
    // }
}