package me.buroa.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The md5 utility.
 * @author Buroa
 */
public final class MD5Util {

	/**
	 * The md5 encryption.
	 */
	private static MessageDigest mg;

	/**
	 * Initializes the MD5 message digest class.
	 */
	static {
		try {
			mg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Your system does not have MD5 encryption.");
		}
	}

	/**
	 * Hashes the string.
	 * @param string The string to hash.
	 * @return The hashed string.
	 */
	public static final String hash(String string) {
		final StringBuffer sb = new StringBuffer();
		try {
			final byte[] bytes = mg.digest(string.getBytes("UTF-8"));
			for (int i = 0; i < bytes.length; ++i) {
				sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (Exception e) {

		}
		return sb.toString();
	}

}
