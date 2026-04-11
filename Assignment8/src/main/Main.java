package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int userInput = -1;
		System.out.println("This is a running sum calculator. Please enter a number greater than 0 and I will compute the sum of all of the positive numbers less than or equal to the number");
		System.out.print("Please enter the number to compute a running sum: ");
		do {
			try {
				userInput = sc.nextInt();
			} catch (InputMismatchException ie) {
				System.err.println("Invalid user input. The input was not an integer number");
				sc.nextLine();
			}
			if (userInput <= 0) {
				System.out.println("Please enter an integer greater than 0: ");
			}
		} while (userInput <= 0);
		sc.close();
		int runningSum = runningSum(userInput);
		System.out.println("The sum of the first " + userInput + " positive integers is: " + runningSum);
	}
	
	/**
	 * Computes the sum of the first 'num' positive integers up to and including 'num'
	 * Ex. if num is 5, this method will return 5 + 4 + 3 + 2 + 1
	 * @param num must be a number greater than 0 (positive integer)
	 * @return the sum of the first 'num' integers greater than 0. If num is an integer less than -1, this method will return -1
	 */
	private static int runningSum(int num) throws NumberFormatException {
		if (num < 1) return -1;
		if (num == 1) return num;
		return num + runningSum(num - 1);
	}
	
	/**
	 * Given a file containing 
	 * @param file
	 * @param array
	 */
	private static int[] fillArrayFromFile(File file) {
		
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
	private static void fillByteArrayFromFileAndInitIntArray(int[] byteArray, int[] intArray, File file) throws IOException {
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
																		// Carriage Return or Line Feed...)
				byteArray[i] = nextByte;
				if (i != 0 && !isByteAsciiLineFeedOrCarriageReturnOrSpact(byteArray[i - 1])) { // (if this is not
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
				System.err.println("The file " + file.getName()
						+ " is not an an ascii encoded file containing only digits 0-9 and Carriage Return or Line Feed characters");
				fis.close();
				throw new IOException();
			}
			i++;
		}
		fis.close();
		intArray = new int[numIntegers];
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

	private static boolean isByteAsciiLineFeedOrCarriageReturnOrSpace(int byteToCheck) {
		return isByteAsciiCariageReturn(byteToCheck) || isByteAsciiLineFeed(byteToCheck) || isByteAsciiSpace(byteToCheck);
	}
	
	private static boolean isByteAsciiSpace(int byteToCheck) {
		return (byteToCheck == 32);
	}

}
