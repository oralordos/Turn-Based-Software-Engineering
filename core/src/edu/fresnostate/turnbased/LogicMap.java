package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import edu.fresnostate.turnbased.pathfinding.DijkstraMap;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;
import edu.fresnostate.turnbased.pathfinding.TilemapGraph;


public class LogicMap
{
	private Tile	tiles [ ][ ];

	public LogicMap (TiledMap t)
	{
		tiles = null;
		for (MapLayer layer : t.getLayers ())
		{
			if (layer.getName ().startsWith ("Tile Layer"))
			{
				TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;
				if (tiles == null)
				{
					tiles =
							new Tile [tileLayer.getWidth ()] [tileLayer
									.getHeight ()];
				}
				for (int x = 0; x < tileLayer.getWidth (); ++ x)
				{
					for (int y = 0; y < tileLayer.getHeight (); ++ y)
					{
						TiledMapTile tile = tileLayer.getCell (x, y).getTile ();
						// FIXME Change to update an existing tile instead of
						// overwriting.
						tiles [x] [y] = new Tile (tile.getProperties ());
					}
				}
			}
		}
	}

	public int getWidth ()
	{
		return tiles.length;
	}

	public int getHeight ()
	{
		return tiles [0].length;
	}

	public Tile getTile (final int x, final int y)
	{
		return tiles [x] [y];
	}

	public PathfindingMap getPathfindingMap (final int x, final int y,
			final MovementType movement, final int maxMove)
	{
		TilemapGraph graph = new TilemapGraph (this, movement);
		List <List <Integer>> paths =
				DijkstraMap.createMap (graph, graph.generalize (x, y), maxMove);
		return new PathfindingMap (paths, graph, getWidth (), getHeight ());
	}
}
