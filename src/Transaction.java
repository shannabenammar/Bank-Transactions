public class Transaction {
	
	private String transType, reason;
	private double transAmount;
	private int transMonth, transDay, transYear;
	private boolean success, failure;
	
	
	public Transaction(String transType, double transAmount, int transMonth, int transDay,
			int transYear, boolean outCome, String reason) {
		
		setTransType(transType);
		setTransAmount(transAmount);
		setTransMonth(transMonth);
		setTransYear(transYear);
		setTransDay(transDay);
		
		
		if (outCome == true) {
			setSuccess(outCome);
		}
		else {
			setFailure(outCome);
			setReason(reason);
		}
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

	public int getTransMonth() {
		return transMonth;
	}

	public void setTransMonth(int transMonth) {
		this.transMonth = transMonth;
	}

	public int getTransDay() {
		return transDay;
	}

	public void setTransDay(int transDay) {
		this.transDay = transDay;
	}

	public int getTransYear() {
		return transYear;
	}

	public void setTransYear(int transYear) {
		this.transYear = transYear;
	}
 	public String getOutcome() {
	 
	 if (isSuccess() == true) {
		 return "Success";
	 }
	 else return "failure";
 	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isFailure() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
