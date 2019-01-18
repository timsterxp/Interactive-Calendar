import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that implements MouseListener to add more functionality to it
 *
 */
public class DateAdapter implements MouseListener {
	private int day, month;

	/**
	 * Creates a DateAdapter with current day and month
	 * 
	 * @param day
	 * @param month
	 */
	public DateAdapter(int day, int month) {
		super();
		this.day = day;
		this.month = month;

	}

	/**
	 * Sets up a String Array with the months and gets the correct month name
	 * 
	 * @return
	 */
	public String getMonth() {
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return monthNames[month];
	}

	/**
	 * Gets the date
	 * 
	 * @return
	 */
	public int getDay() {
		return day;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
