package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * @author sam-doyle
 * Date: April 13th 2026
 * Description: This program reads in a file containing ascii encoded integers numbers separated by one or more
 * line feeds, carriage returns, or spaces, and then sorts them using selection sort, and then searches using binary search
 */
public class Part2 {

	private static final File NUMBERS = new File("Numbers-1.txt");

	public static void main(String[] args) {
		int[] arrayFromFile = fillArrayFromFile(NUMBERS);
		sortArray(arrayFromFile);
		System.out.println(recursionBinarySearch(arrayFromFile, 285, 0, arrayFromFile.length - 1));
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
	 * Uses recursive binary search to search for the given key in the array.
	 * This method should receive 0 for low, and array.lenght - 1 for high initially to search the entire array
	 * @param array
	 * @param key
	 * @param low
	 * @param high
	 * @return true if the key exists in the array, false if it does not exist in the array
	 */
	private static boolean recursionBinarySearch(int[] array, int key, int low, int high) {
		if (low > high)
			return false;
		else {
			int mid = (low + high) / 2;
			if (array[mid] > key)
				return recursionBinarySearch(array, key, low, mid - 1);
			else if (array[mid] < key)
				return recursionBinarySearch(array, key, mid + 1, high);
			else return true;
		}
	}

	/**
	 * Given a file containing integers separated by one or more ascii line feed, carraige return or space characters,
	 * this method will return an int[] containing the integers
	 * @param file
	 * @return
	 */
	private static int[] fillArrayFromFile(File file) {
		int[] intArray = null;
		int[] byteArray = new int[(int) file.length()];
		try {
			intArray = fillByteArrayFromFileAndInitIntArray(byteArray, file);
			fillIntArrayFromByteArray(byteArray, intArray);
		} catch (IOException e) {
			System.err.println("Something went wrong reading the file " + file.getName());
			e.printStackTrace();
		}
		return intArray;
	}

	/**
	 * Reads the file as a sequence of bytes and fills byteArray with the bytes.
	 * While reading the bytes, the method also determines how many integers are in
	 * the file by incrementing a count whenever it encounters a line feed, carraige
	 * return or space character that is not located immediately after another line
	 * feed carriage return or space. It uses this count to then initialize an array
	 * of integers, and returns it.
	 * 
	 * @param byteArray
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static int[] fillByteArrayFromFileAndInitIntArray(int[] byteArray, File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		int i = 0;
		int numIntegers = 0;
		int nextByte;
		while ((nextByte = fis.read()) != -1) {
			if (isByteAsciiDecimalDigit(nextByte)) { // (if the next byte is one of the digit 0-9 in
														// ascii...)
				// convert the ascii encoded decimal digit to its numeric value by subtracting
				// 48 (since ascii digits start at 48 https://www.ascii-code.com/)
				byteArray[i] = nextByte - 48;
				if (i == file.length() - 1) { // (if we have reached the last byte in the file...)
					// then treat this also as the end of a line containing an integer, so increment
					// numIntegers
					numIntegers++;
				}
			} else if (isByteAsciiLineFeedOrCarriageReturnOrSpace(nextByte)) { // (if the next byte is an ascii encoded
				// Carriage Return or Line Feed or Space...)
				byteArray[i] = nextByte;
				if (i != 0 && !isByteAsciiLineFeedOrCarriageReturnOrSpace(byteArray[i - 1])) { // (if this is not
																								// the first byte
																								// from the
					// file, and the previous byte was not
					// another Carriage Return or Line Feed or Space...)
					// then this is the end of a line containing an integer, so increment
					// numIntegers
					numIntegers++;
				}
			} else { // (the next byte is not a digit or a Carriage Return or Line Feed or Space
						// character so
						// our input is not
						// correct)
				System.out.println(nextByte);
				System.err.println("The file " + file.getName()
						+ " is not an an ascii encoded file containing only digits 0-9 and Carriage Return or Line Feed or Space characters");
				fis.close();
				throw new IOException();
			}
			i++;
		}
		fis.close();
		return new int[numIntegers];
	}

	/**
	 * Iterates over the byte array and adds the line feed, carriage return, or
	 * space separated integers to the int array
	 * 
	 * @param byteArray
	 * @param intArray
	 */
	private static void fillIntArrayFromByteArray(int[] byteArray, int[] intArray) {
		int digitCount = 0; // number of digits since the last Carriage Return or Line Feed or Space.
		int intArrayIndex = intArray.length - 1; // start at the end of each array and work forwards
		for (int byteArrayIndex = byteArray.length - 1; byteArrayIndex >= 0; byteArrayIndex--) {
			int currentByte = byteArray[byteArrayIndex];
			if (!isByteAsciiLineFeedOrCarriageReturnOrSpace(currentByte)) {
				intArray[intArrayIndex] += currentByte * Math.pow(10, digitCount);
				digitCount++;
			} else {
				if (byteArrayIndex != byteArray.length - 1
						&& !isByteAsciiLineFeedOrCarriageReturnOrSpace(byteArray[byteArrayIndex + 1])) {
					intArrayIndex--;
					digitCount = 0;
				}
			}
		}
	}

	// HELPER METHODS FOR CHECKING ASCII CHARACTERS
	
	private static boolean isByteAsciiDecimalDigit(int byteToCheck) {
		return (byteToCheck <= 57 && byteToCheck >= 48);
	}

	private static boolean isByteAsciiCariageReturn(int byteToCheck) {
		return (byteToCheck == 13);
	}

	private static boolean isByteAsciiLineFeed(int byteToCheck) {
		return (byteToCheck == 10);
	}

	private static boolean isByteAsciiLineFeedOrCarriageReturnOrSpace(int byteToCheck) {
		return isByteAsciiCariageReturn(byteToCheck) || isByteAsciiLineFeed(byteToCheck)
				|| isByteAsciiSpace(byteToCheck);
	}

	private static boolean isByteAsciiSpace(int byteToCheck) {
		return (byteToCheck == 32);
	}
}
