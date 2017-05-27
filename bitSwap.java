//How to reverse all bits of an integer?
public class bitSwap {
	public int bitSwap(int num) {
		for (int i = 0; i < 16; i++) {
			int right = 1 << i;
			int left = 1 << (31 - i);
			if (((num & right) >>> i) == ((num & left) >>> (31 - i))) {
				continue;
			} else {
				num = num ^ right ^ left;
			}
		}
		return num;
	}
}