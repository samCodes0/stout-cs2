package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DocumentApp {

	/**
	 * Given objects that are instances of Email or FileDoc, this method will return true if the instances' getText() method
	 * returns a string that contains the word. If the getText() method does not contain the word, or "obj" is not an instance of either
	 * Email or FileDoc, this method will return false.
	 * @param obj object to check for containing word
	 * @param word the word to check
	 * @return will return true if the instances' getText() method returns a string that contains the word. If the getText() method does not contain the word, or "obj" is not an instance of either Email or FileDoc, this method will return false.
	 */
	public static boolean ContainsKeyword(Object obj, String word) {
		if (obj instanceof Email) {
			Email e = (Email) obj;
			if (e.getText().contains(word))
				return true;
			else
				return false;
		}
		if (obj instanceof FileDoc) {
			FileDoc f = (FileDoc) obj;
			if (f.getText().contains(word))
				return true;
			else
				return false;
		}
		System.out.println(" Passed objec is not valid!");
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner userIn = new Scanner(System.in);
		System.out.print("Please enter the name of a document to read: ");
		String documentFileName = userIn.nextLine();
		userIn.close();
		printDocument(documentFileName);
	}

	/**
	 * reads the file with the name/path given by documentFileName, and prints its
	 * content with formatting. The formatting depends on the format of the file. If
	 * the file is greater than or equal to 3 lines in length, and the first two
	 * lines start with "sender" and "recipient" respectively, this method will
	 * treat this file as an email and format it as such. Otherwise, this method
	 * treats the file as a "GeneralFile" and outputs the file name along with the
	 * files body/contents
	 * 
	 * @param documentFileName the name/path of the file to read and print
	 */
	public static void printDocument(String documentFileName) {
		if (!checkFileName(documentFileName)) {
			System.out.println(String.format(
					"The filename: %s is not a valid filename. Is the extension .txt? Does it contain invalid character?",
					documentFileName));
		} else {
			try {
				Document document = getDocument(documentFileName);
				System.out.println(document);
			} catch (FileNotFoundException e) {
				System.out.println(
						String.format("Could not open the file: %s, because: %s", documentFileName, e.getMessage()));
			}
		}
	}

	/**
	 * reads the file with the name/path given by documentFileName, and returns a Document object
	 * The specific type of document depends on the format of the file. If
	 * the file is greater than or equal to 3 lines in length, and the first two
	 * lines start with "sender" and "recipient" respectively, this method will
	 * treat this file as an email and return an Email object. Otherwise, this method
	 * treats the file as a "GeneralFile" and return a GeneralFile object.
	 * 
	 * @param documentFileName the name/path of the file to read and print
	 */
	public static Document getDocument(String documentFileName) throws FileNotFoundException {
		File fileDocument = new File(documentFileName);
		Document document;
		Scanner sc = new Scanner(fileDocument);
		ArrayList<String> fileDocumentLines = new ArrayList<String>();
		while (sc.hasNext()) {
			fileDocumentLines.add(sc.nextLine());
		}
		// an email needs to have at least 3 lines, and the first 2 lines need to start
		// with "sender" and "recipient"
		if (fileDocumentLines.size() >= 3 && fileDocumentLines.get(0).startsWith("sender")
				&& fileDocumentLines.get(1).startsWith("recipient")) {
			String sender = fileDocumentLines.get(0);
			String recipient = fileDocumentLines.get(1);
			String title = fileDocumentLines.get(2);
			String body = "";
			for (int i = 3; i < fileDocumentLines.size(); i++) {
				body += fileDocumentLines.get(i) + "\n";
			}

			document = new Email(body, sender, recipient, title);
		} else {
			String text = "";
			for (int i = 0; i < fileDocumentLines.size(); i++) {
				text += fileDocumentLines.get(i) + "\n";
			}
			document = new FileDoc(text, documentFileName);
		}
		sc.close();
		return document;
	}

	/**
	 * use to reject invalid filenames from being read. Will return false if the
	 * filename in invalid. True if valid
	 * 
	 * @param fileName the filename to check
	 * @return false if the filename in invalid. True if valid
	 */
	private static boolean checkFileName(String fileName) {
		final char[] invalidChars = { '<', '>', ':', '"', '|', '?', '*', ' ' };
		String fileExtension = "";
		if (fileName.indexOf(".") != -1) {
			fileExtension = fileName.substring(fileName.indexOf("."));
		}

		boolean containsInvalidChar = false;
		for (char charToCheck : invalidChars) {
			if (fileExtension.indexOf(charToCheck) != -1)
				containsInvalidChar = true;
		}
		if (!fileExtension.equals(".txt") || containsInvalidChar)
			return false;
		else
			return true;
	}
}
