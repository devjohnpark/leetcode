// class Solution {
//     // 입력 크기: 
//     // 입력값: 출발지, 도착지, 비용
//     // k: k번째 노드부터 탐색 시작
//     // n: 탐색힐 노드의 개수 (신호를 받은 노드의 개수)
//     // n개의 노드로 탐색 못할시 -1 반환

//     // 입력받은 2차원 배열로 directed matrix를 만든다.
//     // k번째 노드 부터 시작해서 n번 만큼 다익스트라 탐색을 수행한다. 만일, n번 만큼 탐색하지 못할시에 -1을 반환한다.


//     private static final int INF = 101;

//     public int networkDelayTime(int[][] times, int n, int k) {
//         int a[][] = new int[n][n];
//         for (int i = 0; i < times.length; i++) {
//             int u = times[i][0] - 1; // 1-based -> 0-based
//             int v = times[i][1] - 1;
//             int w = times[i][2];
//             a[u][v] = w;
//         } 
//         boolean[] v = new boolean[n]; // 각 노드별 방문 여부를 저장하는 배열
//         int[] d = new int[n]; // 최단 거리를 저장하는 배열
//         dijkstra(k-1, a, n, v, d);
//         int sum = 0;
//         for (int i = 0; i < n; i++) {
//             if (d[i] != 0) {
//                 n--;
//             }
//             sum += d[i];
//         }
//         if (n != 0) return -1;
//         return sum;
//     }

//     private void dijkstra(int start, int a[][], int NUMBER, boolean v[], int d[]) {
//         // 초기화: 시작점으로부터의 거리
//         for (int i = 0; i < NUMBER; i++) {
//             d[i] = a[start][i]; // 시작점으로부터 출발했을때 모든 경로에 대해 거리 저장
//         }
//         v[start] = true; // 시작점 방문했다고 마킹

//         // 나머지 NUMBER-2개의 정점에 대해 반복: 이미 시작 정점을 방문 처리하였고, 마지막 남은 정점은 선택할 필요 없이 최단 거리 값이 이미 정해져 있다.
//         // 정점의 개수는 많은데 간선의 개수가 적을때 차명적으로 비효율적인 알고리즘이다.
//         // 정점의 개수는 많지만 2개의 정점만 연결되어있을 경우에 정점의 개수^2 만큼 연산해야되기 때문이다.
//         // 반면 우선 순위 힙으로 구현하면 O(nlogn)으로 시간복잡도를 줄일수있다.
//         for (int i = 0; i < NUMBER - 2; i++) {
//             // 현재 방문할수있는 인접 노드중에서 가장 빠르게 도착할수있는 정점의 위치를 가져온다
//             int current = getSmallIndex(NUMBER, v, d); // O(N) x O(N) = 다익스트라의 시간 비용이 O(N^2)
//             v[current] = true; // 방문 처리 (인접 노드 방문 완료)
//             // 다익스트라 알고리즘의 가장 핵심적인 원리 (어려움)
//             // 현재 정점을 거쳐서 가는 경로가 더 짧으면 갱신
//             for (int j = 0; j < NUMBER; j++) {
//                 if (!v[j]) {
//                     // 시작점에서 방문 노드의 최단 거리 + 방문 노드와 인접(지정)한 노드의 거리 < 시작점과 특정 지점의 최단 거리
//                     // ex) d[4], a[4][3] < d[3] (실제로는 인덱스 -1 빼야함)
//                     // 1 -> 3 으로 이동하는 거리는 5임
//                     // 1 -> 4 -> 3 으로 이동하는 거리는 4임
//                     // 따라서 1 -> 3의 최단 거리를 4로 갱신
//                     if (d[current] + a[current][j] < d[j]) {
//                         d[j] = d[current] + a[current][j]; // 작은 값으로 갱신
//                     }
//                 }
//             }
//         }
//     }

//     private int getSmallIndex(int NUMBER, boolean v[], int d[]) {
//         int min = INF;
//         int index = 0;
//         // 모두 순차적으로 탐색: 선형 탐색 -> 가장 쉽게 다익스트라 구현 (가장 효율적이지는 않음)
//         for (int i = 0; i < NUMBER; i++) {
//             if (!v[i] && d[i] < min) {
//                 min = d[i];
//                 index = i;
//             }
//         }
//         return index;
//     }
// }

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
        // 2) 간선 채우기 (1-based → 0-based)
        for (int i = 0; i < times.length; i++) {
            int u = times[i][0] - 1;
            int v = times[i][1] - 1;
            int w = times[i][2];
            if (w < a[u][v]) a[u][v] = w; // 혹시 모를 중복 간선 대비
        }

        boolean[] visited = new boolean[n];
        int[] d = new int[n];
        dijkstra(k - 1, a, n, visited, d);

        // 3) 결과 계산: 도달 못한 노드 있으면 -1, 아니면 최댓값 반환
        int max = 0;
        for (int dist : d) {
            if (dist >= INF) return -1;
            if (dist > max) max = dist;
        }
        return max;
    }

    private int getSmallIndex(boolean v[], int d[], int NUMBER) {
        int min = INF;
        int index = 0;
        // 모두 순차적으로 탐색: 선형 탐색 -> 가장 쉽게 다익스트라 구현 (가장 효율적이지는 않음)
        for (int i = 0; i < NUMBER; i++) {
            if (!v[i] && d[i] < min) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    private void dijkstra(int start, int[][] a, int N, boolean[] visited, int[] d) {
        Arrays.fill(d, INF);
        d[start] = 0;

        for (int i = 0; i < N; i++) {
            d[i] = a[start][i]; // 시작점으로부터 출발했을때 모든 경로에 대해 거리 저장
        }

        visited[start] = true; // 시작점 방문했다고 마킹

        for (int iter = 0; iter < N; iter++) {
            // 아직 방문 안 했고 d가 최소인 정점 u 선택
            // int u = -1, min = INF;
            int current = getSmallIndex(visited, d, N);
            visited[current] = true;

            // for (int i = 0; i < N; i++) {
            //     if (!visited[i] && d[i] < min) {
            //         min = d[i];
            //         u = i;
            //     }
            // }
            
            // // 만약 모든 노드가 방문되었거나 도달 불가능해서 조건을 만족하는 노드가 하나도 없으면, 루프를 다 돌아도 u 값이 갱신되지 않고 그대로 -1이 남는다.
            // if (u == -1) break; // 더 이상 갱신 불가
            // visited[u] = true;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && d[current] + a[current][j] < d[j]) {
                    d[j] = d[current] + a[current][j]; // 작은 값으로 갱신
                }
            }
        }
    }
}
