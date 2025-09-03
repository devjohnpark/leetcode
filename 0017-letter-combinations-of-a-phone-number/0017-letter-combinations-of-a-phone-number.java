// 문제 정의: digit에 매칭되는 String 문자열들을 주어진 String의 길이만큼 조합한 문자열을 반환해라.
// 입력 크기: digit에 매칭되는 문자열이 최대 길이 ^ digits의 길이 = 4 ^ digits의 길이 = 4 ^ n -> 한번의 탐색으로 풀어야한다.
// 문제 풀이 과정
// 다시 완벽 정리 필요 (현재 그래프 탐색 문제 경험이 없어서 굉장히 약함)
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>(); // 반혼값을 저장할 문자열 리스트: 그래프 탐색으로 만들어진 문자열 조합을 추가
        if (digits == null || digits.isEmpty()) return ans;

        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        dfs(digits, 0, new StringBuilder(), ans, digitToLetters);  // 문자열 조합을 위한 dfs 탐색 시작
        return ans;
    }

    // digits: "23"
    // idx: "23" 문자열의 인덱스
    // path: 탐색한 경로를 나타내는 조합 문자열: "ab"(a->d), "ae"(a->e), "af"(a->f), "bd"(b->d), "be"(b->e), "bf"(b->f), ...
    private void dfs(String digits, int idx, StringBuilder path, List<String> ans, Map<Character, String> digitToLetters) {
        // 1. 종료 조건: 모든 숫자를 처리했을 때 (digits.length 문자열 path가 완성됬을때)
        if (idx == digits.length()) { /// idx가 순차적으로 커지면서 
            ans.add(path.toString()); // 반환 리스트에 생성한 문자열 추가 (그래프 탐색으로 문자를 추가해서 생성한 문자열)
            return;
        }

        // 2. 현재 숫자에 해당하는 문자들 가져오기
        String letters = digitToLetters.get(digits.charAt(idx)); // "abc" = digitToLetters.get('2')

        // 3. 가능한 문자들을 하나씩 붙이고 DFS 탐색
        for (int i = 0; i < letters.length(); i++) {  // "abc"의 문자 순회
            path.append(letters.charAt(i)); // 경로 확장: "ad"(a->d), "ae"(a->e), "af"(a->f), "bd"(b->d), "be"(b->e), "bf"(b->f), ...
            // System.out.println(path); // "ad", "ae", "af", "bd", "be", "bf", ...
            dfs(digits, idx + 1, path, ans, digitToLetters); // 다음 digit 탐색 ('2' -> '3')
            path.deleteCharAt(path.length() - 1); // 경로 되돌리기: "ab"(a->d) -> "a" -> "ae"(a -> e) ...
            // System.out.println(path); // a, a, a, b, b, b, ...
        }
    }
}