class Solution {
    // amanaplana c analpanama
    // abc____________________c____b___a
    // abc__c____b___a____________
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        // 양쪽 끝에서 부터 문자인지 확인 -> 대문자를 소문자로 변경하고 동일 문자인지 확인 -> 만일 동일 문자가 아닐 경우 return false -> 모두 동일 문자라면 return true
        // "0P": return false -> '0'은 자리수 이므로 start 인덱스 이동을 안한다. -> start와 end가 P 인덱스를 가리키므로 true가 된다.
        int start = 0;
        int end = s.length() - 1;
        while(start <= end) {
            char left = s.charAt(start);
            char right = s.charAt(end);
            if (!Character.isLetterOrDigit(left)) { 
                start++;
            } else if (!Character.isLetterOrDigit(right)) {
                end--;
            } else if (Character.isLetterOrDigit(left) && Character.isLetterOrDigit(right)) {
                if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }
}