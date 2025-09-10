// // 시간 복잡도: n의 개수가 100이므로 100 x 100 = 10000이므로 O(N^2) 내에 풀어야한다.
// // 입력값: 출발지, 도착지, 비용
// // k: k번째 노드부터 탐색 시작
// // n: 탐색힐 노드의 개수 (신호를 받은 노드의 개수)

// // 입력받은 2차원 배열로 directed matrix를 만든다.
// // k번째 노드 부터 시작해서 n번 만큼 다익스트라 탐색을 수행한다. 만일, n번 만큼 탐색하지 못할시에 -1을 반환한다.
// // n개의 노드간에 연결되지 못할시 -1 반환
// // 반환값은 “모든 사람이 메시지를 받는 시간”이 "가장 마지막에 받는 사람의 시간"이다.
// import java.util.Arrays;

// class Solution {
//     private static final int INF = 1_000_000_000;

//     public int networkDelayTime(int[][] times, int n, int k) {
//         // 1) 인접 행렬: 기본 INF, 자기자신 0
//         int[][] a = new int[n][n]; // 도달해야되는 노드가 n개 이므로 n x n 매트릭스 초기화
//         for (int i = 0; i < n; i++) {
//             Arrays.fill(a[i], INF); // 배열 a[i]의 모든 요소(열) 를 INF 값으로 채워 넣는 방식
//             a[i][i] = 0; // 자기 자신으로 가는 경로는 거리 0으로 설정
//         }

//         // 2) 간선 채우기
//         for (int i = 0; i < times.length; i++) {
//             int u = times[i][0] - 1; // 인접행렬의 인덱스로 쓸것이므로 -1 
//             int v = times[i][1] - 1; // 인접행렬의 인덱스로 쓸것이므로 -1 
//             int w = times[i][2]; 
//             a[u][v] = w;
//         }

//         boolean[] v = new boolean[n];
//         int[] d = new int[n];
//         dijkstra(k - 1, a, n, v, d);

//         // 3) 결과 계산: 도달 못한 노드 있으면 -1, 아니면 최댓값 반환
//         int max = 0;
//         for (int dist: d) {
//             if (dist >= INF) return -1; // 도달 못한 노드
//             if (dist > max) max = dist; // 가장 마지막에 신호를 받은 노드의 비용을 반환해야하므로 최대값 반환
//         }
//         return max;
//     }

//     private int getSmallIndex(boolean v[], int d[], int n) {
//         int min = INF;
//         int index = 0;
//         // 모두 순차적으로 탐색: 선형 탐색 -> 가장 쉽게 다익스트라 구현 (가장 효율적이지는 않음)
//         for (int i = 0; i < n; i++) {
//             if (!v[i] && d[i] < min) {
//                 min = d[i];
//                 index = i;
//             }
//         }
//         return index;
//     }

//     private void dijkstra(int start, int[][] a, int ㅜ, boolean[] v, int[] d) {
//         for (int i = 0; i < N; i++) {
//             d[i] = a[start][i]; // 시작점으로부터 출발했을때 모든 경로에 대해 거리 저장
//         }

//         v[start] = true; // 시작점 방문했다고 마킹

//         for (int i = 0; i < n; i++) {
//             int current = getSmallIndex(v, d, n);
//             v[current] = true;

//             for (int j = 0; j < n; j++) {
//                 if (!v[j] && d[current] + a[current][j] < d[j]) {
//                     d[j] = d[current] + a[current][j]; // 작은 값으로 갱신
//                 }
//             }
//         }
//     }
// }


import java.util.*;
import java.util.Arrays;

class Solution {
    private static final int INF = 1_000_000_000;

    public int networkDelayTime(int[][] times, int n, int k) {
        // 1) 인접 행렬: 기본 INF, 자기자신 0
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(a[i], INF);
            a[i][i] = 0;
        }

        // 2) 간선 채우기 (1-based -> 0-based)
        for (int[] t : times) {
            int u = t[0] - 1, v = t[1] - 1, w = t[2];
            if (w < a[u][v]) a[u][v] = w; // 혹시 모를 중복 간선 대비
        }

        boolean[] visited = new boolean[n];
        int[] d = new int[n];
        dijkstraPQ(k - 1, a, n, visited, d);

        // 3) 결과 계산: 도달 못한 노드 있으면 -1, 아니면 최댓값 반환
        int max = 0;
        for (int dist : d) {
            if (dist >= INF) return -1;
            if (dist > max) max = dist;
        }
        return max;
    }

    // 우선순위 큐(최소 힙) 사용한 다익스트라 (행렬 기반)
    private void dijkstraPQ(int start, int[][] a, int n, boolean[] visited, int[] d) {
        Arrays.fill(d, INF);
        Arrays.fill(visited, false);
        d[start] = 0;

        // (현재까지 거리, 정점)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dist = cur[0], u = cur[1];
            if (visited[u]) continue;
            visited[u] = true;

            // 인접 행렬이므로 모든 v 후보 확인
            for (int v = 0; v < n; v++) {
                if (a[u][v] == INF || visited[v]) continue;
                int nd = dist + a[u][v];
                if (nd < d[v]) {
                    d[v] = nd;
                    pq.offer(new int[]{nd, v});
                }
            }
        }
    }
}
