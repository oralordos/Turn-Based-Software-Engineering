package edu.fresnostate.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.SerializationException;


public class PlayerView implements View, Disposable, GestureListener
{
	private final static float			MOUSE_WHEEL_ZOOM	= 1.25f;
	private TiledMap					map;
	private OrthogonalTiledMapRenderer	renderer;
	private int							tileSize;
	private InputMultiplexer			multiplexer;
	private float						lastInitial			= - 1.0f;
	private float						lastZoom			= - 1.0f;
	private Camera						cam;
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
		return false;
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

	public PlayerView ()
	{
		try
		{
			map =
					new TmxMapLoader (new ExternalFileHandleResolver ())
							.load ("test.tmx");
		}
		catch (SerializationException e)
		{
			map = new TmxMapLoader ().load ("test.tmx");
		}
		tileSize =
				map.getTileSets ().getTile (1).getTextureRegion ()
						.getRegionWidth ();
		renderer = new OrthogonalTiledMapRenderer (map, 1.0f / tileSize);
		float w = Gdx.graphics.getWidth ();
		float h = Gdx.graphics.getHeight ();
		cam = new Camera (w, h);
		cam.zoom (2.0f);
		multiplexer = new InputMultiplexer ();
		multiplexer.addProcessor (new GestureDetector (this));
		multiplexer.addProcessor (adapter);
		Gdx.input.setInputProcessor (multiplexer);
		Gdx.gl.glClearColor (0, 0, 0, 1);
	}

	public void handleInput ()
	{
		cam.handleZoom (); // Controls the camera by updating its zoom and
							// position.
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
		if (Gdx.input.isKeyPressed (Input.Keys.NUM_1))
			map.getLayers ().get (0)
					.setVisible ( ! map.getLayers ().get (0).isVisible ());
		if (Gdx.input.isKeyPressed (Input.Keys.NUM_2))
			map.getLayers ().get (1)
					.setVisible ( ! map.getLayers ().get (1).isVisible ());
	}

	public void resize (int width, int height)
	{
		cam.resize (30f, 30f * height / width);
	}

	public void render ()
	{
		// TODO draw units
		// TODO draw GUI
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		cam.update ();
		cam.applyRenderer(renderer);
		renderer.render ();
	}

	@Override
	public void dispose ()
	{
		renderer.dispose ();
		map.dispose ();
	}

	@Override
	public void update ()
	{
		// TODO Loop through graphical units and call their update functions.
		handleInput ();
	}
}
