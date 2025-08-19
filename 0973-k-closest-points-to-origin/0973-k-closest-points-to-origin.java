class Solution {
    // 시간복잡도: 입력크기 10000개 -> O(N log N)
    // 문제 풀이
    // 이중 for문을 돌면서 각 요소값의 제곱 합을 구해서 작은 순서대로 정렬한다. 
    // 정려된 배열중에서 k개 만큼 반환한다.
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distA, distB);
        });
        return Arrays.copyOfRange(points, 0, k);
    }
}