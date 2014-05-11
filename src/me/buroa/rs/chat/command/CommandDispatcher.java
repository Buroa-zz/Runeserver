package me.buroa.rs.chat.command;

import java.util.HashMap;
import java.util.Map;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;
import me.buroa.rs.chat.command.impl.HistoryCommandListener;
import me.buroa.rs.chat.command.impl.PmCommandListener;
import me.buroa.rs.chat.command.impl.PostCommandListener;
import me.buroa.rs.chat.command.impl.RightsCommandListener;
import me.buroa.rs.chat.command.impl.TimeCommandListener;

/**
 * A class which dispatches {@link Command}s to {@link CommandListener}s.
 * @author Graham
 */
public final class CommandDispatcher {

	/**
	 * A map of event listeners.
	 */
	private final Map<String, CommandListener> listeners = new HashMap<String, CommandListener>();

	/**
	 * Creates the command dispatcher.
	 */
	public CommandDispatcher() {
		register(".time", new TimeCommandListener());
		register(".rights", new RightsCommandListener());
		register(".post", new PostCommandListener());
		register(".pm", new PmCommandListener());
		register(".history", new HistoryCommandListener());
	}

	/**
	 * Dispatches a command to the appropriate listener.
	 * @param forum The runeserver forum.
	 * @param player The player.
	 * @param command The command.
	 * @return True if listener was found, false if otherwise.
	 */
	public boolean dispatch(Runeserver forum, Speech speech, Command command) {
		final CommandListener listener = listeners.get(command.getName().toLowerCase());
		if (listener != null) {
			listener.execute(forum, speech, command);
			return true;
		}
		return false;
	}

	/**
	 * Registers a listener with the.
	 * @param command The command's name.
	 * @param listener The listener.
	 */
	public void register(String command, CommandListener listener) {
		listeners.put(command.toLowerCase(), listener);
	}
}
