package edu.fresnostate.turnbased;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


public class EventManagerTest extends TestCase implements EventListener
{
	private EventManager	manager;
	private int				unitDestroyedID;

	@Before
	protected void setUp () throws Exception
	{
		super.setUp ();
		EventManager.create ();
		manager = EventManager.get ();
		manager.addListener (this, EventType.UNIT_DESTROYED);
		unitDestroyedID = - 1;
	}

	@After
	protected void tearDown () throws Exception
	{
		super.tearDown ();
		manager.dispose ();
	}

	@Test
	public void testQueueEvent ()
	{
		UnitDestroyedEvent e = new UnitDestroyedEvent (0);
		manager.queueEvent (e);
		manager.processEvents ();
		assertEquals (unitDestroyedID, 0);
	}

	@Override
	public void receiveEvent (Event e)
	{
		if (e.getEventType () == EventType.UNIT_DESTROYED)
		{
			unitDestroyedID = ((UnitDestroyedEvent) e).unitID;
		}
	}
}
