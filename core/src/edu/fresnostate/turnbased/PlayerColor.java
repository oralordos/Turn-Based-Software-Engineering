package edu.fresnostate.turnbased;

public enum PlayerColor
{
	RED (1, 0, 0, 1), BLUE (0, 0, 1, 1), GREEN (0, 1, 0, 1),
	WHITE (1, 1, 1, 1), YELLOW (1, 1, 0, 1), BLACK (0, 0, 0, 1);
	public final float	red;
	public final float	green;
	public final float	blue;
	public final float	alpha;

	PlayerColor (float r, float g, float b, float a)
	{
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
}
