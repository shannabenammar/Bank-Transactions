
import java.util.ArrayList;

public class Bank {

	
	private ArrayList <Account> accounts;
	

	public Bank(Account account1) {
		accounts = new ArrayList<>();
		setAccount(account1);
		
	}

	public Bank() {
		 accounts = new ArrayList<>();
	}
	
	/*
	 * Method setAccount()
	 * Input:
	 * 	Account newAccount
	 * Process:
	 * 	takes account that is sent to this method and saves it into the array of account
	 * 	adds one to the total number of accounts 
	 * output:
	 * 	no output--all output in class with main method
	 */

	public void setAccount(Account newAccount) {
		accounts.add(newAccount);
	}
	
	/* Method opennewAcct()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *  numAccts - total number of accounts in the bank account array
	 *  requestedAccount - account number requested
	 *  index - index of account requested
	 *  first1 - first name for new acct
	 *  last1 - last name for new acct
	 *  ssn1 - social secuirty number of new account
	 *  acctT - account type of new account
	 *  bb- balance of new account
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if the
	 *  account exists If the account exists, an error message is printed
	 *  If the account doesnt exist then the new account number and info is added to the 
	 *  bank account array along with depositor information
	 * Output:
	 *  no oputput--output found in class with main method
	 */

	public int openNewAcct(int requestedAccount, String first1, String last1,
			int ssn1, String acctT, double bb, String acctStatus) {
		
		
		DateInfo date = new DateInfo();
		
		int index;
		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "New Account";
		String reason = "";
		double transAmount;
		boolean outcome;
		
        Name name = new Name(first1, last1);
		Depositor depositor = new Depositor(name, ssn1);
		Account account1 = new Account(requestedAccount,bb,acctT, acctStatus, depositor);
		accounts.add(account1);
		
		index = findAcct(requestedAccount);
		outcome = true;
		transAmount = 0;
		Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
				todaysYear, outcome, reason);
		getAccountIndex(index).addTransactions(transaction);
		
		return 1;
	}

	/* Method deleteAcct()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *  numAccts - total number of accounts in the bank account array
	 *  requestedAccount - account number requested
	 *  index - index of account requested
	 *  maxAccts2 - maximum number of accounts
	 * Process:
	*  Prompts for the requested account Calls findAcct() to see if the
	 *  account exists If the account exists, the index of that account is
	 *  found within the bank account array and deleted Otherwise, an
	 *  error message is printed 
	 * Output:
	 *  no output--output found in class with main method
	 */

	public int deleteAcct(int requestedAccount) {
		
		
		int index = findAcct(requestedAccount);
		
		DateInfo date = new DateInfo();
		String transType = "Delete Account";
		String reason = "";
		double transAmount;
		Boolean outcome; 
		int Tmonth, Tday, Tyear;
		Tmonth = date.getTodaysMonth();
		Tday = date.getTodaysDay();
		Tyear = date.getTodaysYear();
		
		
		if (index != -1) {
			
			double oldbalance = accounts.get(index).getBalance();
			
			if(oldbalance > 0) {
					
					outcome = false;
					transAmount = 0;
					reason = "Account has a balance over zero";
					Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
							outcome, reason);
					getAccountIndex(index).addTransactions(transaction);
					
					return -3;
			}
			else {
				accounts.remove(index);
				outcome = true;
				transAmount = 0;
				reason = "Account was able to get deleted";
				Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
						outcome, reason);
				getAccountIndex(index).addTransactions(transaction);
				return 1;
			}
			
		}
		else {
			outcome = false;
			transAmount = 0;
			reason = "Account did not exist";
			Transaction transaction = new Transaction(transType, transAmount, Tmonth, Tday, Tyear,
					outcome, reason);
			getAccountIndex(index).addTransactions(transaction);
			return -1;
		}
		
	}
	
	/*
	 * Method findAcct():
	 * Input: 
	 *  BankAccount - array of bank account objects 
	 *  numAccts - Current number of account object in the bank account array
	 *  requestedAccount - requested account number 
	 * Process: 
	 *  Performs a linear search on the BankAccount array for the requested account
	 * Output: 
	 *  If found, the index of the requested account is returned Otherwise, returns -1
	 */
	
	public int findAcct(int requestedAccount) {

		for (int index = 0; index < accounts.size(); index++)
			if (accounts.get(index).getAcctNum() == requestedAccount) {
				
				return index;
			}
			
		return -1;
			
	}
	
	/*
	 * Method findSSN():
	 * Input: 
	 *  BankAccount - array of bank account objects 
	 *  numAccts - Current number of account object in the bank account array
	 *  reqSSN - requested social security number 
	 * Process: 
	 *  Performs a linear search on the BankAccount array for the requested social 
	 *  security number
	 * Output: 
	 *  If found, the index of the requested social security number is returned Otherwise,
	 *  returns -1
	 */

	public int findSSN(int reqSSN) {

		for (int index = 0; index < accounts.size(); index++) {
			 if (accounts.get(index).getDepositor().getSSN() == reqSSN) {
			for (int j = index + 1 ; j < accounts.size(); j++) {
				if (accounts.get(index).getDepositor().getSSN() == accounts.get(j).getDepositor().getSSN()) {
					return -1;
				}
				
			}
			
			return index;
 
			 }
		}

			
		return -2;

	}

	public ArrayList <Account> getAccount() {
		return accounts;
	}
	public Account getAccountIndex(int index) {
		System.out.println("getAccountIndex: " + index);
		return accounts.get(index);
	}
	public int getNumAccounts() {
		return accounts.size();
	}

}