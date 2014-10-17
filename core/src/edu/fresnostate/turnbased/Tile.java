package edu.fresnostate.turnbased;

import com.badlogic.gdx.maps.tiled.TiledMap;
import java.util.Map;


public class Tile {
	

	private int Max_health;
	private int Cur_health;
	private int effect; 
	private int property;
	private Map <movementCost,Integer> Costs;
	
	
	public int getMaxHealth()
	{
		return Max_health;
	}
	
	public int getCurHealth()
	{
		return Cur_health;
	}
	
	public int getmovementcost(movementCost m)
	{
		return Costs.get(m);
	}
	
	
}
