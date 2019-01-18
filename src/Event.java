import java.awt.Color;

public interface Event {
	int getEventDay();

	int getEventMonth();

	int getEventYear();

	void setTimes(int startHour, int startMinute, int endHour, int endMinute);

	int[] getTimeStart();

	int[] getTimeEnd();

	String getName();

	String getDescription();

	boolean equals(Object o);

	Color getColorFont();
}
