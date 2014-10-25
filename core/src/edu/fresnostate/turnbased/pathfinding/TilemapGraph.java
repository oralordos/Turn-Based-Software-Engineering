package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.List;
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

		int [ ] xs = {x - 1, x + 1};
		int [ ] ys = {y - 1, y + 1};

		for (int currX : xs)
		{
			if (currX >= 0 && currX < map.getWidth ())
			{
				for (int currY : ys)
				{
					if (currY >= 0 && currY < map.getHeight ())
					{
						Tile tile = map.getTile (currX, currY);
						Connection conn =
								new Connection (
										tile.getmovementcost (currentMovement),
										node, generalize (currX, currY));
						connections.add (conn);
					}
				}
			}
		}
		return (Connection [ ]) connections.toArray ();
	}
}
