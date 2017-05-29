public class optimalDivision {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return nums[0] + "";
        }
        if (nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0] + "/(");
        for (int i = 1; i < nums.length - 1; i++) {
            sb.append(nums[i] + "/");
        }
        sb.append(nums[nums.length - 1] + ")");
        return sb.toString();
    }
}