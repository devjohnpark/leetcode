class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        // 전체 연료 양이 전체 비용보다 적으면 애초에 불가능
        int totalFuel = 0;
        for (int i = 0; i < n; i++) {
            totalFuel += gas[i] - cost[i];
        }
        if (totalFuel < 0) return -1;

        // 유일한 해가 있는 경우
        int fuel = 0;
        int start = 0; 
        for (int i = 0; i < n; i++) {
            fuel  += gas[i] - cost[i];

            // 현재까지의 출발점은 모두 불가능: i까지 오는 경로에서 이미 연로가 부족하면 그 이전에 시작해도 반드시 실패한다.
            // 시작점 = 0, 누적 연료 적자 지점 = 2일때 실패
            // 시작점 = 1, 누적 연료 적자 지점 = 2일때 실패
            // diff = [ 1, 4, -6, ... ]
            // 누적 = [ 1, 5, -1, ... ] 

            // 최초 0에서 부족
            // diff = [ -1, +4, -4, ... ]
            // 누적 = [ -1, 3, -1, ... ] 
            if (fuel < 0) { 
                start = i + 1;
                fuel = 0; 
            }
        }
        return start;
    }
}