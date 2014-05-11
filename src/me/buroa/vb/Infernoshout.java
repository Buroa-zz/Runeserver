package me.buroa.vb;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.buroa.model.Rights;
import me.buroa.model.Speech;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The infernoshout box.
 * @author Buroa
 */
public final class Infernoshout {

	/**
	 * The max chats in the shoutbox.
	 */
	public final static int CHAT_MAX = 30;

	/**
	 * The vbulletin forum.
	 */
	private final VBulletin forum;

	/**
	 * The chats.
	 */
	private static final ArrayDeque<Speech> chats = new ArrayDeque<Speech>(CHAT_MAX);

	/**
	 * The listener.
	 */
	private InfernoshoutListener listener;

	/**
	 * Creates a new infernoshout class.
	 * @param forum The forum that contains infernoshout.
	 */
	public Infernoshout(VBulletin forum) {
		this.forum = forum;
	}

	/**
	 * Fetches the chat from the shoutbox.
	 * @return The list of chats.
	 */
	public List<Speech> fetch() {
		final List<Speech> chat_array = new ArrayList<Speech>();
		if (!forum.isSession())
			return chat_array;
		final String shouturl = forum.getDomain().replace("forum.php", "infernoshout.php");

		try {
			final Document document = forum.document(shouturl + "?do=messages&fetchtype=&");

			// start fetching the shouts.
			final Elements elements = document.select("div");
			for (Element element : elements) {
				final String text = element.text();
				String spancolor = element.select("span[style]").attr("style");
				String fontcolor = element.select("font[color]").attr("color");
				Rights rights = Rights.value(spancolor);
				if (rights == Rights.NORMAL)
					rights = Rights.value(fontcolor);
				if (text.startsWith("[")) { // valid chat
					final int end_date = text.indexOf("]") + 2;
					final String date = text.subSequence(1, end_date - 2).toString();
					final String chat = text.subSequence(end_date, text.length()).toString();
					final String[] chat_user_text = chat.split(":");
					if (chat_user_text.length == 2) { // make sure we have valid
														// data
						String user = chat_user_text[0].trim();
						boolean pm = false;
						if (user.startsWith("[PM]")) {
							user = user.replace("[PM] ", "");
							pm = true;
						}
						final String user_text = chat_user_text[1].trim();
						final Speech chat_data = new Speech(user, user_text, pm, date, rights);
						chat_array.add(chat_data);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		filter(chat_array);
		return chat_array;
	}

	/**
	 * Filters unwanted chat.
	 * @param incoming The incoming chat.
	 */
	private void filter(List<Speech> incoming) {
		boolean updated = incoming.removeAll(chats);
		if (chats.size() >= CHAT_MAX) {
			for (Speech chat : incoming) {
				if (!chats.contains(chat))
					chats.remove(chat);
			}
		}
		if (updated) {
			for (Speech chat : incoming) {
				chats.addFirst(chat);
				if (listener != null)
					listener.said(chat);
			}
		} else
			chats.addAll(incoming);
	}

	/**
	 * Sends a private message to a user.
	 * @param user The user.
	 * @param text The text to send the user.
	 * @return {@code true} if sent, {@code false} if otherwise.
	 */
	public boolean pm(String user, String text) {
		return shout("/pm " + user + "; " + text);
	}

	/**
	 * Sets the listener.
	 * @param listener The listener.
	 */
	public void setListener(InfernoshoutListener listener) {
		this.listener = listener;
	}

	/**
	 * Shouts a text.
	 * @param text The text to shout.
	 * @return {@code true} if the shout posted, {@code false} if otherwise.
	 */
	public boolean shout(String text) {
		if (!forum.isSession())
			return false;
		final String shouturl = forum.getDomain().replace("forum.php", "infernoshout.php");

		// setup the post data.
		final Map<String, String> data = new HashMap<String, String>();
		data.put("do", "shout");
		data.put("message", text);
		data.put("securitytoken", forum.getSecurityToken());
		data.put("s", "");

		// now post
		try {
			forum.document(shouturl, true, data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
