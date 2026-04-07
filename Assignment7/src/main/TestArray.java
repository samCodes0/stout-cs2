package main;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import org.junit.jupiter.api.Test;

class TestArray {

	@Test
	void testSortArray() {
		Random rand = new Random();
		// generate random array
		int[] randomArray = new int[100];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = rand.nextInt(0, 1000);
		}
		MainApp.sortArray(randomArray);
		for (int i = 0; i < randomArray.length - 1; i++) {
			if (randomArray[i] > randomArray[i+1]) {
				fail("Sorted array is not in ascending order");
			}
		}
		for (int i = 0; i < randomArray.length; i++) {
			System.out.println(i);
		}
	}

}
