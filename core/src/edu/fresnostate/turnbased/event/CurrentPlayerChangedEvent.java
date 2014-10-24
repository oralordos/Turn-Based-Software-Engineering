package edu.fresnostate.turnbased.event;


public class CurrentPlayerChangedEvent implements Event
{
	public final int	newPlayer;

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
