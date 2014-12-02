package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;


public class Game extends ApplicationAdapter
{
	private GameState	currentState;

	@Override
	public void create ()
	{
		currentState = null;
		setState (new MainMenuState ());
	}

	@Override
	public void render ()
	{
		currentState.update ();
		currentState.render ();
	}

	@Override
	public void resize (int width, int height)
	{
		currentState.resize (width, height);
	}

	@Override
	public void dispose ()
	{
		currentState.exit ();
		currentState = null;
	}

	public void setState (GameState newState)
	{
		if (currentState != null)
		{
			currentState.exit ();
		}
		currentState = newState;
		newState.enter ();
	}
}
