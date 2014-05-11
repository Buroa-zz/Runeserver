package me.buroa.model;

/**
 * The chat class.
 * @author Buroa
 */
public final class Speech {

	/**
	 * The user.
	 */
	private final String user;

	/**
	 * The text.
	 */
	private final String text;

	/**
	 * The date.
	 */
	private final String date;

	/**
	 * The user rights.
	 */
	private final Rights rights;

	/**
	 * The private message flag.
	 */
	private final boolean pm;

	/**
	 * Creates a new chat.
	 * @param user The user.
	 * @param text The text.
	 * @param pm If the speech is a private message.
	 * @param date The date.
	 * @param rights The user rights.
	 */
	public Speech(String user, String text, boolean pm, String date, Rights rights) {
		this.user = user;
		this.text = text;
		this.pm = pm;
		this.date = date;
		this.rights = rights;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Speech other = (Speech) obj;
		if (!other.getText().equals(text))
			return false;
		if (!other.getUser().equals(user))
			return false;
		if (!other.getDate().equals(date))
			return false;
		return true;
	}

	/**
	 * Gets the date.
	 * @return The date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gets the user rights.
	 * @return The user rights.
	 */
	public Rights getRights() {
		return rights;
	}

	/**
	 * Gets the text.
	 * @return The text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the user.
	 * @return The user.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Checks if the message is a pm.
	 * @return {@code true} if pm, {@code false} if otherwise.
	 */
	public boolean isPm() {
		return pm;
	}

	@Override
	public String toString() {
		return Speech.class.getName() + "[user=" + user + ", text=" + text + ", pm=" + pm + ", rights=" + rights
				+ ", date=" + date + "]";
	}
}
