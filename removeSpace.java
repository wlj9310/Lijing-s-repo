//remove all leading/trailing and duplicate empty spaces
public class removeSpace {
	public String removeSpace(String input) {
		if (input == null) {
			return null;
		}
		char[] array = input.toCharArray();
		int word_count = 0;
		int slow = 0;
		int fast = 0;
		while (true) {
			while (fast < array.length && array[fast] == ' ') {
				fast++;
			}
			if (fast == array.length) {
				break;
			}
			if (word_count != 0) {
				array[slow++] = ' ';
			}
			while (fast < array.length && array[fast] != ' ') {
				array[slow++] = array[fast++];
			}
			word_count++;
		}
		char[] result = new char[slow];
		for (int i = 0; i < slow; i++) {
			result[i] = array[i];
		}
		return new String(result);
	}
}