public class findAnagrams {
	public List<Integer> findAnagrams(String s, String l) {
		List<Integer> result = new ArrayList<>();
		if (s.length() > l.length()) {
			return result;
		}
		Map<Character, Integer> map = new HashMap<>();
		int match = 0;
		for (int i = 0; i < s.length(); i++) {
			Integer count = map.get(s.charAt(i));
			if (count == null) {
				map.put(s.charAt(i), 1);
			} else {
				map.put(s.charAt(i), count + 1);
			}
		}
		for (int i = 0; i < l.length(); i++) {
			if (map.containsKey(l.charAt(i))) {
				Integer count = map.get(l.charAt(i));
				if (count == 1) {
					match++;
				}
				map.put(l.charAt(i), count - 1);
			}
			if (i >= s.length()) {
				Integer count = map.get(l.charAt(i - s.length()));
				if (count != null) {
					if (count == 0) {
						match--;
					}
					map.put(l.charAt(i - s.length()), count + 1);
				}
			}
			if (match == map.size()) {
				result.add(i - s.length() + 1);
			}
		}
		return result;
	}
}