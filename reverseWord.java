//reverse 
public class reverseWord {
	public String reverseWord (String input) {
		//Assumptions:
		//Words are separated by one space character
		//No leading or trailing space
		int start = 0;
		int end = 0;
		char[] array = input.toCharArray();
		while (end < array.length) {
			start = end;
			while (end < array.length && array[end] != ' ') {
				end++;
			} 
			reverse(array, start, end - 1);
			end++; 
		}
		reverse(array, 0, array.length - 1);
		return new String(array);
	}
	private void reverse (char[] array, int left, int right) {
		for (int i = 0; i < (right - left) / 2; i++) {
			char temp = array[left + i];
			array[left + i] = array[right - i];
			array[right - i] = temp;
		}
	}
}