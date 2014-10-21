package edu.fresnostate.turnbased;

import java.util.Map;

import com.badlogic.gdx.maps.MapProperties;

public class Tile {

	private int Max_health;
	private int Cur_health;
	private int Defence;
	private boolean canCapture;
	private int capturePoints;
	private int unitRegen;
	private Map<ResourceType, Integer> income;
	private Map<MovementType, Integer> Costs;

	public Tile(MapProperties p) {
		// p.get("maxHealth", Integer.class).getInt()
		p.get("maxHealth",Integer.class).intValue();
		
	}

	public int getMaxHealth() {
		return Max_health;
	}

	public int getCurHealth() {
		return Cur_health;
	}

	public int getmovementcost(MovementType m) {
		return Costs.get(m);
	}
	
	public int getDefence()
	{
		return Defence;
	}
	
	public boolean canCapture()
	{
		return canCapture;
	}
	public int capturePoints()
	{
		return capturePoints;
	}
	public int unitRegen()
	{
		return unitRegen;
	}
	public int getincome(ResourceType t)
	{
		return income.get(t);
	}
	
}
