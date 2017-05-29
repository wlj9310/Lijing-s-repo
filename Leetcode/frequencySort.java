public class frequencySort {
    public String frequencySort(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() <= 1) {
            return s;
        }
        Map<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = set.get(s.charAt(i));
            if (count == null) {
                set.put(s.charAt(i), 1);
            } else {
                set.put(s.charAt(i), count + 1);
            }
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(11, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                if (a.getValue() == b.getValue()) {
                    return 0;
                }
                return a.getValue() > b.getValue() ? -1 : 1;
            }
        });
        for (Map.Entry<Character, Integer> cell : set.entrySet()) {
            pq.offer(cell);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> cell = pq.poll();
            Character c = cell.getKey();
            Integer count = cell.getValue();
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}