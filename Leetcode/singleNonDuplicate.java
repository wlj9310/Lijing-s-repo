public class singleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right - 2) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[mid + 1] && mid % 2 == 0) {
                left = mid;
            } else if (nums[mid] == nums[mid + 1] && mid % 2 == 1) {
                right = mid + 1;
            } else if (mid % 2 == 1) {
                left = mid - 1;
            } else {
                right = mid;
            }
        }
        return nums[left] ^ nums[left + 1] ^ nums[right];
    }
}