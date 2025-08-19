class Solution {
    // 시간복잡도: 입력크기 10000개 -> O(N log N) 이하
    
    // // 문제 풀이 1
    // // 정렬
    // // 이중 for문을 돌면서 각 요소값의 제곱 합을 구해서 작은 순서대로 정렬한다. 
    // // 정려된 배열중에서 k개 만큼 반환한다.
    // public int[][] kClosest(int[][] points, int k) {
    //     // O(NlogN)
    //     Arrays.sort(points, (a, b) -> {
    //         int distA = a[0] * a[0] + a[1] * a[1];
    //         int distB = b[0] * b[0] + b[1] * b[1];
    //         return Integer.compare(distA, distB);
    //     });
    //     return Arrays.copyOfRange(points, 0, k);
    // }

    // 문제 풀이 2
    // 우선순위 큐: 삽입 O(logn)
    public int[][] kClosest(int[][] points, int k) {
       // 최대 힙: 거리가 큰 순으로 정렬
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                b[0] * b[0] + b[1] * b[1],
                a[0] * a[0] + a[1] * a[1]
            )
        );

        for (int[] p : points) {
            maxHeap.offer(p); // 힙에 추가
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 가장 먼 점 제거
            }
        }
        return maxHeap.toArray(new int[0][0]);
        // // 힙에 남은 k개의 점 반환
        // int[][] result = new int[k][2];
        // for (int i = 0; i < k; i++) {
        //     result[i] = maxHeap.poll();
        // }
        // return result;
    }
}