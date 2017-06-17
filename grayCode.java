public class grayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int index = 1;
        while (n > 0) {
            for (int i = result.size() - 1; i >=0; i--) {
                int cur = result.get(i);
                cur += index;
                result.add(cur);
            } 
            index *= 2;
            n--;
        }
        return result;
    }
}