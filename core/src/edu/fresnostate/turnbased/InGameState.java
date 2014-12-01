package edu.fresnostate.turnbased;

import edu.fresnostate.turnbased.event.EventManager;
import edu.fresnostate.turnbased.event.WindowResizedEvent;


public class InGameState implements GameState
{
	private PlayerView	view;
	private GameLogic	logic;

	@Override
	public void enter ()
	{
		EventManager.preloadAssets ();
		view = new PlayerView ("test.tmx");
		logic = new GameLogic ("test.tmx");
	}

	@Override
	public void exit ()
	{
		logic.dispose ();
		logic = null;
		view.dispose ();
		view = null;
		EventManager.unloadAssets ();
	}

	@Override
	public void resize (int width, int height)
	{
		EventManager.queueEvent (new WindowResizedEvent (width, height));
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
