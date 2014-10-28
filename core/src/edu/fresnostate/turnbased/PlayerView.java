package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerView implements ApplicationListener {
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	public void draw () {
		
	}
	public void handleInput () {
		
	}
	@Override
	public void create ()
	{
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/file.png"));
		sprite = new Sprite(texture);
		
	}
	@Override
	public void resize (int width, int height)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render (){
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor (1, 1, 1, 1);
		Gdx.gl.glClear ((GL20.GL_COLOR_BUFFER_BIT));
		
		batch.begin();
		sprite.draw (batch);
		batch.end ();
	}
	@Override
	public void pause ()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume ()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose ()
	{
		// TODO Auto-generated method stub
		
	}
}
