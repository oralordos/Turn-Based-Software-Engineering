package edu.fresnostate.turnbased.event;

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
	public final int	newPlayer;

	/**
	 * @param newPlayer
	 *            The player whose turn it now is.
	 */
	public CurrentPlayerChangedEvent (int newPlayer)
	{
		this.newPlayer = newPlayer;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.CURRENT_PLAYER_CHANGED;
	}
}
