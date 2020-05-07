/*
 * Shanna Benammar
 * 
 * ASSIGNMENT NUMBER FOUR
 * 
 * Professor Ziegler
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;




public class Homework4 {

	public static void main(String[] args) throws IOException {

		// initializes variables used in main
				
				char choice;
				boolean notDone = true;
				Bank BankAccount = new Bank();

				// Calls input and output file
				File testFile = new File("input.txt");
				Scanner kybd = new Scanner(testFile);
				PrintWriter outFile = new PrintWriter("outPut.txt");
				readAccts(BankAccount);
				printAccts(BankAccount, outFile);
				

				do {
					menu();
					choice = kybd.next().charAt(0);
					System.out.println(choice);
					switch (choice) {
					case 'q':
					case 'Q':
						notDone = false;
						printAccts(BankAccount, outFile);
						break;
					case 'b':
					case 'B':
						balance(BankAccount, outFile, kybd);
						break;
					case 'i':
					case 'I':
						accountInfo(BankAccount, outFile, kybd);
						break;
					case 'd':
					case 'D':
						deposit(BankAccount, outFile, kybd);
						break;
					case 'w':
					case 'W':
						Withdrawal(BankAccount, outFile, kybd);
						break;
					case 'n':
					case 'N':
						newAcct(BankAccount, outFile, kybd);
						break;
					case 'x':
					case 'X':
						deleteAcct(BankAccount, outFile, kybd);
						break;
					case 'c':
					case 'C':
						clearCheck(BankAccount, outFile, kybd);
						break;
					case 's':
					case 'S':
						closeAccount(BankAccount,  outFile, kybd);
						break;
					case 'r':
					case 'R':
						reopenAccount(BankAccount,  outFile, kybd);
						break;
					case 'h':
					case 'H':
						acctInfoHistory(BankAccount,  outFile, kybd);
						break;
					default:
						System.out.println("Error: " + choice + " is an invalid selection-  try again");
						System.out.println();
						
						break;
					}

				} while (notDone);

				System.out.println();

				System.out.println("The program is terminating");

				outFile.close(); // close the output file

				kybd.close(); // close the test cases input file

				outFile.println();

				outFile.flush(); // flush the output file

			}


	
	private static void menu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     W -- Withdrawal");
		System.out.println("\t     D -- Deposit");
		System.out.println("\t     C -- Clear Check");
		System.out.println("\t     N -- New Account");
		System.out.println("\t     B -- Balance Inquiry");
		System.out.println("\t     I -- Account Info");
		System.out.println("\t     H -- Account Info Plus Transaction History");
		System.out.println("\t     S -- Close Account");
		System.out.println("\t     R -- Reopen a Closed Account");
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println();
		System.out.print("\tEnter your selection: ");
	
		
	}




	public static void readAccts(Bank bankAccount) throws FileNotFoundException {
		File custFile = new File("accountInfo.txt");
		Scanner sc = new Scanner(custFile);
		

		while (sc.hasNext()) {

			Name name = new Name(sc.next(), sc.next());
			Depositor depositor = new Depositor(name, sc.nextInt());
			int AcctNum = sc.nextInt();
			double AcctBal = sc.nextDouble();
			String AcctType = sc.next();
			if (AcctType.equals("CD")) {
				int month = sc.nextInt();
				int day = sc.nextInt();
				int year = sc.nextInt();
				String acctStatus = sc.next();
				Account account = new Account(AcctNum, AcctBal, AcctType, acctStatus, depositor);
				DateInfo date = new DateInfo(month, day, year);
				account.setDate(date);
				bankAccount.setAccount(account);
			}
			
			else {
				String acctStatus = sc.next();
				Account account = new Account(AcctNum, AcctBal, AcctType, acctStatus, depositor);
				bankAccount.setAccount(account);
			}
			
		}

		sc.close();

		
		
	}

	public static void printAccts(Bank bankAccount, PrintWriter outFile) {
		Name name;
		Depositor depositor;
		Account account;
		int month, day, year;
		//ArrayList<Account> accounts = bankAccount.getAccount();
		// DateInfo date = new DateInfo();

		String divider = "------------------------------------------------------------------"
				+ "----------------------------------------------------------------------";
		outFile.println("\t\tBank Accounts in the Database");
		outFile.println(divider);
		// print table column headings
		outFile.printf("%-10s%-10s%-6s%-6s%-10s%-10s&-40s%-20s", "First Name |", "| Last Name |",
				"| Social Security Number |", "| Account Number |", "| Balance |", "| Account Type |",
				"| Date |","| Account Status ");
		outFile.println();
		outFile.println(divider);

		for (int count = 0; count < bankAccount.getNumAccounts() ; count++) {

			account = bankAccount.getAccountIndex(count);
			depositor = account.getDepositor();
			name = depositor.getName();
			//DateInfo date = new DateInfo();
			

			outFile.printf("%-10s", name.getFirst());
			outFile.printf("%-10s", name.getLast());
			outFile.printf("%-20s", depositor.getSSN());
			outFile.printf("%-12s", account.getAcctNum());
			outFile.printf("$%12.2f", account.getBalance());
			outFile.printf("%-10s", account.getAcctType());

			if (account.getAcctType().equals("CD")) {
				
				month = account.getDate().getMonth();
				day = account.getDate().getDay();
				year = account.getDate().getYear();

				outFile.printf("%-10s", month);
				outFile.printf("%-10s", day);
				outFile.printf("%-10s", year);

			}
			outFile.printf("%-15s", account.getAccountStatus());
			outFile.println("\n"+divider);
		}

		outFile.flush(); // flush the output file buffer
		
	}

	
	/*
	 * Method clearCheck()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to the output file
	 *  kybd - reference to the input file
	 * Process:
	 *  checks the date of check to see if it is within the 6 month time frame 
	 * Output:
	 *  prints out if the check is cleared or error message as to why it is not cleared
	 */

	public static void clearCheck(Bank bankAccount, PrintWriter outFile, Scanner kybd) {
		
	
		System.out.println("please enter account number: ");
		int account = kybd.nextInt();
		System.out.println(account);
		System.out.println("please enter the amount of check: ");
		double checkB = kybd.nextDouble();
		System.out.println(checkB);
		System.out.println("please enter the month on check: ");
		int month = kybd.nextInt();
		System.out.println(month);
		System.out.println("please enter the day on check: ");
		int day = kybd.nextInt();
		System.out.println(day);
		System.out.println("please enter the year on check: ");
		int year = kybd.nextInt();
		System.out.println(year);
		int index = bankAccount.findAcct(account);	
		System.out.println(index);
		
		if (index != -1) {
		int outcome = bankAccount.getAccountIndex(index).clearCheck(bankAccount, account, checkB, month, day, year);
		System.out.println(outcome);
		
		switch (outcome) {
		case -1:
			outFile.println("Transaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.println("Error: This account does not exist");
			break;
		case -2:
			outFile.println("\nTransaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.printf("Check Amount: $%.2f ", checkB);
			outFile.println("\nDate of check: " + month + " " + day + " " + year);
			outFile.println("Error: This account is not a checkings account");
			outFile.println("Account will be charged a $2.50 service fee");
			outFile.println("The new balance of the account is " + bankAccount.getAccountIndex(index).getBalance());
			break;
		case -3:
			outFile.println("Transaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.printf("Check Amount: $%.2f ", checkB);
			outFile.println("\nDate of check: " + month + " " + day + " " + year);
			outFile.println("Error: The date of this check is invalid");
			outFile.println("Account will be charged a $2.50 service fee");
			outFile.println("The new balance of the account is " + bankAccount.getAccountIndex(index).getBalance());
			break;
		
		case -4:
			outFile.println("Transaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.printf("Check Amount: $%.2f ", checkB);
			outFile.println("\nDate of check: " + month + " " + day + " " + year);
			outFile.println("Error: This account is closed");
			outFile.println("Account will be charged a $2.50 service fee ");
			outFile.println("The new balance of the account is " + bankAccount.getAccountIndex(index).getBalance());
			break;
		
		case -5:
			outFile.println("Transaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.printf("Check Amount: $%.2f ", checkB);
			outFile.println("\nDate of check: " + month + " " + day + " " + year);
			outFile.println("Error: This account lacks sufficent funds ");
			outFile.println("Account will be charged a $2.50 service fee ");
			outFile.println("The new balance of the account is " + bankAccount.getAccountIndex(index).getBalance());
			break;
		case 1:
		outFile.println("Transaction Requested: Clear Check ");
		outFile.println("Account Number: " + account);
		outFile.printf("Check Amount: $%.2f ", checkB);
		outFile.println("\nDate of check: " + month + " " + day + " " + year);
		outFile.println("This check has been cleared ");
		System.out.println(index);
		outFile.println("The new balance of the account is " + bankAccount.getAccountIndex(index).getBalance());
			break;
		default:
			outFile.println("Error: Something went wrong  try again");
			outFile.println();
			outFile.flush();
			break;
			
		}
		} else {
			outFile.println("Transaction Requested: Clear Check ");
			outFile.println("Account Number: " + account);
			outFile.println("Error: This account does not exist");
			
			outFile.println();
			outFile.flush();
		}
		outFile.println();
		outFile.flush();
		
}

	/* Method deleteAcct()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to output file
	 *  kybd - reference to input file
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if the
	 *  account exists If the account exists, the index of that account is
	 *  found within the bank account array and deleted Otherwise, an
	 *  error message is printed 
	 * Output:
	 *  If the account exists, the account is deleted Otherwise, an error message is printed
	 */
	
	public static void deleteAcct(Bank bankAccount,PrintWriter outFile, Scanner kybd) {
		
		int requestedAccount;
		System.out.println("Enter the nine digit account number you would like to delete: ");
		requestedAccount = kybd.nextInt(); 
		int index = bankAccount.findAcct(requestedAccount);
		
		if(index != -1) {
			
		int outcome = bankAccount.deleteAcct(requestedAccount);
		double balance = bankAccount.getAccountIndex(index).getBalance();
		
		
		switch (outcome) {
		case -1:
			outFile.println("Transaction Requested: Delete Account ");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: This account does not exist");
			break;
		case -3:
			outFile.println("Transaction Requested: Delete Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: This account has a balance of " + balance + "which is over zero");
			break;
		case 1:
			outFile.println("Transaction Requested: Delete Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("This account has been deleted");
			break;
		default:
			outFile.println("Error: Something went wrong  try again");
			outFile.println();
			outFile.flush();
			break;
		}
		}else {
			outFile.println("Transaction Requested: Delete Account ");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: This account does not exist");
			
			outFile.println();
			outFile.flush(); 
		}
		
		outFile.println();
		outFile.flush(); 

}

	
	/* Method newAcct()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to output file
	 *  kybd - reference to input file
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if the
	 *  account exists If the account exists, an error message is printed
	 *  If the account doesnt exist then the new account number and info is added to the 
	 *  bank account array along with depositor information
	 * Output:
	 *  If the account doesnt exist, the account is added Otherwise, an error message is printed
	 */
	
	public static void newAcct(Bank bankAccount, PrintWriter outFile, Scanner kybd) {
		 
		int requestedAccount;
		int index;
		
		DateInfo date = new DateInfo();
		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "New Account";
		String reason = "";
		double transAmount;
		boolean outcome;
		
		System.out.println("Enter the nine digit account number you would like to add: ");
		requestedAccount = kybd.nextInt(); 
		
		index = bankAccount.findAcct(requestedAccount);

		if (index == -1) { 
			
			System.out.println("Enter the first name for the account: ");
			String first1 = kybd.next();
			System.out.println("Enter the last name for the account: ");
			String last1 = kybd.next();
			System.out.println("Enter the social security number: ");
			int ssn1 = kybd.nextInt();
			System.out.println("Enter the account type: ");
			String acctT = kybd.next();
			System.out.println("Enter the status of the account (open or closed) : ");
			String acctStatus = kybd.next();
			
			int result = bankAccount.openNewAcct(requestedAccount, first1, last1, ssn1, 
					acctT, 0 , acctStatus);
			if(result == 1) {
				outFile.println("Transaction Requested: New Account");
				outFile.println("Account Number: " + requestedAccount + " has been added");
				
				outFile.println();
				outFile.flush();
			}
			else {
				
				outFile.println("Transaction Requested: New Account");
				outFile.println("Error: Account was unable to be added please try again");
				
				outFile.println();
				outFile.flush();
			}
			

		} else { 
			outFile.println(" Transaction Requested: New Account");
			outFile.println(" Error: Account number " + requestedAccount + " already exists");
			
			outFile.println();
			outFile.flush();
			
			outcome = false;
			transAmount = 0;
			reason = "Account already exists";
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
					todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction);  
		}
		
	}
	
	/* Method deposit()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to output file
	 *  kybd - reference to input file
	 * Process:
	 *  Prompts for the requested account Calls findacct() to see if the
	 *  account exists If the account exists, prompts for the amount to deposit If
	 *  the amount is valid, it makes the deposit and prints the new balance
	 *  Otherwise, an error message is printed 
	 * Output:
	 *  For a valid deposit, the deposit transaction is printed Otherwise, an error message is printed
	 */

	public static void deposit(Bank bankAccount, PrintWriter outFile, Scanner kybd) {

		
		int requestedAccount;
		int index;
		double amountToDeposit, oldbalance, newbalance;
		
		
		System.out.println("Enter the account number: ");
		requestedAccount = kybd.nextInt();
		
		System.out.println("Enter amount to deposit: ");
		amountToDeposit = kybd.nextDouble();
		
		index = bankAccount.findAcct(requestedAccount);
		
		if(index != -1) {
			
		oldbalance = bankAccount.getAccountIndex(index).getBalance();	
		int result = bankAccount.getAccountIndex(index).makeDeposit(requestedAccount, bankAccount, index, amountToDeposit);
		
		
		switch(result) {
		case -1 :
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Amount to withdrawal: $" + amountToDeposit);
			outFile.println("Error: Account Number " + requestedAccount
					+ " is a CD account which has not yet passed its maturity date");
			outFile.println();
			break;
		case -2:
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Amount to withdrawal: $" + amountToDeposit);
			outFile.println("Error: Account " + requestedAccount
					+ " is Closed.");
			outFile.println();
			break;
		case -3:
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Amount to Deposit: $" + amountToDeposit);
			outFile.printf("Error: $%.2f is an invalid amount", amountToDeposit);
			outFile.println();
			break;
		case 1:
			System.out.println("please enter new maturity date ");
			System.out.println(" 6  ----six months from current date");
			System.out.println(" 12  ----twelve months from current date");
			System.out.println(" 18  ----eighten months from current date");
			System.out.println(" 24  ----twenty four months from current date");
			int newM = kybd.nextInt();
			
			if(newM == 6) {
			int oldM = bankAccount.getAccountIndex(index).getDate().getMonth();
			bankAccount.getAccountIndex(index).getDate().setMonth(oldM + newM);
			}
			else if (newM == 12) {
			int oldM = bankAccount.getAccountIndex(index).getDate().getYear();	
			bankAccount.getAccountIndex(index).getDate().setYear(oldM + 1);
			}
			else if (newM == 18) {
			int oldY = bankAccount.getAccountIndex(index).getDate().getYear();	
			bankAccount.getAccountIndex(index).getDate().setYear(oldY + 1);
			int oldM = bankAccount.getAccountIndex(index).getDate().getMonth();
			bankAccount.getAccountIndex(index).getDate().setMonth(oldM + 6);
			}
			else if (newM == 24) {
			int oldM = bankAccount.getAccountIndex(index).getDate().getYear();	
			bankAccount.getAccountIndex(index).getDate().setYear(oldM + 2);
			}
			
			newbalance = bankAccount.getAccountIndex(index).getBalance();
			
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Old Balance: $%.2f", oldbalance);
			outFile.println();
			outFile.printf("Amount to Deposit: $%.2f", amountToDeposit);
			outFile.printf("New Balance: $%.2f", newbalance);
			outFile.println();
			break;
		case 2:
			newbalance = bankAccount.getAccountIndex(index).getBalance();
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Old Balance: $%.2f", oldbalance);
			outFile.println("Amount to Deposit: $" + amountToDeposit);
			outFile.printf("New Balance: $%.2f", newbalance);
			outFile.println();
			break;
		default:
			outFile.println("Error: Something went wrong  try again");
			outFile.println();
			outFile.flush();
			break;
		}
		}else {
			
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Amount to withdrawal: $" + amountToDeposit);
			outFile.println("Error: Account Number " + requestedAccount + " does not exist");
			outFile.println();
			outFile.flush();
		}
		
		
		outFile.println();
		outFile.flush();
	

	}
	
	/* Method accountInfo()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
       	 *  outFile - reference to the output file
	 * Process:
	 *  Prompts the user for the social security number of the account they 
 	 *  want to look up calls findSSN() to lookup account with that SSN
	 *  if the account exists it prints all account info attached to that SSN 
 	 *  if account doesnt exist it prints and error message
	 * Output:
	 *  prints out account info if an account has the SSN requested Otherwise,
	 *  an error message is printed
	 */

	public static void accountInfo(Bank bankAccount, PrintWriter outFile, Scanner kybd) {

		int reqSSN;
		int index;
		int acct, month, day, year;
		int acct2, month2, day2, year2;
		String first, last, acctT, accttS;
		String first2, last2, acctT2, accttS2;
		double bb;
		double bb2;
		DateInfo date = new DateInfo();

		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Account Info";
		String reason ="";
		double transAmount;
		boolean outcome;

		System.out.println();
		System.out.println("enter the social security number of the account: ");
		reqSSN = kybd.nextInt();
		
		index = bankAccount.findSSN(reqSSN);
		System.out.println(index);
		
		if (index == -2) {
			outFile.println("Transaction Requested: Account Info");
			outFile.println("Error: social security number " + reqSSN + " does not exist");
			outFile.println();
			outFile.println();
			outFile.flush();
		} 
		else if(index == -1) {
		
			for (int i = 0; i < bankAccount.getNumAccounts(); i++) {
				for (int j = i + 1 ; j < bankAccount.getNumAccounts(); j++) {
					if (bankAccount.getAccountIndex(i).getDepositor().getSSN() == bankAccount.getAccountIndex(j).getDepositor().getSSN()) {
						
				//this is for the first account associated with the ssn
						
				outFile.println("Transaction Requested: Account Info");
				outFile.println("Social security number requested: " + reqSSN);
				first = bankAccount.getAccountIndex(i).getDepositor().getName().getFirst();
				last = bankAccount.getAccountIndex(i).getDepositor().getName().getLast();
				bb = bankAccount.getAccountIndex(i).getBalance();
				acct = bankAccount.getAccountIndex(i).getAcctNum();
				acctT = bankAccount.getAccountIndex(i).getAcctType();
				accttS = bankAccount.getAccountIndex(i).getAccountStatus();

				outFile.println("Name: " + first + " " + last);
				outFile.println("Account Number: " + acct);
				outFile.println("Current Balance: $" + bb);
				outFile.println("Account Type: " + acctT);
				outFile.println("Account Status: " + accttS);

				if (acctT.equals("CD")) {

					month = bankAccount.getAccountIndex(i).getDate().getMonth();
					day = bankAccount.getAccountIndex(i).getDate().getDay();
					year = bankAccount.getAccountIndex(i).getDate().getYear();

					outFile.println("Date Info: " + month + " " + day + " " + year);
				}
				
				//this is for the second account associated with the ssn
				outFile.println();
				outFile.println("This is the second account under SSN: " + reqSSN);
				first2 = bankAccount.getAccountIndex(j).getDepositor().getName().getFirst();
				last2 = bankAccount.getAccountIndex(j).getDepositor().getName().getLast();
				bb2 = bankAccount.getAccountIndex(j).getBalance();
				acct2 = bankAccount.getAccountIndex(j).getAcctNum();
				acctT2 = bankAccount.getAccountIndex(j).getAcctType();
				accttS2 = bankAccount.getAccountIndex(j).getAccountStatus();

				outFile.println("Name: " + first2 + " " + last2);
				outFile.println("Account Number: " + acct2);
				outFile.println("Current Balance: $" + bb2);
				outFile.println("Account Type: " + acctT2);
				outFile.println("Account Status: " + accttS2);

				if (acctT2.equals("CD")) {

					month2 = bankAccount.getAccountIndex(j).getDate().getMonth();
					day2 = bankAccount.getAccountIndex(j).getDate().getDay();
					year2 = bankAccount.getAccountIndex(j).getDate().getYear();

					outFile.println("Date Info: " + month2 + " " + day2 + " " + year2);
				}
				
				outcome = true;
				transAmount = 0;
				Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
				todaysYear, outcome, reason);
				bankAccount.getAccountIndex(i).addTransactions(transaction); 
				bankAccount.getAccountIndex(j).addTransactions(transaction); 
					}
				}
			}
			outFile.println();
			outFile.flush();
		}	
		else {
			
			//this is if only one account is associated with the ssn
			
			outFile.println("Transaction Requested: Account Info");
			outFile.println("Social security number requested: " + reqSSN);
			first = bankAccount.getAccountIndex(index).getDepositor().getName().getFirst();
			last = bankAccount.getAccountIndex(index).getDepositor().getName().getLast();
			bb = bankAccount.getAccountIndex(index).getBalance();
			acct = bankAccount.getAccountIndex(index).getAcctNum();
			acctT = bankAccount.getAccountIndex(index).getAcctType();
			accttS = bankAccount.getAccountIndex(index).getAccountStatus();

			outFile.println("Name: " + first + " " + last);
			outFile.println("Account Number: " + acct);
			outFile.println("Current Balance: $" + bb); 
			outFile.println("Account Type: " + acctT);
			outFile.println("Account Status: " + accttS);
			
			outcome = true;
			transAmount = 0;
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
			todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 
			}
		
		outFile.println();
		outFile.flush();
	}
	
	 
	public static void acctInfoHistory(Bank bankAccount,PrintWriter outFile,
	Scanner kybd) {
		
		int reqSSN;
		int index;
		int acct, month, day, year;
		int acct2, month2, day2, year2;
		String first, last, acctT, acctS, transType1, outcome1;
		String first2, last2, acctT2, acctS2, transType12, outcome12;
		
		String failureReason;
		double bb, transAmount1;
		double bb2, transAmount12;
		int transMonth, transDay, transYear, transMonth2, transDay2, transYear2;
		
		System.out.println();
		System.out.println("Enter the social security number of the account: ");
		reqSSN = kybd.nextInt();
		index = bankAccount.findSSN(reqSSN);

		if (index == -2) {
			
			outFile.println("Transaction Requested: Account Info and Transaction History");
			outFile.println("Error: social security number " + reqSSN + " does not exist");
			outFile.println();
			outFile.flush();
		} 
		else if (index == -1) {
			
			for (int i = 0; i < bankAccount.getNumAccounts(); i++) {
				for (int j = i + 1 ; j < bankAccount.getNumAccounts(); j++) {
					if (bankAccount.getAccountIndex(i).getDepositor().getSSN() == bankAccount.getAccountIndex(j).getDepositor().getSSN()) {
						
					//This is for the first account found associated with the SSN
						
					first = bankAccount.getAccountIndex(i).getDepositor().getName().getFirst();
					last = bankAccount.getAccountIndex(i).getDepositor().getName().getLast();
					bb = bankAccount.getAccountIndex(i).getBalance();
					acct = bankAccount.getAccountIndex(i).getAcctNum();
					acctT = bankAccount.getAccountIndex(i).getAcctType();
					acctS = bankAccount.getAccountIndex(i).getAccountStatus();
					
					outFile.println();
					outFile.println("Transaction Requested: Account Info and Transaction History");
					outFile.println("Social security number requested: " + reqSSN);
					outFile.println("Name: " + first + " " + last);
					outFile.println("Account Number: " + acct);
					outFile.println("Current Balance: $" + bb);
					outFile.println("Account Type: " + acctT);
					outFile.println("Account Status: " + acctS);
	
					if (acctT.equals("CD")) {
	
						month = bankAccount.getAccountIndex(i).getDate().getMonth();
						day = bankAccount.getAccountIndex(i).getDate().getDay();
						year = bankAccount.getAccountIndex(i).getDate().getYear();
	
						outFile.println("Date Info: " + month + " " + day + " " + year);
	
					}
					
					for(int x = 0; x < bankAccount.getAccountIndex(i).getTransSize(); x++) {
					
					transType1 = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getTransType();
					transAmount1 = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getTransAmount();
					transMonth = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getTransMonth();
					transDay = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getTransDay();
					transYear = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getTransYear();
					outcome1 = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getOutcome();
					
					
					outFile.println();
					outFile.println("Transaction History of this Account: ");
					outFile.println("Transaction Type: " + transType1);
					outFile.println("Transaction Amount: " + transAmount1);
					outFile.println("Transaction Date: " + transMonth + " " + transDay + " " + transYear);
					outFile.println("Transaction Outcome: " + outcome1);
					
					if (outcome1.equals("failure")) {
						 failureReason = bankAccount.getAccountIndex(i).getTransactionsIndex(x).getReason();
						 outFile.println("Transaction Failure Reason: " + failureReason);
					}
						}
					//this is for the second account associated with the ssn
					
					first2 = bankAccount.getAccountIndex(j).getDepositor().getName().getFirst();
					last2 = bankAccount.getAccountIndex(j).getDepositor().getName().getLast();
					bb2 = bankAccount.getAccountIndex(j).getBalance();
					acct2 = bankAccount.getAccountIndex(j).getAcctNum();
					acctT2 = bankAccount.getAccountIndex(j).getAcctType();
					acctS2 = bankAccount.getAccountIndex(j).getAccountStatus();
					
					outFile.println();
					outFile.println("Transaction Requested: Account Info and Transaction History");
					outFile.println("Social security number requested: " + reqSSN);
					outFile.println("Name: " + first2 + " " + last2);
					outFile.println("Account Number: " + acct2);
					outFile.println("Current Balance: $" + bb2);
					outFile.println("Account Type: " + acctT2);
					outFile.println("Account Status: " + acctS2);
	
					if (acctT2.equals("CD")) {
	
						month2 = bankAccount.getAccountIndex(j).getDate().getMonth();
						day2 = bankAccount.getAccountIndex(j).getDate().getDay();
						year2 = bankAccount.getAccountIndex(j).getDate().getYear();
	
						outFile.println("Date Info: " + month2 + " " + day2 + " " + year2);
	
					}
					
					for(int x = 0; x < bankAccount.getAccountIndex(j).getTransSize(); x++) {
					
					transType12 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getTransType();
					transAmount12 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getTransAmount();
					transMonth2 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getTransMonth();
					transDay2 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getTransDay();
					transYear2 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getTransYear();
					outcome12 = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getOutcome();
					
					
					outFile.println();
					outFile.println("Transaction History of this Account: ");
					outFile.println("Transaction Type: " + transType12);
					outFile.println("Transaction Amount: " + transAmount12);
					outFile.println("Transaction Date: " + transMonth2 + " " + transDay2 + " " + transYear2);
					outFile.println("Transaction Outcome: " + outcome12);
					
					if (outcome12.equals("failure")) {
						 failureReason = bankAccount.getAccountIndex(j).getTransactionsIndex(x).getReason();
						 outFile.println("Transaction Failure Reason: " + failureReason);
					}
					}
				
					}
				}
			}
			
			outFile.println();
			outFile.flush();
		}
		else {
			
			first = bankAccount.getAccountIndex(index).getDepositor().getName().getFirst();
			last = bankAccount.getAccountIndex(index).getDepositor().getName().getLast();
			bb = bankAccount.getAccountIndex(index).getBalance();
			acct = bankAccount.getAccountIndex(index).getAcctNum();
			acctT = bankAccount.getAccountIndex(index).getAcctType();
			acctS = bankAccount.getAccountIndex(index).getAccountStatus();
			
			outFile.println();
			outFile.println("Transaction Requested: Account Info and Transaction History");
			outFile.println("Social security number requested: " + reqSSN);
			outFile.println("Name: " + first + " " + last);
			outFile.println("Account Number: " + acct);
			outFile.println("Current Balance: $" + bb);
			outFile.println("Account Type: " + acctT);
			outFile.println("Account Status: " + acctS);

			if (acctT.equals("CD")) {

				month = bankAccount.getAccountIndex(index).getDate().getMonth();
				day = bankAccount.getAccountIndex(index).getDate().getDay();
				year = bankAccount.getAccountIndex(index).getDate().getYear();

				outFile.println("Date Info: " + month + " " + day + " " + year);

			}
			
			for(int x = 0; x < bankAccount.getAccountIndex(index).getTransSize(); x++) {
			
			transType1 = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getTransType();
			transAmount1 = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getTransAmount();
			transMonth = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getTransMonth();
			transDay = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getTransDay();
			transYear = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getTransYear();
			outcome1 = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getOutcome();
			
			outFile.println();
			outFile.println("Transaction History of this Account: ");
			outFile.println("Transaction Type: " + transType1);
			outFile.println("Transaction Amount: " + transAmount1);
			outFile.println("Transaction Date: " + transMonth + " " + transDay + " " + transYear);
			outFile.println("Transaction Outcome: " + outcome1);
			
			if (outcome1.equals("failure")) {
				 failureReason = bankAccount.getAccountIndex(index).getTransactionsIndex(x).getReason();
				 outFile.println("Transaction Failure Reason: " + failureReason);
			}
			
		}
		outFile.println();
		outFile.flush();
	
		}
	}	
	/* Method balance()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to output file
	 *  kybd - reference to input file
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if the
	 *  account exists If the account exists, the balance is printed Otherwise, an
	 *  error message is printed 
	 * Output:
	 *  If the account exists, the balance is printed Otherwise, an error message is printed
	 */

	public static void balance(Bank bankAccount, PrintWriter outFile, Scanner kybd) {

		int requestedAccount;
		int index;
		
		//getting info for transaction 
		
		DateInfo date = new DateInfo();
		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Balance";
		String reason = "";
		double transAmount;
		boolean outcome;

		//getting info from keyboard
		
		System.out.println();
		System.out.println("Enter the account number: ");
		requestedAccount = kybd.nextInt();
		index = bankAccount.findAcct(requestedAccount);
		
		//if the account doesn't exist

		if (index == -1) {
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Error: Account Number " + requestedAccount + " does not exist");
			outFile.println();
			
		} 
		
		//if the account does exist
		
		else {
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Current Balance: $" + bankAccount.getAccountIndex(index).getBalance());
			
			outcome = true;
			transAmount = 0;
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
					todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 
		}
		
		outFile.println();
		outFile.flush();

	}
	
	/* Method withdrawal()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to output file
	 *  kybd - reference to input file
	 * Process:
	 *  Prompts for the requested account Calls findAcct() to see if
	 *  the account exists If the account doesn't exist an error message is printed.
	 *  If the account does exist, withdrawal amount is requested. if account doesnt 
	 *  have enought funds error message is printed
	 * Output:
	 *  If the account exists and the account has sufficient funds the amount is withdrawn
	 *  from the balance. If the account exists but has insufficient funds, an error
	 *  message is printed. If the account does not exist, an error message is
	 *  printed.
	 */

	public static void Withdrawal(Bank bankAccount, PrintWriter outFile, Scanner kybd) {

		
		int requestedAccount, index, result;
		double amountToWithdrawal, oldbalance, newbalance;
	
		
		outFile.println();
		System.out.println("Enter the account number: "); 
		requestedAccount = kybd.nextInt(); 
		System.out.println("Enter the amount to withdrawal: ");
		amountToWithdrawal = kybd.nextDouble();
		
		index = bankAccount.findAcct(requestedAccount);
		
		if(index != -1) {
		
			oldbalance = bankAccount.getAccountIndex(index).getBalance();
			result = bankAccount.getAccountIndex(index).makeWithdrawal(bankAccount, index, amountToWithdrawal);
			
			
			switch(result) {
			case -1 :
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to withdrawal: $" + amountToWithdrawal);
				outFile.println("Error: Account Number " + requestedAccount
						+ " is a CD account which has not yet passed its maturity date");
				outFile.println();
				break;
			case -2:
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to withdrawal: $" + amountToWithdrawal);
				outFile.println("Error: Account Number " + requestedAccount
						+ " is Closed.");
				outFile.println();
				break;
			case -3:
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to Deposit: $" + amountToWithdrawal);
				outFile.println("Error: $" + amountToWithdrawal + " is an invalid amount");
				outFile.println();
				break;
			case 1:
				System.out.println("please enter new maturity date ");
				System.out.println(" 6  ----six months from current date");
				System.out.println(" 12  ----twelve months from current date");
				System.out.println(" 18  ----eighten months from current date");
				System.out.println(" 24  ----twenty four months from current date");
				int newM = kybd.nextInt();
				
				if(newM == 6) {
				int oldM = bankAccount.getAccountIndex(index).getDate().getMonth();
				bankAccount.getAccountIndex(index).getDate().setMonth(oldM + newM);
				}
				else if (newM == 12) {
				int oldM = bankAccount.getAccountIndex(index).getDate().getYear();	
				bankAccount.getAccountIndex(index).getDate().setYear(oldM + 1);
				}
				else if (newM == 18) {
				int oldY = bankAccount.getAccountIndex(index).getDate().getYear();						bankAccount.getAccountIndex(index).getDate().setYear(oldY + 1);
				int oldM = bankAccount.getAccountIndex(index).getDate().getMonth();
				bankAccount.getAccountIndex(index).getDate().setMonth(oldM + 6);
				}
				else if (newM == 24) {
				int oldM = bankAccount.getAccountIndex(index).getDate().getYear();	
				bankAccount.getAccountIndex(index).getDate().setYear(oldM + 2);
				}
				
				newbalance = bankAccount.getAccountIndex(index).getBalance();
				
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Old Balance: $" + oldbalance);
				outFile.println();
				outFile.println("Amount to Deposit: $" + amountToWithdrawal);
				outFile.println("New Balance: $" + newbalance);
				outFile.println();
				break;
			case 2:
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Old Balance: $"+ oldbalance);
				outFile.println("Amount to withdrawal: $" + amountToWithdrawal);
				outFile.println("New Balance: $" + bankAccount.getAccountIndex(index).getBalance());
				outFile.println();
				break;
			}
			}else {
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Amount to withdrawal: $" + amountToWithdrawal);
			outFile.println("Error: Account Number " + requestedAccount
					+ " does not exist");
			outFile.println();
		}
		
		
		outFile.println();
		outFile.flush();
	}
		
	/*
	 * Method closeAccount()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to the output file
	 *  kybd - reference to the input file
	 * Process:
	 *  calls method to close account that is open 
	 * Output:
	 *  prints out if the process was a success or not
	 */

	public static void closeAccount(Bank bankAccount, PrintWriter outFile, Scanner kybd) 
	{
		int requestedAccount;
		int index;
		
		DateInfo date = new DateInfo();

		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Close Account";
		String reason ="";
		double transAmount;
		boolean outcome;
		
		outFile.println();
		System.out.println("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number
		
		// call findAcct to search if requestedAccount exists
		index = bankAccount.findAcct(requestedAccount);
		
		if (index == -1) { 
			outFile.println("Transaction Requested: Close Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: Account does not exist");
			outFile.println();
			
		}
		else {
			
			 outcome = bankAccount.getAccountIndex(index).closeAccts(bankAccount, index, requestedAccount);
			 
		if(outcome = true) {
			
			outFile.println("Transaction Requested: Close Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Account has been closed");
			outFile.println();
			
			outcome = true;
			transAmount = 0;
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth, todaysDay,
					todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 

		}
		else {
			outFile.println("Transaction Requested: Close Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: Account did not close. Try Again Later");
			outFile.println();
			
			outcome = false;
			transAmount = 0;
			reason = "Account was not able to close";
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth,
					todaysDay, todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 

		}
		
		}
		outFile.println();
		outFile.flush();
		
	}
	/*
	 * Method reopenAccount()
	 * Input:
	 *  bankAccount[] BankAccount - reference to array of bank account objects
	 *   - maximum number of active accounts allowed
	 *  numAccts - total number of accounts in the bank account array
	 *  outFile - reference to the output file
	 *  kybd - reference to the input file
	 * Process:
	 *  calls reopen account to open account that is closed
	 * Output:
	 *  prints out if the process was successful
	 */
	

	public static void reopenAccount(Bank bankAccount, PrintWriter outFile, Scanner kybd){
	
		int requestedAccount;
		int index;
		
		DateInfo date = new DateInfo();

		int todaysMonth = date.getTodaysMonth();
		int todaysDay = date.getTodaysDay();
		int todaysYear = date.getTodaysYear();
		String transType = "Reopen Account";
		String reason = "";
		double transAmount;
		boolean outcome;
		
		outFile.println();
		System.out.println("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number
		
		// call findAcct to search if requestedAccount exists
		index = bankAccount.findAcct(requestedAccount);
		if (index == -1) {
			outFile.println("Transaction Requested: Reopen Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: Account does not exist");
			outFile.println();
			
		}
		else {
			
			 outcome = bankAccount.getAccountIndex(index).reopenAcct(bankAccount, index, requestedAccount);
			 
		if(outcome = true) {
			
			outFile.println("Transaction Requested: Reopen Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Account Opened");
			outFile.println();
			
			outcome = true;
			transAmount = 0;
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth,
					todaysDay, todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 

		}
		else {
			outFile.println("Transaction Requested: Reopen Account");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Error: Account did not open. Try Again Later");
			
			outFile.println();
			
			outcome = false;
			transAmount = 0;
			reason = "Account could open";
			Transaction transaction = new Transaction(transType, transAmount, todaysMonth,
					todaysDay, todaysYear, outcome, reason);
			bankAccount.getAccountIndex(index).addTransactions(transaction); 

		}
		} 
		outFile.flush();
		
		}

	}

	