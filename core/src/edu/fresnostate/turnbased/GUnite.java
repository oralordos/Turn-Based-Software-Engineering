package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.fresnostate.turnbased.event.AnimationFinishedEvent;
import edu.fresnostate.turnbased.event.EventManager;


public class GUnite
{
	public final int						UniteID;
	private float							x;
	private float							y;
	private TextureRegion					image;
	private List <Coordinates <Integer>>	currentPath;
	private Texture							teamIndicator;
	private float							indicatorSize;
	private static Texture [ ]				teamIndicators			= null;
	private static Texture					redHealth				= null;
	private static Texture					greenHealth				= null;
	private static final float				MOVEMENT_SPEED			= 0.25f;
	private static final int				INDICATOR_PIXEL_SIZE	= 4;

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
		teamIndicator = getTeamIndicator (unit.player);
		indicatorSize = INDICATOR_PIXEL_SIZE / (float) tileSize;
		if (redHealth == null || greenHealth == null)
		{
			generateHealthBars ();
		}
	}

	public void render (SpriteBatch batch)
	{
		batch.draw (image, x, y, 1, 1);
		batch.draw (teamIndicator, x, y, indicatorSize, indicatorSize);
		batch.draw (redHealth, x, y + 0.9f, 1, 0.1f);
		Unit unit = EventManager.getUnit (UniteID);
		float percentHealth =
				unit.GetCurentHP () / (float) unit.type.UnitBaseHP;
		batch.draw (greenHealth, x, y + 0.9f, percentHealth, 0.1f);
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

	private static void preloadIndicators ()
	{
		teamIndicators = new Texture [PlayerColor.values ().length];
		PlayerColor [ ] colors = PlayerColor.values ();
		for (int i = 0; i < colors.length; ++ i)
		{
			Pixmap pix =
					new Pixmap (INDICATOR_PIXEL_SIZE, INDICATOR_PIXEL_SIZE,
							Format.RGB888);
			PlayerColor color = colors [i];
			pix.setColor (color.red, color.green, color.blue, color.alpha);
			pix.fill ();
			teamIndicators [i] = new Texture (pix);
		}
	}

	private static Texture getTeamIndicator (int playerID)
	{
		if (teamIndicators == null)
		{
			preloadIndicators ();
		}
		return teamIndicators [playerID];
	}

	private static void generateHealthBars ()
	{
		Pixmap red = new Pixmap (32, 32, Format.RGB565);
		red.setColor (Color.RED);
		red.fill ();
		redHealth = new Texture (red);
		Pixmap green = new Pixmap (32, 32, Format.RGB565);
		green.setColor (Color.GREEN);
		green.fill ();
		greenHealth = new Texture (green);
	}
}
