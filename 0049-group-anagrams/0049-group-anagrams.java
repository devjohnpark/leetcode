class Solution {
    // 문자 개수 동일하면 anagram
    // 동일한 anagram 끼리 리스트에 삽입해서 반환
    
    // 문제 나누서 정의
    // 1. 동일한 문자로 구성되었는지 확인
    // 2. 동일한 문자로 구성된 문자열끼리 리스트로 저장

    // 입력값 크기에 따른 시간복잡도
    // (1) strs의 길이 10000 -> O(NlogN)
    // (2) strs[i]의 길이 100 -> O(N)
    // (3) 총 시간 복잡도: O(N x NlogN)
    
    // 핵심 문제 풀이 로직
    // 동일한 문자 인지 어떻게 판별? -> 문자의 개수가 동일하면 된다. -> 카운팅한다? -> 복잡 -> 정렬를 하면 문자가 동일해진다. O(nlogn) -> 문자열 배열 O(n) -> 시간복잡도 O(n x logn)
    // 정렬 -> 문자열 키로 -> Hashmap에 key:정렬된 문자열(String), value: 기존 문자열들 저장(List<String>) -> 기존의 값 있는지 조회 -> 있다면 문자열 put O(1)
    // output: Hashmap에 저장된 값을 List<List<String>>로 변환 필요 -> HashMap의 모든 값을 ArrayList로 감싸서 반환
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) { // O(n)
            char[] arr = s.toCharArray(); 
            Arrays.sort(arr); // 정렬 -> 평균 O(nlogn),  최악 O(n^2) -> 최적화 되어 있어서 O(nlogn)라고 보면된다.
            String key = String.valueOf(arr); // char[] 배열을 String으로 변환
            if (!map.containsKey(key)) { // 중복되는 키가 존재하는지 확인: O(n)
                map.put(key, new ArrayList<>()); // hashmap에 저장할 List 인스턴스 저장: O(n)
            }
            map.get(key).add(s); // 동일한 anagram인 문자열을 List에 추가: O(n)
        }
        return new ArrayList<>(map.values());
    }
}