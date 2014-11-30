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
		// TODO Alter to skip an entry if it is not in the map properties.
		Max_health = Integer.parseInt (p.get ("health", String.class));
		Cur_health = Max_health;
		Defence = Integer.parseInt (p.get ("defence", String.class));
		canCapture = Boolean.parseBoolean (p.get ("canCapture", String.class));
		capturePoints =
				Integer.parseInt (p.get ("capturePoints", String.class));
		unitRegen = Integer.parseInt (p.get ("unitRegen", String.class));
		resourceRadius =
				Integer.parseInt (p.get ("resourceRadius", String.class));
		income.put (ResourceType.FOOD,
				Integer.parseInt (p.get ("incomeFood", String.class)));
		income.put (ResourceType.MONEY,
				Integer.parseInt (p.get ("incomeMoney", String.class)));
		Costs.put (MovementType.WALK,
				Integer.parseInt (p.get ("movementWalk", String.class)));
		Costs.put (MovementType.FLY,
				Integer.parseInt (p.get ("movementFly", String.class)));
		Costs.put (MovementType.TREAD,
				Integer.parseInt (p.get ("movementTread", String.class)));
		Costs.put (MovementType.SAIL,
				Integer.parseInt (p.get ("movementSail", String.class)));
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
