public class Depositor {

	private Name name;
	private int SSN;

	public Depositor(Name name1, int nextInt) {
		setName(name1);
		setSSN(nextInt);
	}

	public Depositor() {
		//no arg constructor
	}

	public Name getName() {
		return name;
	}

	public void setName(Name Nam) {
		name = Nam;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

}
