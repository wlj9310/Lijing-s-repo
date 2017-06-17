public class findKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            heap.offer(i);
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = heap.poll();
        }
        return result;
    }
}