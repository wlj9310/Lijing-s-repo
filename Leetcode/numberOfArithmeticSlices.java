public class numberOfArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        }
        int total = 0;
        int cur = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur++;
            } else {
                total += cur * (cur + 1) / 2;
                cur = 0;
            }
        }
        return total + cur * (cur + 1) / 2;
    }
}