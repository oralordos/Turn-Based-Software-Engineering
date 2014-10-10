package edu.fresnostate.turnbased;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class EventManager
{
	private Map <EventType, List <EventListener>>	listeners;
	private Queue <Event>							eventQueue;

	public EventManager ()
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
}
