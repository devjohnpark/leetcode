// n번째 계단을 오르는 방법은 그전까지 계단을 오르는 방식의 누적된 결과물이다.
// n번째 계단에 도달하는 방법의 수는 n-1번째 계단에서 1칸 올라오거나 n-2번째 계단에서 2칸 올라오는 경우이다.
// 따라서 점화식은 f(n) = f(n-1) + f(n-2)이다. 
// 문제의 n >= 1 조건과 점화식의 f(n-2) 때문에, 점화식은 n > 2일때부터 성립한다. 그리고 초기 조건(base case)은 n=1일때 1이며, n=2일때 2이다.
// 계단이 1칸(n = 1)이라면 올라가는 방법은 1가지이고, 계단이 2칸(n = 2)이라면 경우의 수는 2가지이기 때문이다.
class Solution {
    int[] d = new int[46];     
    public int climbStairs(int n) {
        if (n == 1) return 1; 
        if (n == 2) return 2; 
        if(d[n] != 0) return d[n];
        return d[n] = climbStairs(n-1) + climbStairs(n-2);
    } 
}