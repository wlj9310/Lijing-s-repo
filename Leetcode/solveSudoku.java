public class solveSudoku {
    public void solveSudoku(char[][] board) {
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    positions.add(new int[]{i, j});
                }
            }
        }
        char[][] solution = new char[9][9];
        solveSudoku(board, positions, 0, solution);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = solution[i][j];
            }
        }
    }
    private void solveSudoku(char[][] board, List<int[]> positions, int cur, char[][] solution) {
        if (cur == positions.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    solution[i][j] = board[i][j];
                }
            }
            return;
        }
        for (char c = '1'; c <= '9'; c++) {
            int[] pos = positions.get(cur);
            if (check(board,pos, c)) {
                board[pos[0]][pos[1]] = c;
                solveSudoku(board, positions, cur + 1, solution);
                board[pos[0]][pos[1]] = '.';
            }
        }
    }
    private boolean check(char[][] board, int[] position, char cur) {
        int row = position[0];
        int col = position[1];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == cur) {
                return false;
            }
            if (board[i][col] == cur) {
                return false;
            }
        }
        int r = row / 3;
        int c = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r * 3 + i][c * 3 + j] == cur) {
                    return false;
                }
            }
        }
        return true;
    }
}