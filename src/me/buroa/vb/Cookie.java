package me.buroa.vb;

/**
 * Contains an browser cookie.
 * @author Buroa
 */
public final class Cookie {

	/**
	 * The cookie value.
	 */
	private final String value;

	/**
	 * The cookie key.
	 */
	private final String key;

	/**
	 * Creates a new browser cookie.
	 * @param key The cookie key.
	 * @param value The cookie value.
	 */
	public Cookie(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the cookie key.
	 * @return The cookie key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the cookie value.
	 * @return The cookie value.
	 */
	public String getValue() {
		return value;
	}

}
