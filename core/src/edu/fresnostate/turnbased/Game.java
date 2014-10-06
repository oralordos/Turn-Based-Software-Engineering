package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SerializationException;


public class Game extends ApplicationAdapter
{
	private final static float			MOUSE_WHEEL_ZOOM	= 1.25f;
	private int							tileSize;
	private TiledMap					map;
	private OrthogonalTiledMapRenderer	renderer;
	private OrthographicCamera			cam;
	private InputMultiplexer			multiplexer;
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
																				cam.zoom *=
																						MOUSE_WHEEL_ZOOM;
																			}
																			else
																			{
																				cam.zoom *=
																						(1 / MOUSE_WHEEL_ZOOM);
																			}
																			cam.update ();
																			return true;
																		}
																	};
	private GestureListener				listener			=
																	new GestureListener ()
																	{
																		private float	lastInitial	=
																											- 1.0f;
																		private float	lastZoom	=
																											- 1.0f;

																		@Override
																		public
																				boolean
																				zoom (float initialDistance,
																						float distance)
																		{
																			if (lastInitial != initialDistance)
																			{
																				lastInitial =
																						initialDistance;
																				lastZoom =
																						1.0f;
																			}
																			float zoom =
																					initialDistance
																							/ distance;
																			cam.zoom *=
																					zoom
																							/ lastZoom;
																			cam.update ();
																			lastZoom =
																					zoom;
																			return true;
																		}

																		@Override
																		public
																				boolean
																				touchDown (
																						float x,
																						float y,
																						int pointer,
																						int button)
																		{
																			return false;
																		}

																		@Override
																		public
																				boolean
																				tap (float x,
																						float y,
																						int count,
																						int button)
																		{
																			return false;
																		}

																		@Override
																		public
																				boolean
																				pinch (Vector2 initialPointer1,
																						Vector2 initialPointer2,
																						Vector2 pointer1,
																						Vector2 pointer2)
																		{
																			return false;
																		}

																		@Override
																		public
																				boolean
																				panStop (
																						float x,
																						float y,
																						int pointer,
																						int button)
																		{
																			return false;
																		}

																		@Override
																		public
																				boolean
																				pan (float x,
																						float y,
																						float deltaX,
																						float deltaY)
																		{
																			cam.translate (
																					- deltaX
																							* cam.zoom
																							/ tileSize,
																					deltaY
																							* cam.zoom
																							/ tileSize);
																			cam.update ();
																			return true;
																		}

																		@Override
																		public
																				boolean
																				longPress (
																						float x,
																						float y)
																		{
																			return false;
																		}

																		@Override
																		public
																				boolean
																				fling (float velocityX,
																						float velocityY,
																						int button)
																		{
																			return false;
																		}
																	};

	@Override
	public void create ()
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
		cam = new OrthographicCamera (w / tileSize, h / tileSize);
		cam.zoom = 2.0f;
		cam.update ();
		multiplexer = new InputMultiplexer ();
		multiplexer.addProcessor (new GestureDetector (listener));
		multiplexer.addProcessor (adapter);
		Gdx.input.setInputProcessor (multiplexer);
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor (0, 0, 0, 1);
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView (cam);
		renderer.render ();
		if (Gdx.input.isKeyPressed (Input.Keys.ESCAPE))
		{
			Gdx.app.exit ();
		}
	}

	@Override
	public void dispose ()
	{
		renderer.dispose ();
		map.dispose ();
	}
}
