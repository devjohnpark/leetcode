// 문제 정의: src에서 dst까지 k번을 거쳐서 도착한 최단거리
// 사용할 알고리즘: k번은 탐색은 깊이를 뜻하므로 최단 경로 탐색 알고리즘 중에서 bfs로 푼다면 카운팅하기 수월하다.
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adjList = new ArrayList<>();
        // 인접리스트 생성
        // 각 노드에 대해서 인접한 노드와 비용을 저장해야한다.
        // 노드는 int, 비용은 int로 표현하므로 List에 int[]로 인접한 노드 번호와 거리를 저장한다.
        // 인접리스트는 List<List<int[]>로 저장한다.
        // 인접리스트 초기화는 ArrayList 인스턴스로 할당한 후에, for문을 돌면서 각 리스트에 ArrayList 인스턴스를 추가한다.
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for(int[] flight: flights) {
            adjList.get(flight[0]).add(new int[] { flight[1], flight[2] } );
        }

        Queue<int[]> q = new LinkedList<>(); // 주의: 큐에 저장할 타입을 지정하는 것을 명시
        int[] minArr = new int[n]; 
        int depth = 0;

        q.add(new int[] { src, 0 } ); // ex) 0 0 
        Arrays.fill(minArr, Integer.MAX_VALUE); // 최소값 저장하기 위한 최대값으로 초기화

        // 큐가 비었거나 깊이가 k보다 크면 종료
        // 큐가 비면 탐색 종료
        // depth가 k보다 크면 동일한 깊이의 모든 노드 탐색을 완료
        // q.isEmpty() || depth > k -> !q.isEmpty() && depth <= k
        // depth 계산을 어떻게 할것인가? 인접 노드를 확인하기위해서 큐에서 제거했을때 큐의 크기가 0일때 depth가 1커진다.
        while(!q.isEmpty() && depth <= k) {
            int size = q.size(); // 큐의 크기를 캡처해서 해당 크기만큼 제거하면 다음 높이를 탐색하는 것이다. 따라서 size가 0이되면 depth가 커진다.
            while (size > 0) {
                int[] cur = q.poll(); // ex) 0 0 
                int curVertex = cur[0];
                int curDistance = cur[1];

                // 현재 노드에 대한 인접 리스트 가져오기
                for (int[] adj: adjList.get(cur[0])) {
                    int adjVertex = adj[0];
                    int adjDistance = adj[1];
                    int distance = curDistance + adjDistance;
                    // 해당 노드까지의 거리가 최소 거리 배열의 값보다 작을때 갱신
                    if (distance < minArr[adjVertex]) {
                        minArr[adjVertex] = distance;
                        q.offer(new int[] { adjVertex, minArr[adjVertex] } ); // 인접 노드까지의 거리를 누적 저장해서 갱신
                    }
                }
                size--;
            }
            depth++;
        } 
        return minArr[dst] == Integer.MAX_VALUE ? -1 : minArr[dst];
    }
}