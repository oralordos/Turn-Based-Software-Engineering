package edu.fresnostate.turnbased.event;

/**
 * This event is sent when a new map needs to be loaded.
 * 
 * @author Daniel
 *
 */
public class LoadMapEvent implements Event
{
	/**
	 * The filename of the map to load.
	 */
	public final String	filename;

	/**
	 * @param filename
	 *            The filename of the map to load.
	 */
	public LoadMapEvent (String filename)
	{
		this.filename = filename;
	}

	@Override
	public EventType getEventType ()
	{
		return EventType.LOAD_MAP;
	}
}
