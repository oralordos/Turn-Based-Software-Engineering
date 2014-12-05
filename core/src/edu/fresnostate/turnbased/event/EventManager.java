package edu.fresnostate.turnbased.event;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
	private static Queue <Event>							eventQueue			=
																						new LinkedList <Event> ();
	private static InformationProvider						informationProvider	=
																						null;
	private static Map <EventType, List <EventListener>>	listeners			=
																						new EnumMap <EventType, List <EventListener>> (
																								EventType.class);
	private static AssetManager								assets				=
																						new AssetManager ();

	public static void preloadAssets ()
	{
		assets = new AssetManager ();
		assets.setLoader (TiledMap.class, new TmxMapLoader ());
		assets.load ("test.tmx", TiledMap.class);
		assets.load ("Extra Medium.png", Texture.class);
		assets.load ("Extra Medium2.png", Texture.class);
		assets.load ("movehighlight.png", Texture.class);
		assets.finishLoading ();
	}

	public static void unloadAssets ()
	{
		assets.dispose ();
		assets = null;
	}

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
		if ( ! EventManager.listeners.containsKey (eventType))
		{
			EventManager.listeners.put (eventType,
					new ArrayList <EventListener> ());
		}
		EventManager.listeners.get (eventType).add (listener);
	}

	public static int getMapHeight ()
	{
		return EventManager.informationProvider.getMapHeight ();
	}

	public static Tile getMapTile (int x, int y)
	{
		return EventManager.informationProvider.getMapTile (x, y);
	}

	public static int getMapWidth ()
	{
		return EventManager.informationProvider.getMapWidth ();
	}

	public static int getNumberPlayers ()
	{
		return EventManager.informationProvider.getNumberPlayers ();
	}

	public static PathfindingMap getPathMap (int unitID)
	{
		return EventManager.informationProvider.getPathMap (unitID);
	}

	public static Player getPlayer (int playerNum)
	{
		return EventManager.informationProvider.getPlayer (playerNum);
	}

	public static Unit getUnit (int unitID)
	{
		return EventManager.informationProvider.getUnit (unitID);
	}

	/**
	 * Sends all events in queue to the appropriate listeners.
	 */
	public static void processEvents ()
	{
		Event e = EventManager.eventQueue.poll ();
		while (e != null)
		{
			EventType type = e.getEventType ();
			if (EventManager.listeners.containsKey (type))
			{
				List <EventListener> delegates =
						EventManager.listeners.get (type);
				for (EventListener listener : delegates)
				{
					listener.receiveEvent (e);
				}
			}
			e = EventManager.eventQueue.poll ();
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
		EventManager.eventQueue.add (e);
	}

	public static void
			registerInformationProvider (InformationProvider provider)
	{
		EventManager.informationProvider = provider;
	}

	public static void unregisterInformationProvider ()
	{
		EventManager.informationProvider = null;
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
		if (EventManager.listeners.containsKey (eventType))
		{
			return EventManager.listeners.get (eventType).remove (listener);
		}
		else
		{
			return false;
		}
	}

	public static <T> T getAsset (String filename, Class <T> type)
	{
		return assets.get (filename, type);
	}
}
