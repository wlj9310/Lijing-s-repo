public class convert {
    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        if (s.length() <= 2 || numRows <= 1) {
            return s;
        }
        int circle = numRows * 2 - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length() / circle; i++) {
            if (circle * i < s.length()) {
                sb.append(s.charAt(circle * i));
            }
        }
        for (int j = 1; j < numRows - 1; j++) {
            for (int i = 0; i <= s.length() / circle; i++) {
                if (circle * i + j < s.length()) {
                    sb.append(s.charAt(circle * i + j));
                }
                if (circle * (i + 1) - j < s.length()) {
                    sb.append(s.charAt(circle * (i + 1) - j));
                }
            }
        }
        for (int i = 0; i <= s.length() / circle; i++) {
            if (circle * i + numRows - 1 < s.length()) {
                sb.append(s.charAt(circle * i + numRows - 1));
            }
        }
        return sb.toString();
    }
}