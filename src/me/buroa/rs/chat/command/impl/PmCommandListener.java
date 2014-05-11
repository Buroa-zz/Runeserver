package me.buroa.rs.chat.command.impl;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;

/**
 * An {@link CommandListener} for the .pm command.
 * @author Buroa
 */
public final class PmCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		if (!speech.getUser().equals("Steve"))
			return;

		if (command.getArguments().length == 2) {
			String user = command.getArguments()[0];
			String text = command.getArguments()[1].replaceAll("_", " ");
			forum.getShoutbox().pm(user, text);
		} else
			forum.getShoutbox().pm(speech.getUser(), "Syntax: .pm [user] [text]");
	}

}
