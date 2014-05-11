package me.buroa.model;

/**
 * Contains different rights based off colors.
 * @author Buroa
 */
public enum Rights {
	ADMINISTRATOR("#336699"), GLOBALMODERATOR("#008B00"), MODERATOR("color: #FF9933; font-weight: bold;"), PROGRAMMER(
			"#6666c9"), ADVERTISER("#02B41D"), RESPECTED("color: #008080;"), VETERAN("color: #E18700;"), DONATOR(
			"color:red; text-shadow: 0pt 0pt 0.5em rgb(255, 0, 0);"), NORMAL("null");

	/**
	 * Gets the rights for the color.
	 * @param color The color we are comparing against.
	 * @return The rights for the color.
	 */
	public static Rights value(String colorv) {
		for (final Rights rights : values())
			if (rights.color.equals(colorv))
				return rights;
		return NORMAL;

	}

	/**
	 * The color of the rights the user has.
	 */
	private final String color;

	/**
	 * Creates a right level.
	 * @param color The color that the rights has.
	 */
	private Rights(String color) {
		this.color = color;
	}
}
