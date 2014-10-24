package edu.fresnostate.turnbased.event;

import edu.fresnostate.turnbased.Player;


/**
 * This event is sent whenever the current player has changed.
 * 
 * @author Daniel
 *
 */
public class CurrentPlayerChangedEvent implements Event
{
	/**
	 * The player whose turn it now is.
	 */
	public final Player	newPlayer;

	/**
	 * @param newPlayer
	 *            The player whose turn it now is.
	 */
	public CurrentPlayerChangedEvent (Player newPlayer)
	{
		this.newPlayer = newPlayer;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.CURRENT_PLAYER_CHANGED;
	}
}
