public class Name {

	private String first;
	private String last;

	public Name(String first1, String last1) {
		first = first1;
		last = last1;
	}

	public Name() {
		//no arg constructor
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
