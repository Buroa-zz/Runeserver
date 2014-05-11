package me.buroa.rs.chat.command;

import me.buroa.model.Speech;
import me.buroa.rs.Runeserver;

/**
 * An interface which should be implemented by classes to listen to {@link Command}s.
 * @author Graham
 */
public interface CommandListener {

	/**
	 * Executes the action for this command.
	 * @param forum The runeserver forum.
	 * @param speech The speech that was said.
	 * @param command The command.
	 */
	public void execute(Runeserver forum, Speech speech, Command command);
}
