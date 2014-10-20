package edu.fresnostate.turnbased;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.Map;

public class Tile {

	private int Max_health;
	private int Cur_health;
	private int Defence;
	private boolean canCapture;
	private int capturePoints;
	private int unitRegen;
	private int incomeX;
	private Map<movementCost, Integer> Costs;

	public Tile(MapProperties p) {
		// p.get("maxHealth", Integer.class).getInt()
		p.get("maxHealth",Integer.class).getInt();
		
	}

	public int getMaxHealth() {
		return Max_health;
	}

	public int getCurHealth() {
		return Cur_health;
	}

	public int getmovementcost(movementCost m) {
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
	public int incomeX()
	{
		return incomeX;
	}
	
}
