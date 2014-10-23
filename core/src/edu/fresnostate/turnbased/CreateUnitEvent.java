package edu.fresnostate.turnbased;

public class CreateUnitEvent implements Event
{
	public final UnitType unitType;
	public final float	x;
	public final float	y;

	public CreateUnitEvent (UnitType unitType, float x, float y)
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
