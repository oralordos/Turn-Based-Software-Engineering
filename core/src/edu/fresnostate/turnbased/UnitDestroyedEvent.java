package edu.fresnostate.turnbased;

public class UnitDestroyedEvent implements Event
{
	public final int	unitID;

	public UnitDestroyedEvent (int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_DESTROYED;
	}
}
