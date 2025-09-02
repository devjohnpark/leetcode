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



    // 문제 정의: 0이나 끝점으로 둘러쌓인 1로 구성된 면적의 개수를 구해라.

    // stack으로 구현하는 dfs로 생각하니깐 호환이 안된다.
    // 인접행렬을 직관적으로 이해가 필요하다.

    // 1을 발견하면 수직과 수평 방향으로 탐색해야한다. 이차원 배열에서 x + 1, x -1, y -1, y + 1으로 이동
    // 0이나 배열의 끝점에 도달하면 카운팅을 한다. 한번 도달한 곳은 1을 0으로 변경해서 마킹을한다. 이미 탐색한 지점일수있기 때문이다.

    // 섬 발견 메서드
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
                    dfs(grid, i, j); // i, j 지점부터 시작해서 수평, 수직 방향으로 dfs 탐색
                    cnt++; // dfs 호출은 재귀적으로 수평, 수직 방향으로 탐색하므로 1으로 시작해서 0이나 끝점에 도달하면 탐색이 종료되어 카운팅을 해준다.
                } 
            }
        }
        return cnt;
    }   

    // 재귀 메서드
    // dfs(int grid[][], int x, int y): 이차원 배열에 전달된 요소를 시발점으로 탐색 필요
    // 탈출 시점: 0에 도달했거나 배열 끝점에 도달했을때
    // 1을 발견하면 수직과 수평 방향으로 탐색한다. 이차원 배열에서 x + 1, x -1, y -1, y + 1
    private void dfs(char grid[][], int x, int y) {
        int n = grid.length; // 2차원 배열의 row 
        int m = grid[0].length; // 2차원 배열의 column
        
        // 재귀 메서드로 호출되므로 0이나 끝점에 도달할시 종료해야한다. (재귀 메서드 탈출 조건)
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == '0') return;

        // 방문 표시 (모든 배열 요소를 시발점으로 탐색하므로 중복 탐색을 할수있다.)
        // bfs 탈출 조건의 값으로 변경
        grid[x][y] = '0'; 

        // 주어진 지점으로 부터 수직 수평 방향으로 탐색 (십자가 방향)
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }













    // public int numIslands(char[][] grid) {
    //     int m = grid.length, n = grid[0].length;
    //     int count = 0;
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (grid[i][j] == '1') { // 땅을 발견 
    //                 dfs(grid, i, j); // 땅으로부터 확장 탐색 (재귀 호출 필요)
    //                 count++; // 땅을 발견할시 카운팅
    //             }
    //         }
    //     }
    //     return count;
    // }

    // // 재귀 메서드
    // private void dfs(char[][] grid, int x, int y) {
    //     // grid.length: 행(row)의 개수 
    //     // grid[0].length: 0번째 행의 열(column) 개수
    //     int m = grid.length, n = grid[0].length;

    //     if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') return; // 끝면이나 물에 도달하면 종료

    //     grid[x][y] = '0'; // 방문 처리 (mark): 방문 처리를 해야지 모든 배열의 요소를 탐색 지점으로 설정했을때 다시 방문하지 않는다.

    //     // 수직, 수평으로 탐색
    //     dfs(grid, x + 1, y); 
    //     dfs(grid, x - 1, y);
    //     dfs(grid, x, y + 1);
    //     dfs(grid, x, y - 1);
    // }
}