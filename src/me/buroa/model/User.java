package me.buroa.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A user that is present in a vbulletin forum.
 * @author Buroa
 */
public final class User {
	
	/**
	 * The username.
	 */
	private final String name;
	
	/**
	 * The information about this user.
	 */
	private final Map<String, Object> info = new HashMap<String, Object>();
	
	/**
	 * Creates a new user.
	 * @param name The name.
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this user.
	 * @return The username of this user.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds information to this user.
	 * @param key The key.
	 * @param value The value.
	 */
	public void add(String key, Object value) {
		info.put(key, value);
	}
	
	/**
	 * Grabs the value of this key.
	 * @param key The key.
	 * @return The value of this key.
	 */
	public Object grab(String key) {
		return info.get(key);
	}
	
	@Override
	public String toString() {
		return info.toString();
	}

}
