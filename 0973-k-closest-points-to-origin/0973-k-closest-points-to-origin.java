class Solution {
    // 문제 정의: 이차원 배열에 저장된 각 배열의 두 요소의 제곱 합이 가장 작은 순서대로 k개를 반환해랴.
    // 시간복잡도: 입력킉가 10000이므로 O(NlogN) 이내로 풀어야한다.
    // 브루트포스: 이차원 배열을 각 배열의 두 요소의 제곱 합을 오름차순으로 정렬해서 k개 만큼 복사해서 반환 -> O(nlogn) + O(k)
    // 이중 for문을 돌면서 각 요소값의 제곱 합을 구해서 작은 순서대로 정렬한다. 
    // 정려된 배열중에서 k개 만큼 반환한다.
    // public int[][] kClosest(int[][] points, int k) {
    //     // O(NlogN)
    //     Arrays.sort(points, (a, b) -> {
    //         int distA = a[0] * a[0] + a[1] * a[1];
    //         int distB = b[0] * b[0] + b[1] * b[1];
    //         return Integer.compare(distA, distB);
    //     });
    //     return Arrays.copyOfRange(points, 0, k); // O(k) 복사 비용 발생
    // }

    // 핵심 문제 풀이
    // 문제의 키포인트인 "가장 가까운 k개"만 필요하고 반환 배열을 정렬하지 않아도 되므로 우선순위 큐를 사용하는 것이 좋다.
    // 우선순위 큐에 삽입시 크기가 k개 보다 크면 최대값을 삭제 시키면된다. 그러기 위해서는 우선순위 큐를 최대힙으로 만들어야한다. 그래야 루트에 위치하는 최대값을 삭제할수있다.
    // 그러면 O(log(k+1)) x O(N)에 문제를 해결할수있다.
    public int[][] kClosest(int[][] points, int k) {
        // int 배열을 저장하는 우선순위 큐
        // x[0]^2 + x[1]^2을 내림차순으로 정렬
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        // 이차원 배열 내의 배열 순회
        for (int[] point: points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) maxHeap.remove(); // 저장한 크기가 k개 보다 크면 최대값을 삭제
        }

        // // k개 만큼 저장하는 배열 (오름 차순 저장을 안해도됨)
        // int[][] result = new int[k][2];
        // for (int i = 0; i < k; i++) {
        //     result[i] = maxHeap.remove(); 
        // }
        // return result;
        return maxHeap.toArray(new int[0][0]);
    }
}