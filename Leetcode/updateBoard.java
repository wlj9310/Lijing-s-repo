public class updateBoard {
    public char[][] updateBoard(char[][] board, int[] click) {
        char[][] result = board.clone();
        if (board[click[0]][click[1]] == 'M') {
            result[click[0]][click[1]] = 'X';
            return result;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int mcount = 0;
            int row = cur[0];
            int col = cur[1];
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                        if (board[i][j] == 'M') {
                            mcount++;
                        } 
                    }
                }
            }
            if (mcount == 0) {
                result[row][col] ='B';
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                            if (board[i][j] == 'E') {
                                q.offer(new int[]{i, j});
                                board[i][j] = 'B';
                            } 
                        }
                    }
                }
            } else {
                result[row][col] =(char) (mcount + '0');
            }
            
        }
        return result;
    }
}
