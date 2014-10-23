package edu.fresnostate.turnbased;

public class UnitCreated implements Event
{
	public final int unitID;
	
	public UnitCreated(int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_CREATED;
	}
}
