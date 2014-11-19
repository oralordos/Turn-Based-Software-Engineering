package edu.fresnostate.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

public class GUnite implements Disposable
{
	public final int UniteID;
	private float x;
	private float y;
	
	private static AssetManager manager = new AssetManager();
	
	
	@Override
	public void dispose() 
	{
		
		tex.dispose();
	}
	public static void GUiniteStartup()
	{
		FileHandle handle =Gdx.files.internal("Cheese");
		FileHandle [] imagePaths= handle.list (".png");
		for( FileHandle imagePath : imagePaths)
		{
	
			AssetDescriptor <Texture> desc=
					new AssetDescriptor<Texture>(imagePath, Texture.class);
			manager.load(desc);
					
		}
		
	}
	private Texture tex;
	public GUnite(int UniteID, String imageName)
	{
		this.UniteID=UniteID;
		 tex = manager.get("data/mytexture.png", Texture.class);
	}
	public void render (SpriteBatch batch)
	{
		batch.draw(tex, x, y);
	}


}
