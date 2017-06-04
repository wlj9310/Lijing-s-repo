class ListIterator {
	private List<String> in;
	private index;
	public ListIterator(List<String> in) {
		this.in = in;
		index = 0;
	}
	public boolean hasNext() {
		return index < in.size();
	}
	public String next() {
		String next = in.get(index);
		index++;
		return next;
	}
}