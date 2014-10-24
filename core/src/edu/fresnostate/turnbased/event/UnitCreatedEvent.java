package edu.fresnostate.turnbased.event;


public class UnitCreatedEvent implements Event
{
	public final int unitID;
	
	public UnitCreatedEvent(int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_CREATED;
	}
}
