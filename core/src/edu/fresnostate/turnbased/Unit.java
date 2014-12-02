package edu.fresnostate.turnbased;

import java.util.List;
import edu.fresnostate.turnbased.event.EventManager;


public class Unit
{
	public final UnitType	type;
	public int				player;
	private int				UnitcurentHP;
	public final int		UnitId;
	public int				x, y;

	public Unit (UnitType unitType, int player, int id, int x, int y)
	{
		type = unitType;
		this.player = player;
		UnitId = id;
		UnitcurentHP = type.UnitBaseHP;
		this.x = x;
		this.y = y;
		EventManager.getMapTile(x, y).unitOnID=id;
	}

	public void Move (List <Coordinates <Integer>> path)
	{
		EventManager.getMapTile(x, y).unitOnID=-1;
		Coordinates <Integer> lastSpace = path.get (path.size () - 1);
		x = lastSpace.x;
		y = lastSpace.y;
		EventManager.getMapTile(x, y).unitOnID=UnitId;
	}

	public void attack (int targetId)
	{
		// TODO Decrease the target's hp due to an attack.
	}

	public void capture ()
	{
		// TODO Adjust capture points on the building on the tile that the unit is on.
	}

	public boolean isInRange (int targetId)
	{
		// TODO Check if the other unit is in range of this unit.
		return false;
	}

	public void Undomove ()
	{
		// TODO Undo the last move if the unit has moved this turn but has not acted yet.
	}

	public int GetCurentHP ()
	{
		return UnitcurentHP;
	}

	public void Heal ()
	{
		UnitcurentHP += EventManager.getMapTile (x, y).getUnitRegen ();
	}

	public boolean CanpathOn (List <Coordinates <Integer>> path)
	{
		int movedSoFar = 0;
		for (Coordinates <Integer> currentPoint : path)
		{
			Tile tile =
					EventManager.getMapTile (currentPoint.x, currentPoint.y);
			movedSoFar += tile.getmovementcost (type.movement);
		}
		return movedSoFar <= type.UnitMovement;
	}

}
