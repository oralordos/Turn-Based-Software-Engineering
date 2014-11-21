package edu.fresnostate.turnbased.event;

/**
 * Sent by a player whenever they want to end their turn.
 *
 * @author Daniel
 */
public class EndTurnEvent implements Event
{
	@Override
	public EventType getEventType ()
	{
		return EventType.END_TURN;
	}
}
