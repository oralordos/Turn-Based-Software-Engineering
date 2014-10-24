package edu.fresnostate.turnbased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameLogic implements EventListener
{
	private List <Player>		playerList;
	private Map <Integer, Unit>	units;

	public GameLogic ()
	{
		playerList = new ArrayList <Player> ();
		units = new HashMap <Integer, Unit> ();
		EventManager manager = EventManager.get ();
		manager.addListener (this, EventType.CREATE_UNIT);
	}

	@Override
	public void receiveEvent (Event e)
	{
		switch (e.getEventType ())
		{
		case CREATE_UNIT :
			handleCreateUnit ((CreateUnitEvent) e);
			break;
		case ATTACK_UNIT :
			handleAttackUnit ((AttackUnitEvent) e);
			break;
		default :
			break;
		}
	}

	private void handleAttackUnit (AttackUnitEvent e)
	{
		// TODO Make sure the attacker's player is the current player
		Unit attacker = getUnit (e.attackerID);
		if (attacker.isInRange (e.targetID))
		{

			attacker.attack (e.targetID);
			EventManager.get ().queueEvent (
					new UnitAttacked (e.attackerID, e.targetID));
		}
	}

	private Unit getUnit (int unitID)
	{
		return units.get (unitID);
	}

	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Auto-generated method stub
	}
}
