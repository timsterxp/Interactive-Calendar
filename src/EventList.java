import java.io.Serializable;
import java.util.ArrayList;

/**
 * EventList is the object used to hold the events for the calendar, where you
 * are able to add and remove events.
 * 
 * @author Timothy Ngoyen, Andy Wu, William Luna
 *
 */
public class EventList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Event> events;
	
	/**
	 * Main constructor.
	 * Uses events
	 */
	public EventList () {
		events = new ArrayList<Event>();
	}
	
	/**
	 * Getter method for events field
	 * @return events
	 */
	public ArrayList<Event> getList () {
		return events;
	}
	/**
	 * Method to add an event to events ArrayList
	 * @param de
	 */
	public void addEvent(Event de)
	{
		events.add(de);
	}
	/**
	 * Alternative method to add an event to events ArrayList
	 * @param d
	 * @param m
	 * @param y
	 * @param name
	 */
	/*
	public void addEvent (int d, int m, int y, String name) {
		Event e = new DateEvent (d, m, y, name);
		events.add(e);
	}
	*/
	/**
	 * Method to remove event from events ArrayList
	 * @param de
	 */
	public void removeEvent(Event de)
	{
		for(int i = 0; i < events.size(); i++)
		{
			if(events.get(i).equals(de))
			{
				events.remove(i);
				break;
			}
				
		}
		
	}
	
	/**
	 * Method to return the events for a specific date
	 * @param month
	 * @param day
	 * @param year
	 * @return eventsOfThisDay An ArrayList of Events that are of a specified date from the EventList 
	 */
	public ArrayList<Event> getEventListOfDay(int month, int day, int year)
	{
		ArrayList<Event> eventsOfThisDay = new ArrayList<Event>();
		Event current;
		for(int i = 0; i < events.size(); i++)
		{
			current = events.get(i);
			if((current.getEventYear() == year) && (current.getEventMonth() == month) && (current.getEventDay() == day))
			{
				eventsOfThisDay.add(current);
			}
		}
		return eventsOfThisDay;
	}
}
