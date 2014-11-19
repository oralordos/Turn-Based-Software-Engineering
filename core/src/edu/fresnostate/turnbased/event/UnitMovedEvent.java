package edu.fresnostate.turnbased.event;

import java.util.List;
import edu.fresnostate.turnbased.Coordinates;


public class UnitMovedEvent implements Event
{
	public final int							unitID;
	public final List <Coordinates <Integer>>	path;

	public UnitMovedEvent (int unitID, List <Coordinates <Integer>> path)
	{
		this.unitID = unitID;
		this.path = path;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.UNIT_MOVED;
	}
}
