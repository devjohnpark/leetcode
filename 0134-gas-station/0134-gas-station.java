class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int fuel  = 0;   
        int start = 0; 

        for (int i = 0; i < n; i++) {
            fuel += gas[i] - cost[i];
        }

        if (fuel < 0) return -1;

        fuel = 0;

        for (int i = 0; i < n; i++) {
            fuel  += gas[i] - cost[i];
            if (fuel < 0) {
                start = i + 1;
                fuel = 0; 
            }
        }
        return start;
    }
}