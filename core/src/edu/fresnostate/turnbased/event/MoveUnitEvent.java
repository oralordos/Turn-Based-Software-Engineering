package edu.fresnostate.turnbased.event;

import java.util.List;
import edu.fresnostate.turnbased.Coordinates;


/**
 * Event sent when a player wants a unit to move.
 *
 * @author Daniel
 *
 */
public class MoveUnitEvent implements Event
{
	/**
	 * The path of coordinates to follow.
	 */
	public final List <Coordinates <Integer>>	path;
	/**
	 * The ID of the unit to move.
	 */
	public final int							unitID;

	/**
	 * @param unitID
	 *            The ID of the unit to move.
	 * @param path
	 *            The path of coordinates to follow.
	 */
	public MoveUnitEvent (int unitID, List <Coordinates <Integer>> path)
	{
		this.unitID = unitID;
		this.path = path;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.MOVE_UNIT;
	}
}
