// 시간복잡도: O(nlogn)
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, count = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) { // 해당 쿠키로 이 아이를 만족
                count++;
                i++; j++;
            } else {
                j++; // 더 큰 쿠키로 시도
            }
        }
        return count;
    }
}