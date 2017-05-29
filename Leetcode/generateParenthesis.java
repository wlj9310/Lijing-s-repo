public class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        generateParenthesis(result, sb, 0, 0, n);
        return result;
    }
    private void generateParenthesis(List<String> result, StringBuilder sb, int index, int left, int n) {
        if (index == 2 * n) {
            result.add(sb.toString());
            return;
        }
        int right = index - left;
        if (left < n) {
            sb.append("(");
            generateParenthesis(result, sb, index + 1, left + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            sb.append(")");
            generateParenthesis(result, sb, index + 1, left, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}