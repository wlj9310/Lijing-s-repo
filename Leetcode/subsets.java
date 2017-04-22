public class subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> comb = new ArrayList<Integer>();
        if (nums == null) {
            return result;
        }
        subsets(nums, comb, 0, result);
        return result;
    }
    private void subsets(int[] nums, List<Integer> comb, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<Integer>(comb));
            return;
        }
        subsets(nums, comb, index + 1, result);
        comb.add(nums[index]);
        subsets(nums, comb, index + 1, result);
        comb.remove(comb.size() - 1);
    }
}