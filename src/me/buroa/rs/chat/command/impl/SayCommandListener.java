package me.buroa.rs.chat.command.impl;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;
import me.buroa.utils.TextUtil;

/**
 * An {@link CommandListener} for the .say command.
 * @author Buroa
 */
public final class SayCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		if (!speech.getUser().equals("Steve"))
			return;

		forum.getShoutbox().shout(TextUtil.concate(command.getArguments(), " "));
	}

}
