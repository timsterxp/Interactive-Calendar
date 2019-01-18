/**
 * Main runner of the Calendar Program
 * @author Timothy Nguyen, Andy Wu, William Luna
 *
 */
public class CalendarRunner {

	public static void main(String[] args) {
		EventList theEventList = new EventList();		
		new CalendarFrame(theEventList);
	}
}
