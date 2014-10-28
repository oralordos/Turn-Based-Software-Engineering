package edu.fresnostate.turnbased.event;

import edu.fresnostate.turnbased.Player;
import edu.fresnostate.turnbased.Unit;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;

public interface InformationProvider
{
	public PathfindingMap getPathMap(int unitID);
	public Unit getUnit(int unitID);
	public Player getPlayer(int playerNum);
}