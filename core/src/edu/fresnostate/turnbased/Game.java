package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;


public class Game extends ApplicationAdapter
{
	private PlayerView	view;

	@Override
	public void create ()
	{
		view = new PlayerView ();
	}

	@Override
	public void render ()
	{
		view.update ();
		view.render ();
	}

	@Override
	public void resize (int width, int height)
	{
		view.resize (width, height);
	}

	@Override
	public void dispose ()
	{
		view.dispose ();
	}
}
