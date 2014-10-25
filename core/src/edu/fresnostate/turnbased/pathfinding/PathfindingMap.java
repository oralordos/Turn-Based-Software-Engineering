package edu.fresnostate.turnbased.pathfinding;

import java.util.ArrayList;
import java.util.List;


public class PathfindingMap
{
	private List <List <List <Coordinates>>>	paths;

	public PathfindingMap (List <List <Integer>> paths, TilemapGraph graph,
			int width, int height)
	{
		this.paths = new ArrayList <List <List <Coordinates>>> ();
		for (int x = 0; x < width; ++ x)
		{
			this.paths.add (new ArrayList <List <Coordinates>> ());
			for (int y = 0; y < height; ++ y)
			{
				this.paths.get (x).add (null);
			}
		}

		for (List <Integer> path : paths)
		{
			int pathTo = path.get (path.size () - 1);
			int pathToX = graph.getLocalizedX (pathTo);
			int pathToY = graph.getLocalizedY (pathTo);
			this.paths.get (pathToX).set (pathToY, localizePath (graph, path));
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
			List <Integer> path)
	{
		return null;
	}

	public class Coordinates
	{
		public int	x;
		public int	y;

		public Coordinates (int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
