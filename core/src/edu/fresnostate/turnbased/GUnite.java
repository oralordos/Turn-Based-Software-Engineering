package edu.fresnostate.turnbased;

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;


public class GUnite implements Disposable
{
	public final int			UniteID;
	private float				x;
	private float				y;
	private Texture				tex;

	private static AssetManager	manager	= new AssetManager ();

	@Override
	public void dispose ()
	{
		tex.dispose ();
	}

	public static void GUiniteStartup ()
	{
		FileHandle handle = Gdx.files.internal ("Cheese");
		FileHandle [ ] imagePaths = handle.list (".png");
		for (FileHandle imagePath : imagePaths)
		{
			AssetDescriptor <Texture> desc =
					new AssetDescriptor <Texture> (imagePath, Texture.class);
			manager.load (desc);
		}
	}

	public GUnite (int UniteID, String imageName)
	{
		this.UniteID = UniteID;
		tex = manager.get ("data/mytexture.png", Texture.class);
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
