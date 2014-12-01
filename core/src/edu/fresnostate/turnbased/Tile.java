package edu.fresnostate.turnbased;

import java.util.EnumMap;
import java.util.Map;
import com.badlogic.gdx.maps.MapProperties;


public class Tile
{
	private int							Max_health;
	private int							Cur_health;
	private int							Defence;
	private boolean						canCapture;
	private int							capturePoints;
	private int							unitRegen;
	private int							resourceRadius;
	private Map <ResourceType, Integer>	income;
	private Map <MovementType, Integer>	Costs;
	public int							unitOnID;

	public Tile ()
	{
		Max_health = 20;
		Cur_health = Max_health;
		Defence = 0;
		canCapture = false;
		capturePoints = 0;
		unitRegen = 0;
		resourceRadius = 0;
		unitOnID = - 1;
		income = new EnumMap <ResourceType, Integer> (ResourceType.class);
		income.put (ResourceType.FOOD, 0);
		income.put (ResourceType.MONEY, 0);
		Costs = new EnumMap <MovementType, Integer> (MovementType.class);
		Costs.put (MovementType.WALK, 1);
		Costs.put (MovementType.FLY, 1);
		Costs.put (MovementType.TREAD, 1);
		Costs.put (MovementType.SAIL, 0);
	}

	public void updateData (MapProperties p)
	{
		if (p.containsKey ("health"))
		{
			Max_health = Integer.parseInt (p.get ("health", String.class));
			Cur_health = Max_health;
		}
		if (p.containsKey ("defence"))
		{
			Defence = Integer.parseInt (p.get ("defence", String.class));
		}
		if (p.containsKey ("canCapture"))
		{
			canCapture =
					Boolean.parseBoolean (p.get ("canCapture", String.class));
		}
		if (p.containsKey ("capturePoints"))
		{
			capturePoints =
					Integer.parseInt (p.get ("capturePoints", String.class));
		}
		if (p.containsKey ("unitRegen"))
		{
			unitRegen = Integer.parseInt (p.get ("unitRegen", String.class));
		}
		if (p.containsKey ("resourceRadius"))
		{
			resourceRadius =
					Integer.parseInt (p.get ("resourceRadius", String.class));
		}
		if (p.containsKey ("incomeFood"))
		{
			income.put (ResourceType.FOOD,
					Integer.parseInt (p.get ("incomeFood", String.class)));
		}
		if (p.containsKey ("incomeMoney"))
		{
			income.put (ResourceType.MONEY,
					Integer.parseInt (p.get ("incomeMoney", String.class)));
		}
		if (p.containsKey ("movementWalk"))
		{
			Costs.put (MovementType.WALK,
					Integer.parseInt (p.get ("movementWalk", String.class)));
		}
		if (p.containsKey ("movementFly"))
		{
			Costs.put (MovementType.FLY,
					Integer.parseInt (p.get ("movementFly", String.class)));
		}
		if (p.containsKey ("movementTread"))
		{
			Costs.put (MovementType.TREAD,
					Integer.parseInt (p.get ("movementTread", String.class)));
		}
		if (p.containsKey ("movementSail"))
		{
			Costs.put (MovementType.SAIL,
					Integer.parseInt (p.get ("movementSail", String.class)));
		}
	}

	public int getMaxHealth ()
	{
		return Max_health;
	}

	public int getCurHealth ()
	{
		return Cur_health;
	}

	public int getmovementcost (MovementType m)
	{
		return Costs.get (m);
	}

	public int getDefence ()
	{
		return Defence;
	}

	public boolean canCapture ()
	{
		return canCapture;
	}

	public int getCapturePoints ()
	{
		return capturePoints;
	}

	public int getUnitRegen ()
	{
		return unitRegen;
	}

	public int getincome (ResourceType t)
	{
		return income.get (t);
	}

	public int getResourceRadius ()
	{
		return resourceRadius;
	}
}
