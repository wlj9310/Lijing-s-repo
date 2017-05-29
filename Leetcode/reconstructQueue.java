public class reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null) {
            return null;
        }
        if (people.length == 0) {
            return new int[0][2];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(11, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0] && a[1] == b[1]) {
                    return 0;
                }
                if (a[0] > b[0]) {
                    return -1;
                }
                if (a[0] == b[0] && a[1] < b[1]) {
                    return -1;
                }
                return 1;
            }
        });
        for (int[] cur : people) {
            pq.offer(cur);
        }
        List<int[]> temp = new LinkedList<>();
        while (pq.size() != 0) {
            int[] cur = pq.poll();
            temp.add(cur[1], cur);
        }
        int[][] result = new int[people.length][2];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}