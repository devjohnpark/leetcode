// 시간복잡도
// 모든 경로에 대한 시간 복답도는 

// 1이 벽이나 0으로 둘러쌓인 개수를 구해라.
// 모든 경로를 탐색하는 문제이므로 dfs 풀이가 적합하다.
// dfs는 하나의 경로가 완성될때까지 탐색하기 때문이다.
// 0이나 벽이 아닐 경우 상하좌우 4방향으로 dfs 탐색을 돌린다.
// 벽은 어떻게 인지하나? 인덱스의 범위를 초과하면 벽이다. 0 < 인덱스, 인덱스 >= 배열의 길이 
class Solution {
   
    public int numIslands(char[][] grid) {
        // 2차원 배열을 순회하면서 요소값이 1이면 dfs 탐색 시작
        // 탐색 시작 지점이 1이면 dfs 탐색을 완료한것치므로 경로 하나 찾은것과 마찬가지이다. 따라서 1추가
        // 재귀 dfs 메서드로 인덱스 i, j로 상하좌우 이동을 한다.
        // 재귀 메서드에서 방문 표시를 하기 위해 탐색 지점 0으로 변경
        // 인덱스가 0미만, 배열 길이 초과, 탐색 지점 0이면 리턴해서 종료
        
        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }   

    private void dfs(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;

        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') return;

        grid[x][y] = '0';

        dfs(grid, x + 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x - 1, y);
        dfs(grid, x, y - 1);
    }
}