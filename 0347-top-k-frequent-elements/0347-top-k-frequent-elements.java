class Solution {
    // 문제 정의: 주어진 배열에서 가장 빈도수가 높은 요소를 k개 만큼 출력 (각 배열 요소의 개수는 다 다름)
    // 조건:
    // (1) O(nlogn) 내에 찾아야한다.
    // (2) 가장 높은 빈도수를 k개 만큼 찾아야한다.
    // (3) 배열 입력값은 오름차순이 아니라 랜덤이다.

    // 문제 나눠서 정의
    // 1. 각 배열 요소 마다 빈도수를 저장해야한다.
    // 2. 저장한 배열의 빈도수중에서 가장 높은 값을 k개 만큼 가져와서 출력한다.

    // 핵심 알고리즘
    // 1. 주어진 배열 요소를 순차적으로 조회해서 빈도수를 hashmap<요소 값, 빈도수>를 O(1)으로 저장한다.
    // 2. 빈도수별 숫자를 저장할 ArrayList[]를 생성한다.
    // 3. hashmap에서 값을 가져와서 빈도수에 따라 ArrayList[빈도수]에 추가한다.
    // 4. ArrayList[]에서 끝 리스트부터 탐색해서 k개 만큼 요소값을 가져와서 반환할 배열에 저장한다.

    // 1. 정렬 O(nlogn)
    // 2. 배열 순회하면서 카운팅 

    // public int[] topKFrequent(int[] nums, int k) {
    //     int n = nums.length;
    //     HashMap<Integer, Integer> freqMap = new HashMap<>();
    //     for (int num: nums) {
    //         freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    //     }

    //     List<Integer>[] freqList = new ArrayList[n + 1]; // n개 까지 빈도수 가능하므로 하나더 크게 생성
    //     // 해시맵에서 키와 값을 둘다 가져와야한다.
    //     // 키를 먼저 가져와서 키로 값을 가져온다.
    //     for (int freqKey: freqMap.keySet()) {
    //         int freqValue = freqMap.get(freqKey);
    //         if (freqList[freqValue] == null) { 
    //             freqList[freqValue] = new ArrayList<>(); // 저장할 리스트 초기화
    //         }
    //         freqList[freqValue].add(freqKey);
    //     }

    //     // 3. 빈도수가 가장 높은 뒤에서부터 조회하며 반환할 결과값 저장
    //     List<Integer> result = new ArrayList<>();
    //     for (int i = freqList.length - 1; i >= 0 && result.size() < k; i--) {
    //         if (freqList[i] != null) {
    //             result.addAll(freqList[i]);
    //         }
    //     }

    //     return result.stream().mapToInt(i -> i).limit(k).toArray(); 
    //     // int[] result = new int[k];
    //     // int idx = 0;
    //     // for (int i = freqList.length - 1; i >= 0; i--) {
    //     //     if (freqList[i] != null) {
    //     //         for (int num: freqList[i]) {
    //     //             if (idx == k) break;
    //     //             result[idx++] = num;
    //     //         }
    //     //     }
    //     // }
    //     // return result;
    // }

    // public int[] topKFrequent(int[] nums, int k) {
    //     Map<Integer, Integer> freqMap = new HashMap<>();
    //     for (int num : nums) {
    //         freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    //     }

    //     // 2. Entry를 리스트로 뽑아서 빈도 기준으로 내림차순 정렬
    //     List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(freqMap.entrySet()); // Set(키와 값의 쌍)을 리스트로 저장
    //     entryList.sort((a, b) -> b.getValue() - a.getValue()); // 값 기준 내림차순: O(nlogn)

    //     // 3. 정렬된 리스트 값 중에서 상위 k개 추출
    //     int[] result = new int[k];
    //     for (int i = 0; i < k; i++) {
    //         result[i] = entryList.get(i).getKey();
    //     }

    //     return result;
    // }

    // max heap using PriorityQueue
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll().getKey();
        }
        return res;

        // return maxHeap.toArray(new int[k]); 
    }

}