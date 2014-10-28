package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.List;
import edu.fresnostate.turnbased.Coordinates;


public class PathfindingMap
{
	private List <List <List <Coordinates <Integer>>>>	paths;

	public PathfindingMap (List <List <Integer>> pathData, TilemapGraph graph,
			int width, int height)
	{
		paths = new ArrayList <List <List <Coordinates <Integer>>>> ();
		for (int x = 0; x < width; ++ x)
		{
			paths.add (new ArrayList <List <Coordinates <Integer>>> ());
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
				List <Coordinates <Integer>> localizedPath =
						localizePath (graph, path);
				paths.get (pathToX).set (pathToY, localizedPath);
			}
		}
	}

	public List <Coordinates <Integer>> getPathTo (int x, int y)
	{
		return paths.get (x).get (y);
	}

	public boolean hasPathTo (int x, int y)
	{
		return paths.get (x).get (y) != null;
	}

	private static List <Coordinates <Integer>> localizePath (
			TilemapGraph graph, List <Integer> pathData)
	{
		List <Coordinates <Integer>> path =
				new ArrayList <Coordinates <Integer>> ();
		for (Integer node : pathData)
		{
			path.add (new Coordinates <Integer> (graph.getLocalizedX (node),
					graph.getLocalizedY (node)));
		}
		return path;
	}
}
