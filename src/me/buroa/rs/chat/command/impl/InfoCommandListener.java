package me.buroa.rs.chat.command.impl;

import me.buroa.model.Speech;
import me.buroa.model.User;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;

/**
 * An {@link CommandListener} for the .history command.
 * @author Buroa
 */
public final class InfoCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		if (command.getArguments().length == 1) {
			final User user = forum.getUsername(command.getArguments()[0]);
			forum.getShoutbox().pm(speech.getUser(), user.toString());
		} else
			forum.getShoutbox().pm(speech.getUser(), "Syntax: .info [user]");
	}

}
