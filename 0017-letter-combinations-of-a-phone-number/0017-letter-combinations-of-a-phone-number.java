// 문자 조합 문제: 모든 조합을 탐색해야하므로 그래프 탐색이다. 모든 완성된 경로를 탐색하므로 깊이 탐색이 적합하다.
// 조합의 조건: digits의 길이만큼만 깊이를 탐색해야한다. 따라서 깊이를 계산해서 digits의 길이와 동일하면 탐색을 종료해야한다.
// 

// 깊이 탐색을 위한 로직을 파악한다.

// digits = "23"
// digits = "234"

// 다음 로직 재귀로 반복적으로 돌린다. 
// '2'로 문자열 가져오기 "abc"
// StringBuilder에 문자 하나씩 추가 (a, b, c)

// '3'로 문자열 가져오기 "def"
// StringBuilder에 문자 하나씩 추가 (d, e, f)

// a에 대해 d, e, f를 탐색해야하므로 백트래킹을 사용해서 문자를 추가한뒤에 원상태로 만든다.
// "ad" -> "a" -> "ae" -> "a" -> "af"
// 따라서 백트래킹을 써야한다.
// for (String str: digits에 매핑되는 문자열)
// StringBuilder.append(a) -> dfs(depth + 1, StringBuilder("a"), ) -> 문자 삭제

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<>();
        HashMap<Character, String> mapLetters = new HashMap<>();
        mapLetters.put('2', "abc");
        mapLetters.put('3', "def");
        mapLetters.put('4', "ghi");
        mapLetters.put('5', "jkl");
        mapLetters.put('6', "mno");
        mapLetters.put('7', "pqrs");
        mapLetters.put('8', "tuv");
        mapLetters.put('9', "wxyz");
        backtracking(digits, 0, new StringBuilder(), mapLetters, answer);
        return answer;
    }

    private void backtracking(String digits, int depth, StringBuilder sb, HashMap<Character, String> mapLetters, List<String> ans) {
        if (digits.length() == depth) {
            ans.add(sb.toString());
            return;
        }

        String letters = mapLetters.get(digits.charAt(depth)); // "abc"

        // 재귀의 동작 흐름 이해가 아직 미약함
        // 첫번째 for문 돌기: "ad" -> "a" -> "ae" -> "a" -> "af"
        // 두번째 for문 돌기: "bd" -> "b" -> "be" -> "b" -> "bf"
        // 세번째 for문 돌기: "cd" -> "c" -> "ce" -> "c" -> "cf"
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i)); 
            backtracking(digits, depth + 1, sb, mapLetters, ans); 
            sb.deleteCharAt(sb.length() - 1); 
        }

    }
}