package edu.fresnostate.turnbased;

public class CreateUnitEvent implements Event
{
	// TODO Uncomment once UnitType class has been added.
	// public final UnitType unitType;
	public final float	x;
	public final float	y;

	CreateUnitEvent (/* UnitType unitType, */float x, float y)
	{
		// this.unitType = unitType;
		this.x = x;
		this.y = y;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.CreateUnit;
	}
}
