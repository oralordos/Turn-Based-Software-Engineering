package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.fresnostate.turnbased.event.EventManager;


public class GUnite
{
	public final int		UniteID;
	private float			x;
	private float			y;
	private TextureRegion	image;

	public GUnite (int UniteID, float x, float y, String imageName, int texX,
			int texY, int w, int h)
	{
		this.UniteID = UniteID;
		this.x = x;
		this.y = y;
		Texture tex = EventManager.getAsset (imageName, Texture.class);
		image = new TextureRegion (tex, texX, texY, w, h);
	}

	public void render (SpriteBatch batch)
	{
		batch.draw (image, x, y, 1, 1);
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
