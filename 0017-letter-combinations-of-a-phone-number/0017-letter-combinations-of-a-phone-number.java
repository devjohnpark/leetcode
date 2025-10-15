// // 문제 정의: 문자열 digits에서 매핑되는 문자열의 조합을 구해라. 단, 문자열 조합의 길이는 digits의 길이만큼이다.
// // 시간 복잡도: 문자간의 조합이므로 digits.length ^ digit 최대 문자열 길이 = 4^4이다.
// // 문제 풀이:
// // 문자를 노드로 치면, 깊이 우선 탐색을 하면서 문자를 StringBuilder에 추가하면서 digits.length에 도달한 문자열을 생성하고 반환 리스트에 추가하면된다.
// // 단, 자식 끝노드 까지 탐색후 다시 올라가서 형제 서브 트리를 탐색해야하므로 깊이 우선 탐색 후에 StringBuilder의 문자를 한개씩 제거해줘야한다.
// class Solution {
//     public List<String> letterCombinations(String digits) {
//         List<String> ans = new ArrayList<>();
//         if (digits == null || digits.isEmpty()) return ans;
//         HashMap<Character, String> digitToLetters = new HashMap<>(); // 인덱스 2부터 시작하므로 편리하게 해시맵으로 매핑된 문자열을 생성
//         digitToLetters.put('2', "abc");
//         digitToLetters.put('3', "def");
//         digitToLetters.put('4', "ghi");
//         digitToLetters.put('5', "jkl");
//         digitToLetters.put('6', "mno");
//         digitToLetters.put('7', "pqrs");
//         digitToLetters.put('8', "tuv");
//         digitToLetters.put('9', "wxyz");
//         backtracking(digits, 0, new StringBuilder(), ans, digitToLetters);
//         return ans;
//     }

//     // backtracking 재귀 메서드
//     // "23"일때 두번을 반복했다고 인지해야한다. 따라서 깊이(depth)를 0부터 시작해서 digits.length이면 멈춘다.
//     private void backtracking(String digits, int depth, StringBuilder path, List<String> ans, Map<Character, String> digitToLetters) {
//         // 재귀 호출 종료 조건
//         if (depth == digits.length()) {
//             ans.add(path.toString());
//             return;
//         }

//         // 현재 숫자에 매핑된 문자열 가져오기
//         String letters = digitToLetters.get(digits.charAt(depth)); // "abc" = digitToLetters.get(2)

//         // digits와 매핑되는 문자열을 순회하면서 문자 하나당, 깊이 탐색 재귀 호출로 다음 인덱스를 호출
//         for (int i = 0; i < letters.length(); i++) {
//             path.append(letters.charAt(i)); // 탐색 전 문자 추가 -> 경로 확장: "ad"(a->d), "ae"(a->e), "af"(a->f), "bd"(b->d), "be"(b->e), "bf"(b->f), ...
//             backtracking(digits, depth + 1, path, ans, digitToLetters); // 문자 경로에 추가후에 깊이 탐색 
//             path.deleteCharAt(path.length() - 1); // 탐색 후 문자 삭제 -> 경로 되돌리기: "ab"(a->d) -> "a" -> "ae"(a -> e) ...
//         }
//     }
// }

// 2-9의 번호에 대해서 매핑되는 문자열이 존재한다.
// 번호를 누르면 매핑되는 문자열중에서 하나씩 문자 조합을 반환한다. 단, 문자의 조합의 순서는 상관없다.
// 조합은 모든 경로를 탐색하는 것이다. 따라서 그래프 탐색을 통해 찾을수있다. 그중 하나의 경로를 탐색할때까지 찾는 DFS가 적합하다.

// 직과적 해석
// ex) digits = "23"
// 경로 탐색시 추가된 문자를 제거해야되기 때문에 back해야한다.
// 문자는 다이얼에 맵핑되는 문자열을 가져와서 for 문으로 add -> 재귀 dfs -> delete 하면된다.
// 탐색 깊이가 digits.length이면 정답 문자열을 반환할 문자열 리스트에 더하고 탐색 종료
class Solution {
    public List<String> letterCombinations(String digits) {

        HashMap<Character, String> map = new HashMap<>();

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> ans = new ArrayList<>();
        backtracking(digits, 0, new StringBuilder(), ans, map);
        return ans;
    }

    // 탐색중인 깊이를 알아야하므로 depth 매개변수 필요
    // 동적으로 문자를 추가해서 문자열을 생성해야하므로 StringBuilder 필요
    // digits에 depth로 키값을 가져와서 (digits 매개변수 필요), 다이얼에 매핑된 문자열을 가져와야하한다. (Map 매개변수 필요)    
    private void backtracking(String digits, int depth, StringBuilder path, List<String> ans, Map<Character, String> map) {
        // 재귀 종료 조건
        if (digits.length() == depth) {
            ans.add(path.toString());
            return;
        }

        // 다이얼에 매핑된 문자열 가져오기
        // 0 -> '2' -> "abc"
        String letters = map.get(digits.charAt(depth));
        
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            backtracking(digits, depth + 1, path, ans, map);
            path.deleteCharAt(path.length() - 1);
        }
    }

}






