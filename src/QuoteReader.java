import java.io.*;

/**
 * QuoteReader is used to read in which the file of quotes and get a quote for
 * the day.
 * 
 * @author Timothy Nguyen, Andy Wu, William Luna
 *
 */
public class QuoteReader {

	private String fileName;

	/**
	 * Main constructor to instantiate fileName
	 * 
	 * @param name
	 */
	public QuoteReader(String name) {
		fileName = name;
	}

	/**
	 * Method used to grab a random quote from the file. Uses fileName
	 * 
	 * @param num
	 * @return
	 */
	public String getQuote(int num) {
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int i = 0;
			while (((line = bufferedReader.readLine()) != null) && i < num) {
				// System.out.println(line);
				i++;
			}
			System.out.println(line);
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

		return line;
	}
}
