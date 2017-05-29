public class nextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
           return new int[0];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = -1;
            for (int j = i + 1 + nums.length; j < i + 2 * nums.length; j++) {
                if (nums[j % nums.length] > nums[i]) {
                    result[i] = nums[j % nums.length];
                    break;
                }
            }
        }
        return result;
    }
}