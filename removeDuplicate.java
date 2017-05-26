//Char Deduplication
//remove duplicated & adjacent letters in a string
public class removeDuplicate {
	public String removeDuplicate (String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		int slow = 1;
		int fast = 1;
		while (fast < array.length) {
			if (array[fast] == array[slow - 1]) {
				fast++;
			} else {
				array[slow++] = array[fast++];
			}
		}
		char[] result = new char[slow];
		for (int i = 0; i < slow; i++) {
			result[i] = array[i];
		}
		return new String(result);
	}
}