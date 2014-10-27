package edu.fresnostate.turnbased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.fresnostate.turnbased.event.AttackUnitEvent;
import edu.fresnostate.turnbased.event.CreateUnitEvent;
import edu.fresnostate.turnbased.event.EndTurnEvent;
import edu.fresnostate.turnbased.event.Event;
import edu.fresnostate.turnbased.event.EventListener;
import edu.fresnostate.turnbased.event.EventManager;
import edu.fresnostate.turnbased.event.EventType;
import edu.fresnostate.turnbased.event.UnitAttackedEvent;


public class GameLogic implements EventListener
{
	private List <Player> playerList;
	private Map <Integer, Unit>	units;

	public GameLogic ()
	{
		setPlayerList (new ArrayList <Player> ());
		units = new HashMap <Integer, Unit> ();
		EventManager.addListener (this, EventType.CREATE_UNIT);
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
		case END_TURN : 
			handleEndTurn ((EndTurnEvent) e);
			break;
		default :
			break;
		}
	}

	private void handleEndTurn (EndTurnEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleAttackUnit (AttackUnitEvent e)
	{
		// TODO Make sure the attacker's player is the current player
		Unit attacker = getUnit (e.attackerID);
		if (attacker.isInRange (e.targetID))
		{

			attacker.attack (e.targetID);
			EventManager.queueEvent (
					new UnitAttackedEvent (e.attackerID, e.targetID));
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

	public List <Player> getPlayerList ()
	{
		return playerList;
	}

	private void setPlayerList (List <Player> playerList)
	{
		this.playerList = playerList;
	}
}
