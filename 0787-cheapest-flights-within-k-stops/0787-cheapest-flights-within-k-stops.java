// class Solution {
//     // 시간복잡도: 입력크기 10000 -> O(n^2) 이내
//     // 문제 풀이
//     // 1. 인접행렬을 만든다.
//     // 2. 다익스트라 알고리즘으로 최단 경로를 찾느다.
//     // 3. 단, k개의 정점만 거쳐서 이동해야한다.
//     // 그러면 depth가 k+1이면 다시 올라가서 탐색하도록한다.
//     // bfs로 풀면 가장 가까운 정점의 비용을 더하면서 탐색할수있고 depth를 알수있다.
//     // 가장 가까운 정점의 비용을 더하
//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//         int a[][] = new int[n][n];

//     }
// }

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }

        Queue<int[]> q = new LinkedList<>();
        // {노드, 비용, 경유횟수}
        q.offer(new int[] {src, 0, 0});
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0], cost = curr[1], stops = curr[2];
            if (stops > k) continue;

            for (int[] neighbour : adj.get(node)) {
                int neighbourNode = neighbour[0], price = neighbour[1];
                if (price + cost >= minCost[neighbourNode]) continue;
                minCost[neighbourNode] = price + cost;
                q.offer(new int[] {neighbourNode, minCost[neighbourNode], stops + 1});
            }
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}
