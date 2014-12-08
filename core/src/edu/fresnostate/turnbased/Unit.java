package edu.fresnostate.turnbased;

import java.lang.annotation.Target;
import java.util.List;

import edu.fresnostate.turnbased.event.EventManager;


public class Unit
{
	public final UnitType	type;
	public int				player;
	private int				UnitcurentHP;
	private int 			Range;
	public final int		UnitId;
	public int				x, y;
	private int 			sum;
	public Unit (UnitType unitType, int player, int id, int x, int y)
	{
		type = unitType;
		this.player = player;
		UnitId = id;
		UnitcurentHP = type.UnitBaseHP;
		Range= type.UnitRang;
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

	public void attack (int targetId )
	{
		
		Unit target = EventManager.getUnit(targetId);
        target.UnitcurentHP -= type.UnitAD;
		
	}

	public void capture ()
	{
		// TODO Adjust capture points on the building on the tile that the unit is on.
	}

	public boolean isInRange (int targetId)
	{
		
		int dx;
		int dy;
		Unit target = EventManager.getUnit(targetId);
		//Math.abs(dx) + Math.abs(dy) <= type.range;
		//where 
		//dx = x - target.x and dy = y - target.y;
		dx=x-target.x;
		dy=y-target.y;
		if(dx<0)
		{
		dx=-dx;	
		}
		if(dy<0)
		{
			dy=-dy;
		}
		//sum=(x-target.x)+(y-target.y);
		sum=dx+dy;
		if(sum<= Range) 
		{
		return true ;
		}
		else
		{
		return false;
		}
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
