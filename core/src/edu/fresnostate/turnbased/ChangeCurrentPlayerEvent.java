package edu.fresnostate.turnbased;

public class ChangeCurrentPlayerEvent implements Event
{
	public final int	newPlayer;

	ChangeCurrentPlayerEvent (int newPlayer)
	{
		this.newPlayer = newPlayer;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.ChangeCurrentPlayer;
	}
}
