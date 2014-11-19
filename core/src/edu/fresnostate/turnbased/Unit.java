
package edu.fresnostate.turnbased;

import java.util.List;

import edu.fresnostate.turnbased.event.EventManager;

public class Unit 
{
	public final UnitType type;
	int UnitcurentHP;
	int UnitId;
	int BilldingCurentHP;
	int x,y;

	public Unit (UnitType unitType, int player, int id)
	{
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void Move(List<Coordinates<Integer>> path)
	{
		Coordinates<Integer> lastSpace = path.get(path.size()-1);
		x=lastSpace.x;
		y=lastSpace.y;
	}
	
	public void AttackUint()
	{
		final int	attackerID;
		final int	targetID;
		
	}

	public void capture()
	{
	final int BilldingHP;
		BilldingHP-UnitcurentHP=BilldingCurentHP;
	}

	public void IsInrange()
	{
		
	}
	
	public void Undomove()
	{
		
	}
	
	public void GetCurentHP()
	{
		
	}
	
	public void Heal()
	{
		UnitcurentHP+2;
	}

	public boolean CanpathOn(List <Coordinates<Integer>>path)
	{
		int movedSoFar=0;
		for(Coordinates<Integer>currentPoint :path)
		{
			Tile tile = EventManager. getMapTile(currentPoint.x, currentPoint.y);
			movedSoFar +=tile.getmovementcost(type.movement);
		}
		return movedSoFar <=type.UnitMovement;
	}
	
	

}

