package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import main.Document;
import main.DocumentApp;
import main.Email;
import main.FileDoc;

class DocumentTest {
	/**
	 * Tests if the correct class instance is created from Email.txt
	 */
	@Test
	void testGetDocumentEmail() {
		try {
			Document testDoc = DocumentApp.getDocument("Email.txt");
			if (!(testDoc instanceof Email)) fail("Document created from Email.txt was not instance of Email");
		} catch (FileNotFoundException e) {
			System.err.println("There was an error reading Email.txt");
			e.printStackTrace();
			fail("Couldnt find Email.txt");
		}
	}

	/**
	 * Tests if the correct class instance is created from GeneralFile.txt
	 */
	@Test
	void testGetDocumentFileDoc() {
		try {
			Document testDoc = DocumentApp.getDocument("GeneralFile.txt");
			if (!(testDoc instanceof FileDoc)) fail("Document created from GeneralFile.txt was not instance of FileDoc");
		} catch (FileNotFoundException e) {
			System.err.println("There was an error reading GeneralFile.txt");
			e.printStackTrace();
			fail("Couldnt find GeneralFile.txt");
		}
	}
}
