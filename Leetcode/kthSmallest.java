public class kthSmallest {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Cell> minheap = new PriorityQueue<Cell>(k);
        boolean[][] used = new boolean[matrix.length][matrix.length];
        used[0][0] = true;
        minheap.offer(new Cell(0, 0, matrix[0][0]));
        int result = matrix[0][0];
        for(int i = 0; i < k; i++) {
            Cell cell = minheap.poll();
            if (cell.row + 1 < n && used[cell.row + 1][cell.col] == false) {
                minheap.offer(new Cell(cell.row + 1, cell.col, matrix[cell.row + 1][cell.col]));
                used[cell.row + 1][cell.col] = true;
            }
            if (cell.col + 1 < n && used[cell.row][cell.col + 1] == false) {
                minheap.offer(new Cell(cell.row, cell.col + 1, matrix[cell.row][cell.col + 1]));
                used[cell.row][cell.col + 1] = true;
            }
            result = cell.value;
        }
        return result;
    }
}

class Cell implements Comparable<Cell> {
    int row;
    int col;
    int value;
    public Cell(int row, int col, int value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }
    @Override
    public int compareTo(Cell another) {
        if (this.value == another.value) {
            return 0;
        }
        return this.value < another.value? -1:1;
    }
}