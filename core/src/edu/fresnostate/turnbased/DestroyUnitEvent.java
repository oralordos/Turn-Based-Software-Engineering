package edu.fresnostate.turnbased;

public class DestroyUnitEvent implements Event
{
	public final int	unitID;

	DestroyUnitEvent (int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.DestroyUnit;
	}
}
