//Given a Matrix of integers, how to find the submatrix with the largest sum
public class findMaxSubmatrix {
	public int findMaxSubmatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] M = new int [row][col];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (i == 0) {
					M[j][i] = matrix[j][i];
				} else {
					M[j][i] = M[j][i - 1] + matrix[j][i];
				}
			}
		}
		for (int j = 1; j < row; j++) {
			for (int i = 0; i < col; i++) {
				M[j][i] = M[j - 1][i] + M[j][i];
			}
		}
		int[][] M1 = new int[row + 1][col + 1];
		for (int i = 0; i < row + 1; i++) {
			for (int j = 0; j < col + 1; j++) {
				if (i == 0 || j == 0) {
					M1[i][j] = 0;
				} else {
					M1[i][j] = M[i - 1][j - 1];
				}
			}
		}
		int max = M[0][0];
		for (int i = 1; i <= col; i++) {
			for (int j = 1; j <= row; j++) {
				for (int m = 0; m < i; m++) {
					for (int n = 0; n < j; n++) {
						int cur = (M1[j][i] - M1[n][i] - M1[j][m] + M1[n][m]);
						max = max > cur ? max : cur;
					}
				}
			}
		}
		return max;
	}
}