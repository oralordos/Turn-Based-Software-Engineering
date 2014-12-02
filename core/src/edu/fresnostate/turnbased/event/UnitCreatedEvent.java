package edu.fresnostate.turnbased.event;

/**
 * This event is sent whenever a new unit is created.
 *
 * @author Daniel
 *
 */
public class UnitCreatedEvent implements Event
{
	/**
	 * The ID number of the new unit.
	 */
	public final int	unitID;

	/**
	 * @param unitID
	 *            The ID number of the new unit.
	 */
	public UnitCreatedEvent (int unitID)
	{
		this.unitID = unitID;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_CREATED;
	}
}
