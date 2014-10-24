package edu.fresnostate.turnbased.event;

/**
 * This event is sent whenever a unit is destroyed.
 * 
 * @author Daniel
 *
 */
public class UnitDestroyedEvent implements Event
{
	/**
	 * The ID number of the destroyed unit.
	 */
	public final int	unitID;

	/**
	 * @param unitID
	 *            The ID number of the destroyed unit.
	 */
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
