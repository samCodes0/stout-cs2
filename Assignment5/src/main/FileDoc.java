package main;

import java.util.Objects;

/**
 * @author Sam Doyle
 * Date: 03/09/2026
 * Description: Represents a general file (one that just contains some text - in no particular format -).
 * in addition to the text content, a FileDoc also stores the fileName
 */
public class FileDoc extends Document {
	@Override
	public String toString() {
		return String.format("File Name: %s, File Body: %s", fileName, super.text);
	}

	public FileDoc(String text, String fileName) {
		super(text);
		this.fileName = fileName;
	}
	
	public FileDoc() {
		super("na");
		this.fileName = "na";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fileName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDoc other = (FileDoc) obj;
		return Objects.equals(fileName, other.fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;

	@Override
	public int fileLength() {
		return text.length() + fileName.length();
	}
}
