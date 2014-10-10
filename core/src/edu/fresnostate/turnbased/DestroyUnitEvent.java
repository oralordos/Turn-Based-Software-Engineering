package edu.fresnostate.turnbased;

public class DestroyUnitEvent implements Event
{
	public final int	unitID;

	public DestroyUnitEvent (int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.DestroyUnit;
	}
}
