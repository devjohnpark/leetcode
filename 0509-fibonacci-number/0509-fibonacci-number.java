// 시간 복잡도 고려사항
// 입력크기: n = 30
// 점화식에 대한 시간복잡도: 2^n = 2^50

// 문제 풀이 아이디어
// 분할 정복으로 푼 피보나치 수열 피보나치 수열은 시간복잡도가 2^50이 나온다.
// 단순 분할정복으로 피보나치 수열을 푼다면, 동일한 문제를 다시 풀게된다.
// 따라서 중복된 연산결과를 미리 저장해서 반환하도록한다.
class Solution {
    // recursive + dp
    private int d[] = new int[31]; // 0 ~ 30: size = 31

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        // if (d[n] != 0) return d[n];
        // return d[n] = fib(n - 1) + fib(n - 2);
        return fib(n - 1) + fib(n - 2);
    }

    // // iterative
    // public int fib(int n) {
    //     if (n == 0) return 0;
    //     if (n == 1) return 1;

    //     int a = 0; // F(0)
    //     int b = 1; // F(1)

    //     // a b c
    //     // 0 1 1 2 3 5
    //     // a = 0, a = b
    //     // b = 1, b = c
    //     for (int i = 1; i < n; i++) {
    //         int c = a + b; // f(n)
    //         a = b; // f(n - 1)
    //         b = c; // f(n - 2)
    //     }

    //     return b;
    // }
}