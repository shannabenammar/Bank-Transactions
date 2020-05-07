
import java.util.ArrayList;

public class Account {
	private int acctNum;
	private double balance;
	private String acctType, accountStatus;
	private Depositor depositor; 
	private DateInfo date;
	private ArrayList <Transaction> transactions;
	
	public Account(int nextInt, double nextDouble, String next, String acctStatus, Depositor depositor1) {
		setAcctNum(nextInt);
		setBalance(nextDouble);
		setAcctType(next);
		setDepositor(depositor1);
		setAccountStatus(acctStatus);
		DateInfo date = new DateInfo();
		setDate(date);
		transactions = new ArrayList<>();
		settransactions(transactions);
		
	}
	public Account() {
		
	}
	public Account(Account account) {
		acctNum = account.acctNum;
		balance = account.balance;
		acctType = account.acctType;
		accountStatus = account.accountStatus;
		depositor = new Depositor();
		date = account.date;
		transactions = account.transactions;
	}
	
	public int getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(int acctNum) {
		this.acctNum = acctNum;
	}

	public double getBalance() {
		System.out.println("Balance: " + balance);
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public Depositor getDepositor() {
		return depositor;
	}

	public void setDepositor(Depositor depositor) {
		this.depositor = depositor;
	}
	
	/* Method makeDeposit()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *  maxAccts - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  index - index of account in the accounts array
	 *  amountToDeposit- amount being requested to deposit
	 * Process:
	 * Prompts for the requested account Calls findacct() to see if the
	 *  account exists If the account exists, prompts for the amount to deposit If
	 *  the amount is valid, it makes the deposit and prints the new balance
	 *  Otherwise, an error message is printed
	 *  if its a CD account it checks for maturity
	 * Output:
	 *  no ouput-output in class with main method
	 */
	public int makeDeposit(int requestedAccount, Bank bankAccount, int index, double amountToDeposit){

		double oldbalance;
		
		
		DateInfo date = new DateInfo();

		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Deposit";
		String reason = "";
		double transAmount;
		boolean outcome;
		
	if (bankAccount.getAccountIndex(index).getAcctType().equals("CD")) {

			bankAccount.getAccountIndex(index).getDate();
			int month = bankAccount.getAccountIndex(index).getDate().getMonth();
			int day = bankAccount.getAccountIndex(index).getDate().getDay();
			int year = bankAccount.getAccountIndex(index).getDate().getYear();
			int compare = bankAccount.getAccountIndex(index).getDate().compare(month, day, year);
			if (compare == 1) {
				
				oldbalance = bankAccount.getAccountIndex(index).getBalance();
				bankAccount.getAccountIndex(index).setBalance(oldbalance + amountToDeposit);

				outcome = true;
				transAmount = amountToDeposit;
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction); 
				
				return 1;
			}
			else {
				
				outcome = false;
				transAmount = 0;
				reason = "Account is a CD account that hasn't passed it maturity date";
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction); 
				
				return -1;
			}
			
		}
	else {
		if(bankAccount.getAccountIndex(index).getAccountStatus().equals("closed")) {
			
			outcome = false;
			transAmount = 0;
			reason = "Account is closed";
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
					todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction);
			
			return -2;
		}
		else {
			if(amountToDeposit <= 0.00) {
				
				outcome = false;
				transAmount = 0;
				reason = "Amount to deposit was invalid";
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction);  
				
				return -3;
			}
			else {
				
				oldbalance = bankAccount.getAccountIndex(index).getBalance();
				bankAccount.getAccountIndex(index).setBalance(oldbalance + amountToDeposit);
				
				outcome = true;
				transAmount = amountToDeposit;
				
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction);  
				
				return 2;
			}
			
		}
	}

		

	}

	/* Method makewithdrawal()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *  maxAccts - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  index - index of account in the accounts array
	 *  amountToWithdrawal- amount being requested to withdrawl
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if
	 *  the account exists If the account doesn't exist an error message is printed.
	 *  If the account does exist, withdrawal amount is requested. if account doesnt 
	 *  have enought funds error message is printed
	 *  if it is CD account it is checked for maturity
	 * Output:
	 *  no ouput-output in class with main method
	 */
	
	public int makeWithdrawal(Bank bankAccount, int index, double amountToWithdrawal) {

		double oldbalance;
	

		
		DateInfo date = new DateInfo();

		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Withdrawal";
		String reason = "";
		double transAmount;
		boolean outcome;
		
	if (bankAccount.getAccountIndex(index).getAcctType().equals("CD")) {

			bankAccount.getAccountIndex(index).getDate();
			int month = bankAccount.getAccountIndex(index).getDate().getMonth();
			int day = bankAccount.getAccountIndex(index).getDate().getDay();
			int year = bankAccount.getAccountIndex(index).getDate().getYear();
			int compare = bankAccount.getAccountIndex(index).getDate().compare(month, day, year);
			if (compare == 1) {
				
				oldbalance = bankAccount.getAccountIndex(index).getBalance();
				bankAccount.getAccountIndex(index).setBalance(oldbalance - amountToWithdrawal);

				outcome = true;
				transAmount = amountToWithdrawal;
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction); 
				
				return 1;
			}
			else {
				
				outcome = false;
				transAmount = 0;
				reason = "Account is a CD account that hasn't passed it maturity date";
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction); 
				
				return  -1;
			}
			
		}
	else {
		if(bankAccount.getAccountIndex(index).getAccountStatus().equals("closed")) {
			
			outcome = false;
			transAmount = 0;
			reason = "Account is closed";
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
					todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction);
			
			return  -2;
		}
		else {
			if(amountToWithdrawal <= 0.00 || amountToWithdrawal > bankAccount.getAccountIndex(index).getBalance()) {
				
				outcome = false;
				transAmount = 0;
				reason = "Amount to deposit was invalid";
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction);  
				
				return  -3;
			}
			else {
				
				oldbalance = bankAccount.getAccountIndex(index).getBalance();
				bankAccount.getAccountIndex(index).setBalance(oldbalance - amountToWithdrawal);
				
				outcome = true;
				transAmount = amountToWithdrawal;
				
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
						todaysYear, outcome, reason);
				bankAccount.getAccountIndex(index).addTransactions(transaction);  
				
				return  2;
			}
		}
	}

	}
	/*
	 * method clearCheck()
	 * input:
	 * 	month-month be checked for
	 * 	day-day if the date being checked
	 * 	year- year of the date being checked
	 * process:
	 * 	sends the date of the check being cleared to the compare date 
	 * 	method to make sure the check is within 6 month time 
	 * output:
	 * 	no output- all output in clas with main method
	 */
	public int clearCheck(Bank bankAccount, int account, double checkB, int month, int day, int year) {

		//int result; 
		int exp;
		double oldbalance = getBalance();
		String transType = "Clear Check";
		String reason = "";
		double transAmount;
		Boolean outcome; 
		int Tmonth, Tday, Tyear;
		Tmonth = getDate().getTodaysMonth();
		Tday = getDate().getTodaysDay();
		Tyear = getDate().getTodaysYear();
		
		
		int index = bankAccount.findAcct(account);
		if(index == -1){
			
			outcome = false;
			transAmount = 0;
			reason = "Account doesn't exist";
			Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
					outcome, reason);
			addTransactions(transaction);
			
			return -1;
		}
		
		else if(getAcctType().equals("checking")) {
				exp = getDate().compareCheck(month,day,year);
				if (exp == -1) {
					setBalance(oldbalance - 2.50);
					
					outcome = false;
					transAmount = -2.50;
					reason = "The check has expired";
					Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
							outcome, reason);
					addTransactions(transaction);
					
				
					return -3;
				}
				else if(getAccountStatus().equals("closed")) {
						setBalance(oldbalance - 2.50);
						
						outcome = false;
						transAmount = -2.50;
						reason = "Account is closed";
						Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
								outcome, reason);
						addTransactions(transaction);
						
						
						return -4;
					}
					else if(oldbalance < checkB) {
							setBalance(oldbalance - 2.50);
							
			 				outcome = false;
							transAmount = -2.50;
							reason = "Insufficent funds";
							Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
									outcome, reason);
							addTransactions(transaction);
							
							
							return -5;
						}
						
						outcome = true;
						transAmount = checkB;
						reason = "Check was cleared and balance was sufficent";
						Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
								outcome, reason);
						addTransactions(transaction);
						setBalance(oldbalance - checkB);
						
						return 1;
					}
				
			
			else {
				
				outcome = false;
				transAmount = -2.50;
				reason = "Account was not a checkings account";
				Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
						outcome, reason);
				addTransactions(transaction);
				setBalance(oldbalance - 2.50);
				
				return -2;
			}
		
		
		
		
	}

	public DateInfo getDate() {
		return date;
	}

	public void setDate(DateInfo date) {
		this.date = date;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public ArrayList <Transaction> getTransaction() {
		return transactions;
	}
	
	public Transaction getTransactionsIndex(int index) {
		return transactions.get(index);
	}
    public void settransactions(ArrayList<Transaction> transactions){
    	this.transactions = transactions;
    }
	public void addTransactions(Transaction newTransaction) {
		transactions.add(newTransaction);
	}
	
	public boolean closeAccts(Bank bankAccount, int index, int requestedAccount) {
		bankAccount.getAccountIndex(index).setAccountStatus("closed");
		if(bankAccount.getAccountIndex(index).getAccountStatus().equals("closed")) {
		return true;
		}
		else {
			return false;
		}
	}
	public boolean reopenAcct(Bank bankAccount, int index, int requestedAccount) {
		
		bankAccount.getAccountIndex(index).setAccountStatus("open");
		if(bankAccount.getAccountIndex(index).getAccountStatus() == "open") {
		return true;
		}
		else {
			return false;
		}
	}

	public int getTransSize() {
		return transactions.size();
	}

	
}
