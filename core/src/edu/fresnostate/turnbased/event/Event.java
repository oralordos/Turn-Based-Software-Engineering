package edu.fresnostate.turnbased.event;

/**
 * A generic inteface that all events implement. Mostly used to cast to the
 * specific sub-type of event.
 * 
 * @author Daniel
 *
 */
public interface Event
{
	/**
	 * @return An element of EventType that is unique for every implementing
	 *         type of Event.
	 */
	public EventType getEventType ();
}
