package me.buroa.rs.chat.command.impl;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;
import me.buroa.vb.Infernoshout;

/**
 * An {@link CommandListener} for the .rights command.
 * @author Buroa
 */
public final class RightsCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		final Infernoshout shoutbox = forum.getShoutbox();
		shoutbox.pm(speech.getUser(), "Your rights level in the system are " + speech.getRights() + ".");
	}

}
