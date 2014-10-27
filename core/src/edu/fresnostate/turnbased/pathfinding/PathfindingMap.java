package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.List;
import edu.fresnostate.turnbased.Coordinates;


public class PathfindingMap
{
	private List <List <List <Coordinates>>>	paths;

	public PathfindingMap (List <List <Integer>> pathData, TilemapGraph graph,
			int width, int height)
	{
		paths = new ArrayList <List <List <Coordinates>>> ();
		for (int x = 0; x < width; ++ x)
		{
			paths.add (new ArrayList <List <Coordinates>> ());
			for (int y = 0; y < height; ++ y)
			{
				paths.get (x).add (null);
			}
		}

		for (List <Integer> path : pathData)
		{
			if ( ! path.isEmpty ())
			{
				int pathTo = path.get (path.size () - 1);
				int pathToX = graph.getLocalizedX (pathTo);
				int pathToY = graph.getLocalizedY (pathTo);
				List <Coordinates> localizedPath = localizePath (graph, path);
				paths.get (pathToX).set (pathToY, localizedPath);
			}
		}
	}

	public List <Coordinates> getPathTo (int x, int y)
	{
		return paths.get (x).get (y);
	}

	public boolean hasPathTo (int x, int y)
	{
		return paths.get (x).get (y) != null;
	}

	private static List <Coordinates> localizePath (TilemapGraph graph,
			List <Integer> pathData)
	{
		List <Coordinates> path = new ArrayList <Coordinates> ();
		for (Integer node : pathData)
		{
			path.add (new Coordinates (graph.getLocalizedX (node), graph
					.getLocalizedY (node)));
		}
		return path;
	}
}
