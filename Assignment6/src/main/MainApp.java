package main;

import java.util.ArrayList;



public class MainApp {
@FunctionalInterface
interface GeneralFunction<T, E, R> {
	R perform(T t, E e);
}
	public static void main(String[] args) {
		/**
		 * Lambda expression which receives two array lists and returns a combined array list containing the elements of both arrays
		 */
		GeneralFunction<ArrayList<String>, ArrayList<String>, ArrayList<String>> combineArrays = (ArrayList<String> a, ArrayList<String> b) -> {
			if (a == null || b == null) return null;
			ArrayList<String> combinedArray = new ArrayList<String>();
			for (String s : a) {
				combinedArray.add(s);
			}
			for (String s : b) {
				combinedArray.add(s);
			}
			return combinedArray;
		};
		
		/**
		 * lambda expression that receives two integers and returns true of they are both prime.
		 * Otherwise, it will return false.
		 */
		GeneralFunction<Integer, Integer, Boolean> checkPrime = (Integer a, Integer b) -> {
			return (checkPrime(a) && checkPrime(b));
		};
	}
	
	private static boolean checkPrime(int num) {
		if (num < 1) return false;
		
		for (int i = 2; i < num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
