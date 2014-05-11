package me.buroa.utils;

/**
 * A text utility.
 * @author Buroa
 */
public final class TextUtil {

	/**
	 * Contacts an array of string together.
	 * @param s The array of strings.
	 * @param separator The seperator.
	 * @return The concatenated string.
	 */
	public static String concate(String[] s, String separator) {
		StringBuilder sb = new StringBuilder();
		if (s.length > 0) {
			sb = new StringBuilder(s[0]);
			for (int i = 1; i < s.length; i++) {
				sb.append(separator);
				sb.append(s[i]);
			}
		}
		return sb.toString();
	}

}
