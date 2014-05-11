package me.buroa.rs.chat.command.impl;

import java.util.List;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;

/**
 * An {@link CommandListener} for the .history command.
 * @author Buroa
 */
public final class HistoryCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		if (command.getArguments().length == 1) {
			final List<String> history = forum.getUsernameHistory(command.getArguments()[0]);
			forum.getShoutbox().pm(speech.getUser(), history.toString());
		} else
			forum.getShoutbox().pm(speech.getUser(), "Syntax: .history [user]");
	}

}
