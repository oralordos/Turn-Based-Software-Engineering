package edu.fresnostate.turnbased.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.maps.MapProperties;
import edu.fresnostate.turnbased.LogicMap;
import edu.fresnostate.turnbased.MovementType;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;


public class PathfindingTest
{
	private LogicMap	map;

	@Before
	public void setUp () throws Exception
	{
	}

	@After
	public void tearDown () throws Exception
	{
	}

	@Test
	public void testInfiniteEmpty ()
	{
		final int MAP_WIDTH = 10;
		final int MAP_HEIGHT = 10;
		final int START_X = 3;
		final int START_Y = 5;
		createEmptyTestMap (MAP_WIDTH, MAP_HEIGHT);
		PathfindingMap pathMap =
				map.getPathfindingMap (START_X, START_Y, MovementType.WALK,
						Integer.MAX_VALUE);
		for (int x = 0; x < MAP_WIDTH; ++ x)
		{
			for (int y = 0; y < MAP_HEIGHT; ++ y)
			{
				String message =
						"Coordinates are: (".concat (Integer.toString (x)
								.concat (
										", ".concat (Integer.toString (y)
												.concat (")"))));
				if (x == START_X && y == START_Y)
				{
					assertEquals (message, false, pathMap.hasPathTo (x, y));
				}
				else
				{
					assertEquals (message, true, pathMap.hasPathTo (x, y));
				}
			}
		}
	}

	@Test
	public void testDistanceEmpty ()
	{
		final int MAP_WIDTH = 10;
		final int MAP_HEIGHT = 10;
		final int START_X = 3;
		final int START_Y = 5;
		final int DISTANCE = 3;
		createEmptyTestMap (MAP_WIDTH, MAP_HEIGHT);
		PathfindingMap pathMap =
				map.getPathfindingMap (START_X, START_Y, MovementType.WALK,
						DISTANCE);
		for (int x = 0; x < MAP_WIDTH; ++ x)
		{
			for (int y = 0; y < MAP_HEIGHT; ++ y)
			{
				String message =
						"Coordinates are: (".concat (Integer.toString (x)
								.concat (
										", ".concat (Integer.toString (y)
												.concat (")"))));
				if (Math.abs (x - START_X) + Math.abs (y - START_Y) > DISTANCE
						|| x == START_X && y == START_Y)
				{
					assertEquals (message, false, pathMap.hasPathTo (x, y));
				}
				else
				{
					assertEquals (message, true, pathMap.hasPathTo (x, y));
				}
			}
		}
	}

	private static MapProperties createEmptyTile ()
	{
		MapProperties tileData = new MapProperties ();
		tileData.put ("health", 5);
		tileData.put ("defence", 0);
		tileData.put ("canCapture", false);
		tileData.put ("capturePoints", 0);
		tileData.put ("unitRegen", 0);
		tileData.put ("resourceRadius", 0);
		tileData.put ("incomeFood", 0);
		tileData.put ("incomeMoney", 0);
		tileData.put ("movementWalk", 1);
		tileData.put ("movementFly", 1);
		tileData.put ("movementTread", 1);
		tileData.put ("movementSail", 0);
		return tileData;
	}

	private MapProperties [ ][ ] createEmptyMap (int width, int height)
	{
		MapProperties [ ][ ] mapData = new MapProperties [width] [height];
		for (int x = 0; x < width; ++ x)
		{
			for (int y = 0; y < height; ++ y)
			{
				mapData [x] [y] = createEmptyTile ();
			}
		}
		return mapData;
	}

	private void createEmptyTestMap (final int MAP_WIDTH, final int MAP_HEIGHT)
	{
		map = new LogicMap (createEmptyMap (MAP_WIDTH, MAP_HEIGHT));
	}
}
