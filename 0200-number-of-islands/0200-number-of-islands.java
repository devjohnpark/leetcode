class Solution {
    // 문제 정의: 상하좌우로 0이나 끝점으로 둘러쌓인 1로 구성된 면적의 개수를 구해라.
    // 그래프 탐색 문제 핵심 사항: 문제를 보고 어떤 방향으로 탐색할건지 정의를 해야한다. 이 문제에서는 순회 지점으로 부터 십자가 형태로 (x+1,y), (x-1,y), (x,y-1), (x,y+1)으로 탐색을 해야한는 문제이다.

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
                    dfsRursive(grid, i, j); // i, j 지점부터 시작해서 수평, 수직 방향으로 dfs 탐색
                    cnt++; // dfs 호출은 재귀적으로 수평, 수직 방향으로 탐색하므로 1으로 시작해서 0이나 끝점에 도달하면 탐색이 종료되어 카운팅을 해준다.
                } 
            }
        }
        return cnt;
    }   

    // 재귀 dfsR 메서드
    // dfs(int grid[][], int x, int y): 이차원 배열에 전달된 요소를 시발점으로 탐색 필요
    // 종료 시점: 0에 도달했거나 배열 끝점에 도달했을때
    // 방문 표시: 재귀 메서드 종료 조건인 0으로 변경
    // 동작: 1을 발견하면 수직과 수평 방향으로 탐색한다. 이차원 배열에서 (x+1,y), (x-1,y), (x,y-1), (x,y+1)을 시발점으로 dfs 탐색 호출
    private void dfsRursive(char grid[][], int x, int y) {
        int n = grid.length; // 2차원 배열의 row 
        int m = grid[0].length; // 2차원 배열의 column
        
        // 재귀 메서드로 호출되므로 0이나 끝점에 도달할시 종료해야한다. (재귀 메서드 탈출 조건)
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == '0') return;

        // 방문 표시 (모든 배열 요소를 시발점으로 탐색하므로 중복 탐색을 할수있다.)
        grid[x][y] = '0'; // bfs 탈출 조건의 값으로 변경

        // 주어진 지점으로 부터 수직 수평 방향으로 탐색 (십자가 방향)
        dfsRursive(grid, x - 1, y);
        dfsRursive(grid, x + 1, y);
        dfsRursive(grid, x, y - 1);
        dfsRursive(grid, x, y + 1);
    }

    // 오히려 스택으로 푸는 것이 더 어렵다.
    private void dfsStack(char[][] grid, int startX, int startY) {
        int n = grid.length;
        int m = grid[0].length;

        // 상, 하, 좌, 우 이동 좌표
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 스택 생성: 스택에 x와 y좌표를 저장해야하므로 int[]을 저장
        // 스택에 지정된 노드와 인접한 노드들을 모두 푸시와 합을하면서 탐색한다. 그러면 자식 노드끝까지 들어가다가 상위 노드로 올라가게된다.
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});
        grid[startX][startY] = '0'; // 방문 표시

        // 십자가 방향으로 인접한 노드를 탐색
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int x = cur[0], y = cur[1];
            // 4방향 탐색을 끝에 도달할때까지 반복
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                // 1을 발견하면 인접한 노드(섬)이므로 스택에 추가
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1') {
                    grid[nx][ny] = '0'; // 방문 처리
                    stack.push(new int[]{nx, ny});
                }
            }
        }
    }

    private void bfs(char[][] grid, int startX, int startY) {
        int n = grid.length;
        int m = grid[0].length;

         // 상, 하, 좌, 우 이동 좌표
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{startX, startY});
        grid[startX][startY] = '0'; // 방문
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[0], y = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                // 1을 발견하면 인접한 노드(섬)이므로 스택에 추가
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1') {
                    grid[nx][ny] = '0'; // 방문 처리
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}