package edu.fresnostate.turnbased.event;


public class WindowResizedEvent implements Event
{
	public final int	width;
	public final int	height;

	public WindowResizedEvent (int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.WINDOW_RESIZED;
	}
}
