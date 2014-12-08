package edu.fresnostate.turnbased;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import edu.fresnostate.turnbased.event.AttackUnitEvent;
import edu.fresnostate.turnbased.event.CurrentPlayerChangedEvent;
import edu.fresnostate.turnbased.event.Event;
import edu.fresnostate.turnbased.event.EventListener;
import edu.fresnostate.turnbased.event.EventManager;
import edu.fresnostate.turnbased.event.EventType;
import edu.fresnostate.turnbased.event.LoadMapEvent;
import edu.fresnostate.turnbased.event.MoveUnitEvent;
import edu.fresnostate.turnbased.event.UnitAttackedEvent;
import edu.fresnostate.turnbased.event.UnitCreatedEvent;
import edu.fresnostate.turnbased.event.UnitDestroyedEvent;
import edu.fresnostate.turnbased.event.UnitMovedEvent;
import edu.fresnostate.turnbased.event.WindowResizedEvent;
import edu.fresnostate.turnbased.pathfinding.PathfindingMap;


public class PlayerView implements View, Disposable, GestureListener,
		EventListener
{
	private final static float			MOUSE_WHEEL_ZOOM	= 1.25f;
	private TiledMap					map;
	private OrthogonalTiledMapRenderer	renderer;
	private int							tileSize;
	private InputMultiplexer			multiplexer;
	private float						lastInitial			= - 1.0f;
	private float						lastZoom			= - 1.0f;
	private Camera						cam;
	private SpriteBatch					batch;
	private int							currentPlayer;
	private List <GUnite>				units;
	private int							selectedUnit;
	private GuiManager					gui;
	private PathfindingMap				pathMap;
	private Queue <Event>				animationQueue;
	private boolean						animating;
	private InputAdapter				adapter				=
																	new InputAdapter ()
																	{
																		@Override
																		public
																				boolean
																				scrolled (
																						int amount)
																		{
																			if (amount > 0)
																			{
																				cam.zoom (MOUSE_WHEEL_ZOOM);
																			}
																			else
																			{
																				cam.zoom (1 / MOUSE_WHEEL_ZOOM);
																			}
																			return true;
																		}
																	};

	@Override
	public boolean zoom (float initialDistance, float distance)
	{
		if (lastInitial != initialDistance)
		{
			lastInitial = initialDistance;
			lastZoom = 1.0f;
		}
		float zoom = initialDistance / distance;
		cam.zoom (zoom / lastZoom);
		lastZoom = zoom;
		return true;
	}

	@Override
	public boolean touchDown (float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean tap (float x, float y, int count, int button)
	{
		Coordinates <Float> gameCoords = cam.toGameCoords (x, y);
		int gameX = gameCoords.x.intValue ();
		int gameY = gameCoords.y.intValue ();
		Tile tile = EventManager.getMapTile (gameX, gameY);
		if (tile.unitOnID >= 0)
		{
			if (selectedUnit >= 0)
			{
				Unit oldUnit = EventManager.getUnit (selectedUnit);
				if (oldUnit.player == currentPlayer)
				{
					Unit targetUnit = EventManager.getUnit (tile.unitOnID);
					if (oldUnit.player != targetUnit.player
							&& oldUnit.isInRange (tile.unitOnID))
					{
						AttackUnitEvent event =
								new AttackUnitEvent (selectedUnit,
										tile.unitOnID);
						EventManager.queueEvent (event);
					}
					else
					{
						selectedUnit = tile.unitOnID;
						updatePathMap ();
					}
				}
				else
				{
					selectedUnit = tile.unitOnID;
					updatePathMap ();
				}
			}
			else
			{
				selectedUnit = tile.unitOnID;
				updatePathMap ();
			}
		}
		else if (selectedUnit >= 0)
		{
			Unit unit = EventManager.getUnit (selectedUnit);
			if (unit.player == currentPlayer
					&& pathMap.hasPathTo (gameX, gameY))
			{
				MoveUnitEvent event =
						new MoveUnitEvent (selectedUnit, pathMap.getPathTo (
								gameX, gameY));
				EventManager.queueEvent (event);
			}
			else
			{
				selectedUnit = - 1;
				updatePathMap ();
			}
		}
		return true;
	}

	@Override
	public boolean pinch (Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2)
	{
		return false;
	}

	@Override
	public boolean panStop (float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean pan (float x, float y, float deltaX, float deltaY)
	{
		cam.move ( - deltaX / tileSize, deltaY / tileSize);
		return true;
	}

	@Override
	public boolean longPress (float x, float y)
	{
		return false;
	}

	@Override
	public boolean fling (float velocityX, float velocityY, int button)
	{
		return false;
	}

	public PlayerView (String mapFilename)
	{
		// Default values for some variables
		units = new ArrayList <GUnite> ();
		batch = new SpriteBatch ();
		currentPlayer = 0;
		selectedUnit = - 1;
		animationQueue = new LinkedList <Event> ();
		animating = false;
		pathMap = null;
		Gdx.gl.glClearColor (0, 0, 0, 1);
		loadMap (mapFilename);
		gui = new GuiManager ();
		// Find tile size
		tileSize =
				map.getTileSets ().getTile (1).getTextureRegion ()
						.getRegionWidth ();
		renderer = new OrthogonalTiledMapRenderer (map, 1.0f / tileSize, batch);
		// Find map size
		TiledMapTileLayer mapLayer =
				(TiledMapTileLayer) (map.getLayers ().get (0));
		int mapWidth = mapLayer.getWidth ();
		int mapHeight = mapLayer.getHeight ();
		// Find window size
		float w = Gdx.graphics.getWidth ();
		float h = Gdx.graphics.getHeight ();
		// Create camera
		cam = new Camera (30.0f, 30.0f * h / w, mapWidth, mapHeight);
		cam.zoom (1.5f);
		// Setup input
		multiplexer = new InputMultiplexer ();
		multiplexer.addProcessor (new GestureDetector (this));
		multiplexer.addProcessor (adapter);
		Gdx.input.setInputProcessor (multiplexer);
		// Register interest in certain event types
		EventManager.addListener (this, EventType.UNIT_ATTACKED);
		EventManager.addListener (this, EventType.UNIT_DESTROYED);
		EventManager.addListener (this, EventType.UNIT_CREATED);
		EventManager.addListener (this, EventType.CURRENT_PLAYER_CHANGED);
		EventManager.addListener (this, EventType.WINDOW_RESIZED);
		EventManager.addListener (this, EventType.UNIT_MOVED);
		EventManager.addListener (this, EventType.LOAD_MAP);
		EventManager.addListener (this, EventType.ANIMATION_FINISHED);
	}

	private void loadMap (String mapFilename)
	{
		// Load map
		map = EventManager.getAsset (mapFilename, TiledMap.class);
		// Disable drawing unit layers in map
		for (MapLayer layer : map.getLayers ())
		{
			if (layer.getName ().startsWith ("Unit Layer"))
			{
				layer.setVisible (false);
			}
		}
		units.clear ();
	}

	public void handleInput ()
	{
		if (Gdx.input.isKeyPressed (Input.Keys.ESCAPE))
			Gdx.app.exit ();
		if (Gdx.input.isKeyPressed (Input.Keys.LEFT))
			cam.move ( - 2, 0);
		if (Gdx.input.isKeyPressed (Input.Keys.RIGHT))
			cam.move (2, 0);
		if (Gdx.input.isKeyPressed (Input.Keys.UP))
			cam.move (0, 2);
		if (Gdx.input.isKeyPressed (Input.Keys.DOWN))
			cam.move (0, - 2);
	}

	public void resize (int width, int height)
	{
		cam.resize (30f, 30f * height / width);
	}

	public void render ()
	{
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		cam.update ();
		cam.applyRenderer (renderer);
		renderer.render ();
		batch.begin ();
		if (pathMap != null)
		{
			Texture moveImage =
					EventManager.getAsset ("movehighlight.png", Texture.class);
			Texture attackImage =
					EventManager.getAsset ("acthighlight.png", Texture.class);
			for (int x = 0; x < EventManager.getMapWidth (); ++ x)
			{
				for (int y = 0; y < EventManager.getMapHeight (); ++ y)
				{
					Tile tile = EventManager.getMapTile (x, y);
					if (tile.unitOnID >= 0)
					{
						Unit target = EventManager.getUnit (tile.unitOnID);
						Unit selected = EventManager.getUnit (selectedUnit);
						if (target.player != selected.player
								&& selected.isInRange (tile.unitOnID))
						{
							batch.draw (attackImage, x, y, 1, 1);
						}
					}
					else if (pathMap.hasPathTo (x, y))
					{
						batch.draw (moveImage, x, y, 1, 1);
					}
				}
			}
		}
		for (GUnite unit : units)
		{
			unit.render (batch);
		}
		gui.render (batch);
		batch.end ();
	}

	@Override
	public void dispose ()
	{
		EventManager.removeListener (this, EventType.UNIT_ATTACKED);
		EventManager.removeListener (this, EventType.UNIT_DESTROYED);
		EventManager.removeListener (this, EventType.UNIT_CREATED);
		EventManager.removeListener (this, EventType.CURRENT_PLAYER_CHANGED);
		EventManager.removeListener (this, EventType.WINDOW_RESIZED);
		EventManager.removeListener (this, EventType.UNIT_MOVED);
		EventManager.removeListener (this, EventType.LOAD_MAP);
		EventManager.removeListener (this, EventType.ANIMATION_FINISHED);
		gui.dispose ();
		renderer.dispose ();
		map.dispose ();
	}

	@Override
	public void update ()
	{
		if ( ! animating)
		{
			handleNextAnimation ();
		}
		for (GUnite unit : units)
		{
			unit.update ();
		}
		gui.update ();
		handleInput ();
		cam.update ();
	}

	private void handleNextAnimation ()
	{
		Event e = animationQueue.poll ();
		if (e != null)
		{
			switch (e.getEventType ())
			{
			case UNIT_MOVED :
				handleUnitMoved ((UnitMovedEvent) e);
				break;
			case UNIT_ATTACKED :
				handleUnitAttacked ((UnitAttackedEvent) e);
				break;
			default :
				break;
			}
		}
	}

	@Override
	public void receiveEvent (Event e)
	{
		switch (e.getEventType ())
		{
		case UNIT_CREATED :
			handleUnitCreated ((UnitCreatedEvent) e);
			break;
		case UNIT_DESTROYED :
			handleUnitDestroyed ((UnitDestroyedEvent) e);
			break;
		case UNIT_ATTACKED :
			animationQueue.add (e);
			break;
		case CURRENT_PLAYER_CHANGED :
			handleCurrentPlayer ((CurrentPlayerChangedEvent) e);
			break;
		case WINDOW_RESIZED :
			handleWindowResized ((WindowResizedEvent) e);
			break;
		case UNIT_MOVED :
			animationQueue.add (e);
			break;
		case LOAD_MAP :
			handleLoadMap ((LoadMapEvent) e);
			break;
		case ANIMATION_FINISHED :
			animating = false;
			handleNextAnimation ();
			break;
		default :
			break;
		}
	}

	private void handleLoadMap (LoadMapEvent e)
	{
		loadMap (e.filename);
	}

	private void handleUnitMoved (UnitMovedEvent e)
	{
		animating = true;
		if (selectedUnit == e.unitID)
		{
			updatePathMap ();
		}
		GUnite unit = getGUnit (e.unitID);
		unit.move (e.path);
	}

	private void handleWindowResized (WindowResizedEvent e)
	{
		resize (e.width, e.height);
	}

	private void handleCurrentPlayer (CurrentPlayerChangedEvent e)
	{
		currentPlayer = e.newPlayer;
	}

	private void handleUnitAttacked (UnitAttackedEvent e)
	{
		GUnite attacker = getGUnit (e.attackerID);
		attacker.attack (getGUnit (e.targetID));
	}

	private void handleUnitDestroyed (UnitDestroyedEvent e)
	{
		GUnite deadUnit = getGUnit (e.unitID);
		units.remove (deadUnit);
		updatePathMap ();
	}

	private GUnite getGUnit (int id)
	{
		GUnite targetUnit = null;
		for (GUnite unit : units)
		{
			if (unit.UniteID == id)
			{
				targetUnit = unit;
				break;
			}
		}
		return targetUnit;
	}

	private void handleUnitCreated (UnitCreatedEvent e)
	{
		Unit logicUnit = EventManager.getUnit (e.unitID);
		float x = logicUnit.x;
		float y = logicUnit.y;
		GUnite newUnit = new GUnite (e.unitID, x, y, tileSize);
		units.add (newUnit);
	}

	private void updatePathMap ()
	{
		if (selectedUnit >= 0
				&& EventManager.getUnit (selectedUnit).player == currentPlayer)
		{
			pathMap = EventManager.getPathMap (selectedUnit);
		}
		else
		{
			pathMap = null;
		}
	}
}
