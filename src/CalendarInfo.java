import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class that contains most of a Calendar's info This class was taken/adapted
 * from https://introcs.cs.princeton.edu/java/21function/Calendar.java.html,
 * credits to princeton as well as
 * https://github.com/dwatring/Memo-Calendar/blob/master/src/CalendarData.java ,
 * author dwatring
 *
 */
public class CalendarInfo {

	private int[][] calInfo = new int[6][7];
	private int year, month, day, totalAmount;
	private int[] totalDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private Calendar currentDate;

	/**
	 * Creates a new batch of Calendar Info with the current Day
	 */
	public CalendarInfo() {
		setCurrentDate();
	}

	/**
	 * Returns the array position of days in a month
	 * 
	 * @return
	 */
	public int[][] getCalendarInfo() {
		return calInfo;
	}

	/**
	 * The days that each month has
	 * 
	 * @return
	 */
	public int[] getTotalDays() {
		return totalDays;
	}

	/**
	 * Utilizes Calendar instances for the data
	 */
	public void setCurrentDate() {
		currentDate = Calendar.getInstance();
		this.year = currentDate.get(1);
		this.month = currentDate.get(2);
		this.day = currentDate.get(5);

		initializeCalendarData(currentDate);
	}

	/**
	 * @param cal
	 */
	private void initializeCalendarData(Calendar cal) {
		final int calStartingPos = (cal.get(7) + 7 - cal.get(5) % 7) % 7;
		if (this.month == 1) {
			this.totalAmount = this.totalDays[this.month] + checkLeapYear(year);
		} else {
			this.totalAmount = this.totalDays[this.month];
		}
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 7; ++j) {
				this.calInfo[i][j] = 0;
			}
		}
		int i = 0;
		int num = 1;
		int k = 0;
		while (i < 6) {
			if (i == 0) {
				k = calStartingPos;
			} else {
				k = 0;
			}
			for (int l = k; l < 7; ++l) {
				if (num <= this.totalAmount) {
					this.calInfo[i][l] = num++;
				}
			}
			++i;
		}

	}

	/**
	 * Checks if it is a leap year, necessary to change days of month
	 * 
	 * @param year
	 * @return
	 */
	private int checkLeapYear(int year) {
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			return 1;
		}
		return 0;
	}

	/**
	 * To change a month we would update the calendar
	 * 
	 * @param mon
	 */
	public void moveMonth(int mon) {
		month += mon;
		if (month > 11)
			while (month > 11) {
				year++;
				month -= 12;
			}
		else if (month < 0)
			while (month < 0) {
				year--;
				month += 12;
			}
		currentDate = new GregorianCalendar(year, month, day);
		initializeCalendarData(currentDate);
	}

	/**
	 * Return what the current month count is
	 * 
	 * @return
	 */
	public int getCurrentMonth() {
		return month;
	}

	/**
	 * Return the value of the year
	 * 
	 * @return
	 */
	public int getCurrentYear() {
		return year;
	}
}