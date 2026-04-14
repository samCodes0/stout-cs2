package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author sam-doyle
 * Date: apr 13th 2026
 * Description: This program prompts the user for a positive integer and then computes and shows the sum of all the positive integers up to and inluding the number
 */
public class Part1 {

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
	
}
