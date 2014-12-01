package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import edu.fresnostate.turnbased.event.EventManager;


public class GUnite implements Disposable
{
	public final int			UniteID;
	private float				x;
	private float				y;
	private Texture				tex;

	@Override
	public void dispose ()
	{
		tex.dispose ();
	}

	public GUnite (int UniteID, String imageName)
	{
		this.UniteID = UniteID;
		tex = EventManager.getAsset (imageName, Texture.class);
	}

	public void render (SpriteBatch batch)
	{
		batch.draw (tex, x, y);
	}

	public void update ()
	{
		// TODO run animations
	}

	public void move (List <Coordinates <Integer>> path)
	{
		Coordinates <Integer> lastSpot = path.get (path.size () - 1);
		x = lastSpot.x;
		y = lastSpot.y;
	}
}
