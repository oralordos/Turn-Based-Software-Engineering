package edu.fresnostate.turnbased;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;


public class Camera
{
	private OrthographicCamera	cam;

	public Camera (float w, float h)
	{
		cam = new OrthographicCamera (w, h);
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
		// TODO Save coordinates to move towards and calculate speed to move camera at.
		//if
		cam.position.x += x;
		cam.position.y += y;
		cam.update ();
	}
	
	public void update()
	{
		// TODO Move camera towards saved coordinates at the pre-calculated speed.
		// TODO Clear coordinates when camera arrives.
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
		Vector3 vector = new Vector3 ();
		cam.getPickRay (screenX, screenY).getEndPoint (vector, 0);
		return new Coordinates <Float> (vector.x, vector.y);
	}

	public Coordinates <Float> fromGameCoords (float x, float y)
	{
		Vector3 vector = cam.project (new Vector3 (x, y, 0));
		return new Coordinates <Float> (vector.x, vector.y);
	}
}
