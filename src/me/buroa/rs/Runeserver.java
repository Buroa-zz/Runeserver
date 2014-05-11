package me.buroa.rs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	 * Gets the username history for a user.
	 * @param username The username we are getting the history for.
	 * @return All of the usernames the user had.
	 */
	public List<String> getUsernameHistory(String username) {
		final List<String> history = new ArrayList<String>();
		try {
			final String domain = getDomain().replace("forum.php", "");
			final String url = domain + "/members/" + username + "?tab=usernamehistory";
			final Document document = document(url);
			for (Element table : document.select("table[class=historyblock]")) {
				for (Element row : table.select("tr:gt(0)")) {
					Elements tds = row.select("td:not([rowspan])");
					for (int i = 0; i < 2; i++) {
						String old = tds.get(i).text();
						if (!history.contains(old))
							history.add(old);
					}
				}
			}
		} catch (IOException e) {
			// should not happen
		}
		return history;
	}

}
