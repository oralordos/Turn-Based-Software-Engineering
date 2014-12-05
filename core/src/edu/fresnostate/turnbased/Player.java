package edu.fresnostate.turnbased;

import java.util.HashMap;
import java.util.Map;


public class Player
{
	public Map <ResourceType, Integer>	resources;
	public final PlayerColor			color;

	public Player (int playerNum)
	{
		resources = new HashMap <ResourceType, Integer> ();
		color = PlayerColor.values () [playerNum];
	}
}
