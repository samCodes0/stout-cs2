package main;

import java.util.Objects;
/**
 * @author Sam Doyle
 * Date: 03/09/2026
 * Description: Document represents an abstract kind of file that contains text, and can get its file length
 */
public abstract class Document {
	public abstract int fileLength();
	@Override
	public int hashCode() {
		return Objects.hash(text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		return Objects.equals(text, other.text);
	}

	public Document(String text) {
		super();
		this.text = text;
	}
	
	public Document() {
		this.text = "na";
	}

	@Override
	public String toString() {
		return text;
	}

	protected String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
