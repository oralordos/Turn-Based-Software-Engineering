package edu.fresnostate.turnbased;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.badlogic.gdx.utils.Disposable;


public class EventManager implements Disposable
{
	private static EventManager manager;

	private Map <EventType, List <EventListener>>	listeners;
	private Queue <Event>							eventQueue;
	
	public static void create()
	{
		manager = new EventManager();
	}

	private EventManager ()
	{
		listeners =
				new EnumMap <EventType, List <EventListener>> (EventType.class);
		eventQueue = new ArrayDeque <Event> ();
	}

	public void addListener (EventListener listener, EventType eventType)
	{
		if ( ! listeners.containsKey (eventType))
		{
			listeners.put (eventType, new ArrayList <EventListener> ());
		}
		listeners.get (eventType).add (listener);
	}

	public boolean removeListener (EventListener listener, EventType eventType)
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

	public void queueEvent (Event e)
	{
		eventQueue.add (e);
	}

	public void processEvents ()
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

	public static EventManager get ()
	{
		return manager;
	}

	@Override
	public void dispose ()
	{
		manager = null;
	}
}
