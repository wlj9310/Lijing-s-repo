//找最大空心正方形
//Given a matrix where every element is either '0' or '1', find the largest subsquare surrounded by '1'
public class Solution {
	public int findSquare(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int n = matrix.length;
		if (n == 1) {
			return matrix[0][0];
		}
		int[][] M1 = new int[n][n];
		int[][] M2 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if (j == n - 1) {
					M1[i][j] = matrix[i][j];
					M2[j][i] = matrix[j][i];
				} else {
					M1[i][j] = matrix[i][j] == 0 ? 0 : M1[i][j + 1] + 1;
					M2[j][i] = matrix[j][i] == 0 ? 0 : M2[j + 1][i] + 1;
				}
			}
		}
		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int local_max = 0;
				for (int l = 1; l <= Math.min(n - i, n - j); l++) {
					local_max = (M1[i][j] >= l) && (M2[i][j] >= l) && (M2[i][j + l - 1] >= l) && (M1[i + l - 1][j] >= l) ? l : local_max;
				}
				max = max > local_max ? max : local_max;
			}
		}
		return max;
	}
}