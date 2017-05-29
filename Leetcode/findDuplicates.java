public class findDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null) {
            return null;
        }
        if (nums.length <= 1) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            cur = cur > 0 ? cur : - cur;
            if (nums[cur - 1] < 0) {
                result.add(cur);
            } else {
                nums[cur - 1] = -nums[cur - 1];
            }
        }
        return result;
    }
}