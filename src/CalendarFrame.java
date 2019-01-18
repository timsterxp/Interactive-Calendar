import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import java.util.*;

/**
 * CalendarFrame displays an interactive calendar that allows you to input
 * different events for certain dates as well as other features such as
 * exercises or a funny picture.
 * 
 * @author Timothy Nguyen, Andy Wu, William Luna
 *
 */
public class CalendarFrame {
	private JFrame calFrame, dayFrame;
	private JPanel calPanel, monthPanel, eventPanel, quoteFrame;
	private JLabel quote;
	private int[][] calInfo;
	private JButton[][] dates;
	private CalendarInfo getInfo;
	public static String[] monthNames = new String[] { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	private int currentDay, currentMonth, currentYear;
	private EventList eventList;
	private static ArrayList<Event> currentDateEventList;

	/**
	 * Main Constructor of the main calendar frame, calFrame. Initializes the fields
	 * getInfo, eventList, quoteFrame, quote, dayFrame, calInfo, calPanel. Calls
	 * methods addDaysOfWeek, addDays, setUpMonthButtons, addQuoteoftheDay, and
	 * listEventButton.
	 * 
	 * @param theEventList
	 */

	public CalendarFrame(EventList theEventList) {

		eventList = readEventList("events.txt");
		calFrame = new JFrame("Calendar");
		getInfo = new CalendarInfo();
		calFrame.setSize(700, 450);

		quoteFrame = new JPanel();
		quote = new JLabel();
		dayFrame = new JFrame();

		calInfo = getInfo.getCalendarInfo();
		calPanel = new JPanel();
		addDaysOfWeek();

		addDays();

		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		setUpMonthButtons();

		gbc.gridx = 0;
		gbc.gridy = 0;
		calFrame.add(monthPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		addQuoteOfTheDay(gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		calFrame.add(calPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		listEventButton(gbc);
		calPanel.setBackground(new Color(153, 204, 255));
		calFrame.getContentPane().setBackground(new Color(153, 204, 255));
		quoteFrame.setBackground(new Color(153, 204, 255));
		calFrame.setVisible(true);
	}

	/**
	 * Method to remove the days of the month on the calendar (calPanel). Used when
	 * switching between months
	 */

	private void removeDays() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				calPanel.remove(dates[i][j]);
			}
		}
	}

	/**
	 * Method to add the days of the month on the calendar (calPanel). Used when
	 * switching between months. Calls updateCalendar
	 */

	private void addDays() {
		dates = new JButton[6][7];

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dates[i][j] = new JButton();
				calPanel.add(dates[i][j]);
			}
		}
		updateCalendar();
	}

	/**
	 * This method is used to form the buttons to label days of the week of each
	 * column on the calendar (calPanel)
	 */

	private void addDaysOfWeek() {
		JButton sunday = new JButton("SUN");
		JButton monday = new JButton("MON");
		JButton tuesday = new JButton("TUE");
		JButton wednesday = new JButton("WED");
		JButton thursday = new JButton("THU");
		JButton friday = new JButton("FRI");
		JButton saturday = new JButton("SAT");
		sunday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		monday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		tuesday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		wednesday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		thursday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		friday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		saturday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		calPanel.add(sunday);
		calPanel.add(monday);
		calPanel.add(tuesday);
		calPanel.add(wednesday);
		calPanel.add(thursday);
		calPanel.add(friday);
		calPanel.add(saturday);
	}

	/**
	 * Method to set up the buttons displaying the month/year and to go to the
	 * previous/next month on monthPanel. Uses getInfo. Calls removeDays, addDays
	 */

	private void setUpMonthButtons() {
		JButton prevMonth = new JButton();
		prevMonth.setText("<");

		JButton monthName = new JButton();
		monthName.setBackground(Color.BLACK);
		monthName.setForeground(Color.CYAN);
		monthName.setSize(10, 20);
		Dimension maxSize = new Dimension(100, 20);
		monthName.setMaximumSize(maxSize);
		monthName.setText(monthNames[getInfo.getCurrentMonth()] + " " + getInfo.getCurrentYear());

		monthPanel = new JPanel();
		monthPanel.setLayout(new BorderLayout());
		// monthPanel.setLayout(new GridBagLayout());
		JButton nextMonth = new JButton();
		nextMonth.setText(">");
		prevMonth.addMouseListener(new MouseListener() {

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
				removeDays();
				getInfo.moveMonth(-1);
				addDays();

				monthName.setText(monthNames[getInfo.getCurrentMonth()] + " " + getInfo.getCurrentYear());

				QuoteReader quoteReader = new QuoteReader("quotes.txt");
				Random rand = new Random();
				int i = rand.nextInt(17);
				quote.setText(quoteReader.getQuote(i));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		nextMonth.addMouseListener(new MouseListener() {

			// Code to move to next month
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				removeDays();
				getInfo.moveMonth(1);
				addDays();

				monthName.setText(monthNames[getInfo.getCurrentMonth()] + " " + getInfo.getCurrentYear());
				QuoteReader quoteReader = new QuoteReader("quotes.txt");
				Random rand = new Random();
				int i = rand.nextInt(17);
				quote.setText(quoteReader.getQuote(i));

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		// monthPanel.setLayout();
		monthPanel.add(prevMonth, BorderLayout.LINE_START);
		monthPanel.add(monthName, BorderLayout.CENTER);
		monthPanel.add(nextMonth, BorderLayout.LINE_END);

	}

	/**
	 * Method to get random quote for the day/month, sets up label to display on the
	 * main calendar frame. Uses quote, quoteFrame, calFrame
	 * 
	 * @param GBC
	 */

	private void addQuoteOfTheDay(GridBagConstraints GBC) {

		QuoteReader quoteReader = new QuoteReader("quotes.txt");
		Random rand = new Random();
		int i = rand.nextInt(17);
		String quoteToUse = "";
		quoteToUse += "<html><p>";
		quoteToUse += quoteReader.getQuote(i);
		quoteToUse += "</p><html>";
		quote = new JLabel(quoteToUse, SwingConstants.CENTER);
		quote.setText(quoteToUse);
		quote.setOpaque(false);
		quoteFrame.add(quote);
		calFrame.add(quoteFrame, GBC);

	}

	/**
	 * Method to remove the listeners of the day buttons. Used when switching months
	 * due to resetting of buttons. Uses dates
	 */

	private void removeActionListeners() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				for (ActionListener a : dates[i][j].getActionListeners()) {
					dates[i][j].removeActionListener(a);
				}
			}
		}
	}

	/**
	 * Method used the form the button to open the frame for list of events. Calls
	 * showEvents, memeButton. Uses eventPanel, calFrame
	 * 
	 * @param gbc
	 */

	private void listEventButton(GridBagConstraints gbc) {
		JButton listEvents = new JButton();
		listEvents.setText("See Events");
		listEvents.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Event button was pressed");
				showEvents(); // Complete method
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		eventPanel = new JPanel();
		eventPanel.setBackground(new Color(153, 204, 255));
		eventPanel.add(listEvents);
		memeButton();
		calFrame.add(eventPanel, gbc);
	}

	/**
	 * Method used to display a new frame displaying all events on the calendar.
	 * Uses eventList, monthNames
	 */

	private void showEvents() {

		JFrame eventFrame = new JFrame();
		JPanel newEventPanel = new JPanel();

		ArrayList<JLabel> eventLabels = new ArrayList<JLabel>();
		for (Event e : eventList.getList()) {
			eventLabels.add(new JLabel(monthNames[e.getEventMonth()] + " " + e.getEventDay() + ", " + e.getEventYear()
					+ " --- " + e.getName()));
		}

		for (JLabel l : eventLabels) {
			newEventPanel.add(l);
		}

		if (eventLabels.isEmpty()) {
			JLabel emptyList = new JLabel();
			emptyList.setText("You have no events!");
			newEventPanel.add(emptyList);
		}

		newEventPanel.setLayout(new BoxLayout(newEventPanel, BoxLayout.Y_AXIS));
		eventFrame.setSize(400, 400);
		eventFrame.add(newEventPanel);
		eventFrame.setVisible(true);
	}

	boolean dayFrameOpen = false;

	/**
	 * Method to update the calendar frame whenever a change in the calendar
	 * happens. Calls removeActionListeners, displayDateFrame. Uses dates, calInfo,
	 * currentMonth, currentDay, currentYear, getInfo, eventList
	 */
	private void updateCalendar() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dates[i][j].removeAll();
				dates[i][j].setText("" + calInfo[i][j]);
				removeActionListeners();
				if (calInfo[i][j] == 0) {
					dates[i][j].setVisible(false);

				} else {
					dates[i][j].setVisible(true);

					dates[i][j].addMouseListener(new DateAdapter(calInfo[i][j], getInfo.getCurrentMonth()) {
						public void mousePressed(MouseEvent arg0) {
							System.out.println(this.getMonth() + " " + this.getDay());

							if (!dayFrameOpen) {
								currentMonth = getInfo.getCurrentMonth();
								currentDay = this.getDay();
								currentYear = getInfo.getCurrentYear();
								currentDateEventList = eventList.getEventListOfDay(currentMonth, currentDay,
										currentYear);
								displayDateFrame();
								dayFrameOpen = true;
							}
						}
					});
				}
			}
		}
	}

	/**
	 * Method to display a new Date frame when a date is clicked. Calls
	 * clearDateFrame, toAddEventFrame, toRemoveEventFrame. Uses
	 * currentDateEventList, eventList, currentMonth, currentDay, currentYear,
	 * dayFrame, monthNames
	 */
	private void displayDateFrame() {
		clearDateFrame();
		currentDateEventList = eventList.getEventListOfDay(currentMonth, currentDay, currentYear);
		dayFrame.setTitle(monthNames[currentMonth] + " " + currentDay + ", " + currentYear);
		dayFrame.setLayout(new GridBagLayout());

		dayFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dayFrameOpen = false;
				System.out.println("DayFrame closed");
			}
		});

		JButton addEvent = new JButton("Add Event");
		addEvent.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {

				System.out.println("addEvent button was pressed, current date: " + (currentMonth + 1) + "/" + currentDay
						+ "/" + currentYear);
				// Changes frame state to add events
				toAddEventFrame(); // Complete method

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
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton removeEvent = new JButton("Remove Event");
		removeEvent.addMouseListener(new MouseListener() {

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
				// changes frame state to remove events
				System.out.println("removeEvent button was pressed, current date: " + (currentMonth + 1) + "/"
						+ currentDay + "/" + currentYear);
				toRemoveEventFrame(); // Complete method
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		JLabel schedule = new JLabel("Schedule/Events");
		GridBagConstraints eventGBC = new GridBagConstraints();
		eventGBC.insets = new Insets(0, 0, 5, 5);

		eventGBC.gridx = 0;
		eventGBC.gridy = 0;
		dayFrame.add(schedule, eventGBC);
		eventGBC.gridx = 0;
		eventGBC.gridy = 1;
		dayFrame.add(addEvent, eventGBC);
		eventGBC.gridy = 2;
		dayFrame.add(removeEvent, eventGBC);

		currentDateEventList = eventList.getEventListOfDay(currentMonth, currentDay, currentYear);

		JPanel eventPanel = new JPanel(new GridBagLayout());
		JLabel event;

		// Adding event names to eventPanel
		for (int i = 0; i < currentDateEventList.size(); i++) {
			eventGBC.gridy++;
			event = new JLabel(currentDateEventList.get(i).getName());
			if (currentDateEventList.get(i) instanceof UrgentEvent)
				event.setForeground(currentDateEventList.get(i).getColorFont());
			else if (currentDateEventList.get(i) instanceof ReminderEvent)
				event.setForeground(currentDateEventList.get(i).getColorFont());
			eventPanel.add(event, eventGBC);
		}

		JLabel eventTimes;
		eventGBC.gridx = 1;
		eventGBC.gridy = 2;
		String timeText;
		// Adding event times to event panel
		for (int i = 0; i < currentDateEventList.size(); i++) {
			eventGBC.gridy++;
			String[] start = toReadableTime(currentDateEventList.get(i).getTimeStart());
			String[] end = toReadableTime(currentDateEventList.get(i).getTimeEnd());
			timeText = start[0] + ":" + start[1] + "-" + end[0] + ":" + end[1];
			eventTimes = new JLabel(timeText);
			if (currentDateEventList.get(i) instanceof UrgentEvent)
				eventTimes.setForeground(currentDateEventList.get(i).getColorFont());
			else if (currentDateEventList.get(i) instanceof ReminderEvent)
				eventTimes.setForeground(currentDateEventList.get(i).getColorFont());
			eventPanel.add(eventTimes, eventGBC);
		}

		eventGBC.gridy = 3;
		eventGBC.gridx = 0;
		dayFrame.add(eventPanel, eventGBC);

		dayFrame.setSize(400, 400);
		dayFrame.setVisible(true);

	}

	/**
	 * Method to exit Fate frame, used when a new date button is clicked, the "Add
	 * Event" button is clicked, or the "Remove Event" button is clicked. Uses
	 * dayFrame
	 */

	private void clearDateFrame() {
		dayFrame.setVisible(false);
		dayFrame.getContentPane().removeAll();
	}

	/**
	 * Method used to create interactive button for cute animal. Uses eventPanel
	 */

	private void cuteAnimalButton() {
		JButton cuteAnimalButton = new JButton();
		cuteAnimalButton.setText("Cute Animal Picture");
		cuteAnimalButton.addMouseListener(new MouseListener() {

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
				JFrame animalFrame = new JFrame();
				animalFrame.setTitle("A Cute Animal");
				animalFrame.setSize(600, 600);
				String location = "Cute Animals\\cute";
				Random num = new Random();
				int numb = num.nextInt(18);
				location += numb;
				location += ".jpg";
				ImageIcon meme = new ImageIcon(location);
				Image resizeMeme = meme.getImage();
				Image newMeme = resizeMeme.getScaledInstance(600, 580, Image.SCALE_DEFAULT);
				animalFrame.add(new JLabel(new ImageIcon(newMeme)));
				animalFrame.setVisible(true);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		eventPanel.add(cuteAnimalButton);
	}

	/**
	 * Method used to create interactive button for a random exercise. Opens new
	 * frame when clicked. Uses eventPanel
	 */

	private void exerciseButton() {
		JButton exercise = new JButton();
		exercise.setText("Random Exercise");
		exercise.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				JFrame exerciseFrame = new JFrame();
				exerciseFrame.setSize(700, 700);
				String location = "Exercise Infographics\\exercise";
				Random num = new Random();
				int numb = num.nextInt(5);
				JLabel exerciseType = new JLabel();
				QuoteReader exerciseReader = new QuoteReader("exercises.txt");
				String exerciseToDo = exerciseReader.getQuote(numb);
				exerciseType.setText(exerciseToDo);
				JPanel exercisePanel = new JPanel();
				exercisePanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.anchor = GridBagConstraints.PAGE_START;
				exercisePanel.add(exerciseType, c);
				exerciseFrame.setTitle(exerciseToDo);
				location += numb;
				location += ".jpg";
				ImageIcon meme = new ImageIcon(location);
				Image resizeMeme = meme.getImage();
				Image newMeme = resizeMeme.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
				c.anchor = GridBagConstraints.CENTER;
				c.gridy = 1;
				exercisePanel.add(new JLabel(new ImageIcon(newMeme)), c);
				exerciseFrame.add(exercisePanel);
				exerciseFrame.setVisible(true);

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		eventPanel.add(exercise);
	}

	/**
	 * Method used to create interactive button to display funny meme image. Uses
	 * eventPanel
	 */

	private void memeButton() {
		JButton memeMe = new JButton();
		memeMe.setText("Meme Me");
		eventPanel.add(memeMe);
		cuteAnimalButton();
		exerciseButton();
		memeMe.addMouseListener(new MouseListener() {

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
				JFrame memeTest = new JFrame();
				memeTest.setSize(500, 500);
				memeTest.setTitle("Random Meme");
				String location = "Memes\\meme";
				Random num = new Random();
				int numb = num.nextInt(10) + 1;
				location += numb;
				location += ".jpg";
				ImageIcon meme = new ImageIcon(location);
				Image resizeMeme = meme.getImage();
				Image newMeme = resizeMeme.getScaledInstance(490, 470, Image.SCALE_DEFAULT);
				memeTest.add(new JLabel(new ImageIcon(newMeme)));
				memeTest.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	/**
	 * Checks if value is a valid hour value in the 24hr format
	 * 
	 * @param hour
	 * @return true or false
	 */
	public boolean isValidHour(int hour) {
		if (hour >= 0 && hour <= 24)
			return true;
		return false;
	}

	/**
	 * Checks if values is a valid minute value within an hour
	 * 
	 * @param minute
	 * @return true or false
	 */
	public boolean isValidMinute(int minute) {
		if (minute >= 0 && minute <= 59)
			return true;
		return false;
	}

	/**
	 * Method used to create new frame when the "Add Event" button is clicked in the
	 * Date frame. Calls clearDateFrame, saveEventList, displayDateFrame. Uses
	 * dayFrame, currentMonth, currentDay, currentYear, eventList
	 */
	private void toAddEventFrame() {
		// complete method to update dayFrame for adding Events
		// updateDateFrame();
		clearDateFrame();
		dayFrame.setLayout(new GridBagLayout());
		dayFrame.setTitle("Add Event");
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel timePanel = new JPanel(new GridBagLayout());
		JPanel descriptionPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new GridBagLayout());

		JLabel start = new JLabel("Start Time");
		JLabel end = new JLabel("End Time");
		JLabel name = new JLabel("Name");
		JLabel description = new JLabel("Description");
		JLabel colon1 = new JLabel(" : ");
		JLabel colon2 = new JLabel(" : ");
		JLabel labelEventChoices = new JLabel("Event Type");
		JButton add = new JButton("Add");
		JButton cancel = new JButton("Cancel");

		JTextField startHour = new JTextField(2);
		JTextField startMinute = new JTextField(2);
		JTextField endHour = new JTextField(2);
		JTextField endMinute = new JTextField(2);
		JTextField eName = new JTextField(20);
		JTextField eDescription = new JTextField(20);
		String[] choices = { "Reminder", "Urgent" };
		JComboBox<String> eventChoices = new JComboBox<String>(choices);

		add.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub
				String type = (String) eventChoices.getSelectedItem();

				int sHour = Integer.parseInt(startHour.getText());
				int sMin = Integer.parseInt(startMinute.getText());
				int eHour = Integer.parseInt(endHour.getText());
				int eMin = Integer.parseInt(endMinute.getText());

				if (isValidHour(sHour) && isValidHour(eHour) && isValidMinute(sMin) && isValidMinute(eMin)) {
					String evName = eName.getText();
					String evDes = eDescription.getText();
					Event toAdd;
					if (type == "Reminder") {
						toAdd = new ReminderEvent(currentMonth, currentDay, currentYear, sHour, sMin, eHour, eMin,
								evName, evDes);
						eventList.addEvent(toAdd);
					}

					else if (type == "Urgent") {
						toAdd = new UrgentEvent(currentMonth, currentDay, currentYear, sHour, sMin, eHour, eMin, evName,
								evDes);
						eventList.addEvent(toAdd);
					}

					saveEventList(eventList, "events.txt");
					displayDateFrame();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		cancel.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub
				displayDateFrame();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// Adding Labels to timePanel
		gbc.gridx = 0;
		gbc.gridy = 0;
		timePanel.add(start, gbc);
		gbc.gridy++;
		timePanel.add(end, gbc);

		// Adding start time text fields to timePanel
		gbc.gridx = 1;
		gbc.gridy = 0;
		timePanel.add(startHour, gbc);
		gbc.gridx++;
		timePanel.add(colon1, gbc);
		gbc.gridx++;
		timePanel.add(startMinute, gbc);

		// Adding end time text fields to timePanel
		gbc.gridx = 1;
		gbc.gridy++;
		timePanel.add(endHour, gbc);
		gbc.gridx++;
		timePanel.add(colon2, gbc);
		gbc.gridx++;
		timePanel.add(endMinute, gbc);

		// Adding labels to descriptionPanel
		gbc.gridx = 0;
		gbc.gridy = 0;
		descriptionPanel.add(name, gbc);
		gbc.gridy++;
		descriptionPanel.add(description, gbc);
		gbc.gridy++;
		descriptionPanel.add(labelEventChoices, gbc);

		// Adding name text field
		gbc.gridx = 1;
		gbc.gridy = 0;
		descriptionPanel.add(eName, gbc);

		// Adding description text field
		gbc.gridy++;
		descriptionPanel.add(eDescription, gbc);

		// Adding event choice menu
		gbc.gridy++;
		descriptionPanel.add(eventChoices, gbc);

		// Adding buttons to buttonPanel
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(add, gbc);
		gbc.gridx++;
		buttonPanel.add(cancel, gbc);

		// Adding panels to dayFrame
		gbc.gridx = 0;
		gbc.gridy = 0;
		dayFrame.add(timePanel, gbc);
		gbc.gridy++;
		dayFrame.add(descriptionPanel, gbc);
		gbc.gridy++;
		dayFrame.add(buttonPanel, gbc);
		dayFrame.pack();
		dayFrame.setVisible(true);
	}

	/**
	 * Method used to remove event from the Event list. Calls saveEventList. Uses
	 * eventList
	 * 
	 * @param e
	 */

	private void removeEvent(Event e) {
		eventList.removeEvent(e);
		saveEventList(eventList, "Events.txt");
	}

	/**
	 * Method used to convert the time from integer to string.
	 * 
	 * @param time
	 * @return readable
	 */

	private String[] toReadableTime(int[] time) {
		String[] readable = new String[time.length];
		for (int i = 0; i < time.length; i++) {
			if (time[i] < 10) {
				readable[i] = "0" + time[i];
			} else
				readable[i] = Integer.toString(time[i]);
		}
		return readable;
	}

	/**
	 * Method used to create new frame when the "Remove Event" button is clicked in
	 * the Date frame. Calls clearDateFrame, removeEvent, displayDateFrame,
	 * toReadableTime. Uses dayFrame, currentDateEventList
	 */

	private void toRemoveEventFrame() {
		// complete method to update dayFrame for removing Events
		clearDateFrame();
		dayFrame.setLayout(new GridBagLayout());
		dayFrame.setTitle("Remove Events");
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel eventPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new GridBagLayout());

		JButton remove = new JButton("Remove Selected");
		JButton cancel = new JButton("Cancel");

		JLabel eventNames = new JLabel("Event Names");
		JLabel startEnd = new JLabel("Start/End");
		ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();

		remove.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub
				// (INCOMPLETE???)
				for (int i = 0; i < checkBoxList.size(); i++) {
					if (checkBoxList.get(i).isSelected()) {
						removeEvent(currentDateEventList.get(i));
					}
				}
				displayDateFrame();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		cancel.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub
				displayDateFrame();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		// Adding buttons to buttonPanel
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(remove, gbc);
		gbc.gridx++;
		buttonPanel.add(cancel, gbc);

		// Adding labels to eventPanel
		gbc.gridx = 0;
		gbc.gridy = 0;
		eventPanel.add(eventNames, gbc);
		gbc.gridx++;
		eventPanel.add(startEnd, gbc);

		// Adding checkBoxes to eventPanel
		gbc.gridx = 0;
		gbc.gridy = 1;
		for (int i = 0; i < currentDateEventList.size(); i++) {
			JCheckBox box = new JCheckBox(currentDateEventList.get(i).getName());
			checkBoxList.add(box);
			eventPanel.add(box, gbc);
			gbc.gridy++;
		}

		// Adding timeLabels to eventPanel
		gbc.gridx = 1;
		gbc.gridy = 1;
		for (int i = 0; i < currentDateEventList.size(); i++) {
			int[] start = currentDateEventList.get(i).getTimeStart();
			int[] end = currentDateEventList.get(i).getTimeEnd();
			String[] readableStart = toReadableTime(start);
			String[] readableEnd = toReadableTime(end);
			JLabel startTime = new JLabel(
					readableStart[0] + ":" + readableStart[1] + " - " + readableEnd[0] + ":" + readableEnd[1]);
			eventPanel.add(startTime, gbc);
			gbc.gridy++;
		}

		// Adding panels to dayFrame
		gbc.gridx = 0;
		gbc.gridy = 0;
		dayFrame.add(eventPanel, gbc);
		gbc.gridy++;
		dayFrame.add(buttonPanel, gbc);

		dayFrame.pack();
		dayFrame.setVisible(true);
	}

	/**
	 * Method used to load the previously saved EventList.
	 * 
	 * @param name
	 *            - name of file
	 * @return e the previously saved EventList
	 */

	private EventList readEventList(String name) {
		FileInputStream ifile = null;
		ObjectInputStream in = null;
		EventList e = new EventList();
		try {
			ifile = new FileInputStream(name);
			in = new ObjectInputStream(ifile);
			e = (EventList) in.readObject();
			in.close();
		} catch (Exception ex) {
			System.out.println("Exception caught.");
			ex.printStackTrace();
		}
		return e;
	}

	/**
	 * Method used to save the current EventList
	 * 
	 * @param e
	 * @param name
	 *            - name of file
	 */

	private void saveEventList(EventList e, String name) {
		try {
			FileOutputStream ofile = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(ofile);
			out.writeObject(e);
			ofile.close();
		} catch (Exception ex) {
			System.out.println("Exception caught.");
			ex.printStackTrace();
		}
	}
}