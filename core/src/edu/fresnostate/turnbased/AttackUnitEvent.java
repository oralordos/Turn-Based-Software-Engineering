package edu.fresnostate.turnbased;

public class AttackUnitEvent implements Event
{
	public final int attackerID;
	public final int targetID;

	public AttackUnitEvent (int attackerID, int targetID)
	{
		this.attackerID = attackerID;
		this.targetID = targetID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.ATTACK_UNIT;
	}
}
