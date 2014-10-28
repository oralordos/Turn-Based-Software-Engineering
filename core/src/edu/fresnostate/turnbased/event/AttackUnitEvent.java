package edu.fresnostate.turnbased.event;

/**
 * This event is sent as a request to have a unit attack another unit.
 * 
 * @author Daniel
 */
public class AttackUnitEvent implements Event
{
	/**
	 * The ID number of the attacking unit.
	 */
	public final int	attackerID;
	/**
	 * The ID number of the unit to attack.
	 */
	public final int	targetID;

	/**
	 * @param attackerID
	 *            The ID number of the attacking unit.
	 * @param targetID
	 *            The ID number of the unit to attack.
	 */
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
