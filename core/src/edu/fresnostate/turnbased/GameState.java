package edu.fresnostate.turnbased;

public interface GameState
{
	public void enter ();

	public void exit ();

	public void resize (int width, int height);

	public void update ();

	public void render ();
}
