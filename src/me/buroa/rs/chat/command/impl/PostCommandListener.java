package me.buroa.rs.chat.command.impl;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;

/**
 * An {@link CommandListener} for the .post command.
 * @author Buroa
 */
public final class PostCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		if (!speech.getUser().equals("Steve"))
			return;

		if (command.getArguments().length == 2) {
			final int topic = Integer.parseInt(command.getArguments()[0]);
			final String text = command.getArguments()[1].replaceAll("_", " ");
			forum.post(topic, text);
		} else
			forum.getShoutbox().pm(speech.getUser(), "Syntax: .post [topic] [text]");
	}
}
