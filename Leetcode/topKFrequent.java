public class topKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], count + 1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(11, new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (a.getValue() == b.getValue()) {
                    return 0;
                }
                return a.getValue() > b.getValue() ? -1 : 1;
            }
        });
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(e);
        }
        for (int i = 0; i < k; i++) {
            result.add(pq.poll().getKey());
        }
        return result;
    }
}