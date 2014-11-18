
package edu.fresnostate.turnbased;

import java.util.List;

public class Unit 
{

	int UnitcurentHP;
	int UnitId;
	int BilldingCurentHP;
	

	public Unit (UnitType unitType, int player, int id)
	{
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void Move(List<Coordinates<Integer>> path)
	{
		Coordinates<Integer> lastSpace = path.get(path.size()-1);
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

	public void CanpathOn()
	{
		
	}
	
	

}

