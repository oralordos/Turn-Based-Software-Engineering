package edu.fresnostate.turnbased.event;

/**
 * This event is sent whenever a unit attacks another unit.
 * 
 * @author Daniel
 *
 */
public class UnitAttackedEvent implements Event
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
	public UnitAttackedEvent (int attackerID, int targetID)
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
