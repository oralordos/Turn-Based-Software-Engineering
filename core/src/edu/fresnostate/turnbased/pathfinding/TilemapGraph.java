package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.List;
import edu.fresnostate.turnbased.Coordinates;
import edu.fresnostate.turnbased.LogicMap;
import edu.fresnostate.turnbased.MovementType;
import edu.fresnostate.turnbased.Tile;


public class TilemapGraph implements Graph
{
	private final LogicMap	map;
	private MovementType	currentMovement;

	public TilemapGraph (LogicMap map, MovementType currentMovement)
	{
		this.map = map;
		this.currentMovement = currentMovement;
	}

	public void setCurrentMovement (MovementType currentMovement)
	{
		this.currentMovement = currentMovement;
	}

	public int generalize (final int x, final int y)
	{
		return x * map.getWidth () + y;
	}

	public int getLocalizedX (final int node)
	{
		return node / map.getWidth ();
	}

	public int getLocalizedY (final int node)
	{
		return node % map.getWidth ();
	}

	@Override
	public Connection [ ] getConnections (int node)
	{
		int x = getLocalizedX (node);
		int y = getLocalizedY (node);
		List <Connection> connections = new ArrayList <Connection> (4);

		List<Coordinates<Integer>> coords = new ArrayList <Coordinates<Integer>> (4);
		coords.add(new Coordinates<Integer> (x - 1, y));
		coords.add (new Coordinates<Integer> (x + 1, y));
		coords.add(new Coordinates<Integer> (x, y - 1));
		coords.add (new Coordinates<Integer> (x, y + 1));

		for (Coordinates<Integer> currCoord : coords)
		{
			int currX = currCoord.x;
			int currY = currCoord.y;
			if (currX >= 0 && currX < map.getWidth () && currY >= 0
					&& currY < map.getHeight ())
			{
				Tile tile = map.getTile (currX, currY);
				int movementCost = tile.getmovementcost (currentMovement);
				if (movementCost > 0)
				{
					Connection conn =
							new Connection (movementCost, node, generalize (
									currX, currY));
					connections.add (conn);
				}
			}
		}
		return connections.toArray (new Connection [0]);
	}
}
