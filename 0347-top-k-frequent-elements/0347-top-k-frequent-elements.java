class Solution {
    // 문제 정의: 주어진 배열에서 가장 빈도수가 높은 요소를 k개 만큼 출력 (각 배열 요소의 개수는 다 다름)
    // 조건
    // (1) O(nlogn) 내에 찾아야한다.
    // (2) 가장 높은 빈도수를 k개 만큼 찾아야한다.
    // (3) 배열 입력값은 오름차순이 아니라 랜덤이다.

    
    // 배열 순회: O(n)
    // 배열 요소 값 별로 O(1) 카운트하기 위해 HashMap에 저장
    // 카운팅 배열 정렬: O(nlogn)
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

    //  // use an array for counting bucket
     public int[] topKFrequent(int[] nums, int k) {
        // 1. 빈도 계산
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1); // map에 저장된 값이 없으면 default로 0값 설정 

        // 2. 빈도 수에 따른 버킷 생성 (최대 빈도는 nums.length이므로 배열의 크기는 nums.length + 1)
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<>();
            buckets[freq].add(key); // 빈도수를 인덱스로 하는 배열 요소값 저장
        }

        // 3. 빈도수가 가장 높은 뒤에서부터 조회하며 결과 수집
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }

        // 4. 결과를 int[] 배열로 변환해서 반환
        return result.stream().mapToInt(i -> i).limit(k).toArray(); 
    }

    // // max heap using PriorityQueue
    // public int[] topKFrequent(int[] nums, int k) {
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int n : nums) {
    //         map.put(n, map.getOrDefault(n, 0) + 1);
    //     }

    //     PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
    //         new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

    //     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //         maxHeap.add(entry);
    //     }

    //     int[] res = new int[k];
    //     for (int i = 0; i < k; i++) {
    //         res[i] = maxHeap.poll().getKey();
    //     }

    //     return res;
    // }

}