package me.buroa.model;

/**
 * The authentication class.
 * @author Buroa
 */
public final class Authentication {

	/**
	 * The username for the forum.
	 */
	private final String username;

	/**
	 * The password for the forum.
	 */
	private final String password;

	/**
	 * Creates a new authentication class.
	 * @param username The username for the forum.
	 * @param password The password for the forum.
	 */
	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the password for the forum.
	 * @return The password for the forum.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the username for the forum.
	 * @return The username for the forum.
	 */
	public String getUsername() {
		return username;
	}

}
