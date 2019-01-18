import java.awt.Color;
import java.io.Serializable;

/**
 * DateEvent is the main type of object stored in the EventList (can store
 * others). Holds information of an event.
 * 
 * @author Timothy Nguyen, Andy Wu, William Luna
 *
 */
public class DateEvent implements Serializable, Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] totalDays = {31,28,31,30,31,30,31,31,30,31,30,31};
	private int month;
	private int day;
	private int year;
	private int timeStartHour, timeStartMinute, timeEndHour, timeEndMinute;
	private String eventName, eventDescription;
	
	/**
	 * Main constructor. Instantiates all fields for the DateEvent
	 * @param m month	
	 * @param d		day	
	 * @param y		year
	 * @param tsh	start hour
	 * @param tsm	start minute
	 * @param teh	end hour
	 * @param tem	end minute
	 * @param e		event name	
	 * @param ed	event description
	 */
	public DateEvent(int m, int d, int y, int tsh, int tsm, int teh, int tem, String e, String ed) {
		if(checkValidFields(m, d, y, tsh, tsm, teh, tem, e))
		{
			month = m;
			day = d;
			year = y;
			eventName = e; 
			setTimes(tsh, tsm, teh, tem);
			eventDescription = ed;
		}

	}
	
	/**
	 * Checks for valid constructor fields
	 * @param m		month	
	 * @param d		day
	 * @param y		year
	 * @param tsh	start hour
	 * @param tsm	start minute
	 * @param teh	end hour
	 * @param tem	end minute
	 * @param e		event name
	 * @return
	 */
	public boolean checkValidFields(int m, int d, int y, int tsh, int tsm, int teh, int tem, String e)
	{
		
		boolean validMonth = m >= 0 && m <= 12;
		boolean validDay;
		if(m == 1)
			validDay = d >= 0 && d <= (totalDays[m] + checkLeapYear(y));
		else
			validDay = d >= 0 && d <= totalDays[m];
		
		boolean sequentialTime;
		if(tsh == teh)
			sequentialTime = tem > tem;
			
		else
			sequentialTime = teh > tsh;
		
		if(sequentialTime)
		if(validMonth && validDay && isValidHour(tsh) && isValidHour(teh) && isValidMinute(tsm) && isValidMinute(tem) && 
				!(e.equals("")) && sequentialTime)
			return true;
		return false;
	}
	
	/**
	 * Checks if value is a valid hour value in the 24hr format
	 * @param hour
	 * @return true or false
	 */
	public boolean isValidHour(int hour)
	{
		if(hour >= 0 && hour <= 24)
			return true;
		return false;
	}
	
	/**
	 * Checks if values is a valid minute value within an hour
	 * @param minute
	 * @return true or false
	 */
	public boolean isValidMinute(int minute)
	{
		if(minute >= 0 && minute <= 59)
			return true;
		return false;
	}
	
	private int checkLeapYear(int year) {
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Getter method for day
	 */
	public int getEventDay () {
		return day;
	}
	
	/**
	 * Getter method for month
	 * 
	 */
	public int getEventMonth () {
		return month;
	}
	
	/**
	 * Getter method for year
	 */
	public int getEventYear() {
		return year;
	}
	
	/**
	 * Setter method to set the time(timeStartHour, timeStartMinute, timeEndHour, timeEndMinute) for DateEvent
	 */
	public void setTimes(int startHour, int startMinute, int endHour, int endMinute)
	{
		timeStartHour = startHour;
		timeStartMinute = startMinute;
		timeEndHour = endHour;
		timeEndMinute = endMinute;
	}
	
	/**
	 * Combined getter method for the start time
	 */
	public int[] getTimeStart()
	{
		int[] timeStart = {timeStartHour, timeStartMinute};
		return timeStart;
	}
	
	/**
	 * Combined getter method for the end time
	 */
	public int[] getTimeEnd()
	{
		int[] timeEnd = {timeEndHour, timeEndMinute};
		return timeEnd;
	}
	/**
	 * Getter method for the eventName
	 */
	public String getName()
	{
		return eventName;
	}
	
	/**
	 * Getter method for the eventDescription
	 */
	public String getDescription()
	{
		return eventDescription;
	}
	
	/**
	 * Overridden equals method to compare two DateEvents to each other.
	 * Calls getName, getDescription, getTimeStart, getTimeEnd, getEventDay, getEventMonth, getEventYear
	 */
	public boolean equals(Object o)
	{
		// If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of DateEvent or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof DateEvent)) { 
            return false; 
        } 
          
        // typecast o to DateEvent  
        DateEvent c = (DateEvent) o; 
          
        // Compare the data members and return accordingly  
        boolean sameNameDes = c.getName().equals(this.getName()) && c.getDescription().equals(this.getName());
        boolean start = c.getTimeStart() == this.getTimeStart();
        boolean end = c.getTimeEnd() == this.getTimeEnd();
        boolean day = this.getEventDay() == c.getEventDay() && this.getEventMonth() == c.getEventMonth() && this.getEventYear() == c.getEventYear();
        
        return sameNameDes && start && end && day;
	}
	
	public Color getColorFont()
	{
		return Color.BLACK;
	}
	
}
