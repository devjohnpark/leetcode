class Solution {
    // 문제 정의: 이차원 배열에 저장된 각 배열의 두 요소의 제곱 합이 가장 작은 순서대로 k개를 반환해랴.
    // 시간복잡도: 입력킉가 10000이므로 O(NlogN) 이내로 풀어야한다.
    // 브루트포스: 이차원 배열을 각 배열의 두 요소의 제곱 합을 오름차순으로 정렬해서 k개 만큼 복사해서 반환 -> O(nlogn) + O(k)
    // public int[][] kClosest(int[][] points, int k) {
    //     // O(NlogN)
    //     Arrays.sort(points, (a, b) -> {
    //         int distA = a[0] * a[0] + a[1] * a[1];
    //         int distB = b[0] * b[0] + b[1] * b[1];
    //         return Integer.compare(distA, distB);
    //     });
    //     // int[k][2] 배열을 할당 -> 공간 O(k)
    //     // k개의 int[] 참조 복사 -> 시간 O(k)
    //     return Arrays.copyOfRange(points, 0, k); 
    // }

    // 핵심 문제 풀이
    // 문제의 키포인트인 "가장 가까운 k개"만 필요하고 반환 배열을 정렬하지 않아도 되므로 우선순위 큐를 사용하는 것이 좋다.
    // 우선순위 큐에 삽입시 크기가 k개 보다 크면 최대값을 삭제 시키면된다. 그러기 위해서는 우선순위 큐를 최대힙으로 만들어야한다. 그래야 루트에 위치하는 최대값을 삭제할수있다.
    // 그러면 O(log(k+1)) x O(n) = O(nlogk)에 문제를 해결할수있다.
    public int[][] kClosest(int[][] points, int k) {
        // int 배열을 저장하는 우선순위 큐
        // x[0]^2 + x[1]^2을 내림차순으로 정렬: b[0]^2 - a[0]^2하면 내림차순 정렬
        // PriorityQueue는 Object[]로 저장한다.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        // 총 시간복잡도: O(nlogk)
        // 이차원 배열 내의 배열 순회: O(n)
        for (int[] point: points) {
            maxHeap.add(point); // 최대 삽입 연산: O(logk)
            if (maxHeap.size() > k) maxHeap.remove(); // 저장한 크기가 k개 보다 크면 최대값을 삭제: O(logk)
        }

        // k개 만큼 저장하는 배열 (오름 차순으로 저장 안해도됨)
        // int[][] result = new int[k][2];
        // for (int i = 0; i < k; i++) {
        //     result[i] = maxHeap.remove(); 
        // }
        // return result;

        // 컬렉션의 모든 요소를 주어진 타입의 배열로 반환
        // 반환 타입은 int[][]로 지정
        // 정렬되어서 반환되는 것이 아니라, 단지 힙에 들어있는 원소들을 배열로 모은 것이다. 
        // PriorityQueue는 Object[]로 저장하기 때문에 배열로 변환시에 O(k)가 걸리지만, 삭제 연산하면 O(klogk)가 걸린다.
        // new int[0][0]: 타입만 알려주고 크기는 모를 때 유용, 여기서는 반환할 배열이 int[k][2] 이므로 이렇게 지정
        // int[k][2] 배열을 할당 -> 공간 O(k)
        // k개의 int[] 참조 복사 -> 시간 O(k)
        return maxHeap.toArray(new int[k][2]); 
    }
}