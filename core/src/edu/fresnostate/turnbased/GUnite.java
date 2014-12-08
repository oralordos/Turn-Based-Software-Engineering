package edu.fresnostate.turnbased;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import edu.fresnostate.turnbased.event.AnimationFinishedEvent;
import edu.fresnostate.turnbased.event.EventManager;


public class GUnite
{
	public final int					UniteID;
	private float						x;
	private float						y;
	private TextureRegion				image;
	private List <Coordinates <Float>>	currentPath;
	private Texture						teamIndicator;
	private float						indicatorSize;
	private boolean						dead;
	private Color						color;
	private static Texture [ ]			teamIndicators				= null;
	private static Texture				redHealth					= null;
	private static Texture				greenHealth					= null;
	private static final float			MOVEMENT_SPEED				= 0.25f;
	private static final int			INDICATOR_PIXEL_SIZE		= 4;
	private static final float			COLOR_CHANGE_RATE			= 0.1f;
	private static final float			TRANSPARENCY_CHANGE_RATE	= 0.1f;

	public GUnite (int UniteID, float x, float y, int tileSize)
	{
		this.UniteID = UniteID;
		this.x = x;
		this.y = y;
		currentPath = null;
		color = new Color (1, 1, 1, 1);
		dead = false;
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
		batch.setColor (color);
		batch.draw (image, x, y, 1, 1);
		batch.setColor (Color.WHITE);
		if ( ! dead)
		{
			batch.draw (teamIndicator, x, y, indicatorSize, indicatorSize);
			batch.draw (redHealth, x, y + 0.9f, 1, 0.1f);
		}
		Unit unit = EventManager.getUnit (UniteID);
		float percentHealth =
				unit.GetCurentHP () / (float) unit.type.UnitBaseHP;
		batch.draw (greenHealth, x, y + 0.9f, percentHealth, 0.1f);
	}

	public boolean update ()
	{
		if (currentPath != null)
		{
			Coordinates <Float> nextSpot = currentPath.get (0);
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
		if (dead)
		{
			if (color.r <= 0)
			{
				color.r = 0;
				color.g = 0;
				color.b = 0;
				color.a -= TRANSPARENCY_CHANGE_RATE;
			}
			else
			{
				color.r -= COLOR_CHANGE_RATE;
				color.g -= COLOR_CHANGE_RATE;
				color.b -= COLOR_CHANGE_RATE;
			}
			if (color.a <= 0)
			{
				EventManager.queueEvent (new AnimationFinishedEvent ());
				return true;
			}
		}
		return false;
	}

	public void move (List <Coordinates <Integer>> path)
	{
		currentPath = convertPath (path);
	}

	private List <Coordinates <Float>> convertPath (
			List <Coordinates <Integer>> path)
	{
		List <Coordinates <Float>> newPath =
				new ArrayList <Coordinates <Float>> (path.size ());
		for (Coordinates <Integer> node : path)
		{
			Coordinates <Float> newNode =
					new Coordinates <Float> (node.x.floatValue (),
							node.y.floatValue ());
			newPath.add (newNode);
		}
		return newPath;
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

	public void attack (GUnite target)
	{
		List <Coordinates <Float>> newPath =
				new ArrayList <Coordinates <Float>> ();
		Vector2 vec = new Vector2 (target.x, target.y);
		vec.sub (x, y).nor ().scl (0.75f).add (x, y);
		Coordinates <Float> halfPoint = new Coordinates <Float> (vec.x, vec.y);
		Coordinates <Float> originalPoint = new Coordinates <Float> (x, y);
		newPath.add (halfPoint);
		newPath.add (originalPoint);
		currentPath = newPath;
	}

	public void destroy ()
	{
		dead = true;
	}
}
