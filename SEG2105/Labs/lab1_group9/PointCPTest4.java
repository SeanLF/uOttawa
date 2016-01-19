// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

/**
 * This class prompts the user for a set of coordinates, and then converts them
 * from polar to cartesian or vice-versa.
 * 
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Paul Holden
 * @version July 2000
 */
public class PointCPTest4 {
	// Class methods *****************************************************

	/**
	 * This method is responsible for the creation of the PointCP4 object. This
	 * can be done in two ways; the first, by using the command line and running
	 * the program using <code> java 
	 * PointCPTest4 &lt;coordtype (c/p)&gt; &lt;X/RHO&gt; &lt;Y/THETA&gt;
	 * </code> and the second by getting the program to prompt the user. If the
	 * user does not enter a valid sequence at the command line, the program
	 * will prompte him or her.
	 * 
	 * @param args
	 *            [0] The coordinate type. P for polar and C for cartesian.
	 * @param args
	 *            [1] The value of X or RHO.
	 * @param args
	 *            [2] The value of Y or THETA.
	 */
	public static void main(String[] args) {
		
			PointCP4 point;
			PointCP4 test;

			System.out.println("Cartesian-Polar Coordinates Conversion Program 4");

			// Create new PointCP4 with x,y -> [0,1]
			try {
				point = new PointCP4("P".toUpperCase().charAt(0), Math.random(), Math.random());
			} catch (Exception e) {
				// If we arrive here, it is because either there were no
				// command line arguments, or they were invalid
				if (args.length != 0)
					System.out.println("Invalid arguments on command line");

				try {
					point = getInput();
				} catch (IOException ex) {
					System.out.println("Error getting input. Ending program.");
					return;
				}
			}
			System.out.println("\nYou entered:\n" + point);
			/*
			 * point.convertStorageToCartesian();
			 *  System.out.println("\nAfter asking to store as Cartesian:\n" + point);
			 *  point.convertStorageToPolar();
			 *  System.out.println("\nAfter asking to store as Polar:\n" + point);
			 */
			
			// BEGIN TESTING
			
			/*
			 * currentTimeMillis() returns the difference, measured in ms, between the current time and 
			 * midnight, Jan 1st, 1970 UTC.
			 */ 
			test = new PointCP4('P', Math.random(), Math.random());
			testConstructor(test);
			testGetterCartesian(test);
			testGetterPolar(test);
		}
		
		
	/** 
	 * count number of times we can call the constructor in 10 milliseconds
	 * print out the result
	 * @param point
	 */
	private static void testConstructor(PointCP4 point){
		// startTime for this method
		long startTimeConstructor;
		// i is used to keep track of the # of times we ran the constructor in 10s
		int i = 0;
		
		startTimeConstructor = System.currentTimeMillis();
		for (i = 0; System.currentTimeMillis() < startTimeConstructor + 10000; i++)
			point = new PointCP4("P".toUpperCase().charAt(0), Math.random(), Math.random());
		System.out.println("This constructor ran " + i + " times.");
	}
	
	/** 
	 * count number of times we can call the Cartesian getters in 10 milliseconds
	 * print out the result
	 * @param point
	 */
	private static void testGetterCartesian(PointCP4 point){
		// startTime for this method
		long startTimeCartesian;
		// j is used to keep track of the # of times we ran the constructor in 10s
		int j = 0;
		
		startTimeCartesian = System.currentTimeMillis();
		for (j = 0; System.currentTimeMillis() < startTimeCartesian + 10000; j++){
			point.getX();
			point.getY();
		}
		System.out.println("getX() and getY() ran " + j + " times.");
	}
	
	/** 
	 * count number of times we can call the Polar getters in 10 milliseconds
	 * print out the result
	 * @param point
	 */
	private static void testGetterPolar(PointCP4 point){
		// startTime for this method
		long startTimePolar;
		// j is used to keep track of the # of times we ran the constructor in 10s
		int k = 0;
		
		startTimePolar = System.currentTimeMillis();
		for (k = 0; System.currentTimeMillis() < startTimePolar + 10000; k++){
			point.getRho();
			point.getTheta();
		}
		System.out.println("getRho() and getTheta() ran " + k + " times.");
	}

	
	/**
	 * This method obtains input from the user and verifies that it is valid.
	 * When the input is valid, it returns a PointCP4 object.
	 * 
	 * @return A PointCP4 constructed using information obtained from the user.
	 * @throws IOException
	 *             If there is an error getting input from the user.
	 */
	private static PointCP4 getInput() throws IOException {
		byte[] buffer = new byte[1024]; // Buffer to hold byte input
		boolean isOK = false; // Flag set if input correct
		String theInput = ""; // Input information

		// Information to be passed to the constructor
		char coordType = 'A'; // Temporary default, to be set to P or C
		double a = 0.0;
		double b = 0.0;

		// Allow the user to enter the three different arguments
		for (int i = 0; i < 3; i++) {
			while (!(isOK)) {
				isOK = true; // flag set to true assuming input will be valid

				// Prompt the user
				if (i == 0) // First argument - type of coordinates
				{
					System.out.print("Enter the type of Coordinates you "
							+ "are inputting ((C)artesian / (P)olar): ");
				} else // Second and third arguments
				{
					System.out.print("Enter the value of "
							+ (coordType == 'C' ? (i == 1 ? "X " : "Y ")
									: (i == 1 ? "Rho " : "Theta "))
							+ "using a decimal point(.): ");
				}

				// Get the user's input

				// Initialize the buffer before we read the input
				for (int k = 0; k < 1024; k++)
					buffer[k] = '\u0020';

				System.in.read(buffer);
				theInput = new String(buffer).trim();

				// Verify the user's input
				try {
					if (i == 0) // First argument -- type of coordinates
					{
						if (!((theInput.toUpperCase().charAt(0) == 'C') || (theInput
								.toUpperCase().charAt(0) == 'P'))) {
							// Invalid input, reset flag so user is prompted
							// again
							isOK = false;
						} else {
							coordType = theInput.toUpperCase().charAt(0);
						}
					} else // Second and third arguments
					{
						// Convert the input to double values
						if (i == 1)
							a = Double.valueOf(theInput).doubleValue();
						else
							b = Double.valueOf(theInput).doubleValue();
					}
				} catch (Exception e) {
					System.out.println("Incorrect input");
					isOK = false; // Reset flag as so not to end while loop
				}
			}

			// Reset flag so while loop will prompt for other arguments
			isOK = false;
		}
		// Return a new PointCP4 object
		return (new PointCP4(coordType, a, b));
	}
}
