package edu.fresnostate.turnbased.event;


public class UnitAttackedEvent implements Event
{
	public final int attackerID;
	public final int targetID;

	public UnitAttackedEvent(int attackerID, int targetID)
	{
		this.attackerID = attackerID;
		this.targetID = targetID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_ATTACKED;
	}
}
