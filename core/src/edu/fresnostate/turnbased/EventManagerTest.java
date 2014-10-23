package edu.fresnostate.turnbased;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


public class EventManagerTest extends TestCase implements EventListener
{
	private EventManager		manager;
	private UnitDestroyedEvent	unitDestroyed;
	private AttackUnitEvent		attackUnit;

	@Before
	protected void setUp () throws Exception
	{
		super.setUp ();
		EventManager.create ();
		manager = EventManager.get ();
		manager.addListener (this, EventType.UNIT_DESTROYED);
		manager.addListener (this, EventType.ATTACK_UNIT);
		unitDestroyed = null;
		attackUnit = null;
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
		assertEquals (e, unitDestroyed);
	}

	@Test
	public void testMultipleEvents ()
	{
		UnitDestroyedEvent destroyed = new UnitDestroyedEvent (0);
		AttackUnitEvent attack = new AttackUnitEvent (1, 0);
		manager.queueEvent (attack);
		manager.queueEvent (destroyed);
		manager.processEvents ();
		assertEquals (attack, attackUnit);
		assertEquals (destroyed, unitDestroyed);
	}

	@Override
	public void receiveEvent (Event e)
	{
		System.out.println("Got an event");
		if (e.getEventType () == EventType.UNIT_DESTROYED)
		{
			unitDestroyed = (UnitDestroyedEvent) e;
		}
		else if (e.getEventType () == EventType.ATTACK_UNIT)
		{
			attackUnit = (AttackUnitEvent) e;
		}
	}
}
