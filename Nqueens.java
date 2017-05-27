//NQueens
public class NQueens {
	public List<List<Integer>> Nqueens(int n) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> comb = new ArrayList<>();
		nqueens(result, comb, 0, n);
		return result;
	}
	private void nqueens(List<List<Integer>> result, List<Integer> comb, int index, int n) {
		if (index == n) {
			result.add(new ArrayList<Integer>(comb));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (check(comb, i, index)) {
				comb.add(i);
				nqueens(result, comb, index + 1, n);
				comb.remove(index);
			}
		}
	}
	private boolean check(List<Integer> comb, int i, int index) {
		for (int j = 0; j < index; j++) {
			if (comb.get(j) == i || comb.get(j) - i == j - index || comb.get(j) - i == index - j) {
				return false;
			}
		} 
		return true;
	}
}