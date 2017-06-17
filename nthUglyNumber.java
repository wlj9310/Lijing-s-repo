public class nthUglyNumber {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        heap.offer(1l);
        long cur = 1;
        set.add((long) 1);
        while (n > 0) {
            cur = heap.poll();
            if (!set.contains(cur * 2)){
                heap.offer(cur * 2);
                set.add(cur * 2);
            }
            if (!set.contains(cur * 3)){
                heap.offer(cur * 3);
                set.add(cur * 3);
            }
            if (!set.contains(cur * 5)){
                heap.offer(cur * 5);
                set.add(cur * 5);
            }
            n--;
        }
        return (int) cur;
    }
}