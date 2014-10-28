package edu.fresnostate.turnbased.event;

/**
 * Generic interface that all classes that want to receive events must
 * implement.
 * 
 * @author Daniel
 *
 */
public interface EventListener
{
	/**
	 * Called whenever there is an event that this listener is signed up for.
	 * 
	 * @param e
	 *            The event that is being passed to the listener.
	 */
	public void receiveEvent (Event e);
}
