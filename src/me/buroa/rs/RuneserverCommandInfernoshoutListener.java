package me.buroa.rs;

import me.buroa.model.Speech;
import me.buroa.rs.chat.command.Command;
import me.buroa.rs.chat.command.CommandDispatcher;
import me.buroa.vb.InfernoshoutListener;

/**
 * An {@link InfernoshoutListener} that listens for commands.
 * @author Buroa
 */
public final class RuneserverCommandInfernoshoutListener implements InfernoshoutListener {

	/**
	 * The runeserver forum.
	 */
	private final Runeserver forum;

	/**
	 * The command dispatcher.
	 */
	private final CommandDispatcher dispatcher = new CommandDispatcher();

	/**
	 * Creates a new runeserver command infernoshout box listener.
	 * @param shoutbox The infernoshout box.
	 */
	public RuneserverCommandInfernoshoutListener(Runeserver forum) {
		this.forum = forum;
	}

	@SuppressWarnings("unused")
	@Override
	public void said(Speech chat) {
		if (chat.getUser().equalsIgnoreCase(forum.getAuthentication().getUsername()))
			return;
		final String str = chat.getText();
		final String[] components = str.split(" ");
		final String name = components[0];
		final String[] arguments = new String[components.length - 1];
		System.arraycopy(components, 1, arguments, 0, arguments.length);
		final Command command = new Command(name, arguments);
		System.out.println(chat);
		boolean success = dispatcher.dispatch(forum, chat, command);
	}

}
