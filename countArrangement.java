public class countArrangement {
    public int countArrangement(int N) {
        int[] count = new int[1];
        count[0] = 0;
        int[] comb = new int[N];
        countArrangement(comb, 0, N, count);
        return count[0];
    }
    private void countArrangement(int[] comb, int index, int num, int[] count) {
        if (index == num) {
            count[0]++;
            return;
        }
        for (int i = 1; i <= num; i++) {
            if (check(comb, index, i, num)) {
                comb[index] = i;
                countArrangement(comb, index + 1, num, count);
            }
        }
    }
    private boolean check(int[] comb, int index, int i, int num) {
        for (int j = 0; j < index; j++) {
            if (comb[j] == i) {
                return false;
            }
        }
        if (i % (index + 1) == 0 || (index + 1) % i == 0) {
            return true;
        }
        return false;
    }
}