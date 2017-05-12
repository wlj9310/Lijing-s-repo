public class Stringreplace {
  public String replace(String input, String s, String t) {
    // Write your solution here.
    if (s.length() >= t.length()) {
      return replaceshort(input, s, t);
    } else {
      return replacelong(input, s, t);
    }
  }
  private String replaceshort(String input, String s, String t) {
    char[] inputarray = input.toCharArray();
    char[] tarray = t.toCharArray();
    int slow = 0;
    int fast = 0;
    while (fast < input.length()) {
      if (checkpattern(input, fast, s)) {
        for (int i = 0; i < t.length(); i++) {
          inputarray[slow + i] = tarray[i]; 
        }
        slow = slow + t.length();
        fast = fast + s.length();
      } else {
        inputarray[slow++] = inputarray[fast++];
      }
    }
    return new String(inputarray, 0, slow);
  }
  private String replacelong(String input, String s, String t) {
    char[] inputarray = input.toCharArray();
    char[] tarray = t.toCharArray();
    int index = 0;
    int count = 0;
    while (index <= input.length() - s.length()) {
      if (checkpattern(input, index, s)) {
        count++;
        index = index + s.length();
      } else {
        index++;
      }
    }
    int length = input.length() + (t.length() - s.length()) * count;
    char[] output = new char[length];
    int fast = 0;
    int slow = 0;
    while (fast < input.length()) {
      if (checkpattern(input, fast, s)) {
        for (int i = 0; i < t.length(); i++) {
          output[slow + i] = tarray[i]; 
        }
        slow = slow + t.length();
        fast = fast + s.length();
      } else {
        output[slow++] = inputarray[fast++];
      }
    }
    return new String(output);
  }
  private boolean checkpattern(String input, int fast, String s) {
    char[] inputarray = input.toCharArray();
    char[] sarray = s.toCharArray();
    if (fast > input.length() - s.length()) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      if (inputarray[fast + i] != sarray[i]) {
        return false;
      }
    }
    return true;
  }
}
