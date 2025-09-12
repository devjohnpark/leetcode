class Solution {
    // 문제풀이
    // BFS로 깊이에 따른 제한을 두면서 최단 경로 탐색을 수행한다.
    // 큐에서 방문 정점에 대한 인접 정점들을 모두 제거할때 깊이가 1씩 증가한다. 따라서 카운팅 변수로 컨트롤하면 된다.
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 인접리스트 생성
        List<List<int[]>> adj = new ArrayList<>(); 
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());  
        for (int[] flight: flights) {
            // 각 정점에 인접 노드 추가
            adj.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }

        // BFS를 위한 큐 셋업
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { src, 0 }); // 순회를 시작할 노드 삽입, 거리는 00
        
        // 시작점으로부터 목표 정점까지의 최단 경로 저장 배열 
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        
        // 깊이 제한을 위한 변수
        int depth = 0;

        // BFS
        while(!q.isEmpty() && depth <= k) {
            // 한번에 큐에 있는 정점을 꺼내서 처리해야, 인접한 노드의 거리를 모두 비교 가능하다.
            // 큐 사이즈 스냅샷을 해서 해당 레벨의 정점들만 처리할수있도록 한다. (동일 레벨의 정점들만 큐에서 제거되고 인접 정점이 처리됨)
            int size = q.size(); 
            while (size > 0) { 
                int[] curr = q.poll(); // 방문 정점 

                // 방문 노드로부터 인접 정점간에 비용을 비교하여, 가장 작은 비용의 정점을 추가하고 최단 거리 배열 갱신
                for (int[] adjacent : adj.get(curr[0])) {
                    int adjVertax = adjacent[0];
                    int cost = adjacent[1];

                    // 더 비싼 경로는 버림(이미 더 싼 비용으로 방문함)
                    if (cost + curr[1] >= minCost[adjVertax]) continue;

                    minCost[adjVertax] = cost + curr[1]; // 최단 경로 갱신

                    q.offer(new int[] { adjVertax, minCost[adjVertax] }); // 최단 경로인 정점을 추가
                }

                size--;
            }
            depth++; // 깊이 갱신
        }
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}
