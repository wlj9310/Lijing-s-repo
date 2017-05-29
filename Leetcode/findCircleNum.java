public class findCircleNum {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int size = 0;
        boolean[] visited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == false) {
                size++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    visited[cur] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (visited[j] == false && M[cur][j] == 1) {
                            queue.offer(j);
                        }
                    }
                }
            }
        }
        return size;
    }
}