package me.buroa.rs;

import me.buroa.model.User;
import me.buroa.utils.UserUtil;
import me.buroa.vb.Infernoshout;
import me.buroa.vb.VBulletin;
import static me.buroa.rs.RuneserverConstants.*;

/**
 * Creates a new runeserver vbulletin class.
 * @author Buroa
 */
public final class Runeserver extends VBulletin {

	/**
	 * The infernoshout box.
	 */ 
	private final Infernoshout shoutbox = new Infernoshout(this);

	/**
	 * Initializes the runeserver vbulletin.
	 */
	public Runeserver() {
		super(DOMAIN);
		init();
	}

	/**
	 * Gets the shoutbox.
	 * @return The shoutbox.
	 */
	public Infernoshout getShoutbox() {
		return shoutbox;
	}

	/**
	 * Initializes this vbulletin forum.
	 */
	private void init() {
		shoutbox.setListener(new RuneserverCommandInfernoshoutListener(this));
	}

	/**
	 * Pulses this forum.
	 */
	public void pulse() {
		shoutbox.fetch();
	}
	
	/**
	 * Get information about a user.
	 * @param username The username we are populating.
	 * @return The information about a user.
	 */
	public User getUsername(String username) {
		final UserUtil util = new UserUtil(this, username);
		util.populate();
		return util.getUser();
	}

}
