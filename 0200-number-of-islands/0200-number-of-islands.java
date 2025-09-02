class Solution {
    // 문제 정의: 
    // 입략 크기: 300
    // 문제 풀이
    // 수평 수직으로 0으로 둘러쌓인 개수를 구해라. 
    // 인접행렬(adjacent matrix)은 그래프를 나타내는 이차원 배열이다.
    // 0으로 둘러쌓인 1은 그래프로 생각하면 연결이 끊기 그래프이다.
    // 그렇다면 연결이 끊긴 그래프의 개수를 찾는것이다.
    // 그렇다면 그래프 탐색을 해야한다. 



    // dfs를 사용
    // 연결이 끊긴 동작을 알아내야한다. -> 부모 노드를 pop 이후 인접한 리스트들이 없을때 캬운팅
    // 1를 가진 노드에서 인접한 노드 중에서 0이 있으면 카운팅

    // bfs도 사용


    // stack으로 구현하는 dfs로 생각하니깐 로직의 호환이 안된다.
    // 인접행렬에 dfs 탐색에 대한 직관적 이해로 시작한다.

    // 문제 정의: 상하좌우로 0이나 끝점으로 둘러쌓인 1로 구성된 면적의 개수를 구해라.

    // 1을 발견하면 수직과 수평 방향으로 계속 탐색을 해서 0이나 배열의 끝점에 도달하면 카운팅을 한다. 
    // 한번 탐색한 곳은 1을 0으로 변경해서 마킹을한다. 그래프의 탐색은 인접한 노드를 모두 탐색하는 것이므로 이미 탐색한 노드는 마킹이 필요하기 때문이다.

    // 섬 개수 발견 메서드
    // 2차원 배열을 모두 순회하면서 지정한 지점으로부터 0에 도달하거나 배열 끝점에 도달했을때 카운팅을 한다.
    // 위의 로직을 재귀 메서드를 한번 호출하고 나서 카운팅을하여 마지막에 반환한다.
    public int numIslands(char[][] grid) {
        int cnt = 0;
        // 2차원 배열의 모든 시작으로 탐색을 진행 
        // 이미 탐색한 지점은 dfs 재귀 메서드에서 0으로 변경
        int n = grid.length; // 2차원 배열의 row 
        int m = grid[0].length; // 2차원 배열의 column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1을 발견하면 탐색 시작
                if (grid[i][j] == '1') {
                    dfsStack(grid, i, j); // i, j 지점부터 시작해서 수평, 수직 방향으로 dfs 탐색
                    cnt++; // dfs 호출은 재귀적으로 수평, 수직 방향으로 탐색하므로 1으로 시작해서 0이나 끝점에 도달하면 탐색이 종료되어 카운팅을 해준다.
                } 
            }
        }
        return cnt;
    }   

    // 재귀 dfs 메서드
    // dfs(int grid[][], int x, int y): 이차원 배열에 전달된 요소를 시발점으로 탐색 필요
    // 종료 시점: 0에 도달했거나 배열 끝점에 도달했을때
    // 방문 표시: 재귀 메서드 종료 조건인 0으로 변경
    // 동작: 1을 발견하면 수직과 수평 방향으로 탐색한다. 이차원 배열에서 (x+1,y), (x-1,y), (x,y-1), (x,y+1)을 시발점으로 dfs 탐색 호출
    private void dfs(char grid[][], int x, int y) {
        int n = grid.length; // 2차원 배열의 row 
        int m = grid[0].length; // 2차원 배열의 column
        
        // 재귀 메서드로 호출되므로 0이나 끝점에 도달할시 종료해야한다. (재귀 메서드 탈출 조건)
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == '0') return;

        // 방문 표시 (모든 배열 요소를 시발점으로 탐색하므로 중복 탐색을 할수있다.)
        grid[x][y] = '0'; // bfs 탈출 조건의 값으로 변경

        // 주어진 지점으로 부터 수직 수평 방향으로 탐색 (십자가 방향)
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

    private void dfsStack(char[][] grid, int startX, int startY) {
        int n = grid.length;
        int m = grid[0].length;

        // 상, 하, 좌, 우 이동 좌표
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});
        grid[startX][startY] = '0'; // 방문 표시

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int x = cur[0], y = cur[1];

            // 4방향 탐색
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1') {
                    grid[nx][ny] = '0'; // 방문 처리
                    stack.push(new int[]{nx, ny});
                }
            }
        }
    }
}