package edu.fresnostate.turnbased.event;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import edu.fresnostate.turnbased.Player;
import edu.fresnostate.turnbased.Tile;
import edu.fresnostate.turnbased.Unit;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;


/**
 * A singleton manager for all events. Is used to handle passing events between
 * sub-systems in the program.
 * 
 * @author Daniel
 *
 */
public abstract class EventManager
{
	private static Map <EventType, List <EventListener>>	listeners			=
																						new EnumMap <EventType, List <EventListener>> (
																								EventType.class);
	private static Queue <Event>							eventQueue			=
																						new LinkedList <Event> ();
	private static InformationProvider						informationProvider	=
																						null;

	/**
	 * Registers a listener for a specific event.
	 * 
	 * @param listener
	 *            The listener to add.
	 * @param eventType
	 *            The EventType to listen for.
	 */
	public static void
			addListener (EventListener listener, EventType eventType)
	{
		if ( ! listeners.containsKey (eventType))
		{
			listeners.put (eventType, new ArrayList <EventListener> ());
		}
		listeners.get (eventType).add (listener);
	}

	/**
	 * Deregisters a listener from listening for an event.
	 * 
	 * @param listener
	 *            The listener to remove.
	 * @param eventType
	 *            The type to stop listening for.
	 * @return true if the listener was successfully removed, false otherwise.
	 */
	public static boolean removeListener (EventListener listener,
			EventType eventType)
	{
		if (listeners.containsKey (eventType))
		{
			return listeners.get (eventType).remove (listener);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Adds an event to be passed to the listeners.
	 * 
	 * @param e
	 *            The event to pass.
	 */
	public static void queueEvent (Event e)
	{
		eventQueue.add (e);
	}

	/**
	 * Sends all events in queue to the appropriate listeners.
	 */
	public static void processEvents ()
	{
		Event e = eventQueue.poll ();
		while (e != null)
		{
			EventType type = e.getEventType ();
			if (listeners.containsKey (type))
			{
				List <EventListener> delegates = listeners.get (type);
				for (EventListener listener : delegates)
				{
					listener.receiveEvent (e);
				}
			}
			e = eventQueue.poll ();
		}
	}

	public static Unit getUnit (int unitID)
	{
		return informationProvider.getUnit (unitID);
	}

	public static int getNumberPlayers ()
	{
		return informationProvider.getNumberPlayers ();
	}

	public static PathfindingMap getPathMap (int unitID)
	{
		return informationProvider.getPathMap (unitID);
	}

	public static Player getPlayer (int playerNum)
	{
		return informationProvider.getPlayer (playerNum);
	}

	public static int getMapWidth ()
	{
		return informationProvider.getMapWidth ();
	}

	public static int getMapHeight ()
	{
		return informationProvider.getMapHeight ();
	}

	public static Tile getMapTile (int x, int y)
	{
		return informationProvider.getMapTile (x, y);
	}
}
