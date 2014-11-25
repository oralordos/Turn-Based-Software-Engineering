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

	public Tile (MapProperties p)
	{
		Max_health = p.get ("health", Integer.class);
		Cur_health = Max_health;
		Defence = p.get ("defence", Integer.class);
		canCapture = p.get ("canCapture", Boolean.class);
		capturePoints = p.get ("capturePoints", Integer.class);
		unitRegen = p.get ("unitRegen", Integer.class);
		resourceRadius = p.get ("resourceRadius", Integer.class);
		unitOnID = - 1;

		income = new EnumMap <ResourceType, Integer> (ResourceType.class);
		income.put (ResourceType.FOOD, p.get ("incomeFood", Integer.class));
		income.put (ResourceType.MONEY, p.get ("incomeMoney", Integer.class));

		Costs = new EnumMap <MovementType, Integer> (MovementType.class);
		Costs.put (MovementType.WALK, p.get ("movementWalk", Integer.class));
		Costs.put (MovementType.FLY, p.get ("movementFly", Integer.class));
		Costs.put (MovementType.TREAD, p.get ("movementTread", Integer.class));
		Costs.put (MovementType.SAIL, p.get ("movementSail", Integer.class));
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
