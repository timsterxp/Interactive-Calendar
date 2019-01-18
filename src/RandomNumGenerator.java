import java.util.Random;

/**
 * This follows conventions of a Singleton Number Generator (Design Pattern)
 * 
 * @author Timothy Ngoyen, Andy Wu, William Luna
 *
 *
 */
public class RandomNumGenerator {
	private Random gen;
	private static RandomNumGenerator instance = new RandomNumGenerator();

	private RandomNumGenerator() {
		gen = new Random();
	}

	public int nextInt(int i) {
		return gen.nextInt(i);
	}

	public static RandomNumGenerator getInstance() {
		return instance;
	}
}
