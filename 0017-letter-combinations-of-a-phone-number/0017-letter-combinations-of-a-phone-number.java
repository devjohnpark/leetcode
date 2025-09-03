// 문제 정의: 문자열 digits에서 매핑되는 문자열의 조합을 구해라. 단, 문자열 조합의 길이는 digits의 길이만큼이다.
// 시간 복잡도: 문자간의 조합이므로 digits.length ^ digit 최대 문자열 길이 = 4^4이다.
// 문제 풀이:
// 문자를 노드로 치면, 깊이 우선 탐색을 하면서 문자를 StringBuilder에 추가하면서 digits.length에 도달한 문자열을 생성하고 반환 리스트에 추가하면된다.
// 단, 자식 끝노드 까지 탐색후 다시 올라가서 형제 서브 트리를 탐색해야하므로 깊이 우선 탐색 후에 StringBuilder의 문자를 한개씩 제거해줘야한다.
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return ans;
        HashMap<Character, String> digitToLetters = new HashMap<>(); // 인덱스 2부터 시작하므로 편리하게 해시맵으로 매핑된 문자열을 생성
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
        backtracking(digits, 0, new StringBuilder(), ans, digitToLetters);
        return ans;
    }

    // backtracking 재귀 메서드
    // "23"일때 두번을 반복했다고 인지해야한다. 따라서 깊이(depth)를 0부터 시작해서 digits.length이면 멈춘다.
    private void backtracking(String digits, int depth, StringBuilder path, List<String> ans, Map<Character, String> digitToLetters) {
        // 재귀 호출 종료 조건
        if (depth == digits.length()) {
            ans.add(path.toString());
            return;
        }

        // 현재 숫자에 매핑된 문자열 가져오기
        String letters = digitToLetters.get(digits.charAt(depth)); // "abc" = digitToLetters.get(2)

        // digits와 매핑되는 문자열을 순회하면서 문자 하나당, 깊이 탐색 재귀 호출로 다음 인덱스를 호출
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i)); // 탐색 전 문자 추가 -> 경로 확장: "ad"(a->d), "ae"(a->e), "af"(a->f), "bd"(b->d), "be"(b->e), "bf"(b->f), ...
            backtracking(digits, depth + 1, path, ans, digitToLetters); // 문자 경로에 추가후에 깊이 탐색 
            path.deleteCharAt(path.length() - 1); // 탐색 후 문자 삭제 -> 경로 되돌리기: "ab"(a->d) -> "a" -> "ae"(a -> e) ...
        }
    }
}