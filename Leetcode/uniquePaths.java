public class uniquePaths {
    public int uniquePaths(int m, int n) {
        long result = 1;
        int s = Math.max(m, n);
        for ( int i = m + n - 2; i >= s; i--){
                result = result * i;
                result = result / (m + n - 1 - i);
        }
        return (int) result;
    }
}