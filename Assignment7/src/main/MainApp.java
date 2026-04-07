package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sam Doyle
 * Date: 04/06/2026
 * Description: A program that reads a file containing integers into an array and performs sorting and searching on the integers
 * Note: all bytes in this program are represented as integers. this is because
 * bytes in java are signed, and hold values from -128 to 127, and I would like
 * the treat the bytes in this program as unsigned, holding values 0 - 255
 */
public class MainApp {
	// needs to be an ascii encoded file containing only digits 0-9 and Carriage
	// Return or Line Feed
	// characters
	private static final File UNIQUE_INTEGERS_FILE = new File("UniqueIntegers.txt");
	private static final int[] UNIQUE_INTEGERS_BYTES = new int[(int) UNIQUE_INTEGERS_FILE.length()];
	private static int[] UNIQUE_INTEGERS;

	public static void main(String[] args) {
		try {
			fillByteArrayFromFileAndInitIntArray();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Something went wrong reading the input file");
			System.exit(0);
		}
		fillIntArrayFromByteArray();
		sortArray(UNIQUE_INTEGERS);
		System.out.println(linearSearch(UNIQUE_INTEGERS, 38));
		System.out.println(binarySearch(UNIQUE_INTEGERS, 9549305));
	}

	/**
	 * sorts numbers in ascending order using selection sort
	 * 
	 * @param numbers
	 */
	public static void sortArray(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[smallestIndex]) {
					smallestIndex = j;
				}
			}
			// swap numbers at i and smallestIndex
			int tmp = numbers[i];
			numbers[i] = numbers[smallestIndex];
			numbers[smallestIndex] = tmp;
		}
	}

	/**
	 * searches for the integer 'key' inside the array using linear search and
	 * returns true if the integer exists, false if it does not exist in the array
	 * 
	 * @param numbers array to search inside
	 * @param key     number to search for
	 * @return true if key exists in the array, false if key is not found in the
	 *         array
	 */
	public static boolean linearSearch(int[] numbers, int key) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == key)
				return true;
		}
		return false;
	}

	/**
	 * searches for the integer 'key' inside the array using binary search and returns true if the integer exists,
	 * false if it does not exist in the array
	 * @param numbers
	 * @param key
	 * @return
	 */
	public static boolean binarySearch(int[] numbers, int key) {
		int lowIndex = 0;
		int highIndex = numbers.length - 1;
		while(lowIndex < highIndex) {
			int middleIndex = (lowIndex + highIndex) / 2;
			if (numbers[middleIndex] == key) {
				return true;
			} else if (numbers[middleIndex] > key) {
				highIndex = middleIndex - 1;
			} else if (numbers[middleIndex] < key) {
				lowIndex = middleIndex + 1;
			}
		}
		return false;
	}

	/**
	 * Reads the bytes from UNIQUE_INTEGERS_FILE into UNIQUE_INTEGERS_BYTES, and
	 * initializes UNIQUE_INTEGERS to an empty array with length equal to the number
	 * of integers in the file (assuming each integer is a string of digits on its
	 * own line)
	 * 
	 * @throws IOException if there was a problem reading the file, or if the file
	 *                     contains ascii characters other than the digits 0-9 or CR
	 *                     or LF
	 */
	private static void fillByteArrayFromFileAndInitIntArray() throws IOException {
		FileInputStream fis = new FileInputStream(UNIQUE_INTEGERS_FILE);
		int i = 0;
		int numIntegers = 0;
		int nextByte;
		while ((nextByte = fis.read()) != -1) {
			if (isByteAsciiDecimalDigit(nextByte)) { // (if the next byte is one of the digit 0-9 in
														// ascii...)
				// convert the ascii encoded decimal digit to its numeric value by subtracting
				// 48 (since ascii digits start at 48 https://www.ascii-code.com/)
				UNIQUE_INTEGERS_BYTES[i] = nextByte - 48;
				if (i == UNIQUE_INTEGERS_FILE.length() - 1) { // (if we have reached the last byte in the file...)
					// then treat this also as the end of a line containing an integer, so increment
					// numIntegers
					numIntegers++;
				}
			} else if (isByteAsciiLineFeedOrCarriageReturn(nextByte)) { // (if the next byte is an ascii encoded
																		// Carriage Return or Line Feed...)
				UNIQUE_INTEGERS_BYTES[i] = nextByte;
				if (i != 0 && !isByteAsciiLineFeedOrCarriageReturn(UNIQUE_INTEGERS_BYTES[i - 1])) { // (if this is not
																									// the first byte
																									// from the
					// file, and the previous byte was not
					// another Carriage Return or Line Feed...)
					// then this is the end of a line containing an integer, so increment
					// numIntegers
					numIntegers++;
				}
			} else { // (the next byte is not a digit or a Carriage Return or Line Feed character so
						// our input is not
						// correct)
				System.out.println(nextByte);
				System.err.println("The file " + UNIQUE_INTEGERS_FILE.getName()
						+ " is not an an ascii encoded file containing only digits 0-9 and Carriage Return or Line Feed characters");
				fis.close();
				throw new IOException();
			}
			i++;
		}
		fis.close();
		UNIQUE_INTEGERS = new int[numIntegers];
	}

	/**
	 * fill UNIQUE_INTEGERS
	 */
	private static void fillIntArrayFromByteArray() {
		int digitCount = 0; // number of digits since the last Carriage Return or Line Feed.
		int intArrayIndex = UNIQUE_INTEGERS.length - 1; // start at the end of each array and work forwards
		for (int byteArrayIndex = UNIQUE_INTEGERS_BYTES.length - 1; byteArrayIndex >= 0; byteArrayIndex--) {
			int currentByte = UNIQUE_INTEGERS_BYTES[byteArrayIndex];
			if (!isByteAsciiLineFeedOrCarriageReturn(currentByte)) {
				UNIQUE_INTEGERS[intArrayIndex] += currentByte * Math.pow(10, digitCount);
				digitCount++;
			} else {
				if (byteArrayIndex != UNIQUE_INTEGERS_BYTES.length - 1
						&& !isByteAsciiLineFeedOrCarriageReturn(UNIQUE_INTEGERS_BYTES[byteArrayIndex + 1])) {
					intArrayIndex--;
					digitCount = 0;
				}
			}
		}
	}

	private static boolean isByteAsciiDecimalDigit(int byteToCheck) {
		return (byteToCheck <= 57 && byteToCheck >= 48);
	}

	private static boolean isByteAsciiCariageReturn(int byteToCheck) {
		return (byteToCheck == 13);
	}

	private static boolean isByteAsciiLineFeed(int byteToCheck) {
		return (byteToCheck == 10);
	}

	private static boolean isByteAsciiLineFeedOrCarriageReturn(int byteToCheck) {
		return isByteAsciiCariageReturn(byteToCheck) || isByteAsciiLineFeed(byteToCheck);
	}
}
