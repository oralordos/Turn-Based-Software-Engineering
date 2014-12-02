package edu.fresnostate.turnbased.event;

/**
 * This event is sent whenever the screen has been resized.
 *
 * @author Daniel
 *
 */
public class WindowResizedEvent implements Event
{
	/**
	 * The new height of the screen.
	 */
	public final int	height;
	/**
	 * The new width of the screen.
	 */
	public final int	width;

	/**
	 * @param width
	 *            The new width of the screen.
	 * @param height
	 *            The new height of the screen.
	 */
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
