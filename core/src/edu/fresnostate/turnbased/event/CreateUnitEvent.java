package edu.fresnostate.turnbased.event;

import edu.fresnostate.turnbased.UnitType;


/**
 * This event is send as a request to create a unit for the active player at the
 * specified coordinates.
 * 
 * @author Daniel
 *
 */
public class CreateUnitEvent implements Event
{
	/**
	 * The type of unit to create.
	 */
	public final UnitType	unitType;
	/**
	 * The x position of the unit to create.
	 */
	public final int		x;
	/**
	 * The y position of the unit to create.
	 */
	public final int		y;

	/**
	 * @param unitType
	 *            The type of unit to create.
	 * @param x
	 *            The x position of the unit to create.
	 * @param y
	 *            The y position of the unit to create.
	 */
	public CreateUnitEvent (UnitType unitType, int x, int y)
	{
		this.unitType = unitType;
		this.x = x;
		this.y = y;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.CREATE_UNIT;
	}
}
