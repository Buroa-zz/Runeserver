package me.buroa.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.buroa.model.User;
import me.buroa.vb.VBulletin;

public final class UserUtil {
	
	/**
	 * The username on the forum.
	 */
	private final User user;
	
	/**
	 * The forum.
	 */
	private final VBulletin forum;

	/**
	 * Gets the username document.
	 */
	private final Document document;
	
	/**
	 * Creates a new user util.
	 * @param user The user.
	 */
	public UserUtil(VBulletin forum, String user) {
		this.forum = forum;
		this.user = new User(user);
		this.document = document(user);
	}
	
	/**
	 * Gets complete information about this user.
	 */
	public void populate() {
		user.add("username-history", getUsernameHistory().toString());
		user.add("total-visits", getTotalVisits());
		user.add("join-date", getJoinDate());
		user.add("current-activity", getCurrentActivity());
		user.add("friend-count", getFriendCount());
	}
	
	/**
	 * Gets the username history for a user.
	 * @param username The username we are getting the history for.
	 * @return All of the usernames the user had.
	 */
	private Document document(String username) {
		Document document = null;
		try {
			final String domain = forum.getDomain().replace("forum.php", "");
			final String url = domain + "/members/" + username;
			document = forum.document(url);
		} catch (IOException e) {
			// should not happen
		}
		return document;
	}
	
	/**
	 * Gets the username history on the document.
	 * @return The username history for the members profile.
	 */
	public List<String> getUsernameHistory() {
		if (document == null)
			return null;
		
		final List<String> username_history = new ArrayList<String>();
		for (Element table : document.select("table[class=historyblock]")) {
			for (Element row : table.select("tr:gt(0)")) {
				Elements tds = row.select("td:not([rowspan])");
				for (int i = 0; i < 2; i++) {
					String old = tds.get(i).text();
					if (!username_history.contains(old))
						username_history.add(old);
				}
			}
		}
		return username_history;
	}
	
	/**
	 * Gets the total number of visits.
	 * @return The total number of visits.
	 */
	public String getTotalVisits() {
		if (document == null)
			return null;
		
		return document.select("span[class=totalvisits]").text();
	}
	
	/**
	 * Gets the join date for the user.
	 * @return The join date.
	 */
	public String getJoinDate() {
		if (document == null)
			return null;
		
		return document.select("dl[class=stats]").select("dd").first().text();
	}
	
	/**
	 * Gets the current activity for the user.
	 * @return The current activity.
	 */
	public String getCurrentActivity() {
		if (document == null)
			return null;
		
		return document.select("dl[class=stats]").select("dd").get(1).text();
	}
	
	/**
	 * Gets the user title for the user.
	 * @return The current user title.
	 */
	public String getUserTitle() {
		if (document == null)
			return null;
		
		return document.select("span[class=usertitle]").text();
	}
	
	/**
	 * Gets the friend count for the user.
	 * @return The friend count.
	 */
	public String getFriendCount() {
		if (document == null)
			return null;
		
		return document.select("span[class=friends_total]").text();
	}

	/**
	 * Gets the user.
	 * @return The user.
	 */
	public User getUser() {
		return user;
	}

}
