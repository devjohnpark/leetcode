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
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') return;

        grid[x][y] = '0'; // 방문 처리 (mark)

        // 수직, 수평으로 탐색
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }
}