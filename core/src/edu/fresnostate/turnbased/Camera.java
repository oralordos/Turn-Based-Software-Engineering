package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Camera
{
	private OrthographicCamera cam;
	public void zoom(float x, float y) {
		zoom (x, y);		
	}
	public void move (float screenX, float screenY){
		cam.getPickRay (screenX, screenY);
		
	}
	public void moveTo (float x, float y){
	}
	public void toGameCoords (float screenX, float screenY) {
		cam.getPickRay (screenX, screenY);
		
	}
	public void fromGameCoords () {
		
	}
}
