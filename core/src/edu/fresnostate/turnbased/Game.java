package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;


public class Game extends ApplicationAdapter
{
	SpriteBatch					batch;
	// Texture img;
	TiledMap					map;
	OrthogonalTiledMapRenderer	renderer;
	OrthographicCamera			cam;

	private GestureListener listener = new GestureListener()
	{
		private float lastInitial = -1.0f;
		private float lastZoom = -1.0f;
		
		@Override
		public boolean zoom (float initialDistance, float distance)
		{
			if(lastInitial != initialDistance)
			{
				lastInitial = initialDistance;
				lastZoom = 1.0f;
			}
			float zoom = initialDistance / distance;
			cam.zoom *= zoom / lastZoom;
			cam.update ();
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
			cam.translate (-deltaX * cam.zoom, deltaY * cam.zoom);
			cam.update ();
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
	};

	@Override
	public void create ()
	{
		batch = new SpriteBatch ();
		// img = new Texture("badlogic.jpg");
		map = new TmxMapLoader ().load ("test.tmx");
		renderer = new OrthogonalTiledMapRenderer (map);
		int w = Gdx.graphics.getWidth ();
		int h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera (w, h);
		cam.zoom = 2.0f;
		cam.update ();
		Gdx.input.setInputProcessor (new GestureDetector (listener));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor (0, 0, 0, 1);
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView (cam);
		renderer.render ();
		// batch.begin();
		// batch.draw(img, 0, 0);
		// batch.end();
		if (Gdx.input.isKeyPressed (Input.Keys.ESCAPE))
		{
			Gdx.app.exit ();
		}
	}
}
