package edu.fresnostate.turnbased;

public class UnitAttacked implements Event
{
	public final int attackerID;
	public final int targetID;

	public UnitAttacked(int attackerID, int targetID)
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
