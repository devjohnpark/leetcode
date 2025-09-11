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

// class Solution {
//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//         // 인접 리스트 생성 (각 노드가 가리키는 도착지와 비용을 저장)
//         List<List<int[]>> adj = new ArrayList<>();
//         for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

//         // flights = [출발지, 도착지, 비용]
//         // 출발지에 해당하는 리스트에 {도착지, 비용}을 추가
//         for (int[] flight : flights) {
//             adj.get(flight[0]).add(new int[] {flight[1], flight[2]});
//         }

//         // BFS 탐색용 큐 (노드, 누적비용)
//         Queue<int[]> q = new LinkedList<>();
//         q.offer(new int[] {src, 0}); // 출발지에서 시작, 비용=0

//         // 각 노드까지의 최소 비용 기록
//         int[] minCost = new int[n];
//         Arrays.fill(minCost, Integer.MAX_VALUE);

//         int stops = 0; // 현재까지 경유 횟수

//         // BFS (경유 횟수를 레벨 단위로 관리)
//         while (!q.isEmpty() && stops <= k) {
//             int size = q.size(); // 이번 레벨(=경유 횟수 동일)의 노드 개수

//             // 같은 레벨에 있는 노드들 전부 탐색
//             while (size-- > 0) {
//                 int[] curr = q.poll(); // 현재 노드와 비용
//                 for (int[] neighbour : adj.get(curr[0])) {
//                     int price = neighbour[1];         // 간선 비용
//                     int neighbourNode = neighbour[0]; // 도착 노드

//                     // 기존 최소 비용보다 크거나 같으면 갱신하지 않음
//                     if (price + curr[1] >= minCost[neighbourNode]) continue;

//                     // 최소 비용 갱신
//                     minCost[neighbourNode] = price + curr[1];

//                     // 큐에 다음 노드 추가 (누적 비용 포함)
//                     q.offer(new int[] {neighbourNode, minCost[neighbourNode]});
//                 }
//             }
//             stops++; // 한 레벨(=경유 1회) 탐색 종료
//         }

//         // 목적지까지 갈 수 없으면 -1 반환
//         return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
//     }
// }



class Solution {
    // 문제풀이
    // BFS로 깊이에 따른 제한을 두면서 최단 경로 탐색을 수행한다.
    // 큐에서 정점를 제거할때 깊이가 1씩 증가한다. 따라서 카운팅 변수로 컨트롤하면 된다.
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
        
        // 깊이 제한
        int depth = 0;

        // BFS
        while(!q.isEmpty() && depth <= k) {
            // 한번에 큐에 있는 정점을 꺼내서 처리해야, 인접한 노드의 거리를 모두 비교 가능하다.
            int size = q.size();
            while (size > 0) {
                int[] curr = q.poll(); // 방문 정점

                // 방문 노드로부터 인접 정점간에 비용을 비교하여, 가장 작은 비용의 정점을 추가하고 최단 거리 배열 갱신
                for (int[] adjacent : adj.get(curr[0])) {
                    int adjVertax = adjacent[0];
                    int cost = adjacent[1];
                    if (cost + curr[1] >= minCost[adjVertax]) continue;
                    minCost[adjVertax] = cost + curr[1];
                    q.offer(new int[] { adjVertax, minCost[adjVertax] });
                }
                size--;
            }
            depth++;
        }
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}
