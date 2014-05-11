package me.buroa.vb;

import me.buroa.model.Speech;

/**
 * The chat listener.
 * @author Buroa
 */
public interface InfernoshoutListener {

	/**
	 * Called when the chat is updated.
	 * @param chat The user that said something new.
	 */
	public void said(Speech chat);

}
