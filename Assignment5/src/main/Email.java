package main;

import java.util.Objects;

/**
 * @author Sam Doyle
 * Date: 03/09/2026
 * Description: Represents a specific kind of document (an email). In addition to text content, an email has a sender,
 * recipient, and a title
 */
public class Email extends Document {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(recipient, sender, title);
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
		Email other = (Email) obj;
		return Objects.equals(recipient, other.recipient) && Objects.equals(sender, other.sender)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return String.format("Title: %s\nFrom: %s\nTo: %s\nBody: %s", title, sender, recipient, super.text);
	}

	public Email(String text, String sender, String recipient, String title) {
		super(text);
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
	}
	
	public Email() {
		super("na");
		this.sender = "na";
		this.recipient = "na";
		this.title = "na";
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String sender, recipient, title;

	@Override
	public int fileLength() {
		return text.length() + sender.length() + recipient.length() + title.length();
	}

}
