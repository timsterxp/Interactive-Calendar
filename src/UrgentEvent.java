import java.awt.Color;

/**
 * An urgent event follows guidelines of DateEvent but with a different font
 * color
 * 
 * @author Timothy Ngoyen, Andy Wu, William Luna
 *
 *
 */
public class UrgentEvent extends DateEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600768202672240125L;

	public UrgentEvent(int m, int d, int y, int tsh, int tsm, int teh, int tem, String e, String ed) {
		super(m, d, y, tsh, tsm, teh, tem, e, ed);
	}

	public Color getColorFont() {
		return Color.RED;
	}

}
