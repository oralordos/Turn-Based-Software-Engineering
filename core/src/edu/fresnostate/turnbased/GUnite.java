package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import edu.fresnostate.turnbased.event.AnimationFinishedEvent;
import edu.fresnostate.turnbased.event.EventManager;


public class GUnite implements Disposable
{
	public final int						UniteID;
	private float							x;
	private float							y;
	private TextureRegion					image;
	private List <Coordinates <Integer>>	currentPath;
	private Texture							teamIndicator;
	private float							indicatorSize;
	private static final int				INDICATOR_PIXEL_SIZE	= 4;
	private static final float				MOVEMENT_SPEED			= 0.25f;

	public GUnite (int UniteID, float x, float y, int tileSize)
	{
		this.UniteID = UniteID;
		this.x = x;
		this.y = y;
		currentPath = null;
		Unit unit = EventManager.getUnit (UniteID);
		UnitType type = unit.type;
		Texture tex = EventManager.getAsset (type.imageName, Texture.class);
		TextureRegion [ ][ ] regions =
				TextureRegion.split (tex, tileSize, tileSize);
		image = regions [type.tileY] [type.tileX];
		createTeamIndicator (unit, tileSize);
	}

	private void createTeamIndicator (Unit unit, int tileSize)
	{
		Pixmap pix =
				new Pixmap (INDICATOR_PIXEL_SIZE, INDICATOR_PIXEL_SIZE,
						Format.RGB888);
		PlayerColor color = EventManager.getPlayer (unit.player).color;
		pix.setColor (color.red, color.green, color.blue, color.alpha);
		pix.fill ();
		teamIndicator = new Texture (pix);
		indicatorSize = INDICATOR_PIXEL_SIZE / (float) (tileSize);
	}

	@Override
	public void dispose ()
	{
		teamIndicator.dispose ();
	}

	public void render (SpriteBatch batch)
	{
		batch.draw (image, x, y, 1, 1);
		batch.draw (teamIndicator, x, y, indicatorSize, indicatorSize);
	}

	public void update ()
	{
		if (currentPath != null)
		{
			Coordinates <Integer> nextSpot = currentPath.get (0);
			float dx = nextSpot.x - x;
			float dy = nextSpot.y - y;
			if (Math.abs (dx) > MOVEMENT_SPEED)
			{
				x += Math.copySign (MOVEMENT_SPEED, dx);
			}
			else
			{
				x = nextSpot.x;
			}
			if (Math.abs (dy) > MOVEMENT_SPEED)
			{
				y += Math.copySign (MOVEMENT_SPEED, dy);
			}
			else
			{
				y = nextSpot.y;
			}
			if (x == nextSpot.x && y == nextSpot.y)
			{
				currentPath.remove (0);
			}
			if (currentPath.isEmpty ())
			{
				currentPath = null;
				EventManager.queueEvent (new AnimationFinishedEvent ());
			}
		}
	}

	public void move (List <Coordinates <Integer>> path)
	{
		currentPath = path;
	}
}
