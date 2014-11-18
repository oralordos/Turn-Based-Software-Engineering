package edu.fresnostate.turnbased;

public class InGameState implements GameState
{
	private PlayerView	view;

	@Override
	public void enter ()
	{
		view = new PlayerView ();
	}

	@Override
	public void exit ()
	{
		view.dispose ();
		view = null;
	}

	@Override
	public void resize (int width, int height)
	{
		view.resize (width, height);
	}

	@Override
	public void update ()
	{
		view.update ();
	}

	@Override
	public void render ()
	{
		view.render ();
	}
}
