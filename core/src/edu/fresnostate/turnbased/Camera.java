package edu.fresnostate.turnbased;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;


public class Camera
{
	private OrthographicCamera	cam;
	private float				goalX;
	private float				goalY;
	private float				speedX;
	private float				speedY;
	private int					tileSize;
	private final static int	FRAMES	= 30;

	public Camera (float w, float h, int tileSize)
	{
		cam = new OrthographicCamera (w, h);
		goalX = - 1;
		goalY = - 1;
		this.tileSize = tileSize;
	}

	public void zoom (float z)
	{
		cam.zoom *= z;
	}

	public void resize (float width, float height)
	{
		cam.viewportWidth = width;
		cam.viewportHeight = height;
	}

	public void move (float screenX, float screenY)
	{
		cam.translate (screenX * cam.zoom, screenY * cam.zoom);
	}

	public void moveTo (float x, float y)
	{
		goalX = x;
		goalY = y;
		speedX = (cam.position.x - x) / FRAMES;
		speedY = (cam.position.y - y) / FRAMES;
	}

	public void update ()
	{
		if (goalX >= 0 && goalY >= 0)
		{
			move (speedX, speedY);
			if (cam.position.x > goalX - 0.01 && cam.position.x < goalX + 0.01
					&& cam.position.y > goalY - 0.01
					&& cam.position.y < goalY + 0.01)
			{
				goalX = - 1;
				goalY = - 1;
			}
		}
		cam.update ();
	}

	public void handleZoom ()
	{ // Keeps the camera within the bounds of the map.
		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		cam.zoom = MathUtils.clamp (cam.zoom, 0.1f, 100 / cam.viewportWidth);
		cam.position.x =
				MathUtils.clamp (cam.position.x, effectiveViewportWidth / 2f,
						100 - effectiveViewportWidth / 2f);
		cam.position.y =
				MathUtils.clamp (cam.position.y, effectiveViewportHeight / 2f,
						100 - effectiveViewportHeight / 2f);
	}

	public Coordinates <Float> toGameCoords (float screenX, float screenY)
	{
		Vector3 vector = cam.position;
		vector.scl (1.0f / cam.zoom);
		return new Coordinates <Float> ( (screenX - vector.x) / tileSize,
				(screenY - vector.z) / tileSize);
	}

	public Coordinates <Float> fromGameCoords (float x, float y)
	{
		Vector3 vector = cam.position;
		vector.scl (1.0f / cam.zoom);
		return new Coordinates <Float> (x * tileSize + vector.x, y * tileSize
				+ vector.z);
	}

	public void applyRenderer (OrthogonalTiledMapRenderer renderer)
	{
		renderer.setView (cam);
	}
}
