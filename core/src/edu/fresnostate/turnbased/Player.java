package edu.fresnostate.turnbased;

import java.util.HashMap;
import java.util.Map;


public class Player
{
	public Map <ResourceType, Integer>	resources;

	public Player ()
	{
		resources = new HashMap <ResourceType, Integer> ();
	}
}
