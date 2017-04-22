public class permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> comb = new ArrayList<Integer>();
        if (nums == null) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            comb.add(nums[i]);
        }
        permute(nums, 0, comb, result);
        return result;
    }
    private void permute(int[] nums, int index, List<Integer> comb, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<Integer>(comb));
        }
        for (int i = index; i < nums.length; i++) {
            int indexint = comb.get(index);
            int iint = comb.get(i);
            comb.set(index, iint);
            comb.set(i, indexint);
            permute(nums, index + 1, comb, result);
            comb.set(index, indexint);
            comb.set(i, iint);
        }
    }
}