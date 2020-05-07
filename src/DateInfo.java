//calls java calendar class
import java.util.Calendar;

public class DateInfo {

	private int month, todaysMonth;
	private int day, todaysDay;
	private int year, todaysYear;
	Calendar calendar = Calendar.getInstance();
	Calendar accountD = Calendar.getInstance();
	
	
	public DateInfo(int mon, int da, int yea) {
		setMonth(mon);
		setDay(da);
		setYear(yea);
		
	}

	public DateInfo() {
		
		Calendar today = Calendar.getInstance();
		setTodaysMonth(today.get(Calendar.MONTH));
		setTodaysDay(today.get(Calendar.DAY_OF_MONTH));
		setTodaysYear(today.get(Calendar.YEAR));
		
	}

	/*
	 * Method Compare()
	 * input:
	 * 	m- month being compared to
	 * 	D - day being compared
	 * 	Y - year being compared
	 * process:
	 * 	calls for the current date and compare the date being checked
	 * 	to todays date
	 * output:
	 * 	no output- All output in class with main method
	 */
	public int compare(int M, int D, int Y) {
		
		//todays date
		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, today.get(Calendar.YEAR));
		today.set(Calendar.MONTH, today.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
		
		//the date of the CD account
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, M);
		c2.set(Calendar.DAY_OF_MONTH, D);
		c2.set(Calendar.YEAR, Y);
		
		
		//date six months ago
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.MONTH, -6);
		c3.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) );
		c3.set(Calendar.YEAR,today.get(Calendar.YEAR));
		
		//date one year ago (12 months)
		Calendar c4 = Calendar.getInstance();
		c4.set(Calendar.MONTH, today.get(Calendar.MONTH));
		c4.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
		c4.set(Calendar.YEAR, -1);
		
		//date 18 months ago (1 year 6 months)
		Calendar c5 = Calendar.getInstance();
		c5.set(Calendar.MONTH, -6);
		c5.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
		c5.set(Calendar.YEAR, -1);
		
		//date 2 years ago (24months)
		Calendar c6 = Calendar.getInstance();
		c6.set(Calendar.MONTH, today.get(Calendar.MONTH));
		c6.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
		c6.set(Calendar.YEAR, -2);

		
		if (today.after(c2) || today.equals(c3)) {
			return 1;
		} else {
			return -1;
		}
	}
	/*
	 * Method compareCheck()
	 * input:
	 * 	m- month being compared to
	 * 	D - day being compared
	 * 	Y - year being compared
	 * process:
	 * 	calls for the current date and compare the date being checked
	 * 	to todays date
	 * output:
	 * 	no output- All output in class with main method
	 */

	public int compareCheck(int M, int D, int Y) {

		
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, M);
		c2.set(Calendar.DAY_OF_MONTH, D);
		c2.set(Calendar.YEAR, Y);
		
		Calendar today = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, today.get(Calendar.YEAR));
		c3.set(Calendar.MONTH, today.get(Calendar.MONTH));
		c3.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
		c3.set(Calendar.MONTH, -6);
		

		if (c2.before(c3) || c2.equals(c3)) {
			return -1;
		} else {
			return 1;
		}
	}

	public int getMonth() {
		return month;
	}

	/*
	 * Method setMonth()
	 * input:
	 * 	M- reference to the month being sent to it
	 * process:  
	 * 	changes the month of the calendar class to the month 
	 * 	that has been sent to it
	 * output:
	 * 	no output- All output in class with main method
	 */
	
	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month);
		month = calendar.get(Calendar.MONTH);
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int Day) {
		accountD.set(Calendar.DAY_OF_MONTH, Day);
		day = accountD.get(Calendar.DAY_OF_MONTH);
		this.day = Day;
	}

	public int getYear() {
		return year;
	} 

	public void setYear(int year) {
		accountD.set(Calendar.YEAR, year);
		year = accountD.get(Calendar.YEAR);
		this.year = year;
	}

	public int getTodaysMonth() {
		return todaysMonth;
	}

	public void setTodaysMonth(int todaysMonth) {
		
		 this.todaysMonth = todaysMonth;
	}

	public int getTodaysDay() {
		return todaysDay;
	}

	public void setTodaysDay(int todaysDay) {
		this.todaysDay = todaysDay;
	}

	public int getTodaysYear() {
		return todaysYear;
	}

	public void setTodaysYear(int todaysYear) {
		
		this.todaysYear = todaysYear;
	}

}