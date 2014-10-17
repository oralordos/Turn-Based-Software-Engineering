package edu.fresnostate.turnbased;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


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
						tiles [x] [y] = new Tile (tile.getProperties ());
					}
				}
			}
		}
	}
}
