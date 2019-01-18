import java.awt.Color;

/**
 * A reminder event is based off a DateEvent, with changes only in colors
 * 
 * @author Timothy Ngoyen, Andy Wu, William Luna
 *
 *
 */
public class ReminderEvent extends DateEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5484701177460911006L;

	public ReminderEvent(int m, int d, int y, int tsh, int tsm, int teh, int tem, String e, String ed) {
		super(m, d, y, tsh, tsm, teh, tem, e, ed);
	}

	public Color getColorFont() {
		return Color.BLUE;
	}

}
