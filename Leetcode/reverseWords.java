public class reverseWords {
    public String reverseWords(String input) {
        // Write your solution here.
        char[] chararray = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (true) {
            while (fast < chararray.length && chararray[fast] == ' ') {
                fast++;
            }
            slow = fast;
            while (fast < chararray.length && chararray[fast] != ' ') {
                fast++;
            }
            swap(chararray, slow, fast - 1);
            if (fast == chararray.length) {
                break;
            }
        }
        swap(chararray, 0, chararray.length - 1);
        fast = 0;
        slow = 0;
        int wordcount = 0;
        while (true) {
            while (fast < input.length() && chararray[fast] == ' ') {
                fast++;
            }
            if (fast == input.length()) {
                break;
            }
            if (wordcount > 0) {
                chararray[slow++] = ' ';
            }
            while (fast < input.length() && chararray[fast] != ' ') {
                chararray[slow++] = chararray[fast++];
            }
            wordcount++;
        }
        char[] array = new char[slow];
        for (int i = 0; i < slow; i++) {
            array[i] = chararray[i];
        }
        return new String(array);
    }

    private void swap(char[] array, int i, int j) {
        while (i < j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }
}
