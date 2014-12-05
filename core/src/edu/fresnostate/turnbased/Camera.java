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
	private int					mapW, mapH;
	private final static int	FRAMES	= 30;

	public Camera (float w, float h, int mapWidth, int mapHeight)
	{
		cam = new OrthographicCamera (w, h);
		goalX = - 1;
		goalY = - 1;
		mapW = mapWidth;
		mapH = mapHeight;
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
		handleZoom ();
		cam.update ();
	}

	private void handleZoom ()
	{ // Keeps the camera within the bounds of the map.
		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		cam.zoom =
				MathUtils.clamp (
						cam.zoom,
						0.2f,
						Math.min (mapW / cam.viewportWidth, mapH
								/ cam.viewportHeight));
		float leftBoundary = effectiveViewportWidth / 2f;
		float rightBoundary =
				leftBoundary + mapW - cam.viewportWidth * cam.zoom;
		float bottomBoundary = effectiveViewportHeight / 2f;
		float topBoundary =
				bottomBoundary + mapH - cam.viewportHeight * cam.zoom;
		cam.position.x =
				MathUtils.clamp (cam.position.x, leftBoundary, rightBoundary);
		cam.position.y =
				MathUtils.clamp (cam.position.y, bottomBoundary, topBoundary);
	}

	public Coordinates <Float> toGameCoords (float screenX, float screenY)
	{
		Vector3 vec = cam.unproject (new Vector3 (screenX, screenY, 0));
		return new Coordinates <Float> (vec.x, vec.y);
	}

	public Coordinates <Float> fromGameCoords (float x, float y)
	{
		Vector3 vec = cam.project (new Vector3 (x, y, 0));
		return new Coordinates <Float> (vec.x, vec.y);
	}

	public void applyRenderer (OrthogonalTiledMapRenderer renderer)
	{
		renderer.setView (cam);
	}
}
