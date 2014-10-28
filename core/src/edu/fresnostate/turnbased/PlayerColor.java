package edu.fresnostate.turnbased;

public enum PlayerColor
{
	RED (255, 0, 0, 255), BLUE (0, 0, 255, 255), GREEN (0, 255, 0, 255), WHITE (
			255, 255, 255, 255), YELLOW (255, 255, 0, 255),
	BLACK (0, 0, 0, 255);

	public final int	red;
	public final int	green;
	public final int	blue;
	public final int	alpha;

	PlayerColor (int r, int g, int b, int a)
	{
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
}
