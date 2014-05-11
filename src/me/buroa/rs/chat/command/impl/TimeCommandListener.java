package me.buroa.rs.chat.command.impl;

import java.util.Date;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandListener;
import me.buroa.vb.Infernoshout;

/**
 * An {@link CommandListener} for the .time command.
 * @author Buroa
 */
public final class TimeCommandListener implements CommandListener {

	@Override
	public void execute(Runeserver forum, Speech speech, Command command) {
		final Infernoshout shoutbox = forum.getShoutbox();
		shoutbox.pm(speech.getUser(), "The current time is " + new Date());
	}

}
