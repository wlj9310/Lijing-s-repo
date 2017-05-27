//Deduplicate adjacent letters repeatedly
public class repeatedlyDeduplicate {
	public String repeatedlyDeduplicate (String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		int slow = 1;
		int fast = 1;
		while (fast < array.length) {
			char cur = array[slow - 1];
			boolean dedup = false;
			while (fast < array.length && array[fast] == cur) {
				dedup = true;
				fast++;
			}
			if (dedup == true) {
				slow--;
			}
			if (fast < array.length && (slow == 0 || array[slow - 1] != array[fast])) {
				array[slow++] = array[fast++];
			}
		}
		return new String(array, 0, slow);
	}
}