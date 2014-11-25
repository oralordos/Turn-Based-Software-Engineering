package edu.fresnostate.turnbased;

import java.util.EnumMap;
import java.util.Map;


public enum UnitType
{
	INFANTRY (10, 2, 3, 4, 1, 200, 200, MovementType.WALK, "units/infantry.png"),
	TANK (10, 4, 4, 4, 1, 1000, 500, MovementType.TREAD, "units/tank.png"),
	MONSTER (10, 3, 4, 5, 2, 500, 1200, MovementType.FLY, "units/monster.png");

	final int							UnitBaseHP;

	final int							UnitAD;
	final int							UnitMovement;
	final int							UnitDF;
	final int							UnitRang;
	final Map <ResourceType, Integer>	Unitcost;
	final MovementType					movement;
	final String						imageName;

	private static int					nextID	= 0;

	UnitType (int UnitBaseHP, int UnitDF, int UnitAD, int UnitMovement,
			int UnitRang, int UnitMoneycost, int UnitFoodCost,
			MovementType movement, String imageName)
	{
		this.UnitBaseHP = UnitBaseHP;
		this.UnitDF = UnitDF;
		this.UnitAD = UnitAD;
		this.UnitMovement = UnitMovement;
		this.UnitRang = UnitRang;
		Unitcost = new EnumMap <ResourceType, Integer> (ResourceType.class);
		Unitcost.put (ResourceType.MONEY, UnitMoneycost);
		Unitcost.put (ResourceType.FOOD, UnitMoneycost);
		this.movement = movement;
		this.imageName = imageName;
	}

	public Unit create (int player, int x, int y)
	{
		return new Unit (this, player, nextID ++ , x, y);
	}

}
