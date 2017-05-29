public class minMoves2 {
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int median = nums.length % 2 != 0 ? nums[nums.length / 2] : (nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2;
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(median - num);
        }
        return moves;
    }
}