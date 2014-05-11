package me.buroa;

import me.buroa.model.Authentication;
import me.buroa.model.User;
import me.buroa.rs.Runeserver;
import me.buroa.utils.UserUtil;

/**
 * The shoutbox class.
 * @author Buroa
 */
public final class Application {
	
	// TODO Fetch a username page and get all details possible and put into a mutable object.

	/**
	 * The main entrance of this application.
	 * @param args The arguments of this application.
	 */
	public static void main(String[] args) {
		final Runeserver rs = new Runeserver();
		rs.login(new Authentication("Buroa", ""));
		while (true) {
			rs.pulse();
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
