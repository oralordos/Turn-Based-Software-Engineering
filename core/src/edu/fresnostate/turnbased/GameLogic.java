package edu.fresnostate.turnbased;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.fresnostate.turnbased.event.AttackUnitEvent;
import edu.fresnostate.turnbased.event.CreateUnitEvent;
import edu.fresnostate.turnbased.event.CurrentPlayerChangedEvent;
import edu.fresnostate.turnbased.event.EndTurnEvent;
import edu.fresnostate.turnbased.event.Event;
import edu.fresnostate.turnbased.event.EventListener;
import edu.fresnostate.turnbased.event.EventManager;
import edu.fresnostate.turnbased.event.EventType;
import edu.fresnostate.turnbased.event.InformationProvider;
import edu.fresnostate.turnbased.event.UnitAttackedEvent;
import edu.fresnostate.turnbased.event.UnitCreatedEvent;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;


public class GameLogic implements EventListener, InformationProvider
{
	private List <Player>		playerList;
	private Map <Integer, Unit>	units;
	private LogicMap			map;
	private int					Currentplayer;

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
		Currentplayer = (Currentplayer + 1) % getNumberPlayers ();
		EventManager.queueEvent (new CurrentPlayerChangedEvent (Currentplayer));
	}

	private void handleAttackUnit (AttackUnitEvent e)
	{
		// TODO Make sure the attacker's player is the current player
		Unit attacker = getUnit (e.attackerID);
		if (attacker.isInRange (e.targetID))
		{
			attacker.attack (e.targetID);
			EventManager.queueEvent (new UnitAttackedEvent (e.attackerID,
					e.targetID));
		}
	}

	/**
	 * This handles CreateUnitEvent events
	 * 
	 * @param e
	 *            The event to handle
	 */
	private void handleCreateUnit (CreateUnitEvent e)
	{
		// TODO Make sure player is current player and make sure player has
		// enough cost and play is owns tile.
		Player player = getPlayer (Currentplayer);
		int playerMoney = player.resources.get (ResourceType.MONEY);
		int playerFood = player.resources.get (ResourceType.FOOD);
		int moneyCost = e.unitType.costs.get (ResourceType.MONEY);
		int foodCost = e.unitType.costs.get (ResourceType.FOOD);
		if (moneyCost <= playerMoney && foodCost <= playerFood)
		{
			player.resources.put (ResourceType.MONEY, playerMoney - moneyCost);
			player.resources.put (ResourceType.FOOD, playerFood - foodCost);
			Unit newUnit = e.unitType.create (Currentplayer);
			units.put (newUnit.unitID, newUnit);
			EventManager.queueEvent (new UnitCreatedEvent (newUnit.unitID));
		}
	}

	@Override
	public Unit getUnit (int unitID)
	{
		return units.get (unitID);
	}

	@Override
	public PathfindingMap getPathMap (int unitID)
	{
		Unit unit = getUnit (unitID);
		return map.getPathfindingMap (unit.x, unit.y, unit.type.movement,
				unit.type.move);
	}

	@Override
	public Player getPlayer (int playerNum)
	{
		return playerList.get (playerNum);
	}

	public List <Player> getPlayerList ()
	{
		return playerList;
	}

	private void setPlayerList (List <Player> playerList)
	{
		this.playerList = playerList;
	}

	@Override
	public int getNumberPlayers ()
	{
		return playerList.size ();
	}

	@Override
	public int getMapWidth ()
	{
		return map.getWidth ();
	}

	@Override
	public int getMapHeight ()
	{
		return map.getHeight ();
	}

	@Override
	public Tile getMapTile (int x, int y)
	{
		return map.getTile (x, y);
	}
}
