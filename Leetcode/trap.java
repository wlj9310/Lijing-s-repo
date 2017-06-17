public class trap {
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int l = height[0];
        int r = height[height.length - 1];
        for (int i = 1; i < height.length - 1; i++) {
            l = Math.max(l, height[i]);
            r = Math.max(r, height[height.length - i - 1]);
            left[i] = l;
            right[height.length - i - 1] = r;
        }
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int h = Math.min(right[i], left[i]);
            if (h > height[i]) {
                result += h - height[i];
            }
        }
        return result;
    }
}